package citypops.webstore.domain;

import java.math.BigDecimal;

public class OrderItem {

    private final String productId;
    private int quantity;
    private final BigDecimal soldPrice;
    private final float soldTax;
    private BigDecimal nettoSum;
    private BigDecimal bruttoSum;

    public OrderItem(final String productId, int quantity, final BigDecimal soldPrice, final float soldTax) {
        this.productId = productId;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
        this.soldTax = soldTax;
        updateSums();
    }

    private void updateSums() {
        nettoSum = soldPrice.multiply(new BigDecimal(quantity));
        bruttoSum = nettoSum.add(nettoSum.multiply(new BigDecimal(soldTax)));
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSums();
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public float getSoldTax() {
        return soldTax;
    }

    public BigDecimal getNettoSum() {
        return nettoSum;
    }

    public BigDecimal getBruttoSum() {
        return bruttoSum;
    }
}