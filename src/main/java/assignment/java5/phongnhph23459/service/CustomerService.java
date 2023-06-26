package assignment.java5.phongnhph23459.service;


import assignment.java5.phongnhph23459.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer findById(Integer id);

    Customer findByPhoneNumber(String phone);

    Customer findByCode(String code);

    void save(Customer customer);

    void update(Customer customer);

    void delete(Integer id);

    void  updateCustomerByNameAndAdreeAndNumber(String fullName, String adress, String phoneNumber, Integer id);
}
