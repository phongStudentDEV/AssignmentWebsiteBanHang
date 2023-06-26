package assignment.java5.phongnhph23459.controller.web;


import assignment.java5.phongnhph23459.entity.CustomerAccount;
import assignment.java5.phongnhph23459.entity.Roles;
import assignment.java5.phongnhph23459.service.CustomerAccountService;
import assignment.java5.phongnhph23459.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/")
public class AccountController {
    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("view-signup")
    public String viewSignup(Model model) {
        model.addAttribute("customerAccount", new CustomerAccount());
        return "web/signup";
    }

    @GetMapping("view-signup-success")
    public String viewSignupSuccess(Model model) {
        model.addAttribute("customerAccount", new CustomerAccount());
        boolean checkSignUp = true;
        model.addAttribute("signUpSuccess", "Signup Success");
        model.addAttribute("checkSignUp", checkSignUp);
        return "web/signup";
    }

    @PostMapping("signup")
    public String Signup(Model model, @ModelAttribute("customerAccount") CustomerAccount customerAccount,
                         @RequestParam("confirmPassword") String confirmPassword) {
        if (!confirmPassword.equals(customerAccount.getPassword())) {
            model.addAttribute("customerAccount", customerAccount);
            boolean checkSignUp = false;
            model.addAttribute("signUpSuccess", "signup failed");
            model.addAttribute("checkSignUp", checkSignUp);
            return "web/signup";
        }
        customerAccountService.save(customerAccount);
        Roles roles = new Roles();
        roles.setName("Customer");
        roles.setCustomerAccount(customerAccountService.findById(customerAccount.getId()));
        rolesService.save(roles);
        model.addAttribute("customerAccount", new CustomerAccount());
        return "redirect:/account/view-signup-success";
    }
}
