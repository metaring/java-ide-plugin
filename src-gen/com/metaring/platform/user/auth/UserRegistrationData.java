package com.metaring.platform.user.auth;

import com.metaring.framework.type.Email;
import com.metaring.framework.localization.LocaleEnumerator;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class UserRegistrationData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.userRegistrationData";

    private String name;
    private String surname;
    private Email email;
    private String password;
    private String passwordConfirm;
    private LocaleEnumerator language;
    private String registrationIp;

    private UserRegistrationData(String name, String surname, Email email, String password, String passwordConfirm, LocaleEnumerator language, String registrationIp) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.language = language;
        this.registrationIp = registrationIp;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Email getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPasswordConfirm() {
        return this.passwordConfirm;
    }

    public LocaleEnumerator getLanguage() {
        return this.language;
    }

    public String getRegistrationIp() {
        return this.registrationIp;
    }

    public static UserRegistrationData create(String name, String surname, Email email, String password, String passwordConfirm, LocaleEnumerator language, String registrationIp) {
        return new UserRegistrationData(name, surname, email, password, passwordConfirm, language, registrationIp);
    }

    public static UserRegistrationData fromJson(String jsonString) {

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

        String surname = null;
        if(dataRepresentation.hasProperty("surname")) {
            try {
                surname = dataRepresentation.getText("surname");
            } catch (Exception e) {
            }
        }

        Email email = null;
        if(dataRepresentation.hasProperty("email")) {
            try {
                email = dataRepresentation.getEmail("email");
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

        String passwordConfirm = null;
        if(dataRepresentation.hasProperty("passwordConfirm")) {
            try {
                passwordConfirm = dataRepresentation.getText("passwordConfirm");
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

        String registrationIp = null;
        if(dataRepresentation.hasProperty("registrationIp")) {
            try {
                registrationIp = dataRepresentation.getText("registrationIp");
            } catch (Exception e) {
            }
        }

        UserRegistrationData userRegistrationData = create(name, surname, email, password, passwordConfirm, language, registrationIp);
        return userRegistrationData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (surname != null) {
            dataRepresentation.add("surname", surname);
        }

        if (email != null) {
            dataRepresentation.add("email", email);
        }

        if (password != null) {
            dataRepresentation.add("password", password);
        }

        if (passwordConfirm != null) {
            dataRepresentation.add("passwordConfirm", passwordConfirm);
        }

        if (language != null) {
            dataRepresentation.add("language", language);
        }

        if (registrationIp != null) {
            dataRepresentation.add("registrationIp", registrationIp);
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