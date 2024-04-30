package com.mitrei.dictionary.model.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(400, String.format("Required parameter '%s' is missing", ex.getParameterName()), request.getRequestURI());
        ApiMetaData meta = new ApiMetaData();
        ApiResponse<ApiError> response = new ApiResponse<>(apiError, meta);
        return ResponseEntity.status(400).body(response);
    }
}