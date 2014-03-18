package main.java;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Time {

    public static void main(String[] args) {

        Date now = new Date();

        Instant inTwoDays = now.toInstant().plus(Duration.ofDays(2));
        System.out.println(inTwoDays);

        Duration diff = Duration.between(now.toInstant(), inTwoDays);
        System.out.println(diff.getSeconds() == 2 * 24 * 60 * 60);

        LocalDate nextOrThisMonday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(nextOrThisMonday);
    }
}
