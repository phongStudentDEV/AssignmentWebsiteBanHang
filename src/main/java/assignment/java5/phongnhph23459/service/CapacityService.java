package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Capacity;

import java.util.List;

public interface CapacityService {
    List<Capacity> getAll();
    Capacity findById(Integer id);
    void save(Capacity capacity);
    void update(Capacity capacity);
    void delete(Integer id);
}
