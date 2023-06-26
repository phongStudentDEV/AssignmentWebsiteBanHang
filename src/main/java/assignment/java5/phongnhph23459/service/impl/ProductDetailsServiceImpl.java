package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Category;
import assignment.java5.phongnhph23459.entity.ProductDetails;
import assignment.java5.phongnhph23459.repository.CategoryRepository;
import assignment.java5.phongnhph23459.repository.ProductDetailsRepository;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDetails> getAll() {
        return productDetailsRepository.findAll();
    }

    @Override
    public ProductDetails findById(Integer id) {
        return productDetailsRepository.findById(id).get();
    }

    @Override
    public void save(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    public void update(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    public void delete(Integer id) {
        productDetailsRepository.deleteById(id);
    }

    @Override
    public Page<ProductDetails> pageAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailsRepository.findAll(pageable);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    //update so luong
    @Override
    public void updateQuantity(Integer soLuong, Integer idProDuct) {
        productDetailsRepository.updateQuantity(soLuong, idProDuct);
    }
}
