package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.BillProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillProductService {
    List<BillProduct> getAll();

    BillProduct findById(Integer id);

    List<BillProduct> findByIdBill(Integer idBill);

    void save(BillProduct billProduct);

    void update(BillProduct billProduct);

    void delete(Integer id);

//    Page<Bill> pageAll(Integer pageNo, Integer size);
}
