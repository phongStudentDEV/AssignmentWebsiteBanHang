package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Capacity;
import assignment.java5.phongnhph23459.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/capacity/")
public class CapacityController {
    @Autowired
    private CapacityService capacityService;

    @GetMapping("hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("capacityForm", new Capacity());
        List<Capacity> capacities = capacityService.getAll();
        model.addAttribute("capacities", capacities);
        return "capacity/capacity-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("capacityForm") Capacity capacity) {
        capacityService.save(capacity);
        return "redirect:/capacity/hien-thi";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        Capacity capacity = capacityService.findById(id);
        model.addAttribute("capacity", capacity);
        return "capacity/capacity-view-update";
    }

    @GetMapping("edit/{id}")
    public String edir(Model model, @PathVariable("id") Integer id) {
        Capacity capacity = capacityService.findById(id);
        model.addAttribute("capacityForm", capacity);
        model.addAttribute("capacities", capacityService.getAll());
        return "capacity/capacity-trang-chu";
    }

    @GetMapping("remove/{id}")
    public String delete(@PathVariable("id") Integer id) {
        capacityService.delete(id);
        return "redirect:/capacity/hien-thi";
    }

    @PostMapping("update")
    public String update(Model model, @ModelAttribute("capacity") Capacity capacity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("capacity", capacity);
            return "capacity/capacity-view-update";
        }
        capacityService.update(capacity);
        return "redirect:/capacity/hien-thi";
    }

}
