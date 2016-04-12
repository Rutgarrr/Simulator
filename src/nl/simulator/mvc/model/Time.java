package nl.simulator.mvc.model;

/**
 * Created by rutgerschroder on 12/04/16.
 *
 */
public class Time {

    private int minute;
    private int hour;
    private int day;
    private int week;
    private int realDay;
    private int[] time;

    public Time(){
        minute = 0;
        hour = 0;
        day = 0;
        time = new int[5];
    }

    private void incrMinute(){
        minute++;
    }

    private void incrHour(){
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
    }

    private void incrDay(){
        while (hour > 23) {
            hour -= 24;
            day++;
        }
    }

    private void incrWeek(){
        while (day > 6) {
            day -= 7;
            week++;
        }
    }

    private void setRealday(){
        realDay = week * 7 + day;
    }

    /**
     *
     * @return the time as an array:
     * time[0] is the current minute in hour
     * time[1] is the current hour in day
     * time[2] is current day in week
     * time[3] is the current week
     * time[4] is the current amount of days that have passed since the simulator started
     */
    public int[] updateTime(){
        incrMinute();
        time[0] = minute;
        incrHour();
        time[1] = hour;
        incrDay();
        time[2] = day;
        incrWeek();
        time[3] = week;
        setRealday();
        time[4] = realDay;

        return time;
    }


}
