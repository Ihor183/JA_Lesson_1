package ua.igorbendera.cinema;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Movie implements Serializable {
    private String title;
    private Time duration;

    public Movie() {
        duration = new Time();
    }

    public Movie(String title, Time duration) {
        this.title = title;
        this.duration = duration;
    }

    public void setMovie() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Film title: ");
        String filmName = scanner.nextLine();
        setTitle(filmName);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equalsIgnoreCase(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title + ", duration: " + duration;
    }
}
