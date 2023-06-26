package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillProductRepository extends JpaRepository<BillProduct, Integer> {
    @Query(value = " select * from Bill_Product where ID_Bill =?1", nativeQuery = true)
    List<BillProduct> findByIdBill(Integer idBill);
}
