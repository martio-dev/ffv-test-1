package it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain;

public interface ChainElement<T> {

    void setNext(ChainElement<T> next);

    void doAction(T context);

}
