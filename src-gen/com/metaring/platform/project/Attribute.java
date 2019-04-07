package com.metaring.platform.project;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Attribute implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.attribute";

    private String name;
    private String type;
    private Boolean manyOf;
    private String description;

    private Attribute(String name, String type, Boolean manyOf, String description) {
        this.name = name;
        this.type = type;
        this.manyOf = manyOf;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Boolean getManyOf() {
        return this.manyOf;
    }

    public String getDescription() {
        return this.description;
    }

    public static Attribute create(String name, String type, Boolean manyOf, String description) {
        return new Attribute(name, type, manyOf, description);
    }

    public static Attribute fromJson(String jsonString) {

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

        String type = null;
        if(dataRepresentation.hasProperty("type")) {
            try {
                type = dataRepresentation.getText("type");
            } catch (Exception e) {
            }
        }

        Boolean manyOf = null;
        if(dataRepresentation.hasProperty("manyOf")) {
            try {
                manyOf = dataRepresentation.getTruth("manyOf");
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

        Attribute attribute = create(name, type, manyOf, description);
        return attribute;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (type != null) {
            dataRepresentation.add("type", type);
        }

        if (manyOf != null) {
            dataRepresentation.add("manyOf", manyOf);
        }

        if (description != null) {
            dataRepresentation.add("description", description);
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