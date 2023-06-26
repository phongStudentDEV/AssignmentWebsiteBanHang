package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Manufacturer;
import assignment.java5.phongnhph23459.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manufacturer/")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturer/manufacturer-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("manufacturer") Manufacturer manufacturer) {

        manufacturerService.save(manufacturer);
        return "redirect:/manufacturer/hien-thi";
    }

    // xoa du lieu
    @GetMapping("remove/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        manufacturerService.delete(id);
        return "redirect:/manufacturer/hien-thi";
    }

    // hien thi du lieu len input
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Manufacturer manufacturer = manufacturerService.finById(id);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("manufacturers", manufacturerService.getAll());

        return "/manufacturer/manufacturer-trang-chu";
    }

    // hien thi du lieu len input view update
    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        Manufacturer manufacturer = manufacturerService.finById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "/manufacturer/manufacturer-view-update";
    }
    // hien thi du lieu len input view update
    @PostMapping("update")
    public String viewUpdate(Model model, @ModelAttribute("manufacturer")Manufacturer manufacturer) {
        manufacturerService.update(manufacturer);
        return "redirect:/manufacturer/hien-thi";
    }
}
