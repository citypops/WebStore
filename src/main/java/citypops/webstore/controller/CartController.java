package citypops.webstore.controller;

import citypops.webstore.domain.Cart;
import citypops.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Cart getCart(HttpSession session) {
        return cartService.getCart(session.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public void deleteCart(HttpSession session) {
        cartService.deleteCart(session.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public void addCartItem(HttpSession session,
                            @RequestParam("productId") String productId,
                            @RequestParam("quantity") int quantity) {

        cartService.addCartItem(session.getId(), productId, quantity);
    }

    @PutMapping("/{productId}/{quantity}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void updateCartItem(HttpSession session,
                               @PathVariable("productId") String productId,
                               @PathVariable("quantity") int quantity) {

        cartService.updateCartItem(session.getId(), productId, quantity);
    }


    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void removeCartItem(HttpSession session,
                               @PathVariable("productId") String productId) {

        cartService.removeCartItem(session.getId(), productId);
    }
}