package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.entity.Roles;
import assignment.java5.phongnhph23459.repository.RolesRepository;
import assignment.java5.phongnhph23459.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles findById(Integer id) {
        return rolesRepository.findById(id).get();
    }

    @Override
    public void save(Roles roles) {
        rolesRepository.save(roles);
    }

    @Override
    public void update(Roles roles) {
        rolesRepository.save(roles);
    }

    @Override
    public void delete(Integer id) {
        rolesRepository.deleteById(id);
    }
}
