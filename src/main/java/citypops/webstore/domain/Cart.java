package citypops.webstore.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final String cartId;
    private final Map<String, CartItem> cartItems;
    private BigDecimal nettoSum;
    private BigDecimal bruttoSum;

    public Cart(String cartId) {
        this.cartId = cartId;
        cartItems = new HashMap<>();
        nettoSum = BigDecimal.ZERO;
        bruttoSum = BigDecimal.ZERO;
    }

    public void addCartItem(CartItem cartItem) {
        String productId = cartItem.getProduct().getProductId();
        if(cartItems.get(productId) == null) {
            cartItems.put(productId, cartItem);
        } else {
            CartItem existingItem = cartItems.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
            cartItems.put(productId, existingItem);
        }
        updateSums();
    }

    public void updateCartItem(CartItem cartItem) {
        String productId = cartItem.getProduct().getProductId();
        if(cartItems.get(productId) != null) {
            cartItems.put(productId, cartItem);
        }
        updateSums();
    }

    public void removeCartItem(String productId) {
        cartItems.remove(productId);
        updateSums();
    }

    private void updateSums() {
        nettoSum = BigDecimal.ZERO;
        bruttoSum = BigDecimal.ZERO;
        for(CartItem item : cartItems.values()) {
            nettoSum = nettoSum.add(item.getNettoSum());
            bruttoSum = bruttoSum.add(item.getBruttoSum());
        }
        if(nettoSum.doubleValue() < 0) {
            nettoSum = BigDecimal.ZERO;
        }
        if(bruttoSum.doubleValue() < 0) {
            bruttoSum = BigDecimal.ZERO;
        }
    }

    public String getCartId() {
        return cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getBruttoSum() {
        return bruttoSum;
    }

    public BigDecimal getNettoSum() {
        return nettoSum;
    }
}
