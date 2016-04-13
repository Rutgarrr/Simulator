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

    public Controller(Simulator simulator) {
        super(simulator);
        this.simulator = simulator;

        step1 = new JButton("1 step");
        step100 = new JButton("100 steps");
        step1.addActionListener(this);
        step100.addActionListener(this);

        this.add(step1);
        this.add(step100);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == step1) {
            simulator.run(1);
        } else if(e.getSource() == step100) {
            simulator.run(100);
        }
    }
}
