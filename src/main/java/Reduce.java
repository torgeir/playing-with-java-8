package main.java;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static main.java.Lambdas.friends;

public class Reduce {

    public static void main(String[] args) {
        final List<BigDecimal> prices =
            Arrays.asList(
                new BigDecimal("10"),
                new BigDecimal("30"),
                new BigDecimal("17"),
                new BigDecimal("20"),
                new BigDecimal("15"),
                new BigDecimal("18"),
                new BigDecimal("45"),
                new BigDecimal("12")
            );

        BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
                totalOfDiscountedPrices =
                    totalOfDiscountedPrices.add(
                        price.multiply(BigDecimal.valueOf(0.9)));
            }
        }
        System.out.println("Total of discounted prices: " +
            totalOfDiscountedPrices);


        final BigDecimal totalOfDiscountedPrices2 =
            prices.stream()
                .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " +
            totalOfDiscountedPrices2);

        System.out.println(
            "Total number of characters in all names: " +
                friends.stream()
                    .mapToInt(String::length)
                    .sum());


        final Optional<String> longestName =
            friends
                .stream()
                .reduce((n1, n2) ->
                    n1.length() > n2.length()
                        ? n1
                        : n2);

        System.out.println(longestName);
    }
}
