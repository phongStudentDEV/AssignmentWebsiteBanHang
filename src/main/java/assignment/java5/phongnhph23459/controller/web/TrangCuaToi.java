package assignment.java5.phongnhph23459.controller.web;

import assignment.java5.phongnhph23459.entity.Bill;
import assignment.java5.phongnhph23459.entity.BillProduct;
import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.service.BillProductService;
import assignment.java5.phongnhph23459.service.BillService;
import assignment.java5.phongnhph23459.service.CartDetailService;
import assignment.java5.phongnhph23459.service.CartModelService;
import assignment.java5.phongnhph23459.service.CartService;
import assignment.java5.phongnhph23459.service.CustomerService;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/trang-cua-toi/")
public class TrangCuaToi {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillProductService billProductService;

    @GetMapping("hien-thi")
    public String view(Model model){
        return "redirect:/home/trang-cua-toi-log-in";
    }


    @GetMapping("hien-thi-don-hang")
    public String hienThiDonHang(Model model, HttpSession session,
                                 @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo){
        boolean checkLogin = true;
        model.addAttribute("checkLogin", checkLogin);
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        Page<Bill> billPage = billService.getAllByIdCustomer(customer.getId(), pageNo, 5);
        model.addAttribute("bills", billPage.getContent());
        model.addAttribute("bill", billPage);
        model.addAttribute("billTotalPages", billPage.getTotalPages());


        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
//        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        return "web/trang-cua-toi-don-hang";
    }

    @GetMapping("detail/{id}")
    public String detailHienThiDonHang(Model model, HttpSession session,
                                       @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                       @PathVariable("id")Integer idBill){
        boolean checkLogin = true;
        model.addAttribute("checkLogin", checkLogin);
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        List<BillProduct> billProducts = billProductService.findByIdBill(idBill);
        model.addAttribute("billProducts", billProducts);

        Page<Bill> billPage = billService.getAllByIdCustomer(customer.getId(), pageNo, 5);
        model.addAttribute("bills", billPage.getContent());
        model.addAttribute("bill", billPage);
        model.addAttribute("billTotalPages", billPage.getTotalPages());
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());


        Boolean detailCheck = true;
        model.addAttribute("detailCheck", detailCheck);



        return "web/trang-cua-toi-don-hang";
    }

    @GetMapping("cloe")
    public String cloeDetailHienThiDonHang(Model model, HttpSession session,
                                       @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo){
        boolean checkLogin = true;
        model.addAttribute("checkLogin", checkLogin);
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        Customer customer = customerService.findByCode(idAcount.toString());
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()),0);
        Page<Bill> billPage = billService.getAllByIdCustomer(customer.getId(), pageNo, 5);
        model.addAttribute("bills", billPage.getContent());
        model.addAttribute("bill", billPage);
        model.addAttribute("billTotalPages", billPage.getTotalPages());
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());

        Boolean detailCheck = false;
        model.addAttribute("detailCheck", detailCheck);



        return "web/trang-cua-toi-don-hang";
    }

}
