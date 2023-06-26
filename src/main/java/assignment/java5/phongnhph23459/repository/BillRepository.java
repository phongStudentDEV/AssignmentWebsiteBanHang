package assignment.java5.phongnhph23459.repository;

import assignment.java5.phongnhph23459.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    // tim kiem bill thong qua sdt
    @Query(value = " select * from Bill where Phone_Number=?1", nativeQuery = true)
    List<Bill> findByPhoneNumber(String phone);

    // tim bill thong qua ma code, (ma code nay bang id tk khach hang)
    @Query(value = " select * from Bill where Code=?1", nativeQuery = true)
    List<Bill> findBycode(String code);

    // tim bill thong qua ma code, (ma code nay bang id tk khach hang)
    @Query(value = " select * from Bill where ID_Customer =?1", nativeQuery = true)
    Page<Bill> getAllByIdCustomer(Integer idCustomer, Pageable pageable);


}
