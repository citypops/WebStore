package citypops.webstore.persistence;

import citypops.webstore.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String productId);
    void addProduct(Product product);
    void updateProduct(String productId, Product product);
    void deleteProduct(String productId);
}
