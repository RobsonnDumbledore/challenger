package br.com.codart.errors;

public class SubError {

    private String field;
    private String message;

    public SubError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
