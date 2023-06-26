package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Capacity;
import assignment.java5.phongnhph23459.entity.Category;
import assignment.java5.phongnhph23459.entity.Color;
import assignment.java5.phongnhph23459.entity.ProductLine;
import assignment.java5.phongnhph23459.repository.CapacityRepository;
import assignment.java5.phongnhph23459.repository.CategoryRepository;
import assignment.java5.phongnhph23459.repository.ColorRepositpry;
import assignment.java5.phongnhph23459.repository.ProductLineRepository;
import assignment.java5.phongnhph23459.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepositpry colorRepositpry;

    @Autowired
    private ProductLineRepository productLineRepository;

    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<ProductLine> getAllProductline() {
        return productLineRepository.findAll();
    }

    @Override
    public List<Capacity> getAllCapacity() {
        return capacityRepository.findAll();
    }

    @Override
    public List<Color> getAllColor() {
        return colorRepositpry.findAll();
    }
}
