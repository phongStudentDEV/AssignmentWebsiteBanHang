package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.CustomerAccount;

public interface CustomerAccountService {
    CustomerAccount findById(Integer id);

    void save(CustomerAccount customer);

    void update(CustomerAccount customer);

    void delete(Integer id);

    CustomerAccount findByEmailAndPassword(String email, String password);
}
