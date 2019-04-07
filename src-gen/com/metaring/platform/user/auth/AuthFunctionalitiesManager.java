package com.metaring.platform.user.auth;

import com.metaring.framework.functionality.FunctionalitiesManager;
import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.functionality.GeneratedFunctionalitiesManager;

public class AuthFunctionalitiesManager extends FunctionalitiesManager implements GeneratedFunctionalitiesManager {

    public static final FunctionalityInfo HEARTBEAT = FunctionalityInfo.create("com.metaring.platform.user.auth.heartbeat", false, true, false, null, null);

    public static final FunctionalityInfo LOGIN = FunctionalityInfo.create("com.metaring.platform.user.auth.login", false, false, false, "com.metaring.platform.user.auth.LoginRequest", "java.lang.String");

    public static final FunctionalityInfo RESTORE_SESSION = FunctionalityInfo.create("com.metaring.platform.user.auth.restoreSession", false, false, false, "com.metaring.platform.user.auth.RestoreSessionRequest", "com.metaring.platform.user.auth.LoggedUser");
}