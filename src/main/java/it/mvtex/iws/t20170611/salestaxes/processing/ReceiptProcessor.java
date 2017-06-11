package it.mvtex.iws.t20170611.salestaxes.processing;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import it.mvtex.iws.t20170611.salestaxes.model.ReceiptInvoice;
import it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain.ChainBuilder;
import it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain.ChainElement;
import it.mvtex.iws.t20170611.salestaxes.processing.taxes.basicsales.BasicSalesTaxCalculator;
import it.mvtex.iws.t20170611.salestaxes.processing.taxes.importduty.ImportDutyTaxCalculator;

public final class ReceiptProcessor {

    private static final DecimalFormat PRICE_FORMATER = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    private final List<ReceiptInvoice> invoices;

    public ReceiptProcessor() {
        this.invoices = Collections.emptyList();
    }

    public ReceiptProcessor(List<ReceiptInvoice> invoices) {
        this.invoices = invoices;
    }

    public String printReceiptDetails() {
        StringBuilder resultBuilder = new StringBuilder();

        final String systemNewLine = String.format("%n");

        ChainElement<ReceiptInvoice> taxesCalculationChain = ChainBuilder.<ReceiptInvoice>start()
                ._add(new BasicSalesTaxCalculator())
                ._add(new ImportDutyTaxCalculator())
                .get();

        Double totalTaxes = 0.0;
        Double totalPrice = 0.0;

        for (ReceiptInvoice receiptInvoice : this.invoices) {
            taxesCalculationChain.doAction(receiptInvoice);

            double invoiceTax = receiptInvoice.getTaxesAmount().doubleValue();

            totalTaxes += invoiceTax;
            totalPrice += (receiptInvoice.getBasePrice().doubleValue() + invoiceTax);

            resultBuilder
                    .append(receiptInvoice.getQuantity())
                    .append(" ")
                    .append(receiptInvoice.getCaption())
                    .append(": ")
                    .append(PRICE_FORMATER.format(totalPrice))
                    .append(systemNewLine);
        }

        resultBuilder.append("Sales Taxes: ").append(PRICE_FORMATER.format(totalTaxes)).append(systemNewLine);
        resultBuilder.append("Total: ").append(PRICE_FORMATER.format(totalPrice)).append(systemNewLine);

        return resultBuilder.toString();
    }

    @Override
    public String toString() {
        return "ReceiptProcessor [invoicesAmount=" + this.invoices.size() + "]";
    }

    public static final class Builder {

        private final List<String> invoicesText = new ArrayList<>(32);

        public Builder addInvoices(String... invoices) {
            if (invoices != null && invoices.length > 0) {
                for (String invoice : invoices) {
                    addInvoice(invoice);
                }
            }

            return this;
        }

        public Builder addInvoice(String invoice) {
            this.invoicesText.add(invoice);

            return this;
        }

        public ReceiptProcessor build() {
            ReceiptProcessor result = new ReceiptProcessor();

            if (this.invoicesText.isEmpty()) {
                return result;
            }

            ReceiptInvoiceParser parser = new ReceiptInvoiceParser();
            List<ReceiptInvoice> parsedInvoices = new ArrayList<>(this.invoicesText.size());

            for (String invoiceText : this.invoicesText) {
                parsedInvoices.add(parser.parse(invoiceText));
            }

            result = new ReceiptProcessor(parsedInvoices);

            return result;
        }

    }

}
