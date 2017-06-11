package it.mvtex.iws.t20170611.salestaxes.processing.taxes;

public final class TaxRounder {

    private static final TaxRounder INSTANCE = new TaxRounder();

    public static TaxRounder getInstance() {
        return INSTANCE;
    }

    private TaxRounder() {
    }

    public Number round(Number toBeRounded) {
        double result = 0.05 * (Math.ceil(Math.abs(toBeRounded.doubleValue() / 0.05)));

        return result;
    }

}
