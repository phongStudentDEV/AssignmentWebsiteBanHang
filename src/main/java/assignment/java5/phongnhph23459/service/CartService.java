package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAll();

    void add(Cart cart);

    void update(Cart cart);


    List<Cart> findByIdCustomer(String idCustomer);

    Cart findByIdCustomerAndStatus(String idCustomer, Integer status);

    Cart findById(Integer id);

}
