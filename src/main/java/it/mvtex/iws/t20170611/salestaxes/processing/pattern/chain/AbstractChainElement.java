package it.mvtex.iws.t20170611.salestaxes.processing.pattern.chain;

public abstract class AbstractChainElement<T>
        implements ChainElement<T> {

    private ChainElement<T> chain;

    @Override
    public void setNext(ChainElement<T> next) {
        this.chain = next;
    }

    @Override
    public void doAction(T context) {
        internalDoAction(context);

        traverseChain(context);
    }

    protected abstract void internalDoAction(T context);

    protected void traverseChain(T context) {
        if (this.chain != null) {
            this.chain.doAction(context);
        }
    }

}
