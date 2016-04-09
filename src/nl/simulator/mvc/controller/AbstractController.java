package nl.simulator.mvc.controller;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;

/**
 * Created by Bugstorm on 9-4-2016.
 */
public abstract class AbstractController extends JPanel {
    protected Simulator model; //TODO Add new class model!

    public AbstractController (Simulator model) {
        this.model = model;
    }
}
