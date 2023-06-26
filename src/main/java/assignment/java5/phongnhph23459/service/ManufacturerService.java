package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.entity.Manufacturer;

import java.util.List;


public interface ManufacturerService {
    List<Manufacturer> getAll();

    Manufacturer finById(Integer id);

    void save(Manufacturer manufacturer);

    void update(Manufacturer manufacturer);

    void delete(Integer id);
}
