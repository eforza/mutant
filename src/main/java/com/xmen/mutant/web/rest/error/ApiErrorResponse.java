package com.xmen.mutant.web.rest.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    private ApiErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(Integer status, Throwable ex) {
        this();
        this.status = status;
        this.message = ex.getLocalizedMessage();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   }

