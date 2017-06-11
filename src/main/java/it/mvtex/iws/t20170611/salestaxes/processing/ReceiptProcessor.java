package it.mvtex.iws.t20170611.salestaxes.processing;

public final class ReceiptProcessor {

    public static final class Builder {

        public Builder addInvoices(String... invoices) {
            return this;
        }

        public Builder addInvoice(String invoice) {
            return this;
        }

        public ReceiptProcessor build() {
            return new ReceiptProcessor();
        }

    }

    public String printReceiptDetails() {
        return null;
    }

}
