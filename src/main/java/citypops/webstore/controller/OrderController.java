package citypops.webstore.controller;

import citypops.webstore.domain.Cart;
import citypops.webstore.domain.Order;
import citypops.webstore.exception.FieldNotValidException;
import citypops.webstore.service.CartService;
import citypops.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable("orderId") String orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/process")
    @PreAuthorize("isAuthenticated()")
    public String processOrder(HttpSession session) {

        Cart cart = cartService.getCart(session.getId());
        if(cart.getCartItems().isEmpty()) {
            throw new FieldNotValidException("You have to add at least one product to the cart.");
        }
        Order order = orderService.createOrder(cart);
        orderService.addOrder(order);
        cartService.deleteCart(session.getId());
        return order.getOrderId();
    }

    @GetMapping("/realise/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void realiseOrder(@PathVariable("orderId") String orderId) {
        orderService.realiseOrder(orderId);
    }

    @PostMapping("/item")
    @PreAuthorize("hasRole('ADMIN')")
    public void addOrderItem(@RequestParam("orderId") String orderId,
                             @RequestParam("productId") String productId,
                             @RequestParam("quantity") int quantity) {

        orderService.addOrderItem(orderId, productId, quantity);
    }

    @PutMapping("/item")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrderItem(@RequestParam("orderId") String orderId,
                                @RequestParam("productId") String productId,
                                @RequestParam("quantity") int quantity) {

        orderService.updateOrderItem(orderId, productId, quantity);
    }

    /*
     *  If using Tomcat, it may be necessary to configure 'parseBodyMethods' in server.xml
     *  to have @RequestParam working with HTTP DELETE; something like:
     *          <Connector port="8080" protocol="HTTP/1.1"
     *                connectionTimeout="20000"
     *                redirectPort="8443"
     *                parseBodyMethods="POST,PUT,DELETE"
     *                URIEncoding="UTF-8" />
     */
    @DeleteMapping("/item")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrderItem(@RequestParam("orderId") String orderId,
                                @RequestParam("productId") String productId) {

        orderService.deleteOrderItem(orderId, productId);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyResult() {
        return new ResponseEntity<>("There's no such product.", HttpStatus.NOT_FOUND);
    }


}