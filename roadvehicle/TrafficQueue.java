
package roadvehicle;

import java.util.*;
import java.awt.Graphics;
import simplesim.*;
import java.awt.Color;


/*
 * TrafficQueue.java       Francis Kingsworth Prah    COMP70110 Java   Ex3 Part 1
 * Represents a Queue implementation in a class called TrafficQueue
 * The Class also contains a sequence of car objects implemented using an Array
 */

//
//public class TrafficQueue {
//    /**
//     * Instance Variables
//     */
//    private RoadVehicle[] cars;       //An array of cars
//    private int queueSize;    // the size of the queue
//
//    /**
//     * Initialises an empty car queue
//     * @param queueSize the size of the queue
//     */
//    public TrafficQueue(int queueSize) {
//        cars = new RoadVehicle[queueSize];
//
//    }
//
//    /**
//     * Adds a car to the back of the queue
//     * @param car is an element of cars
//     */
//    
//    public void add(RoadVehicle car) {
//        if (this.isFull()) {
//
//            System.out.println("Queue full");
//
//            System.exit(1);
//
//        } else {
//
//            cars[queueSize] = car;
//            queueSize++;
//
//        }
//    }
//    
//    /**
//     * Returns the car removed
//     * @return car removed from the queue
//     */
//    public RoadVehicle remove() {
//
//        RoadVehicle carRemoved = null; //Returns null if Queue is empty
//
//        if (this.isEmpty()) {
//
//            System.out.println("removing from  empty queue");
//
//        } else {
//            carRemoved = cars[0];
//
//            this.queueSize--;
//
//            for (int k = 0; k < this.queueSize; k++) {
//
//                this.cars[k] = this.cars[k + 1];
//            }
//        }
//        return carRemoved;
//
//    }
//
//    /**
//     * Returns number of cars in the queue
//     * @return the size of the queue
//     */
//    public int getNumberOfVehiclesInQue() {
//
//        return queueSize;
//    }
//
//    /**
//     * Checks whether Queue is Empty
//     * @return true if car queue is empty otherwise false
//     */
//    public boolean isEmpty() {
//
//        return queueSize == 0;
//    }
//
//    /**
//     * Checks whether the Queue is full
//     * @return true if car queue is full otherwise false
//     */
//    public boolean isFull() {
//
//        return queueSize == cars.length; //Alternatively could be return getNumberOfVehiclesInQue()== cars.length
//    }
//
//    /**
//     * Returns a String representation of the Queue
//     * @return a string of car queue representation
//     */
//    @Override
//    public String toString() {
//        String result = "";
//
//        for (int z = 0; z < queueSize; z++) {
//            result = result + cars[z].toString() + "\n";
//        }
//
//        return result;
//    }
//
//    /**
//     * Main Program
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // Initialisation of values in Constructor
//
//    TrafficQueue queue1 = new TrafficQueue(10);
//    
//       Car car1 = new Car("Ferrari", "Red");     //car1 first object
//       Car car2 = new Car("Volvo", "Blue");      //car2 second object  
//       
//       Bus bus1 = new Bus("Blue",30);
//       Bus bus2 = new Bus("White",45);
//       
//       GoodsVehicle goodsVehicle1 = new GoodsVehicle("Green","Van",4200);
//       GoodsVehicle goodsVehicle2 = new GoodsVehicle("Black","tanker",3200);
//       
////       queue1.add(car1);
////       queue1.add(car2);
////       
////       queue1.add(bus1);
////       queue1.add(bus2);
////       
////       queue1.add(goodsVehicle1);
////       queue1.add(goodsVehicle2);
////       
////       System.out.println(queue1);
////       
////       
//     RoadVehicle[] someVehicles = {car1,car2,bus1,bus2, goodsVehicle1, goodsVehicle2};
////      
////      System.out.println((Arrays.toString(someVehicles)));
//      
//     Random r = new Random();
//        
//        
//        
//        System.out.println("Adding 10 Random Vehicles..." + "\n");
//
//        
//        
//        for (int p=0; p<=9;p++){
//            queue1.add(someVehicles[r.nextInt(someVehicles.length)]);
//        
//        }
//        System.out.println(queue1);
//        
//        
//        
//    }
//
//}
//*********************************************************************SAMPLE OUTPUT*************************************************************
//run:
//Adding 10 Random Vehicles...
//
//Red Ferrari
//Blue bus with 30 seats
//Red Ferrari
//Red Ferrari
//Blue bus with 30 seats
//White bus with 45 seats
//Blue Volvo
//Black tanker max weight = 3200
//Green Van max weight = 4200
//Black tanker max weight = 3200


