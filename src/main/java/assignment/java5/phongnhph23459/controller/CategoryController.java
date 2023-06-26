package assignment.java5.phongnhph23459.controller;

import assignment.java5.phongnhph23459.entity.Capacity;
import assignment.java5.phongnhph23459.entity.Category;
import assignment.java5.phongnhph23459.entity.ProductLine;
import assignment.java5.phongnhph23459.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("category", new Capacity());
        model.addAttribute("colors", categoryService.getAllColor());
        model.addAttribute("capacitys", categoryService.getAllCapacity());
        model.addAttribute("productLines", categoryService.getAllProductline());

        model.addAttribute("categorys", categoryService.getAll());
        return "category/category-trang-chu";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category/hien-thi";
    }
    // xoa du lieu
    @GetMapping("remove/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/category/hien-thi";
    }

    // hien thi du lieu len input
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        //taoj doi tuong cho form thymeleaf (th:oject="category") o trang html
        model.addAttribute("selectedColor", category.getColor().getId());
        model.addAttribute("selectedCapacity", category.getCapacity().getId());
        model.addAttribute("selectedProductLine", category.getProductLine().getId());

        // lay id Manufacturer ddeer qua the select ben productline-trang-chu de kiem tra va selected

        model.addAttribute("colors", categoryService.getAllColor());
        model.addAttribute("capacitys", categoryService.getAllCapacity());
        model.addAttribute("productLines", categoryService.getAllProductline());
        model.addAttribute("categorys", categoryService.getAll());

        return "category/category-trang-chu";
    }

//    // hien thi du lieu len input view update
    @GetMapping("view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);

        // lay id selectedColor ddeer qua the select ben category-trang-chu de kiem tra va selected
        model.addAttribute("selectedColor", category.getColor().getId());
        model.addAttribute("selectedCapacity", category.getCapacity().getId());
        model.addAttribute("selectedProductLine", category.getProductLine().getId());


        model.addAttribute("colors", categoryService.getAllColor());
        model.addAttribute("capacitys", categoryService.getAllCapacity());
        model.addAttribute("productLines", categoryService.getAllProductline());

        return "/category/category-view-update";
    }
    // hien thi du lieu len input view update
    @PostMapping("update")
    public String viewUpdate(Model model, @ModelAttribute("category")Category category) {
        categoryService.update(category);
        return "redirect:/category/hien-thi";
    }


}
