package ua.igorbendera.cinema;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TimeTest {
    private Time time;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED--> " + description.getMethodName());
        };

        protected void succeeded(Description description) {
            System.out.println("SUCCEED--> " + description.getMethodName());
        };
    };

    @Before
    public void beforeTest() {
        time = new Time();
    }

    @After
    public void afterTest() {
        time = null;
    }

    @Test
    public void calculateTimeTest() {
        Time realTime = Time.calculateTime(new Time(7, 23), new Time(23, 40));
        Time expectedTime = new Time(7, 3);

        Assert.assertEquals(realTime, expectedTime);
    }

    @Test
    public void compareTimeTest() {
        boolean realValue = Time.compareTime(new Time(14, 30), new Time(17, 30));

        Assert.assertTrue(realValue);
    }

}
