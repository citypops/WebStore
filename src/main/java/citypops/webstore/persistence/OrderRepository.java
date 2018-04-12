package citypops.webstore.persistence;

import citypops.webstore.domain.Order;

import java.util.List;

public interface OrderRepository {

    List<Order> getAllOrders();
    Order getOrderById(String orderId);
    void addOrder(Order order);
    void addOrderItem(String orderId, String productId, int quantity);
    void updateOrderItem(String orderId, String productId, int quantity);
    void deleteOrderItem(String orderId, String productId);
    void realiseOrder(String orderId);
    void deleteOrder(String orderId);
}