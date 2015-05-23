package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.utils.TimeFormatter;

public class TimeFormatterTests {

    @Test
    public void formatOneSecond(){

        long millis = 1000;
        String format = TimeFormatter.format(millis);
        Assert.assertEquals("00:00:00:01:0000", format);
    }

    @Test
    public void formatOneHour(){

        long millis = 3600000;
        String format = TimeFormatter.format(millis);
        Assert.assertEquals("00:01:00:00:0000", format);
    }

    @Test
    public void formatOneDay(){

        long millis = 86400000;
        String format = TimeFormatter.format(millis);
        Assert.assertEquals("01:00:00:00:0000", format);
    }

    @Test
    public void formatOneDayAndOneMilli(){

        long millis = 86400001;
        String format = TimeFormatter.format(millis);
        Assert.assertEquals("01:00:00:00:0001", format);
    }
}
