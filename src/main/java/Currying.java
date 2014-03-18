package main.java;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class Currying {

    public static void main(String[] args) {

        IntUnaryOperator square =  x -> x * x;
        Function<Integer, IntUnaryOperator> curriedAdd = a -> b -> a + b;

        IntUnaryOperator add2 = curriedAdd.apply(2);
        System.out.println(add2.applyAsInt(4)); // 6

        IntUnaryOperator squaredPlus2 = square.andThen(add2);
        System.out.println(squaredPlus2.applyAsInt(4)); // 18

    }
}
