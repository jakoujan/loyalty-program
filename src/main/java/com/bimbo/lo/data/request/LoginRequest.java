package com.bimbo.lo.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record LoginRequest(String username, String password) {
}
