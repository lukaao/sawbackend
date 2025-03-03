package lukaao.github.sawbackend.exception;

import lukaao.github.sawbackend.model.Error;
import lukaao.github.sawbackend.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for the application. This class intercepts and handles exceptions
 * thrown by controllers, providing structured error responses.
 */
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions triggered by invalid method arguments.
     *
     * @param ex the exception containing validation errors
     * @return a ResponseEntity with structured validation errors
     */
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

    /**
     * Handles access denied exceptions when a user lacks necessary permissions.
     *
     * @param ex the access denied exception
     * @return a ResponseEntity with a 403 Forbidden status
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Access Denied: You do not have permission to perform this action.", HttpStatus.FORBIDDEN);
    }

    /**
     * Handles generic exceptions that are not specifically handled elsewhere.
     *
     * @param ex the exception that occurred
     * @return a ResponseEntity with a structured error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationError> handleExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "An unexpected error occurred";

        if (ex instanceof RuntimeException responseEx) {
            message = responseEx.getMessage();
        }

        ValidationError validationError = new ValidationError();
        validationError.setTitle("Internal Server Error");
        validationError.setStatus(status.value());
        validationError.setDetail(message);

        // Log the exception


        return new ResponseEntity<>(validationError, status);
    }


    /**
     * Handles method argument type mismatch exceptions.
     *
     * @param ex the MethodArgumentTypeMismatchException
     * @return a ResponseEntity with a detailed error message
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Argument type mismatch: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles I/O exceptions, typically caused by file operations or data reading.
     *
     * @param ex the IOException
     * @return a ResponseEntity with a detailed error message
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>("I/O Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles HTTP message not readable exceptions (e.g., invalid JSON format).
     *
     * @param ex the HttpMessageNotReadableException
     * @return a ResponseEntity with a detailed error message
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid JSON format", HttpStatus.BAD_REQUEST);
    }

}