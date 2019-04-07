package com.metaring.platform.project;

import java.util.concurrent.CompletableFuture;

import com.metaring.framework.functionality.FunctionalitiesManager;
import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.functionality.GeneratedFunctionalitiesManager;

public class ProjectFunctionalitiesManager extends FunctionalitiesManager implements GeneratedFunctionalitiesManager {

    public static final FunctionalityInfo GENERATE = FunctionalityInfo.create("com.metaring.platform.project.generate", false, true, false, "java.lang.String", "com.metaring.platform.project.GeneratedProject");

    public static final CompletableFuture<GeneratedProject> generate(String input) {
        return call(GENERATE, GenerateFunctionality.class, null, input, result -> result.as(GeneratedProject.class));
    }
}