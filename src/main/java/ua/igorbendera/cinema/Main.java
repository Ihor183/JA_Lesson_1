package ua.igorbendera.cinema;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int action;
        boolean flag = false;

        Cinema cinema = new Cinema(new Time(7,0), new Time(23, 0));
        System.out.println("Cinema is opened at " + cinema.getOpen() +
                "\nclosed at " + cinema.getClose());

        deserializeData(cinema);
        System.out.println("Дані з файлу отримано");

        while(!flag) {

            action = menu();

            switch(action) {
                case 1: {
                    Movie movie = movieObjectFactory();
                    System.out.print("Duration ");
                    movie.setDuration(timeObjectFactory());
                    cinema.addMovie(movie);
                    break;
                }
                case 2: {
                    Movie movie = movieObjectFactory();
                    System.out.print("Start seance ");
                    Time time = timeObjectFactory();

                    cinema.addSeance(movie, time);
                    break;
                }
                case 11: {
                    Movie movie = movieObjectFactory();

                    cinema.removeMovie(movie);
                    break;
                }
                case 12: {
                    Movie movie = movieObjectFactory();
                    System.out.print("Start seance ");
                    Time startSeance = timeObjectFactory();
                    movie = cinema.getMovieFromList(movie);

                    if(movie != null) {
                        Seance seance = new Seance(movie, startSeance);

                        System.out.print("Enter day: ");
                        String day = new Scanner(System.in).nextLine();
                        cinema.removeSeance(seance, day);
                    }
                    break;
                }
                case 21: {
                    showSchedule(cinema.getSchedules());
                    break;
                }
                case 22: {
                    cinema.showFilmLibrary();
                    break;
                }
                case 31: {
                    cinema.clearSchedules();
                    break;
                }
                case 32: {
                    cinema.clearMoviesLibrary();
                    break;
                }
                case 41: {
                    serializeData(cinema);
                    flag = true;
                    break;
                }
                default:
                    return;
            }
        }
    }

    private static void deserializeData(Cinema cinema) throws IOException, ClassNotFoundException {
        SaveCinemaData.readScheduleFromFile(cinema);
        SaveCinemaData.filmLibraryFromFile(cinema);
    }

    private static void serializeData(Cinema cinema) throws IOException {
        SaveCinemaData.writeScheduleToFile(cinema);
        SaveCinemaData.filmLibraryToFile(cinema);
    }

    private static Movie movieObjectFactory() {
        Movie movie = new Movie();
        movie.setMovie();

        return movie;
    }

    private static Time timeObjectFactory() {
        Time time = new Time();
        time.setTime();

        return time;
    }

    private static int menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n" +
                "1 - Додати фільм в бібліотеку\n" +
                "2 - Додати сеанс до розкладу\n\n" +

                "11 - Видалити фільм з бібліотеки(Видаляються всі сеанси пов'язані з фільмом)\n" +
                "12 - Видалити сеанс\n\n" +

                "21 - Розклад на тиждень\n" +
                "22 - Побачити бібліотеку фільмів\n\n" +

                "31 - Очистити файл та дані розкладу\n" +
                "32 - Очистити файл бібліотеки фільмів\n\n" +

                "41 - Вихід(Після виходу всі дані серіалізуються в файл)\n" +
                "Select action: ");

        return scanner.hasNextInt()? scanner.nextInt() : 0;
    }

    private static void showSchedule(Map<Days, Schedule> map) {
        map.forEach((x, y) -> System.out.println(x + " " + y));
    }
}


