package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public class AbstractView extends JPanel {
    protected Simulator model;

    public AbstractView(Simulator model) {
        this.model = model;
    }

    public Simulator getModel() {
        return model;
    }
}
