package assignment.java5.phongnhph23459.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartModel {
    private Integer idProductDetail;
    private String name;
    private Integer quantity = 1;
    private Double price;
//    private Double totalPaymentAmount = quantity*price;

}
