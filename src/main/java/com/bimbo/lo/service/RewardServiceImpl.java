package com.bimbo.lo.service;

import com.bimbo.lo.data.entity.UserWallet;
import com.bimbo.lo.data.repository.RewardRepository;
import com.bimbo.lo.data.repository.UserRepository;
import com.bimbo.lo.data.repository.WalletRepository;
import com.bimbo.lo.data.response.RedeemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private UserRepository userRepository;
    private RewardRepository rewardRepository;
    private WalletRepository walletRepository;

    /**
     * @param userId   id de usuario
     * @param rewardId identificador de recompensa
     * @return retorna si la recompensa se puede canjear
     */
    @Override
    public RedeemResponse redeem(Integer userId, Integer rewardId) {
        var oUser = userRepository.findById(userId);
        if (oUser.isPresent()) {
            var oReward = rewardRepository.findById(rewardId);
            if (oReward.isEmpty())
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "Puntos no encontrados");
            var reward = oReward.get();
            var oWallet = walletRepository.findOne(Example.of(new UserWallet(oUser.get())));
            if (oWallet.isPresent()) {
                var wallet = oWallet.get();
                if(wallet.getPoints() >= reward.getValue()) {
                    wallet.setPoints(wallet.getPoints() - reward.getValue());
                    walletRepository.save(wallet);
                    return RedeemResponse.builder().authorized(Boolean.TRUE)
                            .message("La recompensa se puede entregar").build();
                } else {
                    return RedeemResponse.builder().authorized(Boolean.FALSE)
                            .message("No se cuenta con los puntos suficientes").build();
                }
            }
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "Puntos no encontrados");
    }
}
