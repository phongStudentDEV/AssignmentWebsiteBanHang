package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select * from Customer where Phone_Number=?1", nativeQuery = true)
    Customer findByPhoneNumber(String phone);

    @Query(value = "select * from Customer where Code=?1", nativeQuery = true)
    Customer findByCode(String code);

    //    update thong tin customer khi thanh toan (co login)
    //    update Customer set Full_Name='phong nn', Adrress='ha noi', Phone_Number='1234567890'  where id =26;
    @Modifying
    @Transactional
    @Query(value = "update Customer set Full_Name=?1, Adrress=?2, Phone_Number=?3  where id =?4", nativeQuery = true)
    void updateCustomerByNameAndAdreeAndNumber(String fullName, String adress, String phoneNumber, Integer id);

}
