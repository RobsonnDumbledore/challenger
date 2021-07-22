package br.com.codart.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.FieldError;

public class StandardError {

    private static final long serialVersionUID = 1l;
    private Instant timestamp;
    private Integer status;
    private String message;
    private String path;
    private List<SubError> subErrors = new ArrayList<>();

    public StandardError(Integer status, String error) {
    }

    public StandardError(Integer status, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(field -> subErrors.
                add(new SubError(field.getField(), field.getDefaultMessage())));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<SubError> getSubErrors() {
        return subErrors;
    }
    
    
}
