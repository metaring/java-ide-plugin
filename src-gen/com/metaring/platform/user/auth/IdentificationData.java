package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class IdentificationData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.identificationData";

    private String email;
    private String ipAddress;
    private String oneTimeToken;

    private IdentificationData(String email, String ipAddress, String oneTimeToken) {
        this.email = email;
        this.ipAddress = ipAddress;
        this.oneTimeToken = oneTimeToken;
    }

    public String getEmail() {
        return this.email;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getOneTimeToken() {
        return this.oneTimeToken;
    }

    public static IdentificationData create(String email, String ipAddress, String oneTimeToken) {
        return new IdentificationData(email, ipAddress, oneTimeToken);
    }

    public static IdentificationData fromJson(String jsonString) {

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

        String email = null;
        if(dataRepresentation.hasProperty("email")) {
            try {
                email = dataRepresentation.getText("email");
            } catch (Exception e) {
            }
        }

        String ipAddress = null;
        if(dataRepresentation.hasProperty("ipAddress")) {
            try {
                ipAddress = dataRepresentation.getText("ipAddress");
            } catch (Exception e) {
            }
        }

        String oneTimeToken = null;
        if(dataRepresentation.hasProperty("oneTimeToken")) {
            try {
                oneTimeToken = dataRepresentation.getText("oneTimeToken");
            } catch (Exception e) {
            }
        }

        IdentificationData identificationData = create(email, ipAddress, oneTimeToken);
        return identificationData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (ipAddress != null) {
            dataRepresentation.add("ipAddress", ipAddress);
        }

        if (oneTimeToken != null) {
            dataRepresentation.add("oneTimeToken", oneTimeToken);
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