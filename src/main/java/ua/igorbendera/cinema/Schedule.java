package ua.igorbendera.cinema;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.*;

public class Schedule implements Serializable {
    private Set<Seance> seances;

    public Schedule() {
        seances = new TreeSet<>(new FilmTimeComparator());
    }

    public Schedule(Set<Seance> seances) {
        this.seances = seances;
    }

    public boolean checkIfSuchTimeIsFree(Seance newSeance) {

        Iterator<Seance> iterator = seances.iterator();
        while(iterator.hasNext()) {
            Seance seance = iterator.next();

            if(Time.compareTime(seance.getEndTime(), newSeance.getStartTime())
                    && iterator.hasNext()) {
                continue;
            } else if(Time.compareTime(newSeance.getEndTime(), seance.getStartTime())
                    || (Time.compareTime(seance.getEndTime(), newSeance.getStartTime()))){
                return true;
            } else {
               throw new DateTimeException("Such time is already used");
            }
        }

        return false;
    }

    public void addSeance(Seance seance) {
        if(!seances.contains(seance) && (seances.size() == 0 || checkIfSuchTimeIsFree(seance))) {
            seances.add(seance);
        } else {
            System.out.println("Something went wrong! That time is busy");
        }
    }

    public void removeSeance(Seance seance) {
        if(seances.contains(seance)) {
            seances.remove(seance);
        } else {
            System.out.println("Such seance isn't exist");
        }
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }

    @Override
    public String toString() {
        return seances.toString();
    }
}
