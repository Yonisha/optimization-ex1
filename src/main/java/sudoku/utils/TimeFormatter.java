package sudoku.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeFormatter{

    public static String format(long timeInMilliseconds){
        String time = String.format("%02d:%02d:%02d:%02d:%04d",
                TimeUnit.MILLISECONDS.toDays(timeInMilliseconds),
                TimeUnit.MILLISECONDS.toHours(timeInMilliseconds) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(timeInMilliseconds)),
                TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMilliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds)),
                TimeUnit.MILLISECONDS.toMillis(timeInMilliseconds) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds)));

        return time;
    }
}
