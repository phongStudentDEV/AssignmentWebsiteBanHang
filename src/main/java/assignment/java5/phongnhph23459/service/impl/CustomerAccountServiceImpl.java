package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.CustomerAccount;
import assignment.java5.phongnhph23459.repository.CustomerAccountRepository;
import assignment.java5.phongnhph23459.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Override
    public CustomerAccount findById(Integer id) {
        return customerAccountRepository.findById(id).get();
    }

    @Override
    public void save(CustomerAccount customer) {
        customerAccountRepository.save(customer);
    }

    @Override
    public void update(CustomerAccount customer) {
        customerAccountRepository.save(customer);

    }

    @Override
    public void delete(Integer id) {
        customerAccountRepository.deleteById(id);

    }

    @Override
    public CustomerAccount findByEmailAndPassword(String email, String password) {
        return customerAccountRepository.findByEmailAndPassword(email, password);
    }
}
