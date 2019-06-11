package pl.juvat.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author lominskk on 2019-06-11
 */
public final class DateUtils {

    public static String convertDateFormat(final DateTime dateTime) {
        final var formatter = DateTimeFormat.forPattern("MM-yyyy");
        return formatter.print(dateTime);
    }

}
