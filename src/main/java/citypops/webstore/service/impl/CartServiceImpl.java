package citypops.webstore.service.impl;

import citypops.webstore.domain.Cart;
import citypops.webstore.domain.CartItem;
import citypops.webstore.domain.Product;
import citypops.webstore.exception.FieldNotValidException;
import citypops.webstore.persistence.CartRepository;
import citypops.webstore.service.CartService;
import citypops.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public Cart getCart(String sessionId) {
        return cartRepository.getCart(sessionId);
    }

    @Override
    public void deleteCart(String cartId) {
        cartRepository.deleteCart(cartId);
    }

    @Override
    public void addCartItem(String cartId, String productId, int quantity) {
        if(quantity < 1) throw new FieldNotValidException("Quantity must by at least 1.");
        Product product = productService.getProductById(productId);
        CartItem cartItem = new CartItem(product, quantity);
        cartRepository.getCart(cartId).addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(String cartId, String productId, int quantity) {
        if(quantity < 1) throw new FieldNotValidException("Quantity must by at least 1.");
        Product product = productService.getProductById(productId);
        CartItem cartItem = new CartItem(product, quantity);
        cartRepository.getCart(cartId).updateCartItem(cartItem);
    }

    @Override
    public void removeCartItem(String cartId, String productId) {
        cartRepository.getCart(cartId).removeCartItem(productId);
    }
}