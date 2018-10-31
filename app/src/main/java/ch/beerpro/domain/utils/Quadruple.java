package ch.beerpro.domain.utils;

public class Quadruple<T1, T2, T3, T4> {

    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 forth;

    public Quadruple(T1 first, T2 second, T3 third, T4 forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public T3 getThird() {
        return third;
    }

    public T4 getForth() {
        return forth;
    }




}
