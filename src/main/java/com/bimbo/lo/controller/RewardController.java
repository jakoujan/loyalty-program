package com.bimbo.lo.controller;

import com.bimbo.lo.data.response.RedeemResponse;
import com.bimbo.lo.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/reward")
public class RewardController {

    private RewardService service;

    @GetMapping(name = "/redeem")
    public RedeemResponse redeem(@RequestParam("userId") Integer userId, @RequestParam("rewardId") Integer rewardId) {
        return service.redeem(userId, rewardId);
    }
}
