package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Bill;
import assignment.java5.phongnhph23459.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Integer> {

    @Query(value = " select Import_price from Product_Line ORDER BY Import_price  DESC limit 4", nativeQuery = true)
    List<ProductLine> top4Productline();
}
