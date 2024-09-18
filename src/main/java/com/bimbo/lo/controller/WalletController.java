package com.bimbo.lo.controller;

import com.bimbo.lo.data.response.Response;
import com.bimbo.lo.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/wallet")
public class WalletController {

    private WalletService service;

    @GetMapping("/inquiry")
    public Response<Integer> pointsInquiry(@RequestParam("userId") Integer id) {
        return service.pointsInquiry(id);
    }

    @GetMapping("/points/add")
    public Response<Integer> addPoints(@RequestParam("userId") Integer id, @RequestParam("productCode") String productCode) {
        return service.addPoints(id, productCode);
    }

}
