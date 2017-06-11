package it.mvtex.iws.t20170611.salestaxes.processing.taxes.importduty;

import it.mvtex.iws.t20170611.salestaxes.model.ReceiptInvoice;
import it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain.AbstractDeactivableChainElement;
import it.mvtex.iws.t20170611.salestaxes.processing.taxes.TaxRounder;

public class ImportDutyTaxCalculator
        extends AbstractDeactivableChainElement<ReceiptInvoice> {

    @Override
    protected boolean getActive(ReceiptInvoice context) {
        boolean result = context.isImported();

        return result;
    }

    @Override
    protected void internalDoAction(ReceiptInvoice context) {
        Number taxAmount = context.getBasePrice().doubleValue() * 0.05;
        taxAmount = TaxRounder.getInstance().round(taxAmount);
        context.addToTaxesAmount(taxAmount);
    }

}
