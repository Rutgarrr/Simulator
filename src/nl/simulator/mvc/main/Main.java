package nl.simulator.mvc.main;

import javax.swing.*;

import nl.simulator.mvc.controller.Controller;
import nl.simulator.mvc.model.Simulator;
import nl.simulator.mvc.view.CarparkView;
import nl.simulator.mvc.view.SimulatorView;

import java.awt.*;

/**
 * Created by Bugstorm on 9-4-2016.
 * This class will handle the setup of the project]
 * This includes the creation and setup of the biggest part of the GUI
 */
public class Main {
    private Simulator simulator;
    private SimulatorView simulatorView;
    private CarparkView carParkView;
    private Controller simulatorController;
    private JFrame screen;

    public Main() {
        simulator = new Simulator();
        simulatorView = new SimulatorView(3, 6, 30, simulator);
        carParkView = new CarparkView(simulator, simulatorView);
        simulatorController = new Controller(simulator);

        simulator.addSimulatorView(simulatorView);
        simulatorView.addCarparkView(carParkView);

        screen =  new JFrame("Carpark simulator");
        Container contentPane = screen.getContentPane();
        JPanel carPanel = new JPanel();
        carPanel.add(carParkView);
        screen.add(carPanel, BorderLayout.CENTER);
        screen.add(simulatorController, BorderLayout.SOUTH);

        screen.pack();
        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screen.setVisible(true);

        carParkView.updateView();
    }
}
