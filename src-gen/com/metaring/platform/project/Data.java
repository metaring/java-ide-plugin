package com.metaring.platform.project;

import com.metaring.platform.project.AttributeSeries;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Data implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.data";

    private String name;
    private String description;
    private AttributeSeries attributes;
    private String created;
    private String updated;

    private Data(String name, String description, AttributeSeries attributes, String created, String updated) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public AttributeSeries getAttributes() {
        return this.attributes;
    }

    public String getCreated() {
        return this.created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public static Data create(String name, String description, AttributeSeries attributes, String created, String updated) {
        return new Data(name, description, attributes, created, updated);
    }

    public static Data fromJson(String jsonString) {

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

        AttributeSeries attributes = null;
        if(dataRepresentation.hasProperty("attributes")) {
            try {
                attributes = dataRepresentation.get("attributes", AttributeSeries.class);
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

        Data data = create(name, description, attributes, created, updated);
        return data;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (description != null) {
            dataRepresentation.add("description", description);
        }

        if (attributes != null) {
            dataRepresentation.add("attributes", attributes.toArray());
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