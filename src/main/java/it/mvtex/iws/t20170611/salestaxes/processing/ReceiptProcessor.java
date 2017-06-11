package it.mvtex.iws.t20170611.salestaxes.processing;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ReceiptProcessor {

    private static final DecimalFormat PRICE_FORMATER = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

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
        StringBuilder resultBuilder = new StringBuilder();

        final String systemNewLine = String.format("%n");

        resultBuilder.append("Sales Taxes: ").append(PRICE_FORMATER.format(0)).append(systemNewLine);
        resultBuilder.append("Total: ").append(PRICE_FORMATER.format(0)).append(systemNewLine);

        return resultBuilder.toString();
    }

}
