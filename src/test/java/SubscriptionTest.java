import com.example.ezpaytest.Subscription;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SubscriptionTest {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void testWeeklySubscription()
    {
        Subscription subscription = new Subscription("50", "WEEKLY", "2018-02-06", "2018-02-27", "TUESDAY");
        ArrayList<LocalDate> expectedOutput = new ArrayList<LocalDate>();
        expectedOutput.add(LocalDate.parse( "2018-02-06" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2018-02-13" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2018-02-20" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2018-02-27" , dateFormat ));
        assertEquals(expectedOutput, subscription.invoiceDates);

    }

    @Test
    public void testMonthlySubscription()
    {
        Subscription subscription = new Subscription("50", "MONTHLY", "2021-09-01", "2021-11-30", "15");
        ArrayList<LocalDate> expectedOutput = new ArrayList<LocalDate>();
        expectedOutput.add(LocalDate.parse( "2021-09-15" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-10-15" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-11-15" , dateFormat ));
        assertEquals(expectedOutput, subscription.invoiceDates);

    }

    @Test
    public void testDailySubscription()
    {
        Subscription subscription = new Subscription("50", "DAILY", "2021-09-01", "2021-09-05", "15");
        ArrayList<LocalDate> expectedOutput = new ArrayList<LocalDate>();
        expectedOutput.add(LocalDate.parse( "2021-09-01" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-09-02" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-09-03" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-09-04" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-09-05" , dateFormat ));
        assertEquals(expectedOutput, subscription.invoiceDates);
    }

    @Test
    public void testMonthlySubscriptionFeb()
    {
        Subscription subscription = new Subscription("50", "MONTHLY", "2021-01-01", "2021-03-31", "31");
        ArrayList<LocalDate> expectedOutput = new ArrayList<LocalDate>();
        expectedOutput.add(LocalDate.parse( "2021-01-31" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-02-28" , dateFormat ));
        expectedOutput.add(LocalDate.parse( "2021-03-31" , dateFormat ));
        assertEquals(expectedOutput, subscription.invoiceDates);
    }
}
