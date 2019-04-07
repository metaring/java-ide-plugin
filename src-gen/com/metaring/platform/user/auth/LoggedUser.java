package com.metaring.platform.user.auth;

import com.metaring.framework.localization.LocaleEnumerator;
import com.metaring.platform.user.auth.OneTimeToken;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class LoggedUser implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.loggedUser";

    private String email;
    private String name;
    private LocaleEnumerator language;
    private OneTimeToken oneTimeToken;

    private LoggedUser(String email, String name, LocaleEnumerator language, OneTimeToken oneTimeToken) {
        this.email = email;
        this.name = name;
        this.language = language;
        this.oneTimeToken = oneTimeToken;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public LocaleEnumerator getLanguage() {
        return this.language;
    }

    public OneTimeToken getOneTimeToken() {
        return this.oneTimeToken;
    }

    public static LoggedUser create(String email, String name, LocaleEnumerator language, OneTimeToken oneTimeToken) {
        return new LoggedUser(email, name, language, oneTimeToken);
    }

    public static LoggedUser fromJson(String jsonString) {

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

        String name = null;
        if(dataRepresentation.hasProperty("name")) {
            try {
                name = dataRepresentation.getText("name");
            } catch (Exception e) {
            }
        }

        LocaleEnumerator language = null;
        if(dataRepresentation.hasProperty("language")) {
            try {
                language = dataRepresentation.get("language", LocaleEnumerator.class);
            } catch (Exception e) {
            }
        }

        OneTimeToken oneTimeToken = null;
        if(dataRepresentation.hasProperty("oneTimeToken")) {
            try {
                oneTimeToken = dataRepresentation.get("oneTimeToken", OneTimeToken.class);
            } catch (Exception e) {
            }
        }

        LoggedUser loggedUser = create(email, name, language, oneTimeToken);
        return loggedUser;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (language != null) {
            dataRepresentation.add("language", language);
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