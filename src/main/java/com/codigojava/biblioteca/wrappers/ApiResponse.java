package com.codigojava.biblioteca.wrappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ApiResponse<T> {

    private T data;
    private String timestamp;

    public ApiResponse(T data) {
        this.data = data;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));
    }

    public T getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
