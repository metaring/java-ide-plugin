/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.metaring.platform.user.auth.AuthFunctionalitiesManager;
import com.metaring.platform.user.auth.LoggedUser;
import com.metaring.platform.user.auth.LoginRequest;
import com.metaring.platform.user.auth.RestoreSessionRequest;
import com.metaring.util.connection.MetaRingCommunicationProtocol;

import com.metaring.framework.Tools;
import com.metaring.framework.functionality.FunctionalityExecutionResult;
import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.rpc.RpcRequest;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.util.CryptoUtil;
import com.metaring.framework.util.StringUtil;

public final class ConnectionData {

    public final String url;
    public final String email;
    public final String password;
    public final String project;
    public boolean connected = false;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;
    private long sessionRefreshTimeout;

    private boolean toUpdate = true;

    private final List<Project> projects = new ArrayList<>();

    private MetaRingCommunicationProtocol metaRing;

    private DataRepresentation identificationData;
    private String source;

    public ConnectionData(String url, String email, String password, String project) {
        this.url = url;
        this.email = email;
        this.password = password;
        this.project = project;
    }

    public final ConnectionData addProject(Project project) {
        if (getIndex(project) == -1) {
            projects.add(project);
            toUpdate = true;
        }
        return this;
    }

    public final void removeProject(Project project) {
        int index = getIndex(project);
        if (index == -1) {
            return;
        }
        projects.remove(index);
    }

    private final int getIndex(Project project) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i) == project) {
                return i;
            }
        }
        return -1;
    }

    public final boolean exists(Project project) {
        return getIndex(project) != -1;
    }

    public final boolean isEmpty() {
        return projects.isEmpty();
    }

    public final boolean ack() {
        boolean update = toUpdate;
        toUpdate = false;
        return update;
    }

    public final ConnectionData connect() {
        disconnect();
        toUpdate = false;
        this.metaRing = MetaRingCommunicationProtocol.create(url, WorkspaceManager.getConnectionResource(), WorkspaceManager.getIPResource(), this::getIdentificationData).onConnect(this::onConnect).onDisconnect(this::onDisconnect).connect();
        return this;
    }

    public final void onConnect() {
        connected = true;
        this.metaRing.call(AuthFunctionalitiesManager.LOGIN, LoginRequest.create(email, getPassword(), this.metaRing.IP, true)).thenAccept(response -> this.setAuthToken(response.getResult().asText()));
    }

    private final void onDisconnect(Throwable e) {
        e.printStackTrace();
        disconnect();
    }

    public final ConnectionData disconnect() {
        connected = false;
        MetaRingCommunicationProtocol reference = this.metaRing;
        this.identificationData = null;
        this.metaRing = null;
        if(this.scheduledFuture != null) {
            try {
                this.scheduledFuture.cancel(true);
            }
            catch(Exception e) {
            }
        }
        this.scheduledFuture = null;
        if (reference != null) {
            try {
                reference.disconnect();
            } catch(Exception e) {
            }
        }
        return this;
    }

    public final boolean isConnected() {
        return this.metaRing != null && identificationData != null && connected;
    }

    public final boolean isDisconnected() {
        return !isConnected();
    }

    @Override
    public final boolean equals(Object object) {
        if (object == null || !(object instanceof ConnectionData)) {
            return false;
        }
        ConnectionData connectionData = (ConnectionData) object;
        try {
            return connectionData != null && (connectionData == this || (this.url.equals(connectionData.url) && this.email.equals(connectionData.email) && this.password.equals(connectionData.password)));
        }
        catch (Exception e) {
            return false;
        }
    }

    private final void setAuthToken(String authToken) {
        this.source = CryptoUtil.toBase64String(CryptoUtil.toMD5(authToken));
        this.metaRing.call(AuthFunctionalitiesManager.RESTORE_SESSION, RestoreSessionRequest.create(email, authToken, this.metaRing.IP)).thenAccept(result -> this.sessionRestored(result.getResult().as(LoggedUser.class)));
    }

    private final void sessionRestored(LoggedUser loggedUser) {
        this.identificationData = Tools.FACTORY_DATA_REPRESENTATION.create()
                        .add("email", this.email)
                        .add("ipAddress", this.metaRing.IP)
                        .add("oneTimeToken", loggedUser.getOneTimeToken().getValue());
        this.sessionRefreshTimeout = loggedUser.getOneTimeToken().getSessionRefreshTimeoutMillis();
        scheduleRefreshTokenTimeout();
        this.metaRing.callback("oneTimeToken", this::refreshOneTimeToken).callback("projectUpdate", this::generate);
        update(null);
    }

    private final void scheduleRefreshTokenTimeout() {
        if(this.scheduledFuture != null) {
            try {
                this.scheduledFuture.cancel(true);
            }
            catch(Exception e) {
            }
        }
        this.scheduledFuture = scheduledExecutorService.schedule(() ->
            this.metaRing.call(AuthFunctionalitiesManager.HEARTBEAT),
            this.sessionRefreshTimeout, TimeUnit.MILLISECONDS);
    }

    private final void update(String id) {
        if(projects == null || projects.isEmpty()) {
            return;
        }
        final List<String> projectIds = new ArrayList<>();
        if(StringUtil.isNullOrEmpty(id)) {
            projects.forEach(it -> projectIds.add(it.getProject()));
        } else {
            projectIds.add(id);
        }
        final Runnable[] runnable = new Runnable[1];
        runnable[0] = () -> {
            if(projectIds.isEmpty()) {
                return;
            }
            WorkspaceManager.generate(projectIds.remove(0)).thenRun(runnable[0]);
        };
        runnable[0].run();
    }

    private final DataRepresentation getIdentificationData() {
        return identificationData;
    }

    private final void refreshOneTimeToken(FunctionalityExecutionResult response) {
        this.identificationData.add("oneTimeToken", response.getResult().asText());
        scheduleRefreshTokenTimeout();
    }

    private final void generate(FunctionalityExecutionResult response) {
        if (response.getResult().getText("source").equals(this.source)) {
            return;
        }
        update(response.getResult().getText("project"));
    }

    private final String getPassword() {
        return CryptoUtil.toMD5String(email + password);
    }

    public final RpcRequest encapsulate(FunctionalityInfo functionalityInfo) {
        return metaRing.encapsulate(functionalityInfo);
    }

    public final RpcRequest encapsulate(FunctionalityInfo functionalityInfo, String input) {
        return metaRing.encapsulate(functionalityInfo, Tools.FACTORY_DATA_REPRESENTATION.fromObject(input));
    }

    public final RpcRequest encapsulate(FunctionalityInfo functionalityInfo, DataRepresentation input) {
        return metaRing.encapsulate(functionalityInfo, input);
    }

    @Override
    public String toString() {
        return "ConnectionData " + url + " > " + email + " > " + identificationData;
    }
}