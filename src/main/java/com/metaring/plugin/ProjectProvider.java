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

public abstract class ProjectProvider {

    public final String name;
    public final String path;

    protected ProjectProvider(String name, String path) {
        this.name = name.replace("\\", "").replace("/", "");
        path = path.replace("\\", "/");
        if(path.startsWith("/")) {
            path = path.substring(1);
        }
        if(!path.endsWith("/")) {
            path = path + "/";
        }
        this.path = path;
    }

    public final void refresh() {
        try {
            doRefresh();
        } catch(Exception e) {}
    }

    protected void doRefresh() throws Exception {
    }

    public final void refresh(String message) {
        try {
            doRefresh(message);
        } catch(Exception e) {}
    }

    protected void doRefresh(String message) throws Exception {
    }
}