package nl.simulator.mvc.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

import nl.simulator.mvc.controller.Controller;
import nl.simulator.mvc.model.Simulator;
import nl.simulator.mvc.view.CarparkView;
import nl.simulator.mvc.model.CarparkHandler;
import nl.simulator.mvc.view.StatsView;

import java.awt.*;

/**
 * Created by Bugstorm on 9-4-2016.
 * This class will handle the setup of the project]
 * This includes the creation and setup of the biggest part of the GUI
 */
public class Main {
    private Simulator simulator;
    private CarparkHandler carparkHandler;
    private CarparkView carParkView;
    private Controller simulatorController;
    private JFrame screen;
    private StatsView statsView;

    public Main() {
        carparkHandler = new CarparkHandler(3, 6, 30);
        simulator = new Simulator(carparkHandler);
        carParkView = new CarparkView(simulator, carparkHandler);
        simulatorController = new Controller(simulator);

        screen =  new JFrame("Carpark simulator");
        JPanel carPanel = new JPanel();
        carPanel.add(carParkView);
        screen.add(carPanel, BorderLayout.CENTER);
        screen.add(simulatorController, BorderLayout.SOUTH);

        statsView = new StatsView(simulator);
        statsView.setLayout(new BoxLayout(statsView, BoxLayout.Y_AXIS));
        screen.add(statsView, BorderLayout.EAST);

        screen.pack();
        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screen.setVisible(true);

        carParkView.updateView();
        setIcon();
    }

    private void setIcon() {
        try {
            screen.setIconImage(ImageIO.read(new File("res/carIcon.png")));
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
