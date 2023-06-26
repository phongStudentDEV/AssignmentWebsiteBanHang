package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity, Integer> {
}
