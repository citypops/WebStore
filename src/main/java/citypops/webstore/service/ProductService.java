package citypops.webstore.service;

import citypops.webstore.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(String id);
    void addProduct(Product product);
    void updateProduct(String productId, Product product);
    void deleteProduct(String productId);

}
