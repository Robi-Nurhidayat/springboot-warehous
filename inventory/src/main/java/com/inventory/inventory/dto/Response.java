package com.inventory.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )
    private String message;

    @Schema(
            description = "Data in the response"
    )
    private Object data;
}
