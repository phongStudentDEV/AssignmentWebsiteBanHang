package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Capacity;
import assignment.java5.phongnhph23459.repository.CapacityRepository;
import assignment.java5.phongnhph23459.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CapacityServiceImpl implements CapacityService {

    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public List<Capacity> getAll() {
        return capacityRepository.findAll();
    }

    @Override
    public Capacity findById(Integer id) {
        Capacity capacity = capacityRepository.findById(id).get();
        return capacity;
    }

    @Override
    public void save(Capacity capacity) {
        capacity.setDateCreate(new Date());
        capacity.setDateCorrect(new Date());

        capacityRepository.save(capacity);
    }

    @Override
    public void update(Capacity capacity) {
        Capacity capacityUpdate = findById(capacity.getId());
        capacity.setDateCreate(capacityUpdate.getDateCreate());
        capacity.setDateCorrect(new Date());
        capacityRepository.save(capacity);

    }

    @Override
    public void delete(Integer id) {
//        Capacity capacity = capacityRepository.findById(id).get();
        capacityRepository.deleteById(id);
    }
}
