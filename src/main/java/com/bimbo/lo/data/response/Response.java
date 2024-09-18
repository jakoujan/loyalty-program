package com.bimbo.lo.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Response<T> {
    T data;
    String message;
}
