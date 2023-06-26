package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Query(value = "select * from Cart_Detail where Id_Cart =?1", nativeQuery = true)
    List<CartDetail> findByIdCart(Integer idCart);


    @Query(value = "select * from Cart_Detail where Id_Cart =?1 and ID_Product_Details=?2", nativeQuery = true)
    CartDetail findByIdCartAndIdProductDetails(Integer idCart, Integer idProductDetails);


}
