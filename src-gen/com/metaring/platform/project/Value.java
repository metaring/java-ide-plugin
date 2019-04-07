package com.metaring.platform.project;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Value implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.value";

    private String name;
    private Long numericValue;
    private String textualValue;
    private String description;

    private Value(String name, Long numericValue, String textualValue, String description) {
        this.name = name;
        this.numericValue = numericValue;
        this.textualValue = textualValue;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public Long getNumericValue() {
        return this.numericValue;
    }

    public String getTextualValue() {
        return this.textualValue;
    }

    public String getDescription() {
        return this.description;
    }

    public static Value create(String name, Long numericValue, String textualValue, String description) {
        return new Value(name, numericValue, textualValue, description);
    }

    public static Value fromJson(String jsonString) {

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

        Long numericValue = null;
        if(dataRepresentation.hasProperty("numericValue")) {
            try {
                numericValue = dataRepresentation.getDigit("numericValue");
            } catch (Exception e) {
            }
        }

        String textualValue = null;
        if(dataRepresentation.hasProperty("textualValue")) {
            try {
                textualValue = dataRepresentation.getText("textualValue");
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

        Value value = create(name, numericValue, textualValue, description);
        return value;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (numericValue != null) {
            dataRepresentation.add("numericValue", numericValue);
        }

        if (textualValue != null) {
            dataRepresentation.add("textualValue", textualValue);
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