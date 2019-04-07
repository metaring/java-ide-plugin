package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class UserDataFieldEnumerator implements GeneratedCoreType {

    public static final UserDataFieldEnumerator EMAIL = new UserDataFieldEnumerator("EMAIL", 1l, "email");

    private String name;
    private Long numericValue;
    private String textualValue;

    private UserDataFieldEnumerator(String name, Long numericValue, String textualValue) {
        this.name = name;
        this.numericValue = numericValue;
        this.textualValue = textualValue;
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

    public static UserDataFieldEnumerator getByNumericValue(Long numericValue) {
        if(numericValue == null) {
            return null;
        }
        switch(numericValue.intValue()) {

            case 1 : return EMAIL;

            default: return null;
        }
    }

    public static UserDataFieldEnumerator getByTextualValue(String textualValue) {
        if(textualValue == null) {
            return null;
        }
        switch(textualValue) {

            case "email" : return EMAIL;

            default: return null;
        }
    }

    public static UserDataFieldEnumerator getByName(String userDataFieldEnumeratorName) {
        if(userDataFieldEnumeratorName == null) {
            return null;
        }
        switch(userDataFieldEnumeratorName) {

            case "EMAIL" : return EMAIL;

            default: return null;
        }
    }

    public static UserDataFieldEnumerator fromJson(String json) {
        if(json == null) {
            return null;
        }
        if(json.startsWith("\"")) {
            json = json.substring(1);
        }
        if(json.endsWith("\"")) {
            json = json.substring(0, json.length() - 1);
        }
        try {
            return getByNumericValue(Long.parseLong(json));
        }
        catch(Exception e) { }
        UserDataFieldEnumerator result = getByTextualValue(json);
        return result != null ? result : getByName(json);
    }

    @Override
    public String toJson() {
        return "\"" + this.name + "\"";
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }

    @Override
    public String toString() {
        return toJson();
    }
}