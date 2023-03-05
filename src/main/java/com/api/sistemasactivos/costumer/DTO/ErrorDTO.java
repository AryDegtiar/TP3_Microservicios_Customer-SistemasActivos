package com.api.sistemasactivos.costumer.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorDTO {
    private String status_code;
    private String status;
    private String message;
}