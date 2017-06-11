package it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain;

public class ChainBuilder<T> {

    public static final <T> ChainBuilder<T> start() {
        return new ChainBuilder<T>();
    }

    private ChainElement<T> head = null;
    private ChainElement<T> last = null;

    private ChainBuilder() {
    }

    //
    // BEGIN DSL
    //

    public final ChainBuilder<T> _add(ChainElement<T> element) {
        if (element != null) {

            if (head == null) {
                head = element;
            }

            if (last != null) {
                last.setNext(element);
            }

            last = element;
        }

        return this;
    }

    //
    // END DSL
    //

    public final ChainElement<T> get() {
        return this.head;
    }

}
