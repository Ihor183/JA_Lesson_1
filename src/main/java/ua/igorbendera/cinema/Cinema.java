package ua.igorbendera.cinema;

import java.io.*;
import java.util.*;

public class Cinema implements Serializable {
    private TreeMap<Days, Schedule> schedules;
    private ArrayList<Movie> moviesLibrary;
    private Time open;
    private Time close;

    public Cinema(Time open, Time close) {
        schedules = new TreeMap<>();
        moviesLibrary = new ArrayList<>();
        this.open = open;
        this.close = close;

        createNewSchedule();
    }

    public Cinema(TreeMap<Days, Schedule> schedules, ArrayList<Movie> moviesLibrary, Time open, Time close) {
        this.schedules = schedules;
        this.moviesLibrary = moviesLibrary;
        this.open = open;
        this.close = close;
    }

    private void createNewSchedule() {
        Days[] days = Days.values();

        for(Days day : days) {
            schedules.put(day, new Schedule());
        }
    }

    public void addMovie(Movie movie) {
        if(!checkMovie(movie)) {
            moviesLibrary.add(movie);
        } else {
            System.out.println("Such film is exist");
        }
    }

    public void addSeance(Movie movie, Time timeStart) {
        if(checkMovie(movie)) {
            System.out.print("Enter day: ");
            String setDay = new Scanner(System.in).nextLine().toUpperCase();
            Days day = Days.valueOf(setDay);

            Schedule schedule = getSchedule(day);

            if (Time.compareTime(open, timeStart)
                    && Time.compareTime(Time.calculateTime(timeStart, movie.getDuration()), close)) {
                Seance seance = new Seance(moviesLibrary.get(moviesLibrary.indexOf(movie)), timeStart);
                schedule.addSeance(seance);
            } else {
                System.out.println("Cinema is closed");
            }
        } else {
            System.out.println("There isn't such film in library");
        }
    }

    public void removeMovie(Movie movie) {
        if(moviesLibrary.contains(movie)) {
            moviesLibrary.remove(movie);
        }

        for(Days d: Days.values()) {
            Set<Seance> seances = schedules.get(d).getSeances();

            Iterator<Seance> seanceIterator = seances.iterator();
            while(seanceIterator.hasNext()) {
                Seance seance = seanceIterator.next();

                if(seance.getMovie().equals(movie)) {
                    seanceIterator.remove();
                }
            }
        }
    }

    public void showFilmLibrary() {
        moviesLibrary.forEach(System.out::println);
    }

    public void clearSchedules() {
        schedules.clear();

        for(Days day : Days.values()) {
            File file = new File(day + ".txt");
            clearFile(file);
        }
    }

    public void clearMoviesLibrary() {
        moviesLibrary.clear();
        clearFile(new File("movie.txt"));
    }

    private void clearFile(File file) {
        if(file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeSeance(Seance seance, String day) {
        schedules.get(Days.valueOf(day.toUpperCase())).removeSeance(seance);
    }

    public Movie getMovieFromList(Movie movie) {
        return checkMovie(movie) ? moviesLibrary.get(moviesLibrary.indexOf(movie)) : null;
    }

    private boolean checkMovie(Movie movie) {
        return moviesLibrary.contains(movie);
    }

    private Schedule getSchedule(Days day) {
        return schedules.computeIfPresent(day, (x, y) -> y);
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(TreeMap<Days, Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
        this.moviesLibrary = moviesLibrary;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "schedules=" + schedules +
                ", moviesLibrary=" + moviesLibrary +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}
