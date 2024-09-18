package com.bimbo.lo.service;

import com.bimbo.lo.data.response.RedeemResponse;

public interface RewardService {

    RedeemResponse redeem(Integer userId, Integer rewardId);
}
