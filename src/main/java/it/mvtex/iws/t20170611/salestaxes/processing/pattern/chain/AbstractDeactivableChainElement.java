package it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain;

public abstract class AbstractDeactivableChainElement<T>
        extends AbstractChainElement<T> {

    @Override
    public final void doAction(T context) {
        if (getActive(context)) {
            internalDoAction(context);
        }

        traverseChain(context);
    }

    protected abstract void internalDoAction(T context);

    protected abstract boolean getActive(T context);

}
