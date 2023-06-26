package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Bill;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    Page<Bill> getAllByIdCustomer(Integer idCustomer, Integer pageNo, Integer size);

    Bill findById(Integer id);

    List<Bill> findByPhoneNumber(String phone);

    List<Bill> findByCode(String code);


    void save(Bill bill);

    void update(Bill bill);

    void delete(Integer id);

    Page<Bill> pageAll(Integer pageNo, Integer size);

}
