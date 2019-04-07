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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;

import com.metaring.platform.project.GeneratedProject;
import com.metaring.platform.project.ProjectFunctionalitiesManager;

import com.metaring.framework.Core;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.util.StringUtil;

public final class WorkspaceManager {

    public static final String CONFIG_FILE = "metaring.config.json";

    public static final String CFG_DEFAULT_CONFIGURATION = "defaultConfiguration";
    public static final String CFG_URL = "url";
    public static final String CFG_EMAIL = "email";
    public static final String CFG_PASSWORD = "password";
    public static final String CFG_PROJECT = "project";
    public static final String CFG_FUNCTIONALITIES_PATH = "functionalitiesPath";
    public static final String CFG_SOURCE_GEN_PATH = "sourceGenPath";
    public static final String CFG_SERVER_GENERATOR = "serverGenerator";
    public static final String CFG_CLIENT_GENERATOR = "clientGenerator";
    private static final String CFG_CONNECTION = "connection";
    private static final String CFG_CONNECTION_RESOURCE = "connectionResource";
    private static final String CFG_IP_RESOURCE = "ipResource";

    public static final void main(String[] args) {
        try {
            init(args[0]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static WorkspaceProvider WORKSPACE_PROVIDER;

    private static List<Project> PROJECTS = null;

    private static final List<ConnectionData> CONNECTIONS = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static final void init(String className) {
        if (WORKSPACE_PROVIDER != null) {
            return;
        }
        try {
            WORKSPACE_PROVIDER = ((Class<? extends WorkspaceProvider>) Class.forName(className)).newInstance();
            WORKSPACE_PROVIDER.onUpdate(WorkspaceManager::onUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load Workspace Manager with input: " + className, e);
        }
        (PROJECTS = getUpdatedProjects().toList()).forEach(WorkspaceManager::attach);
        refreshConnections();
    }

    private static final DataRepresentation getCommonConfiguration() {
        DataRepresentation configuration = WORKSPACE_PROVIDER.getConfiguration();
        return configuration != null ? configuration : Tools.FACTORY_DATA_REPRESENTATION.create();
    }

    public static final String getCommonURL() {
        String url = getCommonConfiguration().getText(WorkspaceManager.CFG_URL);
        if (StringUtil.isNullOrEmpty(url)) {
            url = Core.SYSKB.get(CFG_DEFAULT_CONFIGURATION).getText(CFG_URL);
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
        return url;
    }

    public static final String getConnectionResource() {
        return Core.SYSKB.get(CFG_CONNECTION).getText(CFG_CONNECTION_RESOURCE);
    }

    public static final String getIPResource() {
        return Core.SYSKB.get(CFG_CONNECTION).getText(CFG_IP_RESOURCE);
    }

    public static final String getCommonFunctionalitiesPath() {
        if (getCommonConfiguration().isTruth(WorkspaceManager.CFG_FUNCTIONALITIES_PATH)
                && !getCommonConfiguration().getTruth(WorkspaceManager.CFG_FUNCTIONALITIES_PATH)) {
            return null;
        }
        String functionalitiesPath = getCommonConfiguration().getText(WorkspaceManager.CFG_FUNCTIONALITIES_PATH);
        if (StringUtil.isNullOrEmpty(functionalitiesPath)) {
            functionalitiesPath = Core.SYSKB.get(CFG_DEFAULT_CONFIGURATION).getText(CFG_FUNCTIONALITIES_PATH);
        }
        functionalitiesPath = functionalitiesPath.trim().replace("\\", "/");
        if (functionalitiesPath.startsWith("/")) {
            functionalitiesPath = functionalitiesPath.substring(1);
        }
        if (!functionalitiesPath.endsWith("/")) {
            functionalitiesPath += "/";
        }
        return functionalitiesPath;
    }

    public static final String getCommonSourceGenPath() {
        if (getCommonConfiguration().isTruth(WorkspaceManager.CFG_SOURCE_GEN_PATH)
                && !getCommonConfiguration().getTruth(WorkspaceManager.CFG_SOURCE_GEN_PATH)) {
            return null;
        }
        String soruceGenPath = getCommonConfiguration().getText(WorkspaceManager.CFG_SOURCE_GEN_PATH);
        if (StringUtil.isNullOrEmpty(soruceGenPath)) {
            soruceGenPath = Core.SYSKB.get(CFG_DEFAULT_CONFIGURATION).getText(CFG_SOURCE_GEN_PATH);
        }
        soruceGenPath = soruceGenPath.trim().replace("\\", "/");
        if (soruceGenPath.startsWith("/")) {
            soruceGenPath = soruceGenPath.substring(1);
        }
        if (!soruceGenPath.endsWith("/")) {
            soruceGenPath += "/";
        }
        return soruceGenPath;
    }

    public static final String getCommonEmail() {
        return getCommonConfiguration().getText(WorkspaceManager.CFG_EMAIL);
    }

    public static final String getCommonPassword() {
        return getCommonConfiguration().getText(WorkspaceManager.CFG_PASSWORD);
    }

    public static final String getCommonServerGenerator() {
        return getCommonConfiguration().getText(WorkspaceManager.CFG_SERVER_GENERATOR);
    }

    public static final String getCommonClientGenerator() {
        return getCommonConfiguration().getText(WorkspaceManager.CFG_CLIENT_GENERATOR);
    }

    private static final void onUpdate() {
        if (PROJECTS == null) {
            return;
        }
        Enumerable<Project> actualProjects = Linq4j.asEnumerable(PROJECTS);
        Enumerable<Project> updatedProjects = getUpdatedProjects();

        List<Project> projects = new ArrayList<>();

        List<Project> discardedProjects = new ArrayList<>();
        discardedProjects.addAll(actualProjects.where(it -> !updatedProjects.contains(it)).toList());
        discardedProjects.forEach(WorkspaceManager::detach);

        List<Project> maintainedProjects = new ArrayList<>();
        maintainedProjects.addAll(actualProjects.where(it -> updatedProjects.contains(it)).toList());
        projects.addAll(maintainedProjects);

        List<Project> newProjects = new ArrayList<>();
        newProjects.addAll(updatedProjects.where(it -> !maintainedProjects.contains(it))
                .select(WorkspaceManager::attach).toList());
        projects.addAll(newProjects);

        PROJECTS = projects;

        refreshConnections();
    }

    private static final void refreshConnections() {
        List<Integer> toDelete = new ArrayList<>();
        boolean callGenerate = false;
        for (int i = 0; i < CONNECTIONS.size(); i++) {
            ConnectionData connectionData = CONNECTIONS.get(i);
            if (connectionData.isEmpty()) {
                toDelete.add(i);
                connectionData.disconnect();
                continue;
            }
            if (!connectionData.isConnected()) {
                connectionData.connect();
                continue;
            }
            if (connectionData.ack()) {
                callGenerate = true;
            }
        }
        if (!toDelete.isEmpty()) {
            for (int i : toDelete) {
                CONNECTIONS.remove(i);
            }
        }
        if (callGenerate) {
            generate();
        }
    }

    private static final Enumerable<Project> getUpdatedProjects() {
        if (WORKSPACE_PROVIDER == null) {
            return null;
        }
        Enumerable<ProjectProvider> projects = Linq4j.asEnumerable(WORKSPACE_PROVIDER.listAllProjects());
        projects = projects.where(it -> {
            String path = it.path.trim().replace("\\", "/");
            if (!path.endsWith("/")) {
                path += "/";
            }
            return new File(path + CONFIG_FILE).exists();
        });
        return Linq4j.asEnumerable(projects.select(Project::new).toList());
    }

    private static final Project attach(Project project) {
        ConnectionData connectionData = project.getConnectionData();
        if (!CONNECTIONS.contains(connectionData)) {
            CONNECTIONS.add(connectionData.addProject(project));
        } else {
            CONNECTIONS.get(CONNECTIONS.indexOf(connectionData)).addProject(project);
        }
        return project;
    }

    private static final void detach(Project project) {
        for (ConnectionData connectionData : CONNECTIONS) {
            connectionData.removeProject(project);
        }
    }

    public static final ConnectionData getConnectionData(Project project) {
        for (ConnectionData connectionData : CONNECTIONS) {
            if (connectionData.exists(project)) {
                return connectionData;
            }
        }
        return null;
    }

    public static final List<Project> getByProject(String project) {
        CONNECTIONS.stream().filter(ConnectionData::isDisconnected).forEach(ConnectionData::connect);
        if (StringUtil.isNullOrEmpty(project)) {
            return PROJECTS;
        }
        return Linq4j.asEnumerable(PROJECTS).where(it -> it.getProject().equals(project)).toList();
    }

    public static final void generate() {
        generate(null);
    }

    public static final CompletableFuture<GeneratedProject> generate(String id) {
        return ProjectFunctionalitiesManager.generate(id);
    }
}