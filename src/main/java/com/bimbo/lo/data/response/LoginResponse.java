package com.bimbo.lo.data.response;

import com.bimbo.lo.data.entity.User;

public record LoginResponse(User user, String token) {
}
