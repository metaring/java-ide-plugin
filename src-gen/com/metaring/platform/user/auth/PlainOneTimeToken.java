package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class PlainOneTimeToken implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.plainOneTimeToken";

    private String id;
    private String email;
    private String authToken;
    private Long authTokenExpirationTime;
    private String ipAddress;
    private Long validUntil;

    private PlainOneTimeToken(String id, String email, String authToken, Long authTokenExpirationTime, String ipAddress, Long validUntil) {
        this.id = id;
        this.email = email;
        this.authToken = authToken;
        this.authTokenExpirationTime = authTokenExpirationTime;
        this.ipAddress = ipAddress;
        this.validUntil = validUntil;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public Long getAuthTokenExpirationTime() {
        return this.authTokenExpirationTime;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Long getValidUntil() {
        return this.validUntil;
    }

    public static PlainOneTimeToken create(String id, String email, String authToken, Long authTokenExpirationTime, String ipAddress, Long validUntil) {
        return new PlainOneTimeToken(id, email, authToken, authTokenExpirationTime, ipAddress, validUntil);
    }

    public static PlainOneTimeToken fromJson(String jsonString) {

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

        String id = null;
        if(dataRepresentation.hasProperty("id")) {
            try {
                id = dataRepresentation.getText("id");
            } catch (Exception e) {
            }
        }

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

        Long authTokenExpirationTime = null;
        if(dataRepresentation.hasProperty("authTokenExpirationTime")) {
            try {
                authTokenExpirationTime = dataRepresentation.getDigit("authTokenExpirationTime");
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

        Long validUntil = null;
        if(dataRepresentation.hasProperty("validUntil")) {
            try {
                validUntil = dataRepresentation.getDigit("validUntil");
            } catch (Exception e) {
            }
        }

        PlainOneTimeToken plainOneTimeToken = create(id, email, authToken, authTokenExpirationTime, ipAddress, validUntil);
        return plainOneTimeToken;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (id != null) {
            dataRepresentation.add("id", id);
        }

        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (authToken != null) {
            dataRepresentation.add("authToken", authToken);
        }

        if (authTokenExpirationTime != null) {
            dataRepresentation.add("authTokenExpirationTime", authTokenExpirationTime);
        }

        if (ipAddress != null) {
            dataRepresentation.add("ipAddress", ipAddress);
        }

        if (validUntil != null) {
            dataRepresentation.add("validUntil", validUntil);
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