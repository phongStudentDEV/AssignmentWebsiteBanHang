package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Bill;
import assignment.java5.phongnhph23459.repository.BillRepository;
import assignment.java5.phongnhph23459.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;


    @Override
    public Page<Bill> getAllByIdCustomer(Integer idCustomer, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return billRepository.getAllByIdCustomer(idCustomer, pageable);
    }

    @Override
    public Bill findById(Integer id) {
        return billRepository.findById(id).get();
    }

    @Override
    public List<Bill> findByPhoneNumber(String phone) {
        return billRepository.findByPhoneNumber(phone);
    }

    @Override
    public List<Bill> findByCode(String code) {
        return billRepository.findBycode(code);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void update(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void delete(Integer id) {
        billRepository.deleteById(id);
    }

    @Override
    public Page<Bill> pageAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return billRepository.findAll(pageable);
    }
}
