package assignment.java5.phongnhph23459.service.impl;

import assignment.java5.phongnhph23459.service.CartModelService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScope
@Service
public class CartModelServiceImpl implements CartModelService {
    Map<Integer, CartModel> modelMap = new HashMap<>(); // gior hangf

    //them moi 1 san pham hoac neu co san pham do o trong gio hang roi thi tang so luong len 1 don vi
    @Override
    public void addCart(CartModel item) {
        CartModel cartModel = modelMap.get(item.getIdProductDetail());

        if (cartModel == null) {
            modelMap.put(item.getIdProductDetail(), item);
        } else {
            cartModel.setQuantity(cartModel.getQuantity() + 1);
        }
    }

    //xoa 1 sp o trong gio hang
    @Override
    public void remove(Integer id) {
        modelMap.remove(id);
    }

    // cap nhat laij so luong khi nhapj so luong moi cho san pham o trong gio hang
    @Override
    public CartModel updateCartModel(Integer idProductDetail, Integer quantity) {
        CartModel cartModel = modelMap.get(idProductDetail);
        cartModel.setQuantity(quantity);
        return cartModel;
    }


    //xoa tat ca san pham trong gio hang
    @Override
    public void removeAll() {
        modelMap.clear();
    }

    //lay ra tat ca sp trong gio hang
    @Override
    public Collection<CartModel> getAll() {
        return modelMap.values();
    }

    @Override
    public Double getTotalPaymentAmount() { // tinh tong tien
        Double sum = 0.00;
        List<CartModel> cartModels = new ArrayList<>(modelMap.values());
        if (cartModels.size() >= 0) {
            for (int i = 0; i < cartModels.size(); i++) {
                sum = sum + cartModels.get(i).getPrice() * cartModels.get(i).getQuantity();
            }
        }
        return sum;
    }

    @Override
    public List<CartModel> listCartItem(){
        List<CartModel> cartModels = new ArrayList<>(modelMap.values());
        return cartModels;
    }
}
