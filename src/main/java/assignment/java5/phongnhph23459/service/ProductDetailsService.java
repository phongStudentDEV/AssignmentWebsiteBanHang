package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Category;
import assignment.java5.phongnhph23459.entity.ProductDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailsService {
    List<ProductDetails> getAll();
    ProductDetails findById(Integer id);

    void save(ProductDetails productDetails);

    void update(ProductDetails productDetails);

    void delete(Integer id);

    Page<ProductDetails> pageAll(Integer pageNo, Integer size);

    List<Category> getAllCategory();

    //update so luong
    void updateQuantity(Integer soLuong, Integer idProDuct);
}
