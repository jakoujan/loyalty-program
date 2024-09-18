package com.bimbo.lo.service;

import com.bimbo.lo.data.response.Response;

public interface WalletService {

    Response<Integer> pointsInquiry(Integer id);

    Response<Integer> addPoints(Integer id, String productCode);

}
