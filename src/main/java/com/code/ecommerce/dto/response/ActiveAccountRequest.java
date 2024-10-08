package com.code.ecommerce.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ActiveAccountRequest {
    @NotEmpty
    private String token;

    @NotEmpty
    private String userId;
}
