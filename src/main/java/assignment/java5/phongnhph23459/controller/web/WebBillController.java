package assignment.java5.phongnhph23459.controller.web;


import assignment.java5.phongnhph23459.entity.Bill;
import assignment.java5.phongnhph23459.entity.BillProduct;
import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.entity.CartDetail;
import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.service.BillProductService;
import assignment.java5.phongnhph23459.service.BillService;
import assignment.java5.phongnhph23459.service.CartDetailService;
import assignment.java5.phongnhph23459.service.CartModelService;
import assignment.java5.phongnhph23459.service.CartService;
import assignment.java5.phongnhph23459.service.CustomerService;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bill/")
public class WebBillController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartModelService cartModelService;

    @Autowired
    private BillService billService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private BillProductService billProductService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;


    // k log in
    @GetMapping("xac-nha-thanh-toan")
    public String viewBill(Model model, @RequestParam("hoVaTenThanhToan") String hoVaTenThanhToan,
                           @RequestParam("sdtThanhToan") String sdtThanhToan,
                           @RequestParam("diaChiThanhToan") String diaChiThanhToan) {

        model.addAttribute("hoVaTenThanhToan", hoVaTenThanhToan);
        model.addAttribute("sdtThanhToan", sdtThanhToan);
        model.addAttribute("diaChiThanhToan", diaChiThanhToan);

        List<CartModel> cartModelList = cartModelService.listCartItem();
        model.addAttribute("cartModelList", cartModelList);

        return "web/bill";
    }

    @PostMapping("thanh-toan")
    public String thanToan(Model model, @RequestParam("hoVaTenThanhToan") String hoVaTenThanhToan,
                           @RequestParam("sdtThanhToan") String sdtThanhToan,
                           @RequestParam("diaChiThanhToan") String diaChiThanhToan) {
//        if () { check tk truoc
//        }
        // neu chua co tai khoan

        // taoj moi khach hang
        Customer customer = new Customer();
        customer.setCode(sdtThanhToan);
        customer.setAdrress(diaChiThanhToan);
        customer.setFullName(hoVaTenThanhToan);
        customer.setPhoneNumber(sdtThanhToan);

        customerService.save(customer);

        // taoj moi bill
        Bill bill = new Bill();
        bill.setAdrress(diaChiThanhToan);
        bill.setCode(hoVaTenThanhToan);
        bill.setPhoneNumber(sdtThanhToan);
        bill.setPurchaseDate(new Date());
        bill.setCustomer(customerService.findById(customer.getId()));

        billService.save(bill);


        // taoj moi BillProduct

        for (int i = 0; i < cartModelService.listCartItem().size(); i++) {
            BillProduct billProduct = new BillProduct();
            billProduct.setNuber(cartModelService.listCartItem().get(i).getQuantity());

            Float price = cartModelService.listCartItem().get(i).getPrice().floatValue();
            billProduct.setUnitPrice(price);

            Float amount = cartModelService.listCartItem().get(i).getQuantity().floatValue() * price;
            billProduct.setAmount(amount);

            Integer idProductDetail = cartModelService.listCartItem().get(i).getIdProductDetail();
            billProduct.setProductDetails(productDetailsService.findById(idProductDetail));


            billProduct.setBill(billService.findById(bill.getId())); // lays ra doi tuong bill
//            billProduct.setBill(bill); // lays ra doi tuong bill


            billProductService.save(billProduct);

            //update so luong
            productDetailsService.updateQuantity(billProduct.getAmount().intValue(), idProductDetail);
//            productDetailsService.update(productDetails);
        }

        //thanh toan xong roi xoa trong gio hang
        cartModelService.removeAll();

        return "redirect:/home/hien-thi";
    }

    // log in
    //taoj bill
    @GetMapping("xac-nha-thanh-toan-login")
    public String viewBillLogin(Model model, HttpSession session, @RequestParam("hoVaTenThanhToan") String hoVaTenThanhToan,
                                @RequestParam("sdtThanhToan") String sdtThanhToan,
                                @RequestParam("diaChiThanhToan") String diaChiThanhToan) {

        model.addAttribute("hoVaTenThanhToan", hoVaTenThanhToan);
        model.addAttribute("sdtThanhToan", sdtThanhToan);
        model.addAttribute("diaChiThanhToan", diaChiThanhToan);

        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());

        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()), 0);
        model.addAttribute("cartDetails", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        return "web/bill-login";
    }

    //    update Customer set Full_Name='phong nn', Adrress='ha noi', Phone_Number='1234567890'  where id =26;
    //thanh toans
    @PostMapping("thanh-toan-login")
    public String thanToanLogin(Model model, HttpSession session, @RequestParam("hoVaTenThanhToan") String hoVaTenThanhToan,
                                @RequestParam("sdtThanhToan") String sdtThanhToan,
                                @RequestParam("diaChiThanhToan") String diaChiThanhToan) {
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()), 0);
        model.addAttribute("cartDetails", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        customer.setFullName(hoVaTenThanhToan);
        customer.setAdrress(diaChiThanhToan);
        customer.setPhoneNumber(sdtThanhToan);

        customerService.update(customer);

        // taoj moi bill
        Bill bill = new Bill();
        bill.setAdrress(hoVaTenThanhToan);
        bill.setCode(hoVaTenThanhToan);
        bill.setPhoneNumber(sdtThanhToan);
        bill.setPurchaseDate(new Date());
        bill.setStatus(1);
        bill.setCustomer(customerService.findById(customer.getId()));

        billService.save(bill);

        // taoj moi BillProduct
        List<CartDetail> listcartDetails = cartDetailService.getAllIdcart(cart.getId());
        for (int i = 0; i < listcartDetails.size(); i++) {
            BillProduct billProduct = new BillProduct();
            billProduct.setNuber(listcartDetails.get(i).getNumber());

            Float price = listcartDetails.get(i).getUnitPrice();
            billProduct.setUnitPrice(price);

            Float amount = price * listcartDetails.get(i).getNumber();
            billProduct.setAmount(amount);
            billProduct.setStatus(1);

            billProduct.setProductDetails(listcartDetails.get(i).getProductDetails());
            System.out.println(listcartDetails.get(i).getProductDetails().getId() + " 0000000000=====>");

            billProduct.setBill(billService.findById(bill.getId())); // lays ra doi tuong bill

            billProductService.save(billProduct);

//            update so luong
            productDetailsService.updateQuantity(billProduct.getNuber(), listcartDetails.get(i).getProductDetails().getId());
        }
        cart.setStatus(1);
        cartService.update(cart);


        //- nếu == null tức là hiện tại tk chưa có giỏ hàng, nên khi add sản phẩm ta sẽ tạo giỏ hàng
        // taoj gio hang
        Cart cart1 = new Cart();
        cart1.setCustomer(customerService.findByCode(String.valueOf(idAcount)));
        cart1.setDateCreate(new Date());
        String codeCart = UUID.randomUUID().toString();
        cart1.setCode(codeCart);
        cart1.setStatus(0);
        cartService.add(cart1);


        return "redirect:/home/hien-thi-log-in";
    }
}
