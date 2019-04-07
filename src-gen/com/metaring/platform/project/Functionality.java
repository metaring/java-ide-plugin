package com.metaring.platform.project;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Functionality implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.functionality";

    private String name;
    private String description;
    private String input;
    private Boolean inputManyOf;
    private String output;
    private Boolean outputManyOf;
    private String created;
    private String updated;

    private Functionality(String name, String description, String input, Boolean inputManyOf, String output, Boolean outputManyOf, String created, String updated) {
        this.name = name;
        this.description = description;
        this.input = input;
        this.inputManyOf = inputManyOf;
        this.output = output;
        this.outputManyOf = outputManyOf;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getInput() {
        return this.input;
    }

    public Boolean getInputManyOf() {
        return this.inputManyOf;
    }

    public String getOutput() {
        return this.output;
    }

    public Boolean getOutputManyOf() {
        return this.outputManyOf;
    }

    public String getCreated() {
        return this.created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public static Functionality create(String name, String description, String input, Boolean inputManyOf, String output, Boolean outputManyOf, String created, String updated) {
        return new Functionality(name, description, input, inputManyOf, output, outputManyOf, created, updated);
    }

    public static Functionality fromJson(String jsonString) {

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

        String input = null;
        if(dataRepresentation.hasProperty("input")) {
            try {
                input = dataRepresentation.getText("input");
            } catch (Exception e) {
            }
        }

        Boolean inputManyOf = null;
        if(dataRepresentation.hasProperty("inputManyOf")) {
            try {
                inputManyOf = dataRepresentation.getTruth("inputManyOf");
            } catch (Exception e) {
            }
        }

        String output = null;
        if(dataRepresentation.hasProperty("output")) {
            try {
                output = dataRepresentation.getText("output");
            } catch (Exception e) {
            }
        }

        Boolean outputManyOf = null;
        if(dataRepresentation.hasProperty("outputManyOf")) {
            try {
                outputManyOf = dataRepresentation.getTruth("outputManyOf");
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

        Functionality functionality = create(name, description, input, inputManyOf, output, outputManyOf, created, updated);
        return functionality;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (description != null) {
            dataRepresentation.add("description", description);
        }

        if (input != null) {
            dataRepresentation.add("input", input);
        }

        if (inputManyOf != null) {
            dataRepresentation.add("inputManyOf", inputManyOf);
        }

        if (output != null) {
            dataRepresentation.add("output", output);
        }

        if (outputManyOf != null) {
            dataRepresentation.add("outputManyOf", outputManyOf);
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