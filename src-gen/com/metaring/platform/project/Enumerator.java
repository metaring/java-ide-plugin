package com.metaring.platform.project;

import com.metaring.platform.project.ValueSeries;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Enumerator implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.enumerator";

    private String name;
    private String description;
    private ValueSeries values;
    private String created;
    private String updated;

    private Enumerator(String name, String description, ValueSeries values, String created, String updated) {
        this.name = name;
        this.description = description;
        this.values = values;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ValueSeries getValues() {
        return this.values;
    }

    public String getCreated() {
        return this.created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public static Enumerator create(String name, String description, ValueSeries values, String created, String updated) {
        return new Enumerator(name, description, values, created, updated);
    }

    public static Enumerator fromJson(String jsonString) {

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

        ValueSeries values = null;
        if(dataRepresentation.hasProperty("values")) {
            try {
                values = dataRepresentation.get("values", ValueSeries.class);
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

        Enumerator enumerator = create(name, description, values, created, updated);
        return enumerator;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (description != null) {
            dataRepresentation.add("description", description);
        }

        if (values != null) {
            dataRepresentation.add("values", values.toArray());
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