package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class RestoreSessionRequest implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.restoreSessionRequest";

    private String email;
    private String authToken;
    private String ipAddress;

    private RestoreSessionRequest(String email, String authToken, String ipAddress) {
        this.email = email;
        this.authToken = authToken;
        this.ipAddress = ipAddress;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public static RestoreSessionRequest create(String email, String authToken, String ipAddress) {
        return new RestoreSessionRequest(email, authToken, ipAddress);
    }

    public static RestoreSessionRequest fromJson(String jsonString) {

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

        String authToken = null;
        if(dataRepresentation.hasProperty("authToken")) {
            try {
                authToken = dataRepresentation.getText("authToken");
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

        RestoreSessionRequest restoreSessionRequest = create(email, authToken, ipAddress);
        return restoreSessionRequest;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (authToken != null) {
            dataRepresentation.add("authToken", authToken);
        }

        if (ipAddress != null) {
            dataRepresentation.add("ipAddress", ipAddress);
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