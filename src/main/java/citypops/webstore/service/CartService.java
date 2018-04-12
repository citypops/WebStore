package citypops.webstore.service;

import citypops.webstore.domain.Cart;

public interface CartService {

    Cart getCart(String sessionId);
    void deleteCart(String cartId);
    void addCartItem(String cartId, String productId, int quantity);
    void updateCartItem(String cartId, String productId, int quantity);
    void removeCartItem(String cartId, String productId);
}
