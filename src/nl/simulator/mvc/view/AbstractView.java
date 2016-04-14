package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public abstract class AbstractView extends JPanel {
    protected Simulator currentSim;
    private Boolean isGraphical;

    public AbstractView(Simulator currentSim) {
        this.currentSim = currentSim;
        currentSim.addView(this);
        isGraphical = false;
    }

    public Simulator getCurrentSim() {
        return currentSim;
    }

    public void updateView() { }

    public Boolean getIsGraphical() {
        return isGraphical;
    }

    protected void setIsGraphical(boolean isGraphical) {
        this.isGraphical = isGraphical;
    }
}
