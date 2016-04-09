package nl.simulator.mvc.main;

import javax.swing.*;

import nl.simulator.mvc.controller.Controller;
import nl.simulator.mvc.model.Simulator;
/**
 * Created by Bugstorm on 9-4-2016.
 * This class will handle the setup of the project]
 * This includes the creation and setup of the biggest part of the GUI
 */
public class Main {
    private Simulator simulator;
    private Controller simulatorController;

    public Main() {
        simulator = new Simulator();
        simulatorController = new Controller(simulator);
    }
}
