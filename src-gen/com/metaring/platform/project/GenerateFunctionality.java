package com.metaring.platform.project;

import java.util.concurrent.CompletableFuture;

import com.metaring.framework.functionality.AbstractFunctionality;
import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.functionality.GeneratedFunctionality;

abstract class GenerateFunctionality extends AbstractFunctionality implements GeneratedFunctionality {

    static final FunctionalityInfo INFO = FunctionalityInfo.create("com.metaring.platform.project.generate", false, true, false, "java.lang.String", "com.metaring.platform.project.GeneratedProject");

    static final GenerateFunctionality INSTANCE = new GenerateFunctionalityImpl();

    protected GenerateFunctionality() {
        super(INFO, GeneratedProject.class);
    }

    @Override
    protected final CompletableFuture<Void> beforePreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = beforePreConditionCheck();
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePreConditionCheck() throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> preConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = preConditionCheck();
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> preConditionCheck() throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = afterPreConditionCheck();
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPreConditionCheck() throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforeCall(Object input) throws Exception {
        CompletableFuture<Void> response = beforeCall();
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforeCall() throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Object> call(Object input) throws Exception {
        CompletableFuture<GeneratedProject> call = call((String) input);
        if(call == null) {
            return end(null);
        }
        final CompletableFuture<Object> response = new CompletableFuture<>();
        call.handleAsync((result, error) -> {
            if(error != null) {
                response.completeExceptionally(error);
            } else {
                response.complete(result);
            }
            return null;
        }, EXECUTOR);
        return response;
    }

    protected abstract CompletableFuture<GeneratedProject> call(String input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterCall(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterCall(output == null ? null : (GeneratedProject) output);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterCall(GeneratedProject output) throws Exception{
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforePostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = beforePostConditionCheck(output == null ? null : (GeneratedProject) output);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePostConditionCheck(GeneratedProject output) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> postConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = postConditionCheck(output == null ? null : (GeneratedProject) output);
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> postConditionCheck(GeneratedProject output) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterPostConditionCheck(output == null ? null : (GeneratedProject) output);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPostConditionCheck(GeneratedProject output) throws Exception {
        return end;
    }
}