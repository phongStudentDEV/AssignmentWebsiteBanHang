package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Color;
import assignment.java5.phongnhph23459.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/color/")
public class ColorController {
    @Autowired
    private ColorService colorService;

    // hien thi du lieu len table
    @GetMapping("hien-thi")
    public String viewColor(Model model) {
        List<Color> colors = colorService.getAll();
        model.addAttribute("colors", colors);
        return "/color/color-trang-chu";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model) {
        model.addAttribute("color", new Color());
        return "/color/color-add";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("color") Color color) {
        color.setDateCreate(new Date());
        color.setDateCorrect(new Date());
        colorService.save(color);
        return "redirect:/color/hien-thi";
    }

    // xoa du lieu
    @GetMapping("remove/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        colorService.delete(id);
        return "redirect:/color/hien-thi";
    }

    // hien thi du lieu len input
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Color color = colorService.finById(id);
        model.addAttribute("color", color);
        return "/color/color-add";
    }

    // hien thi du lieu len input view update
    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        Color color = colorService.finById(id);
        model.addAttribute("color", color);
        return "/color/color-update";
    }
    // hien thi du lieu len input view update
    @PostMapping("update")
    public String viewUpdate(Model model, @ModelAttribute("color")Color color) {
        colorService.update(color);
        return "redirect:/color/hien-thi";
    }


}
