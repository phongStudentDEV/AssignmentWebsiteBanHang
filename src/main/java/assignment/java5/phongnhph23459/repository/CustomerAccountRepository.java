package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {

    @Query(value = "select * from Customer_Account c where c.email =?1 and c.password =?2", nativeQuery = true)
    CustomerAccount findByEmailAndPassword(String email, String password);
}
