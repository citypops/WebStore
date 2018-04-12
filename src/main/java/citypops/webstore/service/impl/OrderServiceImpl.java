package citypops.webstore.service.impl;

import citypops.webstore.domain.Cart;
import citypops.webstore.domain.Order;
import citypops.webstore.persistence.OrderRepository;
import citypops.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public Order createOrder(Cart cart) {
        return new Order(cart);
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public void addOrderItem(String orderId, String productId, int quantity) {
        orderRepository.addOrderItem(orderId, productId, quantity);
    }

    @Override
    public void updateOrderItem(String orderId, String productId, int quantity) {
        orderRepository.updateOrderItem(orderId, productId, quantity);
    }

    @Override
    public void deleteOrderItem(String orderId, String productId) {
        orderRepository.deleteOrderItem(orderId, productId);
    }

    @Override
    public void realiseOrder(String orderId) {
        orderRepository.realiseOrder(orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteOrder(orderId);
    }
}
