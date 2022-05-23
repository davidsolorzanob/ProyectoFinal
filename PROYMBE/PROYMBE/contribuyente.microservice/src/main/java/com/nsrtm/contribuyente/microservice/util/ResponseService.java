package com.nsrtm.contribuyente.microservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ResponseService {

    public static ResponseEntity<Object> setResponse(@Nullable  boolean success,@Nullable HttpStatus status, @Nullable String message, @Nullable Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> setResponse(HttpStatus status, String message, @Nullable Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> setResponse(boolean success, HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", null);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> setResponse(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", null);

        return new ResponseEntity<Object>(map,status);
    }
}
