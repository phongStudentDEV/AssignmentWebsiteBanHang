package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.BillProduct;
import assignment.java5.phongnhph23459.repository.BillProductRepository;
import assignment.java5.phongnhph23459.service.BillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillProductServiceImpl implements BillProductService {
    @Autowired
    private BillProductRepository billProductRepository;

    @Override
    public List<BillProduct> getAll() {
        return billProductRepository.findAll();
    }

    @Override
    public BillProduct findById(Integer id) {
        return billProductRepository.findById(id).get();
    }

    @Override
    public List<BillProduct> findByIdBill(Integer idBill) {
        return billProductRepository.findByIdBill(idBill);
    }

    @Override
    public void save(BillProduct billProduct) {
        billProductRepository.save(billProduct);
    }

    @Override
    public void update(BillProduct billProduct) {
        billProductRepository.save(billProduct);

    }

    @Override
    public void delete(Integer id) {
        billProductRepository.deleteById(id);
    }
}
