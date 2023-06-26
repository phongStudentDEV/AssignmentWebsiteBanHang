package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("hien-thi")
    public String hienThi(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerService.getAll());
        return "customer/customer-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customer/hien-thi";
    }

    // xoa du lieu
    @GetMapping("remove/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        customerService.delete(id);
        return "redirect:/customer/hien-thi";
    }

    // hien thi du lieu len input
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("customers",customerService.getAll());
        return "/customer/customer-trang-chu";
    }

    // hien thi du lieu len input view update
    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/customer-view-update";
    }
    // update du lieu
    @PostMapping("update")
    public String viewUpdate(Model model, @ModelAttribute("customer")Customer customer) {
        customerService.update(customer);
        return "redirect:/customer/hien-thi";
    }
}
