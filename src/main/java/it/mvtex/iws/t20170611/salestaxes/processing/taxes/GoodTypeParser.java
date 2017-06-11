package it.mvtex.iws.t20170611.salestaxes.processing.taxes;

import it.mvtex.iws.t20170611.salestaxes.model.GoodType;

public interface GoodTypeParser {

    GoodType parse(String goodLabel);

}
