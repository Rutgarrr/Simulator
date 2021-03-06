package nl.simulator.mvc.model;

import nl.simulator.mvc.view.*;

import java.util.ArrayList;
import java.util.Random;

public class Simulator implements Runnable {

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private CarparkHandler carparkHandler;
    private Boolean running;
    private ArrayList<AbstractView> views;
    private int[] fullTime;

    // Generates the initial amount of passholders
    private int maxPassHolder = 50;

    //This variable keeps track of the amount of passholder car objects.
    private int passCarAmount = 0;
    private int adHocCarAmount = 0;

    private int tickPause = 100;

    Random random;

    // Here gets the average of cars per hour stored in
    int averageNumberOfCarsPerHour;

    private int weekDayArrivals = 50; // average number of arriving cars per hour
    private int weekendArrivals = 90; // average number of arriving cars per hour

    private int enterSpeed = 3; // number of cars that can enter per minute
    private int paymentSpeed = 10; // number of cars that can pay per minute
    private int exitSpeed = 9; // number of cars that can leave per minute

    private Time time; // Variable to store the time in

    public Simulator(CarparkHandler carparkHandler) {
        this.carparkHandler = carparkHandler;
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        time = new Time();
        random = new Random();
        views =  new ArrayList<AbstractView>();
        fullTime = new int[5];
    }

    public void run(int minutes) {
        for (int i = 0; i < minutes; i++) {
            tick();
        }
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            tick();
        }
    }

    public boolean isRunning() {
        if (running == null) {
            return false;
        }
        return running;
    }

    // Calculates average number of cars per hour and stores it in the averageNumberOfCars variable
    // From tick
    private void setAvgNumberOfCarsPerHour(int day){
        this.averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;
    }

    // Calculates cars per minute (a little random) and returns this number
    // From tick
    private int carsPerMinute(){
        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
        return numberOfCarsPerMinute;
    }

    // Add the cars to the back of the queue.
    // From tick
    private void addCarsToQueue(){

        for (int i = 0; i < carsPerMinute(); i++) {
            if(random.nextInt(100) < 10 && passCarAmount < maxPassHolder){
                Car car = new PassHolderCar();
                entranceCarQueue.addCar(car);
                passCarAmount++;
            }else{
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
                adHocCarAmount++;
            }
        }
    }

    // Remove car from the front of the queue and assign to a parking space.
    private void assignToParkingSpot(){

        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = carparkHandler.getFirstFreeLocation();
            if (freeLocation != null) {
                carparkHandler.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }
    }

    // Add leaving cars to the payment queue.
    // But if the car is an instance of passholder then add this car to the exit queue
    private void addCarToExitQueue(){

        while (true) {
            Car car = carparkHandler.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            //Checks if the car is an passholder car, if yes then he can skip the payment queue
            if (car instanceof PassHolderCar || car instanceof ReservationCar) {
                carparkHandler.removeCarAt(car.getLocation());
                exitCarQueue.addCar(car);
            }else{
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }
        }
    }

    // Let cars pay.
    private void letCarsPay(){

        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            carparkHandler.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }
    }

    // Let cars leave.
    private void letCarsLeave(){

        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // If the leaving car is a passholder car, then decrease the amount of passCarAmount by one.
            if (car instanceof PassHolderCar){
                passCarAmount--;
            }else{
                adHocCarAmount--;
            }
            // Bye!
        }
    }

    // Lets the program pause for a time that is stored in the variable tickPause in miliseconds
    private void pause(){
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // set the maximum amount of passholders
    public void setMaxPassHolder(int maxPassHolder)
    {
        this.maxPassHolder = maxPassHolder;
    }

    public void addView(AbstractView view) {
        views.add(view);
    }

    public void updateViews() {
        for (AbstractView view:views) {
            view.updateView();
        }
    }

    public int getAdHocCarAmount(){
        return adHocCarAmount;
    }

    public int getPassCarAmount(){
        return passCarAmount;
    }

    public int totalAmountOfCars(){
        return adHocCarAmount + passCarAmount;
    }

    public int totalCarsInEntrance(){
        return entranceCarQueue.size();
    }

    public int totalCarsInPayment(){
        return paymentCarQueue.size();
    }

    public int totalCarsInExit(){
        return exitCarQueue.size();
    }

    public int[] getTime() {
        return fullTime;
    }

    private void tick() {
        //update time(duh)
        fullTime = time.updateTime();
        setAvgNumberOfCarsPerHour(fullTime[2]);
        addCarsToQueue();
        assignToParkingSpot();


        // Perform car park tick.
        carparkHandler.tick();

        addCarToExitQueue();
        letCarsPay();
        letCarsLeave();

        // Update the car park view.
        updateViews();
        pause();
    }
}
