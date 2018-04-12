package citypops.webstore.domain;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class Product {

    @NotEmpty(message = "Product ID cannot be empty")
    @Size(max = 128, message = "Product ID can count at most 128 characters")
    private String productId;
    @NotEmpty(message = "Product name cannot be empty")
    @Size(max = 128, message = "Product name can count at most 128 characters")
    private String name;
    @NotNull(message = "Price cannot be empty")
    private BigDecimal price;
    @NotNull(message = "Tax amount cannot be empty")
    private float taxAmount;
    @NotNull(message = "Active cannot be empty")
    private boolean active;
    @Size(max = 255, message = "Image name can count at most 255 characters")
    private String imageName;

    public Product() {
        price = new BigDecimal(0);
    }

    public Product(String productId, String name, BigDecimal price,
                   float taxAmount, boolean active, String imageName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.taxAmount = taxAmount;
        this.active = active;
        this.imageName = imageName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}