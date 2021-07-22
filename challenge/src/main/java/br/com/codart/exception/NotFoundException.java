package br.com.codart.exception;

public class NotFoundException extends RuntimeException {

    private String msg;

    public NotFoundException(String msg) {
       super(msg);
    }

    public String getMsg() {
        return msg;
    }

}
