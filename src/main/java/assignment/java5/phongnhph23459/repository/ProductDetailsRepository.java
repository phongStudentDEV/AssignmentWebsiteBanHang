package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.ProductDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {

    //update so luong
    @Modifying
    @Transactional
    @Query(value = " update Product_Details set number = (number - ?1) where id =?2", nativeQuery = true)
    void updateQuantity(Integer soLuong, Integer idProductDetail);
}
