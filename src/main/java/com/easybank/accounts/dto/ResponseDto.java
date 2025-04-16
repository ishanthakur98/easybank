package com.easybank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String message;
    private String status;
    private Object data;

    public ResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ResponseDto(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
