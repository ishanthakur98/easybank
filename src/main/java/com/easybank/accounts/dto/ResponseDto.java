package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold API Response"
)
public class ResponseDto {

    @Schema(
            description = "Response message of api",example = "Success"
    )
    private String message;

    @Schema(
            description = "Status of api",example = "200"
    )
    private String status;

    @Schema(
            description = "Additional data if needed in response/ Optional"
    )
    private Object data;

    public ResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

}