/*
 * TrafficQueue.java       Francis Kingsworth Prah    COMP70110 Java   Ex4 
 * Represents a Queue implementation in a class called TrafficQueue
 * The Class also contains a sequence of car objects implemented using an Array
 */
//v
/**
 *
 * 
 * @author acer
 */
public class TrafficQueue implements ThingBeingSimulated {

    /**
     * Instance Variables
     */
    private ArrayList<RoadVehicle> vehicle;
    //private RoadVehicle[] cars;        //An array of cars
    private int queueSize = 0;     // the size of the queue
    private int front = 0;     //index of first element of car queue
    private int back = 0;      // index of last element of car queue
    private int maxCapacity = 20;

    private static final ArrayList<RoadVehicle> someVehicles = new ArrayList<>(Arrays.asList(new Car("Ferrari", "Red"), new Car("Volvo", "Blue"), new Bus("Blue", 30), new Bus("White", 45), new GoodsVehicle("Green", "Van", 4200), new GoodsVehicle("Black", "tanker", 3200), new FireEngine("Red", true), new FireEngine("Green", false)));
    private Random rand1 = new Random();           // Generates Random Vehicles
    private static String[] arr = new String[20];  // An array to hold vehicles

    private String vehicleCharArr;                           // Array to hold a summary of vehicle characters
    private ArrayList<String> list1 = new ArrayList<>();

    private static Map<String, Color> myColorMap = new HashMap<>();  // Hashmap to convert String to color Objects
   

    private RoadVehicle vehicleAdded;  // Last vehicle added
    private int maxX, maxY; // Boundary within which we'll display the summary

    
   
    /**
     * Initialises an empty car queue
     *
     * @param maxCapacity the maximum capacity of the vehicle ArrayList
     */
    public TrafficQueue(int maxCapacity) {
        //cars = new RoadVehicle[queueSize];
        vehicle = new ArrayList<RoadVehicle>(Collections.nCopies(this.getMaxCapacity(), (RoadVehicle) null)); //initialise vehicle arraylist with nulls 
        list1 = new ArrayList<String>(Collections.nCopies(this.getMaxCapacity(), (String) null));  //initialise list1 to null
    }
//============================================================================================================================================================================================

    /**
     * Adds a car to the queue
     *
     * @param car the car to add
     */
//    public void add( RoadVehicle roadVehicle) {
//        if (this.isFull()) {
//            System.out.println("Queue Full" + "\n");
//           
//            //System.exit(1);
//        } else {
//            vehicle.set(queueSize, roadVehicle);
//            back=back+1;
//            if (back == vehicle.size()) {
//                back = 0;
//            }
//            
//            queueSize++;
//        }
//    }
    //******************************* ******************************************************************************************
//    public void add(RoadVehicle roadVehicle) {
//
//        EmergencyVehicle tcar = null;
//        if ((roadVehicle instanceof EmergencyVehicle)) {
//            tcar = (EmergencyVehicle) roadVehicle;
//
//            // System.out.println(tcar.getCode());
//        }
//        if (this.isFull()) {
//
//            System.out.println("Queue full");
//
//            //System.exit(1);
//
//        }
//
//        if ((roadVehicle instanceof EmergencyVehicle) && tcar.getCode() == true) {
//            this.addToFront(roadVehicle);
//        } else {
//            this.addToBack(roadVehicle);
//        }
//
//    }
//
//    private void addToBack(RoadVehicle roadVehicle) {
//
//        vehicle.set(back, roadVehicle);
//        back = back + 1;
//        queueSize++;
//    }
//
//    /**
//     *
//     * @param car the element to be added
//     */
//    public void addToFront(RoadVehicle roadVehicle) {
//
//        if (front == 0) {
//            vehicle.set(front = vehicle.size() - 1, roadVehicle);
//        } else {
//            vehicle.set((--front) % vehicle.size(), roadVehicle);
//        }
//
//        queueSize++;
//    }
//============================================================================================================================================================================================= 
    /**
     * Removes a car from the queue
     *
     * @return the roadVehicle removed
     */
    public RoadVehicle remove() {

        if (isEmpty()) {
            throw new RuntimeException("removing from empty queue");
        }
        RoadVehicle roadVehicle = vehicle.get(front);
        vehicle.set(front, null);
        queueSize--;
        front++;
        if (front == vehicle.size()) {
            front = 0;
        }

        return roadVehicle;

    }

