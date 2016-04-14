package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;

/**
 * Created by Bugstorm on 9-4-2016.
 * This class will be used to display the stats.
 */
public class StatsView extends AbstractView {

    JLabel adHocCars;
    JLabel passHolderCars;
    JLabel totalCars;
    JLabel carsInEntrance;
    JLabel carsInPayment;
    JLabel carsInExit;

    public StatsView(Simulator currentSim) {
        super(currentSim);
        adHocCars = new JLabel();
        this.add(adHocCars);
        passHolderCars = new JLabel();
        this.add(passHolderCars);
        totalCars = new JLabel();
        this.add(totalCars);
        carsInEntrance = new JLabel();
        this.add(carsInEntrance);
        carsInPayment = new JLabel();
        this.add(carsInPayment);
        carsInExit = new JLabel();
        this.add(carsInExit);
        updateView();

    }

    public void updateView(){
        setAdHocCars();
        setPassHolderCars();
        setTotalCars();
        setCarsInEntrance();
        setCarsInPayment();
        setCarsInExit();
    }

    private void setAdHocCars(){
        adHocCars.setText("Amount of regular cars: " + currentSim.getAdHocCarAmount());
    }

    private void setPassHolderCars(){
        passHolderCars.setText("Amount of Passholder cars: " + currentSim.getPassCarAmount());
    }

    private void setTotalCars(){
        totalCars.setText("Total amount of cars: " + currentSim.totalAmountOfCars());
    }

    private void setCarsInEntrance(){
        carsInEntrance.setText("Amount of cars in entrance queue: " + currentSim.totalCarsInEntrance());
    }

    private void setCarsInPayment(){
        carsInPayment.setText("Amount of cars in payment queue : " + currentSim.totalCarsInPayment());
    }

    private void setCarsInExit(){
        carsInExit.setText("Amount of cars in exit queue " + currentSim.totalCarsInExit());
    }
}
