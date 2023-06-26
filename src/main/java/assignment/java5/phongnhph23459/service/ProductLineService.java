package assignment.java5.phongnhph23459.service;


import assignment.java5.phongnhph23459.entity.Manufacturer;
import assignment.java5.phongnhph23459.entity.ProductLine;

import java.util.List;

public interface ProductLineService {
    List<ProductLine> getAll();

    ProductLine findById(Integer id);

    void save(ProductLine productLine);

    void update(ProductLine productLine);

    void delete(Integer id);

    List<Manufacturer> getAllManufacturer();

    List<ProductLine> findTop4ProductLine();
}
