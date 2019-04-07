package com.metaring.platform.user.auth;

import com.metaring.platform.user.auth.UserDataFieldEnumerator;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class UserData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.userData";

    private UserDataFieldEnumerator field;
    private String value;

    private UserData(UserDataFieldEnumerator field, String value) {
        this.field = field;
        this.value = value;
    }

    public UserDataFieldEnumerator getField() {
        return this.field;
    }

    public String getValue() {
        return this.value;
    }

    public static UserData create(UserDataFieldEnumerator field, String value) {
        return new UserData(field, value);
    }

    public static UserData fromJson(String jsonString) {

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

        UserDataFieldEnumerator field = null;
        if(dataRepresentation.hasProperty("field")) {
            try {
                field = dataRepresentation.get("field", UserDataFieldEnumerator.class);
            } catch (Exception e) {
            }
        }

        String value = null;
        if(dataRepresentation.hasProperty("value")) {
            try {
                value = dataRepresentation.getText("value");
            } catch (Exception e) {
            }
        }

        UserData userData = create(field, value);
        return userData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (field != null) {
            dataRepresentation.add("field", field);
        }

        if (value != null) {
            dataRepresentation.add("value", value);
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