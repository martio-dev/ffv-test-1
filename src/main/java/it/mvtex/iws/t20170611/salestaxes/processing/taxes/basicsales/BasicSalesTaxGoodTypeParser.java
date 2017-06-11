package it.mvtex.iws.t20170611.salestaxes.processing.taxes.basicsales;

import java.util.regex.Pattern;

import it.mvtex.iws.t20170611.salestaxes.model.GoodType;
import it.mvtex.iws.t20170611.salestaxes.processing.taxes.GoodTypeParser;

public final class BasicSalesTaxGoodTypeParser
        implements GoodTypeParser {

    private static final BasicSalesTaxGoodTypeParser INSTANCE = new BasicSalesTaxGoodTypeParser();

    private static final Pattern BOOK_PATTERN = Pattern.compile("book", Pattern.CASE_INSENSITIVE);
    private static final Pattern FOOD_PATTERN = Pattern.compile("chocolate", Pattern.CASE_INSENSITIVE);
    private static final Pattern MEDICAL_PATTERN = Pattern.compile("pill", Pattern.CASE_INSENSITIVE);

    private BasicSalesTaxGoodTypeParser() {
    }

    public static final GoodTypeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public GoodType parse(String goodLabel) {
        if (BOOK_PATTERN.matcher(goodLabel).find()) {
            return GoodType.BOOK;
        }

        if (FOOD_PATTERN.matcher(goodLabel).find()) {
            return GoodType.FOOD;
        }

        if (MEDICAL_PATTERN.matcher(goodLabel).find()) {
            return GoodType.MEDICAL;
        }

        return GoodType.OTHER;
    }

}
