package it.mvtex.iws.t20170611.salestaxes.model;

public final class ReceiptInvoice {

    private final int quantity;
    private final boolean imported;
    private final String caption;
    private final Number basePrice;
    private Number taxesAmount = new Double(0.0);

    public ReceiptInvoice(int quantity,
                          boolean imported,
                          String caption,
                          Number basePrice) {
        this.quantity = quantity;
        this.caption = caption;
        this.imported = imported;
        this.basePrice = basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isImported() {
        return imported;
    }

    public String getCaption() {
        return caption;
    }

    public Number getBasePrice() {
        return basePrice;
    }

    public Number getTaxesAmount() {
        return taxesAmount;
    }

    public void addToTaxesAmount(Number singleTaxAmount) {
        this.taxesAmount = this.taxesAmount.doubleValue() + singleTaxAmount.doubleValue();
    }

    @Override
    public String toString() {
        return "ReceiptInvoice [quantity=" + quantity + ", imported=" + imported + ", caption=" + caption + ", basePrice=" + basePrice + "]";
    }

    public static final class Builder {

        private Integer quantity = null;
        private Boolean imported = Boolean.FALSE; // default value
        private String caption = null;
        private Number basePrice = null;

        public ReceiptInvoice build() {
            if (this.quantity == null) {
                throw new IllegalArgumentException("missing quantity parameter");
            }
            if (this.caption == null || this.caption.isEmpty()) {
                throw new IllegalArgumentException("missing caption parameter");
            }
            if (this.basePrice == null) {
                throw new IllegalArgumentException("missing basePrice parameter");
            }

            return new ReceiptInvoice(this.quantity,
                                      this.imported,
                                      this.caption,
                                      this.basePrice);
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withImported(Boolean imported) {
            this.imported = Boolean.TRUE.equals(imported);
            return this;
        }

        public Builder withCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public Builder withBasePrice(Number basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        @Override
        public String toString() {
            return "Builder [quantity=" + quantity + ", imported=" + imported + ", caption=" + caption + ", basePrice=" + basePrice + "]";
        }

    }

}
