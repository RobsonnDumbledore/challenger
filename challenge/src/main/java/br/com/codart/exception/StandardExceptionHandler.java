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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StandardExceptionHandler extends ResponseEntityExceptionHandler {



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

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<StandardError> businessException(
            BusinessException e,
            HttpServletRequest request) {

        StandardError standardError = new StandardError(
                BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StandardError error = new StandardError(BAD_REQUEST.value(),
                "validation error", request.getDescription(false));

        error.addValidationErrors(ex.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(error, BAD_REQUEST);
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
