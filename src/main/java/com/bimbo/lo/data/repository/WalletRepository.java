package com.bimbo.lo.data.repository;

import com.bimbo.lo.data.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<UserWallet, Integer> {
}
