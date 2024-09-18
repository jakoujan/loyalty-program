package com.bimbo.lo.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RedeemResponse {
    private Boolean authorized;
    private String message;
}
