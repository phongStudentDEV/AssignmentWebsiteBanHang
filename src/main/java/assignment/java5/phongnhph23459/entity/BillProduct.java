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

@Entity
@Table(name = "Bill_Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BillProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Number")
    private Integer nuber;

    @Column(name = "Unit_price")
    private Float unitPrice;

    @Column(name = "Amount")
    private Float amount;

    @Column(name = "Status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "ID_Product_Details")
    private ProductDetails productDetails;

    @ManyToOne
    @JoinColumn(name = "ID_Bill")
    private Bill bill;

}
