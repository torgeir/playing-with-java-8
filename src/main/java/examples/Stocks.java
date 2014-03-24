package main.java.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Stocks {

    interface Fn { void call(); }

    public static void time(Fn fn) {
        Date start = new Date();
        fn.call();
        Date end = new Date();
        Duration duration =
            Duration.between(
                start.toInstant(),
                end.toInstant());

        System.out.println(String.format(
            "Took ~%s seconds.", duration.getSeconds()));
    }

    public static void main(String[] args) {
        time(() ->
            findHighestPricedUnder500(
                Tickers.symbols.stream()));
        time(() ->
            findHighestPricedUnder500(
                Tickers.symbols.parallelStream()));
    }

    private static void findHighestPricedUnder500(Stream<String> stream) {
        Stock highPriced = stream
            .map(StockUtil::getPrice)
            .filter(StockUtil.isPriceLessThan(500))
            .reduce(StockUtil::pickHigh)
            .get();

        System.out.println("High priced under $500 is " +
            highPriced);
    }

}

class Tickers {
    public static final List<String> symbols = Arrays.asList(
        "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX",
        "AAPL", "ADBE", "AMZN", "CRAY", "CSCO", "SNE",
        "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX",
        "VRSN", "YHOO");
}

class Stock {

    public final String ticker;
    public final BigDecimal price;

    Stock(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String toString() {
        return String.format(
            "Stock(%s, $%s)", ticker, price);
    }
}

class StockUtil {
    public static Stock getPrice(final String ticker) {
        return new Stock(
            ticker, YahooFinance.getPrice(ticker));
    }

    public static Predicate<Stock> isPriceLessThan(final int price) {
        return info ->
            info.price.compareTo(
                BigDecimal.valueOf(price)) < 0;
    }

    public static Stock pickHigh(
        final Stock one, final Stock two) {

        return one.price.compareTo(two.price) > 0
            ? one
            : two;
    }
}

class YahooFinance {

    public static BigDecimal getPrice(final String ticker) {
        try {
            final URL url =
                new URL(String.format(
                    "http://ichart.finance.yahoo.com/table.csv?s=%s",
                    ticker));

            final BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(url.openStream()));

            final String data = reader
                .lines()
                .skip(1)
                .findFirst()
                .get();

            final String[] dataItems = data.split(",");

            return new BigDecimal(
                dataItems[dataItems.length - 1]);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}