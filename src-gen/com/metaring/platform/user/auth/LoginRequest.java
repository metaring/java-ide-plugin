package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class LoginRequest implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.loginRequest";

    private String email;
    private String password;
    private String ipAddress;
    private Boolean termsOfServiceAccepted;

    private LoginRequest(String email, String password, String ipAddress, Boolean termsOfServiceAccepted) {
        this.email = email;
        this.password = password;
        this.ipAddress = ipAddress;
        this.termsOfServiceAccepted = termsOfServiceAccepted;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Boolean getTermsOfServiceAccepted() {
        return this.termsOfServiceAccepted;
    }

    public static LoginRequest create(String email, String password, String ipAddress, Boolean termsOfServiceAccepted) {
        return new LoginRequest(email, password, ipAddress, termsOfServiceAccepted);
    }

    public static LoginRequest fromJson(String jsonString) {

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

        String password = null;
        if(dataRepresentation.hasProperty("password")) {
            try {
                password = dataRepresentation.getText("password");
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

        Boolean termsOfServiceAccepted = null;
        if(dataRepresentation.hasProperty("termsOfServiceAccepted")) {
            try {
                termsOfServiceAccepted = dataRepresentation.getTruth("termsOfServiceAccepted");
            } catch (Exception e) {
            }
        }

        LoginRequest loginRequest = create(email, password, ipAddress, termsOfServiceAccepted);
        return loginRequest;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (password != null) {
            dataRepresentation.add("password", password);
        }

        if (ipAddress != null) {
            dataRepresentation.add("ipAddress", ipAddress);
        }

        if (termsOfServiceAccepted != null) {
            dataRepresentation.add("termsOfServiceAccepted", termsOfServiceAccepted);
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