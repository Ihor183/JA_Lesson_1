package ua.igorbendera.cinema;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Objects;
import java.util.Scanner;

public class Time implements Serializable {
    private int hour;
    private int minute;

    public Time() {    }

    public Time(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }

    public void setTime() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("time hh:mm ---> ");
        String time = scanner.nextLine();

        int i = 0;
        for(String str : time.split(":")) {
            switch (i) {
                case 0: setHour(Integer.parseInt(str)); break;
                case 1: setMinute(Integer.parseInt(str)); break;
            }
            i++;
        }
    }

    public static Time calculateTime(Time t1, Time t2) {
        Time time = new Time();

        int tempAddMinutes = t1.getMinute() + t2.getMinute();
        int tempAddHours = t1.getHour() + t2.getHour();
        time.minute = tempAddMinutes % 60;

        if(tempAddMinutes >= 60) {
            tempAddHours++;
        }

        time.hour = tempAddHours % 24;
        return time;
    }

    public static boolean compareTime(Time t1, Time t2) {
        return (t1.hour < t2.hour) || (t1.hour == t2.hour && t1.minute < t2.minute);
    }

    private String timeFormat(int number) {
        return (number / 10 < 1)? "0" + number : Integer.toString(number);
    }


    public boolean setHour(int hour) {
        if(hour >= 0 && hour < 24) {
            this.hour = hour;
            return true;
        } else {
            timeException("hours");
        }

        return false;
    }

    public boolean setMinute(int minute) {
        if(minute >= 0 && minute < 60) {
            this.minute = minute;
            return true;
        } else {
            timeException("minutes");
        }

        return false;
    }

    private void timeException(String str) {
        throw new DateTimeException("Wrong " + str + " input");
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour &&
                minute == time.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    @Override
    public String toString() {
        return timeFormat(hour) + ":" + timeFormat(minute);
    }
}
