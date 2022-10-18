package com.city.explorer.geo.db.api.advice;

import com.city.explorer.geo.db.api.exception.ResourceNotFoundException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler(ResourceNotFoundException.class)
    public Status handleResourceNotFoundException(ResourceNotFoundException e) {
        return Status.NOT_FOUND.withDescription(e.getMessage()).withCause(e);
    }
}
