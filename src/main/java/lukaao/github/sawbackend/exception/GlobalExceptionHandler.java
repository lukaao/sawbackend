package lukaao.github.sawbackend.exception;

import lukaao.github.sawbackend.model.Error;
import lukaao.github.sawbackend.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Create the list of field errors
        List<Error> fieldErrors = new ArrayList<>();

        // Populate the list with specific errors for each field
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            Error fieldError = new Error();
            fieldError.setField(error.getField());
            fieldError.setMessage(error.getDefaultMessage());
            fieldErrors.add(fieldError);
        });

        // Create the general error object
        ValidationError validationError = new ValidationError();
        validationError.setTitle("Validation Failures");
        validationError.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
        validationError.setDetail("One or more fields are invalid");
        validationError.setErrors(fieldErrors);

        // Return the response with validation failure details
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationError> handleExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "An unexpected error occurred";

        if (ex instanceof RuntimeException responseEx) {
            message = responseEx.getMessage();
        }

        ValidationError validationError = new ValidationError();
        validationError.setTitle("Validation Failures");
        validationError.setStatus(status.value());
        validationError.setDetail(message);


        return new ResponseEntity<>(validationError, status);
    }
}