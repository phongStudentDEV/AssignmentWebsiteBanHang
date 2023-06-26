package assignment.java5.phongnhph23459.service;

import assignment.java5.phongnhph23459.viewModel.CartModel;

import java.util.Collection;
import java.util.List;

public interface CartModelService {
    //them moi 1 san pham hoac neu co san pham do o trong gio hang roi thi tang so luong len 1 don vi
    void addCart(CartModel item);

    //xoa 1 sp o trong gio hang
    void remove(Integer id);

    // cap nhat laij so luong khi nhapj so luong moi cho san pham o trong gio hang
    CartModel updateCartModel(Integer idProductDetail, Integer quantity);

    //xoa tat ca san pham trong gio hang
    void removeAll();

    //lay ra tat ca sp trong gio hang
    Collection<CartModel> getAll();

    //tinh tong tien
    Double getTotalPaymentAmount();

    List<CartModel> listCartItem();
}
