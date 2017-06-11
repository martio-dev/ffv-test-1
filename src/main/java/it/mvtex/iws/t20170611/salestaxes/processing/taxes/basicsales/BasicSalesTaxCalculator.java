package it.mvtex.iws.t20170611.salestaxes.processing.taxes.basicsales;

import it.mvtex.iws.t20170611.salestaxes.model.GoodType;
import it.mvtex.iws.t20170611.salestaxes.model.ReceiptInvoice;
import it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain.AbstractDeactivableChainElement;
import it.mvtex.iws.t20170611.salestaxes.processing.taxes.TaxRounder;

public final class BasicSalesTaxCalculator
        extends AbstractDeactivableChainElement<ReceiptInvoice> {

    @Override
    protected boolean getActive(ReceiptInvoice context) {
        boolean result = GoodType.OTHER.equals(BasicSalesTaxGoodTypeParser
                .getInstance()
                .parse(context.getCaption()));

        return result;
    }

    @Override
    protected void internalDoAction(ReceiptInvoice context) {
        Number taxAmount = context.getBasePrice().doubleValue() * 0.1;
        taxAmount = TaxRounder.getInstance().round(taxAmount);
        context.addToTaxesAmount(taxAmount);
    }

}
