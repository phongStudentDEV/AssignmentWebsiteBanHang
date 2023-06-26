package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.entity.CartDetail;
import assignment.java5.phongnhph23459.repository.CartDetailRepository;
import assignment.java5.phongnhph23459.service.CartDetailService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> getAllIdcart(Integer idCart) {
        return cartDetailRepository.findByIdCart(idCart);
    }

    @Override
    public void save(CartDetail cartDetail) {

        CartDetail cartDetailFind = cartDetailRepository.findByIdCartAndIdProductDetails(cartDetail.getCart().getId(),
                cartDetail.getProductDetails().getId());
        if (cartDetailFind == null) {
            cartDetail.setNumber(1);
            cartDetail.setAmount(cartDetail.getNumber() * cartDetail.getUnitPrice());
            cartDetailRepository.save(cartDetail);
        } else {
            cartDetailFind.setNumber(cartDetailFind.getNumber() + 1);
            cartDetailFind.setAmount(cartDetailFind.getNumber() * cartDetail.getUnitPrice());
            cartDetailRepository.save(cartDetailFind);
        }
    }

    @Override
    public Double getTotalPaymentAmount(List<CartDetail> cartDetails) {
        Double sum = 0.0;
        if (cartDetails.size() >= 0) {
            for (int i = 0; i < cartDetails.size(); i++) {
                sum = sum + cartDetails.get(i).getAmount();
            }
        }
        return sum;
    }

    @Override
    public void remove(Integer id) {
        cartDetailRepository.deleteById(id);
    }
}
