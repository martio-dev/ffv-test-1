package it.mvtex.iws.t20170611.salestaxes.processing.taxes;

public final class TaxRounder {

    private static final TaxRounder INSTANCE = new TaxRounder();

    public static TaxRounder getInstance() {
        return INSTANCE;
    }

    private TaxRounder() {
    }

    public Number round(Number toBeRounded) {
        Long aux = (long)(toBeRounded.doubleValue() * 100);

        aux = (long)(5 * (Math.round(aux / 5)));

        return new Double(aux / 100);
    }

}
