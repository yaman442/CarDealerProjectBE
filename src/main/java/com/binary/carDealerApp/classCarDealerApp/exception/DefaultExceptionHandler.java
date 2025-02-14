package com.binary.carDealerApp.classCarDealerApp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * DefaultExceptionHandler Class
 *
 * This class serves as a global exception handler for the application.
 * It uses Spring's @RestControllerAdvice to provide centralized exception handling
 * across all @RequestMapping methods through @ExceptionHandler methods.
 *
 * Key Points:
 * 1. Global Exception Handling: Catches and handles exceptions thrown from any controller in the application.
 * 2. Standardized Error Responses: Uses ApiError record to create consistent error response structures.
 * 3. Specific Exception Handlers: Provides custom handling for specific exception types.
 * 4. Logging: Utilizes SLF4J for logging exception details.
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * Handles CarNotFoundException.
     * This exception is typically thrown when a requested car is not found in the database.
     *
     * @param e The CarNotFoundException that was thrown
     * @param request The HttpServletRequest associated with the exception
     * @return ResponseEntity containing ApiError with NOT_FOUND status
     */
    @ExceptionHandler(CarNotFoundException.class)
    private ResponseEntity<ApiError> exceptionHandler(
            CarNotFoundException e,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalArgumentException.
     * This exception is typically thrown for invalid input parameters.
     *
     * @param e The IllegalArgumentException that was thrown
     * @param request The HttpServletRequest associated with the exception
     * @return ResponseEntity containing ApiError with BAD_REQUEST status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ApiError> exceptionHandler(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        log.info("apiError " + apiError);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other uncaught exceptions.
     * This is a catch-all handler for any exception not specifically handled above.
     *
     * @param e The Exception that was thrown
     * @param request The HttpServletRequest associated with the exception
     * @return ResponseEntity containing ApiError with BAD_REQUEST status
     */
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> exceptionHandler(
            Exception e,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
