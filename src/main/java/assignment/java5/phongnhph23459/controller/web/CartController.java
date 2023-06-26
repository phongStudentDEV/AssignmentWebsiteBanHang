package assignment.java5.phongnhph23459.controller.web;

import assignment.java5.phongnhph23459.entity.Bill;
import assignment.java5.phongnhph23459.entity.BillProduct;
import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.entity.CartDetail;
import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.entity.ProductDetails;
import assignment.java5.phongnhph23459.service.BillService;
import assignment.java5.phongnhph23459.service.CartDetailService;
import assignment.java5.phongnhph23459.service.CartModelService;
import assignment.java5.phongnhph23459.service.CartService;
import assignment.java5.phongnhph23459.service.CustomerService;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private CartModelService cartModelService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("hien-thi") // hien thi table gio hang
    public String hienThi(Model model, HttpSession session, HttpServletRequest request) {

        boolean checkLogin = false; //
        model.addAttribute("checkLogin", checkLogin);
        model.addAttribute("cartItems", cartModelService.getAll());
        model.addAttribute("sumCartItem", cartModelService.getAll().size());
        model.addAttribute("totalPaymentAmount", cartModelService.getTotalPaymentAmount());
        session = request.getSession();
        session.setAttribute("sumCartItem", cartModelService.getAll().size());
        boolean xacNhanThongTin = false;
        model.addAttribute("xacNhanThongTin", xacNhanThongTin);
        return "web/cart";
    }

    @GetMapping("hien-thi-log-in") // hien thi table gio hang
    public String hienThiCartLogin(Model model, HttpSession session, HttpServletRequest request) {

        boolean checkLogin = true;
        model.addAttribute("checkLogin", checkLogin);

        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());

        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        model.addAttribute("cartItems", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));
        session = request.getSession();
        session.setAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
//        boolean xacNhanThongTin = false;
//        model.addAttribute("xacNhanThongTin", xacNhanThongTin);
        return "web/cart-login";
    }

    // them sp vao gio hang
    @GetMapping("add-to-cart/{id}")
    public String addToCart(Model model, @PathVariable("id") Integer id, HttpSession session) {
        ProductDetails productDetails = productDetailsService.findById(id);

        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");

            if (idAcount == null || idAcount == -1) { // check tk
                CartModel cartModel = new CartModel();
                cartModel.setIdProductDetail(productDetails.getId());
                cartModel.setName(productDetails.getName());
                cartModel.setPrice(Double.valueOf(productDetails.getCategory().getProductLine().getPrice()));
                cartModelService.addCart(cartModel);
                return "redirect:/home/hien-thi";
            } else {
                // kiemr tra xem tk vừa đăng nhập vào đã có sản phẩm trong giỏ hàng hay chưa
                // nếu chưa có thì tạo mới - còn nếu có rồi thì thêm sản phẩm vào giỏ hàng

                // kieemr tra gio hangf thong qua id tk, id tk sẽ đc gán có code customer
//                List<Bill> bills = billService.findByCode(String.valueOf(idAcount));

                //lay ra doi tuong khach hang cos account
                Customer customer = customerService.findByCode(String.valueOf(idAcount));

                //neu null thi taoj moi customer -  cos roi thi bo qua
                if (customer == null) {
                    Customer customerNew = new Customer();
                    customerNew.setCode(String.valueOf(idAcount));
                    customerService.save(customerNew);
                    customer = customerNew;
                }

                List<Cart> carts = cartService.findByIdCustomer(String.valueOf(customer.getId()));
                if (carts.size() == 0) {
                    //- nếu == null tức là hiện tại tk chưa có giỏ hàng, nên khi add sản phẩm ta sẽ tạo giỏ hàng
                    // taoj gio hang
                    Cart cart = new Cart();
                    cart.setCustomer(customerService.findByCode(String.valueOf(idAcount)));
                    cart.setDateCreate(new Date());
                    String codeCart = UUID.randomUUID().toString();
                    cart.setCode(codeCart);
                    cart.setStatus(0);
                    cartService.add(cart);
                }


                // laays ra gio hang chi tiet
                Customer customer1 = customerService.findByCode(String.valueOf(idAcount));
                Cart cart = cartService.findByIdCustomerAndStatus(customer1.getId().toString(), 0);
                if (carts.size() !=0 && cart != null) {

                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProductDetails(productDetails);
                    cartDetail.setUnitPrice(productDetails.getCategory().getProductLine().getPrice());
                    cartDetailService.save(cartDetail);
                }
            }
        return "redirect:/home/hien-thi-log-in";
    }

    //hien thi form xac nhan thoong tin
    @GetMapping("thong-tin-thanh-toan")
    public String xacNhanThongTin(Model model) {
        model.addAttribute("cartItems", cartModelService.getAll());
        model.addAttribute("sumCartItem", cartModelService.getAll().size());
        model.addAttribute("totalPaymentAmount", cartModelService.getTotalPaymentAmount());
        boolean xacNhanThongTin = true;
        model.addAttribute("xacNhanThongTin", xacNhanThongTin);
        return "web/cart";
    }
    // hien thi gio hang
    @GetMapping("gio-hang")
    public String gioHang(Model model){
         return "redirect:/cart/hien-thi";
    }
    // hien thi gio hang
    @GetMapping("gio-hang-log-in")
    public String gioHangLogin(Model model){
        return "redirect:/cart/hien-thi-log-in";
    }

    /// log in
    @GetMapping("thong-tin-thanh-toan-login")
    public String viewThongTinThanhToan(Model model, HttpSession session){
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());

        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        model.addAttribute("cartItems", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        boolean xacNhanThongTin = true;
        model.addAttribute("xacNhanThongTin", xacNhanThongTin);

        return "web/cart-login";
    }

    @GetMapping("thong-tin-account-login")
    public String viewThongTinaccount(Model model, HttpSession session){
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());

        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        model.addAttribute("cartItems", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        boolean xacNhanThongTin = true;
        model.addAttribute("xacNhanThongTin", xacNhanThongTin);
        if (customer.getFullName()!=null && customer.getPhoneNumber()!=null && customer.getAdrress()!=null){
            model.addAttribute("hoVaTenThanhToan", customer.getFullName());
            model.addAttribute("sdtThanhToan", customer.getPhoneNumber());
            model.addAttribute("diaChiThanhToan", customer.getAdrress());
        }else {
            boolean xacNhanThongTinAccount = true;
            model.addAttribute("xacNhanThongTinAccount", xacNhanThongTinAccount);
        }
        return "web/cart-login";
    }

    @GetMapping("remove/{id}")
    public String remove(Model model, @PathVariable("id")Integer id){
        cartModelService.remove(id);
        return "redirect:/cart/hien-thi";
    }

    @GetMapping("login-remove/{id}")
    public String removeLogin(Model model, @PathVariable("id")Integer id){
        cartDetailService.remove(id);
        return "redirect:/cart/hien-thi-log-in";
    }
}
