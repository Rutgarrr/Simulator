package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public class AbstractView extends JPanel {
    protected Simulator currentSim;

    public AbstractView(Simulator currentSim) {
        this.currentSim = currentSim;
    }

    public Simulator getCurrentSim() {
        return currentSim;
    }
}
