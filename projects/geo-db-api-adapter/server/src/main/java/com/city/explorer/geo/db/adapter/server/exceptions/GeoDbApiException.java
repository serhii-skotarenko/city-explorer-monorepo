package com.city.explorer.geo.db.adapter.server.exceptions;

public class GeoDbApiException extends RuntimeException {
    public GeoDbApiException(String message) {
        super(message);
    }

    public GeoDbApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
