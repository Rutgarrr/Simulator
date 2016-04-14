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
    private JButton start;
    private JButton pause;

    public Controller(Simulator simulator) {
        super(simulator);
        this.simulator = simulator;

        step1 = new JButton("1 step");
        start = new JButton("Start");
        pause = new JButton("Pause");
        step1.addActionListener(this);
        start.addActionListener(this);
        pause.addActionListener(this);

        this.add(step1);
        this.add(start);
        this.add(pause);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == step1) {
            simulator.run(1);
        } else if(e.getSource() == start && !simulator.isRunning()) {
            simulator.start();
        } else if(e.getSource() == pause) {
            simulator.stop();
        }
    }
}
