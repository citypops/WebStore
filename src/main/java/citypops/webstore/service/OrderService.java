package citypops.webstore.service;

import citypops.webstore.domain.Cart;
import citypops.webstore.domain.Order;
import citypops.webstore.domain.OrderItem;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    Order getOrderById(String orderId);
    Order createOrder(Cart cart);
    void addOrder(Order order);
    void addOrderItem(String orderId, String productId, int quantity);
    void updateOrderItem(String orderId, String productId, int quantity);
    void deleteOrderItem(String orderId, String productId);
    void realiseOrder(String orderId);
    void deleteOrder(String orderId);
}
