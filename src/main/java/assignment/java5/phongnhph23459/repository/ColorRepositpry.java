package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepositpry extends JpaRepository<Color, Integer> {
}
