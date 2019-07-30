package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseDTO {

    private String message;
    private HttpStatus status;
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ResponseDTO(String message, HttpStatus status, int statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }

    public ResponseDTO(String message, HttpStatus status, int statusCode, Object data) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
}
