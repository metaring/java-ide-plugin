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

package com.metaring.platform.project;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.metaring.framework.Core;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.util.CryptoUtil;
import com.metaring.framework.util.StringUtil;
import com.metaring.plugin.ConnectionData;
import com.metaring.plugin.Project;
import com.metaring.plugin.WorkspaceManager;

public class GenerateFunctionalityImpl extends GenerateFunctionality {

    public static final String CFG_GENERATE = "generate";
    public static final String CFG_RESOURCE = "resource";
    public static final String CFG_FUNCTIONALITIES_FILE_NAME = "functionalitiesFileName";


    @Override
    protected CompletableFuture<Void> preConditionCheck() throws Exception {
        return end;
    }

    @Override
    protected CompletableFuture<GeneratedProject> call(String input) throws Exception {
        List<Project> projects = WorkspaceManager.getByProject(input);
        if (projects == null || projects.isEmpty()) {
            return end(null);
        }
        for (Project project : projects) {
            try {
                String functionalitiesPath = project.getFunctionalitiesPath();
                if (!StringUtil.isNullOrEmpty(functionalitiesPath)) {
                    functionalitiesPath.replace("\\", "/");
                    if (!functionalitiesPath.endsWith("/")) {
                        functionalitiesPath += "/";
                    }
                } else {
                    functionalitiesPath = null;
                }

                String sourceGenPath = project.getSourceGenPath();
                if (!StringUtil.isNullOrEmpty(sourceGenPath)) {
                    sourceGenPath.replace("\\", "/");
                    if (!sourceGenPath.endsWith("/")) {
                        sourceGenPath += "/";
                    }
                } else {
                    sourceGenPath = null;
                }

                try {
                    Files.walk(Paths.get(sourceGenPath), FileVisitOption.FOLLOW_LINKS).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
                } catch (Exception e) {
                }

                ConnectionData connectionData = WorkspaceManager.getConnectionData(project);

                DataRepresentation param = newDataRepresentation().add("projectId", project.getProject()).add("server", project.getServerGenerator()).add("client", project.getClientGenerator());

                String payload = "payload=" + CryptoUtil.toBase64String(connectionData.encapsulate(this.getInfo(), param).toJson());
                URL url = new URL(connectionData.url + Core.SYSKB.get(CFG_GENERATE).getText(CFG_RESOURCE));
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.getOutputStream().write(payload.getBytes());
                httpURLConnection.getOutputStream().flush();
                httpURLConnection.connect();
                try (ZipInputStream zipInputStream = new ZipInputStream(httpURLConnection.getInputStream())) {
                    ZipEntry zipEntry;
                    int read;
                    byte[] buffer;
                    while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                        try {
                            String path = sourceGenPath;
                            if (zipEntry.getName().equals(Core.SYSKB.get(CFG_GENERATE).getText(CFG_FUNCTIONALITIES_FILE_NAME))) {
                                path = functionalitiesPath;
                            }
                            if(StringUtil.isNullOrEmpty(path)) {
                                continue;
                            }
                            File outPath = new File(path + zipEntry.getName());
                            new File(outPath.getParent()).mkdirs();
                            try (FileOutputStream fileOutputStream = new FileOutputStream(outPath)) {
                                read = 0;
                                buffer = new byte[2048];
                                while ((read = zipInputStream.read(buffer)) > 0) {
                                    fileOutputStream.write(buffer, 0, read);
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                    project.refresh();
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }
        }
        return end(null);
    }

    @Override
    protected CompletableFuture<Void> postConditionCheck(GeneratedProject output) throws Exception {
        return end;
    }
}