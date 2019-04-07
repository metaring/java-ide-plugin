package com.metaring.platform.project;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class GeneratedProject implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.project.generatedProject";

    private String name;
    private String fileLocation;
    private String fileExtension;
    private String fileMimeType;

    private GeneratedProject(String name, String fileLocation, String fileExtension, String fileMimeType) {
        this.name = name;
        this.fileLocation = fileLocation;
        this.fileExtension = fileExtension;
        this.fileMimeType = fileMimeType;
    }

    public String getName() {
        return this.name;
    }

    public String getFileLocation() {
        return this.fileLocation;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public String getFileMimeType() {
        return this.fileMimeType;
    }

    public static GeneratedProject create(String name, String fileLocation, String fileExtension, String fileMimeType) {
        return new GeneratedProject(name, fileLocation, fileExtension, fileMimeType);
    }

    public static GeneratedProject fromJson(String jsonString) {

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

        String fileLocation = null;
        if(dataRepresentation.hasProperty("fileLocation")) {
            try {
                fileLocation = dataRepresentation.getText("fileLocation");
            } catch (Exception e) {
            }
        }

        String fileExtension = null;
        if(dataRepresentation.hasProperty("fileExtension")) {
            try {
                fileExtension = dataRepresentation.getText("fileExtension");
            } catch (Exception e) {
            }
        }

        String fileMimeType = null;
        if(dataRepresentation.hasProperty("fileMimeType")) {
            try {
                fileMimeType = dataRepresentation.getText("fileMimeType");
            } catch (Exception e) {
            }
        }

        GeneratedProject generatedProject = create(name, fileLocation, fileExtension, fileMimeType);
        return generatedProject;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (fileLocation != null) {
            dataRepresentation.add("fileLocation", fileLocation);
        }

        if (fileExtension != null) {
            dataRepresentation.add("fileExtension", fileExtension);
        }

        if (fileMimeType != null) {
            dataRepresentation.add("fileMimeType", fileMimeType);
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