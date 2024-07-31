package jmsmarcelo.raffleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class ExceptionDetails extends LinkedHashMap<Object, String> {

    public ExceptionDetails(MethodArgumentNotValidException e, HttpStatus status) {
        set("status::" + status.toString(),
                "field::" + e.getFieldError().getField(),
                "error::" + e.getFieldError().getDefaultMessage());
    }
    public ExceptionDetails(String ... keyName) {
        set(keyName);
    }
    public ExceptionDetails(HttpMessageNotReadableException e) {
        String location = "";
        String error = e.getLocalizedMessage();
        if(error.contains("RaffleStatus")) {
            error = error.replaceAll(".*from String (\".+)", "error::$1").replaceAll("\"", "");
            location = "field::status";
        }
        set("status::" + HttpStatus.BAD_REQUEST, location, error);
    }
    private void set(String ... keyName) {
        super.put("timestamp", LocalDateTime.now().toString());
        for (String kn : keyName) {
            String[] arrKn = kn.split("::");
            super.put(arrKn[0], arrKn[1]);
        }
    }
}