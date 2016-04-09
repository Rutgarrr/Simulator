package nl.simulator.mvc.old;
import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private Color color;

    /**
     * Constructor for objects of class nl.simulator.mvc.old.Car
     */
    public Car() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public void tick() {
        minutesLeft--;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}