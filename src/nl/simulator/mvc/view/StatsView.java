package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Simulator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by Bugstorm on 9-4-2016.
 * This class will be used to display the stats.
 */
public class StatsView extends AbstractView {

    JLabel date;
    JLabel clock;
    JLabel adHocCars;
    JLabel passHolderCars;
    JLabel totalCars;
    JLabel carsInEntrance;
    JLabel carsInPayment;
    JLabel carsInExit;

    private int[] time;

    public StatsView(Simulator currentSim) {
        super(currentSim);
        time = new int[5];

        EmptyBorder topBorder = new EmptyBorder(60, 20, 5, 0);
        EmptyBorder clockBorder = new EmptyBorder(5, 65, 5, 0);
        EmptyBorder normBorder = new EmptyBorder(0, 0, 5, 0);

        date = new JLabel();
        clock = new JLabel();
        adHocCars = new JLabel();
        passHolderCars = new JLabel();
        totalCars = new JLabel();
        carsInEntrance = new JLabel();
        carsInPayment = new JLabel();
        carsInExit = new JLabel();

        date.setBorder(topBorder);
        clock.setBorder(clockBorder);
        adHocCars.setBorder(normBorder);
        passHolderCars.setBorder(normBorder);
        totalCars.setBorder(normBorder);
        carsInEntrance.setBorder(normBorder);
        carsInPayment.setBorder(normBorder);
        carsInExit.setBorder(normBorder);

        this.add(date);
        this.add(clock);
        this.add(adHocCars);
        this.add(passHolderCars);
        this.add(totalCars);
        this.add(carsInEntrance);
        this.add(carsInPayment);
        this.add(carsInExit);

        updateView();

    }

    public void updateView(){
        updateTime();
        setDate();
        setClock();
        setAdHocCars();
        setPassHolderCars();
        setTotalCars();
        setCarsInEntrance();
        setCarsInPayment();
        setCarsInExit();
    }

    private void setClock() {
        String minutes = String.valueOf(time[0]);
        if (time[0] < 10) {
            minutes = "0" + minutes;
        }

        clock.setText(time[1] + ":" + minutes);
    }

    private void setDate() {
        date.setText("Week: " + time[3] + "     Day: " + time[2]);
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

    private void updateTime() {
        time = currentSim.getTime();
    }
}
