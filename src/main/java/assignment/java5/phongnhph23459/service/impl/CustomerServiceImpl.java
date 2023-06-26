package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.repository.CustomerRepository;
import assignment.java5.phongnhph23459.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }


    //tim theo sdt
    @Override
    public Customer findByPhoneNumber(String phone) {
        return customerRepository.findByPhoneNumber(phone);
    }

    @Override
    public Customer findByCode(String code) {
        return customerRepository.findByCode(code);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomerByNameAndAdreeAndNumber(String fullName, String adress, String phoneNumber, Integer id) {
        customerRepository.updateCustomerByNameAndAdreeAndNumber(fullName, adress, phoneNumber,  id);
    }
}
