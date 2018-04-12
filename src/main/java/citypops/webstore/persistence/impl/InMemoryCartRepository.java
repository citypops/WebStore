package citypops.webstore.persistence.impl;

import citypops.webstore.domain.Cart;
import citypops.webstore.persistence.CartRepository;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryCartRepository implements CartRepository {

    private static Map<String, Cart> carts;

    public InMemoryCartRepository() {
        if(carts == null) {
            carts = new HashMap<>();
        }
    }

    @Override
    public Cart getCart(String cartId) {

        Cart cart;
        if((cart = carts.get(cartId)) == null) {
            cart = new Cart(cartId);
            carts.put(cartId, cart);
        }
        return cart;
    }

    @Override
    public void deleteCart(String cartId) {

        if(carts.get(cartId) == null) {
            throw new IllegalArgumentException(String.format("Cart with id %s does not exist", cartId));
        } else {
            carts.remove(cartId);
        }
    }
}