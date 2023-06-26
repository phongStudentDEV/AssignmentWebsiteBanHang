package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.ProductDetails;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product-detail/")
public class ProductDetailsController {
    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("productDetail", new ProductDetails());

        List<ProductDetails> productDetailsList = productDetailsService.getAll();
        model.addAttribute("productDetailsList", productDetailsList);
        model.addAttribute("categorys", productDetailsService.getAllCategory());
        return "productdetails/product-detail-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("productDetail") ProductDetails productDetail) {

        productDetailsService.save(productDetail);
        return "redirect:/product-detail/hien-thi";
    }

    @GetMapping("remove/{id}")
    public String remove(Model model, @PathVariable("id") Integer id) {
        productDetailsService.delete(id);
        return "redirect:/product-detail/hien-thi";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        ProductDetails productDetail = productDetailsService.findById(id);
        model.addAttribute("productDetail", productDetail);

        model.addAttribute("selectedCategory", productDetail.getCategory().getId());
        List<ProductDetails> productDetailsList = productDetailsService.getAll();
        model.addAttribute("productDetailsList", productDetailsList);
        model.addAttribute("categorys", productDetailsService.getAllCategory());
        return "productdetails/product-detail-trang-chu";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        ProductDetails productDetail = productDetailsService.findById(id);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("selectedCategory", productDetail.getCategory().getId());
        model.addAttribute("categorys", productDetailsService.getAllCategory());

        return "productdetails/view-update-product-detail";
    }

    @PostMapping("update")
    public String update(Model model, @ModelAttribute("productDetail") ProductDetails productDetail) {
        productDetailsService.update(productDetail);
        return "redirect:/product-detail/hien-thi";
    }


}
