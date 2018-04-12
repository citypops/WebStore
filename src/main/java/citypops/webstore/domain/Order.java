package citypops.webstore.domain;

import org.springframework.security.core.context.SecurityContextHolder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order {

    private final String orderId;
    private List<OrderItem> orderItems;
    private final String username;
    private Timestamp created;
    private boolean state;

    public Order(Cart cart) {
        orderItems = new ArrayList<>();
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        this.orderId = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss_").format(new Date()) + cart.getCartId();

        for(CartItem cartItem : cart.getCartItems().values()) {
            orderItems.add(cartItem.convertToOrderItem());
        }
    }

    public Order(String orderId, List<OrderItem> orderItems, String username, Timestamp created, boolean state) {
        this.orderId = orderId;
        this.username = username;
        this.orderItems = orderItems;
        this.created = created;
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Timestamp getCreated() {
        return created;
    }

    public boolean getState() {
        return state;
    }
}