    /**
     *
     * @param roadVehicle the roadVehicle to be added
     * @throws QueueFullException the Custom Exception
     */
    public void add(RoadVehicle roadVehicle) throws QueueFullException {

        if (this.isFull()) {

            // System.out.println("Queue full");
            //System.exit(1);
            throw new QueueFullException(this.getMaxCapacity(), "Queue full: max capacity");
        } else {

            vehicle.set(back, roadVehicle);
            back = back + 1;
            if (back == vehicle.size()) {
                back = 0;
            }

            queueSize++;

        }

    }

    /**
     * Returns the car removed
     *
     * @return car removed from the queue
     */
//    public RoadVehicle remove() {
//
//        RoadVehicle carRemoved = null; //Returns null if Queue is empty
//        carRemoved = vehicle.set(0, vehicle.get(0));
//
//        if (this.isEmpty()) {
//
//            System.out.println("removing from  empty queue");
//
//        } else {
//            vehicle.set(front, vehicle.get(front));
//
//            this.queueSize--;
//            front++;
//            if (front == vehicle.size()) {
//                front = 0;
//            }
//            for (int k = 0; k < this.queueSize; k++) {
//
//                this.vehicle.set(k, this.vehicle.get(k + 1));
//            }
//        }
//        return carRemoved;
//
//    }
//    public RoadVehicle
    /**
     * Returns the number of cars in the queue
     *
     * @return the queueSize of the queue
     */
    public int getNumberOfVehiclesInQue() {

        return vehicle.size(); // (back + array.length - front) % array.length
    }

    /**
     * finds whether the car queue is empty
     *
     * @return true if empty otherwise false
     */
    public boolean isEmpty() {

        return queueSize == 0; //return front == back;
    }

    /**
     * finds whether the Car queue is full
     *
     * @return true if queue is full otherwise false
     */
    public boolean isFull() {

        return queueSize == this.maxCapacity; //Alternatively could be return getNumberOfVehiclesInQue()== vehicle.Size()
    }

    //==================================================================================================================================================
    /**
     *
     * @return the maxCapacity of the Vehicle ArrayList
     */
    public int getMaxCapacity() {
        this.maxCapacity = 20;
        return maxCapacity;
    }
    
    /**
     *
     * Display the current Summary of the Queue on the Screen
     * @param g the Graphics object
     */
    @Override
    public void display(Graphics g) {

        maxX = 10;
        maxY = 10;

        g.drawString("Last item added to Queue is" + " " + vehicleAdded, maxX, maxY + 10);

        g.drawString("Summary of Queue is" + " " + "\n" + vehicleCharArr, maxX, maxY + 20);

        for (int i = 0; i < vehicle.size(); i++) {
            for (Map.Entry<String, Color> entry : myColorMap.entrySet()) {
                if (entry.getKey().equals(list1.get(i))) {
                    g.setColor(myColorMap.get(list1.get(i)));
                }
            }

        }


    }
    
     /**
     *
     * Set the bounds within which the Summary of the Queue will be displayed
     * @param maxX the maxX Boundary
     * @param maxY the maxY Boundary
     */
    @Override
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     *
     * Resets the Simulation to its original state
     * @return new TrafficQueue of its original size
     */
    @Override
    public ThingBeingSimulated reset() {

        return new TrafficQueue(maxCapacity);
    }

    /**
     *
     * Performs the Simulation
     * @throws simplesim.SimulationException the SimulationException
     */
    
