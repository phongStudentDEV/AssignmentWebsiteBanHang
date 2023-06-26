package assignment.java5.phongnhph23459.service;


import assignment.java5.phongnhph23459.entity.Roles;

public interface RolesService {
    Roles findById(Integer id);

    void save(Roles roles);

    void update(Roles roles);

    void delete(Integer id);
}
