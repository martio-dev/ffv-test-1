package it.mvtex.iws.t20170611.salestaxes.processing;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.mvtex.iws.t20170611.salestaxes.model.ReceiptInvoice;

public final class ReceiptInvoiceParser {

    private static final Pattern DEFAULT_PATTERN = Pattern.compile("(\\d+)\\s+(.+)\\s+at\\s+(\\d+\\.\\d{2})");

    private static final Function<String, ReceiptInvoice> DEFAULT_PARSER = new Function<String, ReceiptInvoice>() {

        @Override
        public ReceiptInvoice apply(String text) {
            Matcher matcher = DEFAULT_PATTERN.matcher(text);

            if (!matcher.find()) {
                throw new IllegalArgumentException("input invoice text is not valid");
            }

            String caption = matcher.group(2);

            ReceiptInvoice result = new ReceiptInvoice.Builder()
                    .withQuantity(Integer.parseInt(matcher.group(1)))
                    .withImported(caption.contains("imported"))
                    .withCaption(caption)
                    .withBasePrice(Double.parseDouble(matcher.group(3)))
                    .build();

            return result;
        }

    };

    private Function<String, ReceiptInvoice> parser;

    public ReceiptInvoiceParser() {
        this(DEFAULT_PARSER);
    }

    public ReceiptInvoiceParser(Function<String, ReceiptInvoice> customParser) {
        this.parser = customParser;
    }

    public ReceiptInvoice parse(String invoiceText) {
        return this.parser.apply(invoiceText);
    }

}
