package nl.simulator.mvc.controller;

import nl.simulator.mvc.model.Simulator;

import java.awt.event.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public class Controller extends AbstractController implements ActionListener{

    public Controller(Simulator model) {
        super(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty for now
    }
}
