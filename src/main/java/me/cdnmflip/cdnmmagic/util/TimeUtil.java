package me.cdnmflip.cdnmmagic.util;

import java.util.concurrent.TimeUnit;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public final class TimeUtil {

    private TimeUtil()
    {
    }

    /**
     * Converts a millisecond duration to a string format
     *
     * @param millis A duration to convert to a string form
     * @return A string of the form "10h03s"
     */
    public static String getDurationBreakdown(long millis)
    {
        return String.format("%02dm%02ds", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

}
