package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Capacity;
import assignment.java5.phongnhph23459.entity.Category;
import assignment.java5.phongnhph23459.entity.Color;
import assignment.java5.phongnhph23459.entity.ProductLine;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category findById(Integer id);

    void save(Category category);

    void update(Category category);

    void delete(Integer id);

    List<ProductLine> getAllProductline();
    List<Capacity> getAllCapacity();
    List<Color> getAllColor();
}
