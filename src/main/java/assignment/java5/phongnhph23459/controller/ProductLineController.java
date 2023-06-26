package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Manufacturer;
import assignment.java5.phongnhph23459.entity.ProductLine;
import assignment.java5.phongnhph23459.service.ProductLineService;
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
@RequestMapping("/productline/")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;

    @GetMapping("hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("productline", new ProductLine());
        List<ProductLine> productLines = productLineService.getAll();
        model.addAttribute("productLines", productLines);
        model.addAttribute("manufacturers", productLineService.getAllManufacturer());
        return "productline/productline-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("productline") ProductLine productLine) {
        productLineService.save(productLine);
        return "redirect:/productline/hien-thi";
    }

    // xoa du lieu
    @GetMapping("remove/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        productLineService.delete(id);
        return "redirect:/productline/hien-thi";
    }

    // hien thi du lieu len input
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        ProductLine productLine = productLineService.findById(id);
        model.addAttribute("productline", productLine);
        model.addAttribute("selectedManufacturer", productLine.getManufacturer().getId());
        // lay id Manufacturer ddeer qua the select ben productline-trang-chu de kiem tra va selected

        model.addAttribute("manufacturers", productLineService.getAllManufacturer());
        model.addAttribute("productLines", productLineService.getAll());

        return "productline/productline-trang-chu";
    }

    // hien thi du lieu len input view update
    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        ProductLine productLine = productLineService.findById(id);
        model.addAttribute("productline", productLine);
        model.addAttribute("selectedManufacturer", productLine.getManufacturer().getId());
        // lay id Manufacturer ddeer qua the select ben productline-trang-chu de kiem tra va selected
        model.addAttribute("manufacturers", productLineService.getAllManufacturer());

        return "/productline/productline-view-update";
    }
    // hien thi du lieu len input view update
    @PostMapping("update")
    public String viewUpdate(Model model, @ModelAttribute("productline")ProductLine productLine) {
        productLineService.update(productLine);
        return "redirect:/productline/hien-thi";
    }

}
