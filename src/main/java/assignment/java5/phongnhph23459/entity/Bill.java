package assignment.java5.phongnhph23459.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Bill")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Purchase_date")
    private Date purchaseDate;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Adrress")
    private String adrress;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Amount")
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "ID_Customer")
    private Customer customer;

}
