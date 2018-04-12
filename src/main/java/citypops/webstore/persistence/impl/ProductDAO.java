package citypops.webstore.persistence.impl;

import citypops.webstore.domain.Product;
import citypops.webstore.persistence.ProductRepository;
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
public class ProductDAO implements ProductRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getAllProducts() {

        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product getProductById(String productId) {

        String sql = "SELECT * FROM products WHERE product_id=?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);
    }

    @Override
    public void addProduct(Product p) {

        String sql = "INSERT INTO products (product_id,product_name,price," +
                "tax_amount,active,image_name) VALUES (?,?,?,?,?,?)";

        jdbcTemplate.update(sql, p.getProductId(), p.getName(), p.getPrice(),
                p.getTaxAmount(), p.isActive(), p.getImageName());
    }

    @Override
    public void deleteProduct(String productId) {

        String sql = "DELETE FROM products WHERE product_id=?";
        jdbcTemplate.update(sql, productId);
    }

    @Override
    public void updateProduct(String productId, Product p) {

        String sql = "UPDATE products SET product_id=?,product_name=?,price=?," +
                "tax_amount=?,active=?,image_name=? WHERE product_id=?";

        jdbcTemplate.update(sql, p.getProductId(), p.getName(), p.getPrice(),
                p.getTaxAmount(), p.isActive(), p.getImageName(), productId);
    }

    private class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getBigDecimal("price"),
                    rs.getFloat("tax_amount"), rs.getBoolean("active"), rs.getString("image_name"));
        }
    }
}