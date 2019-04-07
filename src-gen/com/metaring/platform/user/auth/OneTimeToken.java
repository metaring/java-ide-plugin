package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class OneTimeToken implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.oneTimeToken";

    private String value;
    private Long sessionRefreshTimeoutMillis;

    private OneTimeToken(String value, Long sessionRefreshTimeoutMillis) {
        this.value = value;
        this.sessionRefreshTimeoutMillis = sessionRefreshTimeoutMillis;
    }

    public String getValue() {
        return this.value;
    }

    public Long getSessionRefreshTimeoutMillis() {
        return this.sessionRefreshTimeoutMillis;
    }

    public static OneTimeToken create(String value, Long sessionRefreshTimeoutMillis) {
        return new OneTimeToken(value, sessionRefreshTimeoutMillis);
    }

    public static OneTimeToken fromJson(String jsonString) {

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

        String value = null;
        if(dataRepresentation.hasProperty("value")) {
            try {
                value = dataRepresentation.getText("value");
            } catch (Exception e) {
            }
        }

        Long sessionRefreshTimeoutMillis = null;
        if(dataRepresentation.hasProperty("sessionRefreshTimeoutMillis")) {
            try {
                sessionRefreshTimeoutMillis = dataRepresentation.getDigit("sessionRefreshTimeoutMillis");
            } catch (Exception e) {
            }
        }

        OneTimeToken oneTimeToken = create(value, sessionRefreshTimeoutMillis);
        return oneTimeToken;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (value != null) {
            dataRepresentation.add("value", value);
        }

        if (sessionRefreshTimeoutMillis != null) {
            dataRepresentation.add("sessionRefreshTimeoutMillis", sessionRefreshTimeoutMillis);
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