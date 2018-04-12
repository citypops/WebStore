package citypops.webstore.persistence.impl;

import citypops.webstore.domain.Order;
import citypops.webstore.domain.OrderItem;
import citypops.webstore.domain.Product;
import citypops.webstore.persistence.OrderRepository;
import citypops.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class OrderDAO implements OrderRepository {

    private JdbcTemplate jdbcTemplate;
    private ProductService productService;

    @Autowired
    public OrderDAO(DataSource dataSource, ProductService productService) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.productService = productService;
    }

    @Override
    public List<Order> getAllOrders() {

        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    @Override
    public Order getOrderById(String orderId) {

        String sql = "SELECT * FROM orders WHERE order_id=?";
        String sql2 = "SELECT * FROM order_items WHERE order_id=?";

        Order order = jdbcTemplate.queryForObject(sql, new OrderRowMapper(), orderId);
        List<OrderItem> items = jdbcTemplate.query(sql2, (rs, rowNum) -> new OrderItem(
                rs.getString("product_id"), rs.getInt("quantity"),
                rs.getBigDecimal("sold_price"), rs.getFloat("sold_tax")
        ), orderId);

        order.setOrderItems(items);
        return order;
    }

    @Override
    public void addOrder(Order order) {

        String orderId = order.getOrderId();
        String sql = "INSERT INTO orders (order_id,username) VALUES (?,?)";
        String sql2 = "INSERT INTO order_items (product_id,quantity,sold_price," +
                "sold_tax,order_id) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(sql, orderId, order.getUsername());
        for(OrderItem item : order.getOrderItems()) {
            jdbcTemplate.update(sql2, item.getProductId(), item.getQuantity(),
                    item.getSoldPrice(), item.getSoldTax(), orderId);
        }
    }

    @Override
    public void addOrderItem(String orderId, String productId, int quantity) {

        String sql = "INSERT INTO order_items (product_id, quantity, " +
                "sold_price, sold_tax, order_id) VALUES (?,?,?,?,?)";

        Product p = productService.getProductById(productId);
        jdbcTemplate.update(sql, p.getProductId(), quantity,
                p.getPrice(), p.getTaxAmount(), orderId);
    }

    @Override
    public void updateOrderItem(String orderId, String productId, int quantity) {

        String sql = "UPDATE order_items SET quantity=? WHERE order_id=? AND product_id=?";
        jdbcTemplate.update(sql, quantity, orderId, productId);
    }

    @Override
    public void deleteOrderItem(String orderId, String productId) {

        String sql = "DELETE FROM order_items WHERE order_id=? AND product_id=?";
        jdbcTemplate.update(sql, orderId, productId);
    }

    @Override
    public void realiseOrder(String orderId) {

        String sql = "UPDATE orders SET state=1 WHERE order_id=?";
        jdbcTemplate.update(sql, orderId);
    }

    @Override
    public void deleteOrder(String orderId) {

        String sql = "DELETE FROM order_items WHERE order_id=?";
        String sql2 = "DELETE FROM orders WHERE order_id=?";
        jdbcTemplate.update(sql, orderId);
        jdbcTemplate.update(sql2, orderId);
    }

    private class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Order(rs.getString("order_id"), null, rs.getString("username"),
                    rs.getTimestamp("created"), rs.getBoolean("state"));
        }
    }
}