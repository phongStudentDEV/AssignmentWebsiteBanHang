package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // tim list gio hang thong qua ma ID_Customer,
    @Query(value = "select * from Cart where ID_Customer =?1", nativeQuery = true)
    List<Cart> findByIdCustomer(String idCustomer);

    // tim 1 gio hang thong qua ma ID_Customer va status,
    @Query(value = "select * from Cart where ID_Customer =?1 and Status=?2", nativeQuery = true)
    Cart findByIdCustomerAndStatus(String idCustomer, Integer status);
}
