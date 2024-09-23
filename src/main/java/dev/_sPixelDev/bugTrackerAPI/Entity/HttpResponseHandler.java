package dev._sPixelDev.bugTrackerAPI.Entity;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseHandler {

    public static ResponseEntity<Object> generateResponse(HttpStatusCode statusCode, boolean error, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("timestamp", new Date());
            map.put("status", statusCode.value());
            map.put("isError", error);
            map.put("message", message);
            map.put("data", responseObj);

            return new ResponseEntity<>(map,statusCode);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", statusCode.value());
            map.put("isError",false);
            map.put("message", e.getLocalizedMessage());
            map.put("data", null);
            return new ResponseEntity<>(map,statusCode);
        }
    }
}
