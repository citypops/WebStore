package citypops.webstore.controller;

import citypops.webstore.domain.Product;
import citypops.webstore.exception.FieldNotValidException;
import citypops.webstore.service.ProductService;
import citypops.webstore.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void addProduct(@RequestBody @Valid Product product, BindingResult result) {
        if(result.hasErrors()) {
            FieldError error = result.getFieldError();
            throw new FieldNotValidException(error.getDefaultMessage());
        }
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProduct(@PathVariable("productId") String productId,
                              @RequestBody @Valid Product product,
                              BindingResult result) {

        if(result.hasErrors()) {
            FieldError error = result.getFieldError();
            throw new FieldNotValidException(error.getDefaultMessage());
        }
        productService.updateProduct(productId, product);
    }


    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException() {
        return new ResponseEntity<>("Product with this name or id already exist. Both parameters must be unique.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handle(SQLIntegrityConstraintViolationException e) {
        String message;
        if(e.getErrorCode() == 1451) {
            message = "Cannot delete product that appears in saved orders. These must be deleted first.";
        } else {
            message = e.getMessage();
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}