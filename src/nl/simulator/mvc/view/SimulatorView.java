package nl.simulator.mvc.view;

import nl.simulator.mvc.model.Car;
import nl.simulator.mvc.model.Location;
import nl.simulator.mvc.model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulatorView extends JFrame {
    private CarparkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;


    //current simulator object is stored here, not used
    private Simulator currentSim; // Not necessary

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces, Simulator currentSim) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        this.currentSim = currentSim; // Not necessary, not used

        carParkView = new CarparkView(currentSim, this);

        // TODO Move and refactor GUI, MVC
        Container contentPane = getContentPane();
        //contentPane.add(stepLabel, BorderLayout.NORTH);
        contentPane.add(carParkView, BorderLayout.CENTER);
        //contentPane.add(population, BorderLayout.SOUTH);

        //Creation of testpanel and the buttons to move the time and and hundred steps
        JPanel testPanel = new JPanel();
        contentPane.add(testPanel, BorderLayout.SOUTH);
        JButton oneStep = new JButton("Move one step");
        oneStep.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSim.run(1);
            }
        });
        testPanel.add(oneStep);
        JButton hundredStep = new JButton("Move hundred steps");
        hundredStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 100; i++) {
                    currentSim.run(1);
                    paintComponents(carParkView.getGraphics());
                }
            }
        });
        testPanel.add(hundredStep);


        pack();
        setVisible(true);

        updateView();
    }

    public void updateView() {
        carParkView.updateView();
    }
    
     public int getNumberOfFloors() {
            return numberOfFloors;
        }
    
        public int getNumberOfRows() {
            return numberOfRows;
        }
    
        public int getNumberOfPlaces() {
            return numberOfPlaces;
        }
    
        public Car getCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            return cars[location.getFloor()][location.getRow()][location.getPlace()];
        }
    
        public boolean setCarAt(Location location, Car car) {
            if (!locationIsValid(location)) {
                return false;
            }
            Car oldCar = getCarAt(location);
            if (oldCar == null) {
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                return true;
            }
            return false;
        }
    
        public Car removeCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            Car car = getCarAt(location);
            if (car == null) {
                return null;
            }
            cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
            car.setLocation(null);
            return car;
        }
    
        public Location getFirstFreeLocation() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);

                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
            return null;
        }
    
        public Car getFirstLeavingCar() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                            return car;
                        }
                    }
                }
            }
            return null;
        }
    
        public void tick() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null) {
                            car.tick();
                        }
                    }
                }
            }
        }
    
        private boolean locationIsValid(Location location) {
            int floor = location.getFloor();
            int row = location.getRow();
            int place = location.getPlace();
            if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
                return false;
            }
            return true;
        }
}
