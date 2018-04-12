package citypops.webstore.domain;

import java.math.BigDecimal;

public class CartItem {

    private final Product product;
    private int quantity;
    private BigDecimal nettoSum;
    private BigDecimal bruttoSum;

    public CartItem(final Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        updateSums();
    }

    public OrderItem convertToOrderItem() {
        return new OrderItem(product.getProductId(), quantity, product.getPrice(), product.getTaxAmount());
    }

    private void updateSums() {
        nettoSum = product.getPrice().multiply(new BigDecimal(quantity));
        bruttoSum = nettoSum.add(nettoSum.multiply(new BigDecimal(product.getTaxAmount())));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSums();
    }

    public BigDecimal getNettoSum() {
        return nettoSum;
    }

    public BigDecimal getBruttoSum() {
        return bruttoSum;
    }
}
