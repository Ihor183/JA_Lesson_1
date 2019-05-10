package ua.igorbendera.cinema;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;
import java.util.ArrayList;

public class SaveCinemaDataTest {
    private Cinema cinema;

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
        cinema = new Cinema(new Time(7, 0),
                new Time(23,0));
    }

    @After
    public void afterTest() {
        cinema = null;
    }

    @Test
    public void filmLibrarySaveDataTest() throws IOException, ClassNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        initialMoviesLibrary(movies);

        cinema.setMoviesLibrary(movies);
        SaveCinemaData.filmLibraryToFile(cinema);

        cinema.getMoviesLibrary().clear(); // ArrayList movies also was cleared
        initialMoviesLibrary(movies);      // Initial ArrayList again for comparing

        SaveCinemaData.filmLibraryFromFile(cinema);

        Assert.assertEquals(movies, cinema.getMoviesLibrary());
    }

    public void initialMoviesLibrary(ArrayList<Movie> movies) {
        movies.clear();
        movies.add(new Movie("Film1", new Time(1, 20)));
        movies.add(new Movie("Film2", new Time(2, 10)));
    }
}
