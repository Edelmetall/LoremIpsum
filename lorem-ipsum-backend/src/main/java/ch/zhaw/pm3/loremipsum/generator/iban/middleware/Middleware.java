package ch.zhaw.pm3.loremipsum.generator.iban.middleware;

public abstract class Middleware<T> {
    private Middleware<T> next;

    /**
     * Builds chains of middleware objects.
     */
    public Middleware<T> linkWith(Middleware<T> next) {
        this.next = next;
        return this;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract String handle(String field, T businessWrapper);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected String handleNext(String field, T businessWrapper) {
        if (next == null) {
            return field;
        }
        return next.handle(field, businessWrapper);
    }
}
