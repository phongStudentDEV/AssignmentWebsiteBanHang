package assignment.java5.phongnhph23459.controller.web;

import assignment.java5.phongnhph23459.entity.Cart;
import assignment.java5.phongnhph23459.entity.CartDetail;
import assignment.java5.phongnhph23459.entity.Customer;
import assignment.java5.phongnhph23459.entity.CustomerAccount;
import assignment.java5.phongnhph23459.entity.ProductDetails;
import assignment.java5.phongnhph23459.entity.ProductLine;
import assignment.java5.phongnhph23459.service.CartDetailService;
import assignment.java5.phongnhph23459.service.CartModelService;
import assignment.java5.phongnhph23459.service.CartService;
import assignment.java5.phongnhph23459.service.CustomerAccountService;
import assignment.java5.phongnhph23459.service.CustomerService;
import assignment.java5.phongnhph23459.service.ProductDetailsService;
import assignment.java5.phongnhph23459.service.ProductLineService;
import assignment.java5.phongnhph23459.viewModel.CartModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home/")
public class ViewHomeController {
    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartModelService cartModelService;

    @Autowired
    private ProductLineService productLineService;

    @GetMapping("hien-thi")
    public String hienThi(Model model, HttpSession session,
                          @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {

        Page<ProductDetails> productDetailsPage = productDetailsService.pageAll(pageNo, 12);
        boolean checkLogin = false; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);

        model.addAttribute("products", productDetailsPage); // list getAll ddeer chay foreach cho sp
        model.addAttribute("product", productDetailsPage); // list getAll để lấy số trang đang đúng hiện tại
        model.addAttribute("pageNumber", pageNo); // laays so trang hien tai
        model.addAttribute("pageProduct", productDetailsPage.getTotalPages()); // lấy ra tổng số trang khi phân trang

        //hien thi top 4 sp gia cao
//        List<ProductLine> productLines = productLineService.findTop4ProductLine();
//        model.addAttribute("productLines", productLines);


//        Integer sumCartItem = (Integer) session.getAttribute("sumCartItem");
        model.addAttribute("sumCartItem", cartModelService.listCartItem().size());
        return "web/index";

    }

    @GetMapping("hien-thi-log-in")
    public String loginView(Model model, HttpSession session,
                            @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {

        Page<ProductDetails> productDetailsPage = productDetailsService.pageAll(pageNo, 12);
        boolean checkLogin = true; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);

        model.addAttribute("products", productDetailsPage); // list getAll ddeer chay foreach cho sp
        model.addAttribute("product", productDetailsPage); // list getAll để lấy số trang đang đúng hiện tại
        model.addAttribute("pageNumber", pageNo); // laays so trang hien tai
        model.addAttribute("pageProduct", productDetailsPage.getTotalPages()); // lấy ra tổng số trang khi phân trang

        //lay top 4 sp gia cap
//        List<ProductLine> productLines = productLineService.findTop4ProductLine();
//        model.addAttribute("productLines", productLines);

        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        System.out.println(idAcount + " ajbdjhabsdhbahjdbahjs=======================>");

        if (customerService.findByCode(idAcount.toString()) == null) {
            Customer customerNew = new Customer();
            customerNew.setCode(String.valueOf(idAcount));
            customerService.save(customerNew);
        }
        Customer customer = customerService.findByCode(idAcount.toString());

        if (cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()), 0) == null) {
            //- nếu == null tức là hiện tại tk chưa có giỏ hàng, nên khi add sản phẩm ta sẽ tạo giỏ hàng
            // taoj gio hang
            Cart cartNew = new Cart();
            cartNew.setCustomer(customerService.findByCode(String.valueOf(idAcount)));
            cartNew.setDateCreate(new Date());
            String codeCart = UUID.randomUUID().toString();
            cartNew.setCode(codeCart);
            cartNew.setStatus(0);
            cartService.add(cartNew);
        }
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()), 0);
        model.addAttribute("cartItems", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());

        return "web/index";
    }

    @GetMapping("gioi-thieu")
    public String hienThiSanPham(Model model, HttpSession session) {

        return "web/gioi-thieu";
    }

    @GetMapping("view-login")
    public String viewLogIn(Model model, HttpSession session) {
        model.addAttribute("sumCartItem", "");
        session.setAttribute("idCustomerAccount", -1);

        boolean checkLogin = false; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);
        return "web/login";
    }

    @GetMapping("login")
    public String logIn(Model model, @RequestParam("emailLogin") String emailLogin,
                        @RequestParam("passwordLogin") String passwordLogin,
                        HttpSession session, HttpServletRequest request) {

        CustomerAccount customerAccount = customerAccountService.findByEmailAndPassword(
                emailLogin.trim(),
                passwordLogin.trim());

        if (customerAccount == null) {
            boolean checkLogin = false;
            model.addAttribute("checkAcountLogin", checkLogin);//messenger khi dang nhap sai tk
            model.addAttribute("checkLogin", checkLogin);//kiểm tra để hiện thị nút đăng nhập hay đăng xuất

            model.addAttribute("emailLogin", emailLogin);
            model.addAttribute("passwordLogin", passwordLogin);
            return "web/login";
        }
        //lay ra id account cho vao session để đưa id sang trang gio hang
//        model.addAttribute("customerAccount", customerAccount.getId());
        session = request.getSession();
        session.setAttribute("idCustomerAccount", customerAccount.getId());
//        System.out.println(customerAccount.getId() + " =======>");
        //kiểm tra giỏ hàng đây nữa


        return "redirect:/home/hien-thi-log-in";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        boolean checkLogin = false; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);
        model.addAttribute("customerAccount", "");
        return "redirect:/home/view-login";
    }


    @GetMapping("trang-cua-toi")
    public String trangCuaToi(Model model) {
        boolean checkLogin = false; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);
        return "redirect:/home/view-login";
    }

    @GetMapping("trang-cua-toi-log-in")
    public String trangCuaToiLogin(Model model, HttpSession session) {

        boolean checkLogin = true; // check nếu đăng nhập rồi thì k cho đăng nhập nữa, bắt đăng xuất
        model.addAttribute("checkLogin", checkLogin);
        Integer idAcount = (Integer) session.getAttribute("idCustomerAccount");
        CustomerAccount customerAccount = customerAccountService.findById(idAcount);
        model.addAttribute("emailAustomerAccount", customerAccount.getEmail());

        Customer customer = customerService.findByCode(idAcount.toString());
        model.addAttribute("customer", customer);
        Cart cart = cartService.findByIdCustomerAndStatus(String.valueOf(customer.getId()), 0);
        model.addAttribute("cartDetails", cartDetailService.getAllIdcart(cart.getId()));
        model.addAttribute("sumCartItem", cartDetailService.getAllIdcart(cart.getId()).size());
        model.addAttribute("totalPaymentAmount", cartDetailService.getTotalPaymentAmount(cartDetailService.getAllIdcart(cart.getId())));

        return "web/trang-cua-toi";
    }


}
