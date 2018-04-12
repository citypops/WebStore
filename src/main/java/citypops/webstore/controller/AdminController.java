package citypops.webstore.controller;

import citypops.webstore.domain.Order;
import citypops.webstore.domain.Product;
import citypops.webstore.domain.User;
import citypops.webstore.service.OrderService;
import citypops.webstore.service.ProductService;
import citypops.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    private ProductService productService;
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public AdminController(ProductService productService,
                          UserService userService,
                          OrderService orderService) {

        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/admin/accounts")
    public String accountsManager() {
        return "manage_accounts";
    }

    @GetMapping("/admin/accounts/{username}")
    public String accountEditPage(
            @PathVariable("username") String username, Model model) {

        User user = userService.getUserById(username);
        model.addAttribute("user", user);
        return "edit_account";
    }

    @GetMapping("/admin/orders")
    public String ordersManager() {
        return "manage_orders";
    }

    @GetMapping("/admin/orders/{orderId}")
    public String orderEditPage(
            @PathVariable("orderId") String orderId, Model model) {

        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        User user = userService.getUserById(order.getUsername());
        model.addAttribute("user", user);
        return "edit_order";
    }

    @GetMapping({"/admin/products", "admin"})
    public String productsManager() {
        return "manage_products";
    }

    @GetMapping("/admin/products/addproduct")
    public String addProduct() {
        return "add_product";
    }

    @GetMapping("/admin/products/{productId}")
    public String productEditPage(
            @PathVariable("productId") String productId, Model model) {

        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "edit_product";
    }
}