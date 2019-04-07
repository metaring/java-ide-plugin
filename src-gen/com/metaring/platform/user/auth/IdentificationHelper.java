package com.metaring.platform.user.auth;

import com.metaring.framework.functionality.FunctionalityContext;

public class IdentificationHelper {

    private static final String IDENTIFICATION_DATA = "IDENTIFICATION_DATA";

    public static final IdentificationData getIdentificationData(FunctionalityContext functionalityContext) {
        return (IdentificationData) functionalityContext.getData().get(IDENTIFICATION_DATA);
    }
}