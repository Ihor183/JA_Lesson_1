package ua.igorbendera.cinema;

import java.util.Comparator;
import java.io.Serializable;

public class FilmTimeComparator implements Serializable, Comparator<Seance> {

    @Override
    public int compare(Seance o1, Seance o2) {
        return Time.compareTime(o1.getStartTime(), o2.getStartTime())? -1
                : Time.compareTime(o2.getStartTime(), o1.getStartTime())? 1 : 0;
    }
}
