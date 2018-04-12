package citypops.webstore.service.impl;

import citypops.webstore.domain.Product;
import citypops.webstore.persistence.impl.ProductDAO;
import citypops.webstore.persistence.ProductRepository;
import citypops.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductDAO productDAO) {
        productRepository = productDAO;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(String productId, Product product) {
        productRepository.updateProduct(productId, product);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteProduct(productId);
    }
}
