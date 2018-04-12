package citypops.webstore.persistence;

import citypops.webstore.domain.Cart;

public interface CartRepository {

    Cart getCart(String cartId);
    void deleteCart(String cartId);
}
