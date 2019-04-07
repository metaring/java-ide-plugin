package com.metaring.platform.project;

import com.metaring.platform.project.DataSeries;
import com.metaring.platform.project.FunctionalitySeries;
import com.metaring.platform.project.EnumeratorSeries;
import com.metaring.platform.project.ModuleSeries;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Module implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.module";

    private String name;
    private String description;
    private DataSeries data;
    private FunctionalitySeries functionalities;
    private EnumeratorSeries enumerators;
    private ModuleSeries modules;
    private String created;
    private String updated;

    private Module(String name, String description, DataSeries data, FunctionalitySeries functionalities, EnumeratorSeries enumerators, ModuleSeries modules, String created, String updated) {
        this.name = name;
        this.description = description;
        this.data = data;
        this.functionalities = functionalities;
        this.enumerators = enumerators;
        this.modules = modules;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public DataSeries getData() {
        return this.data;
    }

    public FunctionalitySeries getFunctionalities() {
        return this.functionalities;
    }

    public EnumeratorSeries getEnumerators() {
        return this.enumerators;
    }

    public ModuleSeries getModules() {
        return this.modules;
    }

    public String getCreated() {
        return this.created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public static Module create(String name, String description, DataSeries data, FunctionalitySeries functionalities, EnumeratorSeries enumerators, ModuleSeries modules, String created, String updated) {
        return new Module(name, description, data, functionalities, enumerators, modules, created, updated);
    }

    public static Module fromJson(String jsonString) {

        if(jsonString == null) {
            return null;
        }

        jsonString = jsonString.trim();
        if(jsonString.isEmpty()) {
            return null;
        }

        if(jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);

        String name = null;
        if(dataRepresentation.hasProperty("name")) {
            try {
                name = dataRepresentation.getText("name");
            } catch (Exception e) {
            }
        }

        String description = null;
        if(dataRepresentation.hasProperty("description")) {
            try {
                description = dataRepresentation.getText("description");
            } catch (Exception e) {
            }
        }

        DataSeries data = null;
        if(dataRepresentation.hasProperty("data")) {
            try {
                data = dataRepresentation.get("data", DataSeries.class);
            } catch (Exception e) {
            }
        }

        FunctionalitySeries functionalities = null;
        if(dataRepresentation.hasProperty("functionalities")) {
            try {
                functionalities = dataRepresentation.get("functionalities", FunctionalitySeries.class);
            } catch (Exception e) {
            }
        }

        EnumeratorSeries enumerators = null;
        if(dataRepresentation.hasProperty("enumerators")) {
            try {
                enumerators = dataRepresentation.get("enumerators", EnumeratorSeries.class);
            } catch (Exception e) {
            }
        }

        ModuleSeries modules = null;
        if(dataRepresentation.hasProperty("modules")) {
            try {
                modules = dataRepresentation.get("modules", ModuleSeries.class);
            } catch (Exception e) {
            }
        }

        String created = null;
        if(dataRepresentation.hasProperty("created")) {
            try {
                created = dataRepresentation.getText("created");
            } catch (Exception e) {
            }
        }

        String updated = null;
        if(dataRepresentation.hasProperty("updated")) {
            try {
                updated = dataRepresentation.getText("updated");
            } catch (Exception e) {
            }
        }

        Module module = create(name, description, data, functionalities, enumerators, modules, created, updated);
        return module;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (description != null) {
            dataRepresentation.add("description", description);
        }

        if (data != null) {
            dataRepresentation.add("data", data.toArray());
        }

        if (functionalities != null) {
            dataRepresentation.add("functionalities", functionalities.toArray());
        }

        if (enumerators != null) {
            dataRepresentation.add("enumerators", enumerators.toArray());
        }

        if (modules != null) {
            dataRepresentation.add("modules", modules.toArray());
        }

        if (created != null) {
            dataRepresentation.add("created", created);
        }

        if (updated != null) {
            dataRepresentation.add("updated", updated);
        }

        return dataRepresentation;
    }

    @Override
    public String toJson() {
        return toDataRepresentation().toJson();
    }

    @Override
    public String toString() {
        return this.toJson();
    }
}