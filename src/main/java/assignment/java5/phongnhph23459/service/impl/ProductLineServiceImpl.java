package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Manufacturer;
import assignment.java5.phongnhph23459.entity.ProductLine;
import assignment.java5.phongnhph23459.repository.ManufacturerRepository;
import assignment.java5.phongnhph23459.repository.ProductLineRepository;
import assignment.java5.phongnhph23459.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductLineServiceImpl implements ProductLineService {
    @Autowired
    private ProductLineRepository productLineRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Override
    public List<ProductLine> getAll() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine findById(Integer id) {
        return productLineRepository.findById(id).get();
    }

    @Override
    public void save(ProductLine productLine) {

        productLine.setDateCreate(new Date());
        productLine.setDateCorrect(new Date());
        productLineRepository.save(productLine);
    }

    @Override
    public void update(ProductLine productLine) {
        ProductLine productLineUpdate = findById(productLine.getId());
        productLine.setDateCreate(productLineUpdate.getDateCreate());
        productLine.setDateCorrect(new Date());
        productLineRepository.save(productLine);
    }

    @Override
    public void delete(Integer id) {
        productLineRepository.deleteById(id);
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    @Override
    public List<ProductLine> findTop4ProductLine() {
        return productLineRepository.top4Productline();
    }
}
