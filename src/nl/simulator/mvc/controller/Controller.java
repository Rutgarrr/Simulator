package nl.simulator.mvc.controller;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public class Controller extends AbstractController implements ActionListener{
    private Simulator simulator;
    private JButton step1;
    private JButton step100;
    private JButton start;
    private JButton stop;

    public Controller(Simulator simulator) {
        super(simulator);
        this.simulator = simulator;

        step1 = new JButton("1 step");
        step100 = new JButton("100 steps");
        start = new JButton("Start");
        stop = new JButton("Stop");
        step1.addActionListener(this);
        step100.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);

        this.add(step1);
        this.add(step100);
        this.add(start);
        this.add(stop);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == step1) {
            simulator.run(1);
        } else if(e.getSource() == step100) {
            simulator.run(100);
        } else if(e.getSource() == start) {
            simulator.start();
        } else if(e.getSource() == stop) {
            simulator.stop();
        }
    }
}
