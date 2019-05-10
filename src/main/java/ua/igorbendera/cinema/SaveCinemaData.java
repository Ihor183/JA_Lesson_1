package ua.igorbendera.cinema;

import java.io.*;
import java.util.ArrayList;

public class SaveCinemaData {

    public static void writeScheduleToFile(Cinema cinema) {
        for(Days day : Days.values()) {
            String fileName = day.toString() + ".txt";
            try {

                FileOutputStream fos = new FileOutputStream(fileName);
                Schedule schedule = cinema.getSchedules().get(day);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                schedule.getSeances().forEach(seance -> {
                    try {
                        oos.writeObject(seance);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readScheduleFromFile(Cinema cinema) throws IOException, ClassNotFoundException {
        Schedule schedule;
        for(Days day : Days.values()) {

            schedule = new Schedule();
            String fileName = day.toString() + ".txt";

            if(new File(fileName).length() == 0) {
                continue;
            }

            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while(true) {
                try {
                    Seance seance = (Seance)ois.readObject();
                    schedule.addSeance(seance);

                } catch (EOFException e) {
                    fis.close();
                    ois.close();
                    cinema.getSchedules().replace(day, schedule);
                    break;
                }
            }
        }
    }

    public static void filmLibraryToFile(Cinema cinema) throws IOException {
        FileOutputStream fos = new FileOutputStream("movie.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        cinema.getMoviesLibrary().forEach(movie -> {
            try {
                oos.writeObject(movie);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        oos.close();
        fos.close();
    }

    public static void filmLibraryFromFile(Cinema cinema) throws IOException, ClassNotFoundException{
        ArrayList<Movie> movies = new ArrayList<>();
        File file = new File("movie.txt");

        if(file.length() == 0) {
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while(true) {
            try {
                Movie movie = (Movie)ois.readObject();
                movies.add(movie);
            } catch (EOFException ex) {
                fis.close();
                ois.close();
                cinema.setMoviesLibrary(movies);
                break;
            }
        }
    }
}
