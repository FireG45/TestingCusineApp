package ru.bucm.TestingApp.responses;

import org.springframework.http.HttpStatus;

public class TestUploadResponse {
    private int id;
    HttpStatus status;

    public TestUploadResponse(int id, HttpStatus status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
