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

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.util.CryptoUtil;
import com.metaring.framework.util.StringUtil;

public final class Project {

    private final ProjectProvider projectProvider;
    private DataRepresentation configuration;
    private final String fingerPrint;

    public Project(ProjectProvider projectProvider) {
        this.projectProvider = projectProvider;
        this.configuration = loadConfiguration();
        this.fingerPrint = getFingerPrint();
    }

    public final ConnectionData getConnectionData() {
        ConnectionData alreadyExisting = WorkspaceManager.getConnectionData(this);
        return alreadyExisting != null ? alreadyExisting : new ConnectionData(getURL(), getEmail(), getPassword(), getProject());
    }

    @Override
    public final boolean equals(Object object) {
        if(object == null || !(object instanceof Project)) {
            return false;
        }
        Project project = (Project) object;
        try {
            return project != null && project == this || this.fingerPrint.equals(project.fingerPrint);
        } catch(Exception e) {
            return false;
        }
    }

    private final DataRepresentation loadConfiguration() {
        String path = this.projectProvider.path.replace("\\", "/").trim();
        if(!path.endsWith("/")) {
            path += "/";
        }
        return Tools.FACTORY_SYSKB.load(path + WorkspaceManager.CONFIG_FILE);
    }

    private final DataRepresentation getConfiguration() {
        return configuration;
    }

    private final String getFingerPrint() {
        return CryptoUtil.toMD5String(configuration.toJson().replace("\n", "").replace("\t", "").trim());
    }

    public final String getURL() {
        String url = getConfiguration().getText(WorkspaceManager.CFG_URL);
        if(StringUtil.isNullOrEmpty(url)) {
            url = WorkspaceManager.getCommonURL();
        }
        if(StringUtil.isNullOrEmpty(url)) {
            return null;
        }
        if(!url.endsWith("/")) {
            url += "/";
        }
        return url;
    }

    public final String getEmail() {
        String email = getConfiguration().getText(WorkspaceManager.CFG_EMAIL);
        if(StringUtil.isNullOrEmpty(email)) {
            email = WorkspaceManager.getCommonEmail();
        }
        if(StringUtil.isNullOrEmpty(email)) {
            return null;
        }
        return email;
    }

    public final String getPassword() {
        String password = getConfiguration().getText(WorkspaceManager.CFG_PASSWORD);
        if(StringUtil.isNullOrEmpty(password)) {
            password = WorkspaceManager.getCommonPassword();
        }
        if(StringUtil.isNullOrEmpty(password)) {
            return null;
        }
        return password;
    }

    public final String getServerGenerator() {
        String serverGenerator = getConfiguration().getText(WorkspaceManager.CFG_SERVER_GENERATOR);
        if(StringUtil.isNullOrEmpty(serverGenerator)) {
            serverGenerator = WorkspaceManager.getCommonServerGenerator();
        }
        if(StringUtil.isNullOrEmpty(serverGenerator)) {
            return null;
        }
        return serverGenerator;
    }

    public final String getClientGenerator() {
        String clientGenerator = getConfiguration().getText(WorkspaceManager.CFG_CLIENT_GENERATOR);
        if(StringUtil.isNullOrEmpty(clientGenerator)) {
            clientGenerator = WorkspaceManager.getCommonClientGenerator();
        }
        if(StringUtil.isNullOrEmpty(clientGenerator)) {
            return null;
        }
        return clientGenerator;
    }

    public final String getFunctionalitiesPath() {
        if(getConfiguration().isTruth(WorkspaceManager.CFG_FUNCTIONALITIES_PATH) && !getConfiguration().getTruth(WorkspaceManager.CFG_FUNCTIONALITIES_PATH)) {
            return null;
        }
        String functionalitiesPath = getConfiguration().getText(WorkspaceManager.CFG_FUNCTIONALITIES_PATH);
        if(StringUtil.isNullOrEmpty(functionalitiesPath)) {
            functionalitiesPath = WorkspaceManager.getCommonFunctionalitiesPath();
        }
        if(StringUtil.isNullOrEmpty(functionalitiesPath)) {
            return null;
        }
        functionalitiesPath = functionalitiesPath.trim().replace("\\", "/");
        if(functionalitiesPath.startsWith("/")) {
            functionalitiesPath = functionalitiesPath.substring(1);
        }
        if(!functionalitiesPath.endsWith("/")) {
            functionalitiesPath += "/";
        }
        String path = projectProvider.path.trim().replace("\\", "/");
        if(!path.endsWith("/")) {
            path += "/";
        }
        return path + functionalitiesPath;
    }

    public final String getSourceGenPath() {
        if(getConfiguration().isTruth(WorkspaceManager.CFG_SOURCE_GEN_PATH) && !getConfiguration().getTruth(WorkspaceManager.CFG_SOURCE_GEN_PATH)) {
            return null;
        }
        String sourceGenPath = getConfiguration().getText(WorkspaceManager.CFG_SOURCE_GEN_PATH);
        if(StringUtil.isNullOrEmpty(sourceGenPath)) {
            sourceGenPath = WorkspaceManager.getCommonSourceGenPath();
        }
        if(StringUtil.isNullOrEmpty(sourceGenPath)) {
            return null;
        }
        sourceGenPath = sourceGenPath.trim().replace("\\", "/");
        if(sourceGenPath.startsWith("/")) {
            sourceGenPath = sourceGenPath.substring(1);
        }
        if(!sourceGenPath.endsWith("/")) {
            sourceGenPath += "/";
        }
        String path = projectProvider.path.trim().replace("\\", "/");
        if(!path.endsWith("/")) {
            path += "/";
        }
        return path + sourceGenPath;
    }

    public final String getProject() {
        return getConfiguration().getText(WorkspaceManager.CFG_PROJECT);
    }

    public final void refresh() {
        projectProvider.refresh();
    }

    public final void refresh(String message) {
        projectProvider.refresh(message);
    }

    @Override
    public String toString() {
        return "Project " + projectProvider.name + " " + getConnectionData() + " " + fingerPrint;
    }
}