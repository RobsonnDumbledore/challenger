package br.com.codart.exception;

import br.com.codart.errors.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StandardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentInvalidException.class)
    protected ResponseEntity<StandardError> notFoundException(
            DocumentInvalidException e,
            HttpServletRequest request) {

        StandardError standardError = new StandardError(
                BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(NOT_FOUND).body(standardError);
    }
    
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<StandardError> notFoundException(
            NotFoundException e,
            HttpServletRequest request) {

        StandardError standardError = new StandardError(
                NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(NOT_FOUND).body(standardError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        String error = "Malformed JSON request";
        StandardError standardError = new StandardError(BAD_REQUEST.value(), error,
                request.getDescription(false));
        return new ResponseEntity<>(standardError, BAD_REQUEST);
    }

}
