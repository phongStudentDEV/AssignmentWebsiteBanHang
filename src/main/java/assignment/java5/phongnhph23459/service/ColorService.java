package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> getAll();

    Color finById(Integer id);

    void save(Color color);

    void update(Color color);

    void delete(Integer id);

}
