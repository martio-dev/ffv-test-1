package it.mvtex.iws.t20170611.salestaxes;

import it.mvtex.iws.t20170611.salestaxes.processing.ReceiptProcessor;

public class SalesTaxesSolution {

    public static void main(String[] args) {

        System.out.println(new ReceiptProcessor.Builder()
                .addInvoices(args)
                .build()
                .printReceiptDetails());

    }

}
