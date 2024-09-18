package com.bimbo.lo.service;

import com.bimbo.lo.data.entity.UserWallet;
import com.bimbo.lo.data.repository.ProductRepository;
import com.bimbo.lo.data.repository.UserRepository;
import com.bimbo.lo.data.repository.WalletRepository;
import com.bimbo.lo.data.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private WalletRepository repository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    /**
     * @param userId Id de usuario
     * @return Retorna los puntos con los que un usuario cuenta
     */
    @Override
    public Response<Integer> pointsInquiry(Integer userId) {
        var oUser = userRepository.findById(userId);
        if (oUser.isPresent()) {
            var oWallet = repository.findOne(Example.of(new UserWallet(oUser.get())));
            if (oWallet.isPresent()) {
                var wallet = oWallet.get();
                return Response.<Integer>builder()
                        .data(wallet.getPoints())
                        .message("Se cuenta con un total de " + wallet.getPoints() + " puntos")
                        .build();
            }
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "Puntos no encontrados");
    }

    /**
     * @param userId      Id de usuario
     * @param productCode código de producto
     * @return Retorna mensaje de confirmación de agregación de puntos
     */
    @Override
    public Response<Integer> addPoints(Integer userId, String productCode) {
        var oProduct = productRepository.findByCode(productCode);
        if (oProduct.isPresent()) {
            var product = oProduct.get();
            var oUser = userRepository.findById(userId);
            if (oUser.isPresent()) {
                var oWallet = repository.findOne(Example.of(new UserWallet(oUser.get())));
                if (oWallet.isPresent()) {
                    var wallet = oWallet.get();
                    Integer currentPoints = wallet.getPoints();
                    wallet.setPoints(currentPoints + product.getPoints());
                    repository.save(wallet);

                    return Response.<Integer>builder().data(wallet.getPoints())
                            .message("Se cuenta con un total de " + wallet.getPoints() + " puntos")
                            .build();
                }
            }
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "Puntos no encontrados");
    }


}
