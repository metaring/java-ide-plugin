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

import com.metaring.framework.type.DataRepresentation;

public abstract class WorkspaceProvider {

    private Runnable action;

    protected WorkspaceProvider() {
        super();
    }

    public final void onUpdate(Runnable action) {
        if(action == null) {
            return;
        }
        if(this.action != null) {
            return;
        }
        this.action = action;
        startUpdateCallback();
    }

    protected final void update() {
        if(this.action == null) {
            return;
        }
        try {
            this.action.run();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void startUpdateCallback();

    public abstract ProjectProvider[] listAllProjects();

    public DataRepresentation getConfiguration() {
        return null;
    }
}