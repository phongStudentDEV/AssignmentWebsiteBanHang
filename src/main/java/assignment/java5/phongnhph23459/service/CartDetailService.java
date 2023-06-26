package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.CartDetail;

import java.util.List;

public interface CartDetailService {
    List<CartDetail> getAllIdcart(Integer idCart);

    void save(CartDetail cartDetail);

    Double getTotalPaymentAmount(List<CartDetail> cartDetails);

    void remove(Integer id);
}