    @Override
    public void simstep() throws SimulationException {
        System.out.println("Adding some Random Vehicles..." + "\n");

        //Fill the  Queue
        try {
            for (int p = 0; p < 20; p++) {
                this.add(someVehicles.get(rand1.nextInt(someVehicles.size())));
            }
        } catch (QueueFullException ex) {
            System.out.println(ex.getMessage());
        }

        //Basic SimStep Operations       
        if (this.isFull()) {

            System.out.println("Removing vehicle...." + "  " + this.remove());

            System.out.println("Adding with a Random Vehicle...");
            vehicleAdded = (someVehicles.get(rand1.nextInt(someVehicles.size())));
            try {
                this.add(vehicleAdded);
            } catch (QueueFullException ex1) {
                throw new SimulationException(ex1.getMessage());  // throw new SimulationException, Convert QueueFullException to SimulationException
            }


            System.out.println("Summary of Queue" + vehicle.toString());
            System.out.println("Get Last item added in Queue " + vehicleAdded); 
            
            // Get Vehicle Characters using Dynamic Binding
            for (int f = 0; f < vehicle.size(); f++) {
                RoadVehicle r = vehicle.get(f);
                arr[f] = r.getVehicleCharacter();
                list1.set(f, r.getVehicleCharacter());

            }
            //Convert vehicle Array to print
            vehicleCharArr = (Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "").trim());

//            System.out.println(Arrays.toString(arr));
//            System.out.println(list1);
            System.out.println("Queue Summary Character" + " " + vehicleCharArr);

        }

// Assign Values to ColorMap
        myColorMap.put("B", Color.RED);
        myColorMap.put("C", Color.BLUE);
        myColorMap.put("G", Color.GREEN);
        myColorMap.put("F", Color.MAGENTA);

    }

