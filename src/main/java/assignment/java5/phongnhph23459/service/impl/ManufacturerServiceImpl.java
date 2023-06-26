package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Color;
import assignment.java5.phongnhph23459.entity.Manufacturer;
import assignment.java5.phongnhph23459.repository.ColorRepositpry;
import assignment.java5.phongnhph23459.repository.ManufacturerRepository;
import assignment.java5.phongnhph23459.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer finById(Integer id) {
        return manufacturerRepository.findById(id).get();
    }

    @Override
    public void save(Manufacturer Manufacturer) {
        Manufacturer.setDateCorrect(new Date());
        Manufacturer.setDateCreate(new Date());
        manufacturerRepository.save(Manufacturer);
    }

    @Override
    public void update(Manufacturer Manufacturer) {
        Manufacturer manufacturerFindById = finById(Manufacturer.getId());

        Manufacturer.setDateCreate(manufacturerFindById.getDateCreate());
        Manufacturer.setDateCorrect(new Date());
        manufacturerRepository.save(Manufacturer);
    }

    @Override
    public void delete(Integer id) {
        manufacturerRepository.deleteById(id);
    }
}
