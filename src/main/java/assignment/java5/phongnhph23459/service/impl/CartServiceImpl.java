package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.repository.CartRepository;
import assignment.java5.phongnhph23459.service.CartService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return null;
    }

    //them moi 1 san pham hoac neu co san pham do o trong gio hang roi thi tang so luong len 1 don vi

    @Override
    public void add(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void update(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> findByIdCustomer(String idCustomer) {
        return cartRepository.findByIdCustomer(idCustomer);
    }

    @Override
    public Cart findByIdCustomerAndStatus(String idCustomer, Integer status) {
        return cartRepository.findByIdCustomerAndStatus(idCustomer, status);
    }

    @Override
    public Cart findById(Integer id) {
        return cartRepository.findById(id).get();
    }

}
