package com.metaring.platform.user.auth;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class PasswordResetData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.platform.user.auth.passwordResetData";

    private String resetPasswordCode;
    private String ipAddress;
    private String newPassword;
    private String newPasswordConfirm;

    private PasswordResetData(String resetPasswordCode, String ipAddress, String newPassword, String newPasswordConfirm) {
        this.resetPasswordCode = resetPasswordCode;
        this.ipAddress = ipAddress;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getResetPasswordCode() {
        return this.resetPasswordCode;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getNewPasswordConfirm() {
        return this.newPasswordConfirm;
    }

    public static PasswordResetData create(String resetPasswordCode, String ipAddress, String newPassword, String newPasswordConfirm) {
        return new PasswordResetData(resetPasswordCode, ipAddress, newPassword, newPasswordConfirm);
    }

    public static PasswordResetData fromJson(String jsonString) {

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

        String resetPasswordCode = null;
        if(dataRepresentation.hasProperty("resetPasswordCode")) {
            try {
                resetPasswordCode = dataRepresentation.getText("resetPasswordCode");
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

        String newPassword = null;
        if(dataRepresentation.hasProperty("newPassword")) {
            try {
                newPassword = dataRepresentation.getText("newPassword");
            } catch (Exception e) {
            }
        }

        String newPasswordConfirm = null;
        if(dataRepresentation.hasProperty("newPasswordConfirm")) {
            try {
                newPasswordConfirm = dataRepresentation.getText("newPasswordConfirm");
            } catch (Exception e) {
            }
        }

        PasswordResetData passwordResetData = create(resetPasswordCode, ipAddress, newPassword, newPasswordConfirm);
        return passwordResetData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (resetPasswordCode != null) {
            dataRepresentation.add("resetPasswordCode", resetPasswordCode);
        }

        if (ipAddress != null) {
            dataRepresentation.add("ipAddress", ipAddress);
        }

        if (newPassword != null) {
            dataRepresentation.add("newPassword", newPassword);
        }

        if (newPasswordConfirm != null) {
            dataRepresentation.add("newPasswordConfirm", newPasswordConfirm);
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