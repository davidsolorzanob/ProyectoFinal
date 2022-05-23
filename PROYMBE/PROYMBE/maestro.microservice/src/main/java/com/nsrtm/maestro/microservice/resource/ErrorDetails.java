package com.nsrtm.maestro.microservice.resource;

import java.util.Date;

public class ErrorDetails {
    public Date timestamp;
    public String message;
    public String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
