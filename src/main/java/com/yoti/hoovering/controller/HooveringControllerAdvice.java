package com.yoti.hoovering.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is responsible for handling exception thrown in application
 */
@ControllerAdvice
public class HooveringControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(HooveringControllerAdvice.class);

    /**
     * Handles IllegalStateException
     * @param e exception thrown
     * @return ResponseEntity which contains http status and error message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        ResponseEntity<String> body = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        logger.error(e.getMessage());
        return body;
    }

    /**
     * Handles IllegalArgumentException
     * @param e exception thrown
     * @return ResponseEntity which contains http status and error message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalArgumentException e) {
        ResponseEntity<String> body = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        logger.error(e.getMessage());
        return body;
    }

}
