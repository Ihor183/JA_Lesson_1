package ua.igorbendera.cinema;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.time.DateTimeException;
import java.util.Set;
import java.util.zip.DataFormatException;

public class ScheduleTest {
    private Schedule schedule;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("SUCCEED--> " + description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED--> " + description.getMethodName());
        }
    };

    @Before
    public void beforeTest() {
        schedule = new Schedule();
    }

    @After
    public void afterTest() {
        schedule = null;
    }

    @Test
    public void checkIfSuchTimeIsFreeTest() {
        Set<Seance> seances = schedule.getSeances();
        seances.add(new Seance(
                new Movie("Film1", new Time(1, 20)),
                new Time(7, 10)));

        seances.add(new Seance(
                new Movie("Film3", new Time(1, 20)),
                new Time(10, 10)));

        schedule.setSeances(seances);
        boolean realValue = schedule.checkIfSuchTimeIsFree(
                new Seance(
                        new Movie("Film2", new Time(1, 20)),
                        new Time(8, 40)));

        Assert.assertTrue(realValue);
    }

    @Test(expected = DateTimeException.class)
    public void checkIfSuchTimeIsFreeExceptionTest() {
        Set<Seance> seances = schedule.getSeances();
        seances.add(new Seance(
                new Movie("Film1", new Time(1, 20)),
                new Time(7, 10)));

        seances.add(new Seance(
                new Movie("Film3", new Time(1, 20)),
                new Time(9, 50)));

        schedule.setSeances(seances);
        boolean realValue = schedule.checkIfSuchTimeIsFree(
                new Seance(
                        new Movie("Film2", new Time(1, 20)),
                        new Time(8, 40)));

        Assert.assertFalse(realValue);
    }
}