//    }
    /**
     * Returns a String representation of the queue
     *
     * @return the result from the queue.
     */
    @Override
    public String toString() {
        String result = String.format("Traffic Queue Simulation " + "\n", this.vehicle.size());
        if (queueSize == 0) {
            return result;
        }
        if (front >= back) {
            for (int y = front; y < vehicle.size(); y++) {
                result = result + vehicle.get(y).toString() + "\n";
            }
            for (int y = 0; y < back; y++) {
                result = result + vehicle.get(y).toString() + "\n";
            }
        } else {
            for (int y = front; y < back; y++) {
                result = result + vehicle.get(y).toString() + "\n";
            }
        }
        return result;
    }

    /**
     * Main Program
     * Test the Simulation
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //initial Values assigned to Constructor
//        Car car1 = new Car("Ferrari", "Red");     //car1 first object
//        Car car2 = new Car("Volvo", "Blue");      //car2 second object  
//
//        Bus bus1 = new Bus("Blue", 30);
//        Bus bus2 = new Bus("White", 45);
//
//        GoodsVehicle goodsVehicle1 = new GoodsVehicle("Green", "Van", 4200);
//        GoodsVehicle goodsVehicle2 = new GoodsVehicle("Black", "tanker", 3200);
//
//        FireEngine fireEngine1 = new FireEngine("Red", true);
//        FireEngine fireEngine2 = new FireEngine("Green", false);
//
        TrafficQueue queue1 = new TrafficQueue(20);   // Create new queue queue1
        TrafficQueue queue2 = new TrafficQueue(20);   // Create new queue queue2

//        queue1.add(car1);
//        queue1.add(car2);
//
//        queue1.add(bus1);
//        queue1.add(bus2);
//
//
//        
//        queue1.add(goodsVehicle1);
//        queue1.add(goodsVehicle2);
//        
//        queue1.add(fireEngine1);
//        queue1.add(fireEngine2);
//        
//        System.out.println("Printing Queue...");
//        System.out.println(queue1.toString());
//        RoadVehicle[] someVehicles = {car1, car2, bus1, bus2, goodsVehicle1, goodsVehicle2, fireEngine1, fireEngine2};
        //System.out.println("Printing Contents of the Arrray..");
        // System.out.println((Arrays.toString(someVehicles))); 
//        ArrayList<RoadVehicle> someVehicles = new ArrayList<>(Arrays.asList(car1, car2, bus1, bus2, goodsVehicle1, goodsVehicle2, fireEngine1, fireEngine2));
        Random r = new Random();  

//        System.out.println("Adding some Random Vehicles..." + "\n");
//
//        for (int k = 0; k<= 20; k++)
//            try {
//                queue1.add(someVehicles.get(r.nextInt(someVehicles.size())));
//            } catch (QueueFullException ex) {
//                System.out.println(ex.getMessage());
//            }
        SimulationFrame f = new SimulationFrame(500, 400, queue1);
        f.setVisible(true);

        System.out.println("***************************************************************************************************");
        System.out.println("Tries Summary for QueueFUllException and RuntimeException for add and Remove methods respectively");
        System.out.println("****************************************************************************************************");
        
        //*****************************************************Try for RuntimeException********************************************************
        try {
            queue2.remove();
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        
        //******************************************************Try for QueueFullException*******************************************************************************
        try {
            for (int p = 0; p <= 20; p++) {
                queue2.add(someVehicles.get(r.nextInt(someVehicles.size())));

            }
        } catch (QueueFullException ex) {

            System.out.println(ex.getMessage());
            
            
        } finally {
            System.out.println("Execution complete");  //Finally do this regardless
        }

    }
}



//*************************************************************Sample Output from Console*********************************************
/*run:
Look and feel set to com.sun.java.swing.plaf.windows.WindowsLookAndFeel
***************************************************************************************************
Tries Summary for QueueFUllException and RuntimeException for add and Remove methods respectively
****************************************************************************************************
removing from empty queue
Queue full: max capacity20
Execution complete
Adding some Random Vehicles...

Removing vehicle....  Red Ferrari
Adding with a Random Vehicle...
Summary of Queue[Green fire engine, Blue bus with 30 seats, Black tanker max weight = 3200, Blue bus with 30 seats, Blue bus with 30 seats, Fire engine: CODEBLUE!, White bus with 45 seats, Fire engine: CODEBLUE!, Green Van max weight = 4200, White bus with 45 seats, Green fire engine, Green Van max weight = 4200, Fire engine: CODEBLUE!, Green Van max weight = 4200, Green Van max weight = 4200, Black tanker max weight = 3200, White bus with 45 seats, Green fire engine, Blue bus with 30 seats, Black tanker max weight = 3200]
Get Last item added in Queue Green fire engine
Queue Summary Character FBGBBFBFGBFGFGGGBFBG
Adding some Random Vehicles...

Queue full: max capacity20
Removing vehicle....  Blue bus with 30 seats
Adding with a Random Vehicle...
Summary of Queue[Green fire engine, Red Ferrari, Black tanker max weight = 3200, Blue bus with 30 seats, Blue bus with 30 seats, Fire engine: CODEBLUE!, White bus with 45 seats, Fire engine: CODEBLUE!, Green Van max weight = 4200, White bus with 45 seats, Green fire engine, Green Van max weight = 4200, Fire engine: CODEBLUE!, Green Van max weight = 4200, Green Van max weight = 4200, Black tanker max weight = 3200, White bus with 45 seats, Green fire engine, Blue bus with 30 seats, Black tanker max weight = 3200]
Get Last item added in Queue Red Ferrari
Queue Summary Character FCGBBFBFGBFGFGGGBFBG
Adding some Random Vehicles...

Queue full: max capacity20
Removing vehicle....  Black tanker max weight = 3200
Adding with a Random Vehicle...
Summary of Queue[Green fire engine, Red Ferrari, Blue Volvo, Blue bus with 30 seats, Blue bus with 30 seats, Fire engine: CODEBLUE!, White bus with 45 seats, Fire engine: CODEBLUE!, Green Van max weight = 4200, White bus with 45 seats, Green fire engine, Green Van max weight = 4200, Fire engine: CODEBLUE!, Green Van max weight = 4200, Green Van max weight = 4200, Black tanker max weight = 3200, White bus with 45 seats, Green fire engine, Blue bus with 30 seats, Black tanker max weight = 3200]
Get Last item added in Queue Blue Volvo
Queue Summary Character FCCBBFBFGBFGFGGGBFBG
Adding some Random Vehicles...*/
