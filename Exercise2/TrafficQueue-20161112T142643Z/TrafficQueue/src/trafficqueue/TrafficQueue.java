/*
 * TrafficQueue.java       Francis Kingsworth Prah    COMP70110 Java   Ex2 Part 1
 * Represents a Queue implementation in a class called TrafficQueue
 * The Class also contains a sequence of car objects implemented using an Array
 */
//package trafficqueue;
//
//public class TrafficQueue {
//    /**
//     * Instance Variables
//     */
//    private Car[] cars;       //An array of cars
//    private int queueSize;    // the size of the queue
//
//    /**
//     * Initialises an empty car queue
//     * @param queueSize the size of the queue
//     */
//    public TrafficQueue(int queueSize) {
//        cars = new Car[queueSize];
//
//    }
//
//    /**
//     * Adds a car to the back of the queue
//     * @param car is an element of cars
//     */
//    public void add(Car car) {
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
//    public Car remove() {
//
//        Car carRemoved = null; //Returns null if Queue is empty
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
//    public int getNumberOfCarsInQueue() {
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
//        return queueSize == cars.length; //Alternatively could be return getNumberOfCarsInQueue()== cars.length
//    }
//
//    /**
//     * Returns a String representation of the Queue
//     * @return a string of car queue representation
//     */
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
//        TrafficQueue queue1 = new TrafficQueue(5);
//        Car car1 = new Car("Mazda", "Pink", 90);
//        Car car2 = new Car("Lamborghini", "Black", 80);
//        Car car3 = new Car("Benz", "Blue", 10);
//
//        queue1.add(car1);     // Add car1 to the Queue
//        queue1.add(car2);     // Add car 2 to the Queue
//        queue1.add(car3);     //Add car3 to the Queue
//        
//        System.out.println("" + queue1.toString());
//
//        System.out.println("Removing car..." + queue1.remove());
//        System.out.println("Removing car..." + queue1.remove());
//
//        System.out.println("" + queue1.toString());
//
//        System.out.println("Number of Cars in Queue" + " " + "=" + " " + queue1.getNumberOfCarsInQueue());
//
//        System.out.println("" + queue1.toString());
//
//        TrafficQueue queue2 = new TrafficQueue(5);
//
//        System.out.println("Using a For loop and adding 5 cars to queue2(Of the same type):");
//
//        Car car4 = new Car("Toyota", "Green", 60);
//        for (int p = 1; p <= 5; p++) {
//            queue2.add(car4);
//        }
//        System.out.println("" + queue2.toString());
//        queue2.add(car4);
//        
//        System.out.println("" + queue2.toString());
//        
//    }
//
//}

/**
 * ***********************************************************************SAMPLE  OUTPUT 1*********************************************************************************
 */
/**
 * run: 
 * 
 * Pink Mazda 90 
 * Black Lamborghini 80 
 * Blue Benz 10
 *
 * Removing car...Pink Mazda 90 
 * Removing car...Black Lamborghini 80 
 * Blue Benz 10
 *
 * Number of Cars in Queue = 1 
 * Blue Benz 10
 *
 * Using a For loop and adding 5 cars to queue2(Of the same type): 
 * Green Toyota 60 
 * Green Toyota 60 
 * Green Toyota 60 
 * Green Toyota 60 
 * Green Toyota 60
 *
 * Queue full
 * Java Result: 1
 */


//*************************************************************************************************************************************************

/*
 * TrafficQueue.java       Francis Kingsworth Prah    COMP70110 Java   Ex2 Part 2
 * Represents a Queue implementation in a class called TrafficQueue
 * The Class also contains a sequence of car objects implemented using an Array
 */
package trafficqueue;

public class TrafficQueue {

    /**
     * Instance Variables
     */
    private Car[] cars;        //An array of cars
    private int queueSize;     // the size of the queue
    private int front = 0;     //index of first elemrnt of car queue
    private int back = 0;      // index of last element of car queue

    /**
     * Initialises an empty car queue
     *
     * @param queueSize the size of the queue
     */
    public TrafficQueue(int queueSize) {
        cars = new Car[queueSize];

    }

    /**
     * Adds a car to the queue
     *
     * @param car the car to add
     */
    public void add(Car car) {
        if (this.isFull()) {
            System.out.println("Queue Full");
            System.exit(1);
        } else {
            cars[back++] = car;
            if (back == cars.length) {
                back = 0;
            }
            queueSize++;
        }
    }

    /**
     * Removes a car from the queue
     *
     * @return the car removed
     */
    public Car remove() {

        if (isEmpty()) {
            System.out.println("Removing from  empty queue");
        }
        Car car = cars[front];
        cars[front] = null;
        queueSize--;
        front++;
        if (front == cars.length) {
            front = 0;
        }
        return car;

    }

    /**
     * Returns the number of cars in the queue
     *
     * @return the queueSize of the queue
     */
    public int getNumberOfCarsInQueue() {

        return queueSize;
    }

    /**
     * finds whether the car queue is empty
     *
     * @return true if empty otherwise false
     */
    public boolean isEmpty() {

        return queueSize == 0;
    }

    /**
     * finds whether the Car queue is full
     *
     * @return true if queue is full otherwise false
     */
    public boolean isFull() {

        return queueSize == cars.length; //Alternatively could be return getNumberOfCarsInQueue()== cars.length
    }

    /**
     * Returns a String representation of the queue
     *
     * @return the result from the queue.
     */
    public String toString() {
        String result = String.format("The number of cars in the queue is %d\n", queueSize);
        if (queueSize == 0) {
            return result;
        } else if (front >= back) {
            for (int y = front; y < queueSize; y++) {
                result = result + cars[y].toString() + "\n";
            }
            for (int y = 0; y < back; y++) {
                result = result + cars[y].toString() + "\n";
            }
        } else {
            for (int y = front; y < back; y++) {
                result = result + cars[y].toString() + "\n";
            }
        }
        return result;
    }

//***********************************************************************************************************************************************************************************************
    /**
     * Main Program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initial Values assigned to Constructor

        TrafficQueue queue1 = new TrafficQueue(5);
        Car car1 = new Car("Mazda", "Pink", 90);
        Car car2 = new Car("Lamborghini", "Black", 80);
        Car car3 = new Car("Benz", "Blue", 10);

        queue1.add(car1); //add car 1 to the queue
        queue1.add(car2); //add car 2 to the queue
        queue1.add(car3); //add car 3 to the queue

        System.out.println("Printing Queue 1\n" + queue1.toString());

        System.out.println("Removing car..." + queue1.remove());

        System.out.println("Removing car..." + queue1.remove());

        System.out.println("" + queue1.toString());

        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------     
        TrafficQueue queue2 = new TrafficQueue(5);

        System.out.println("Using a For loop and adding 5 cars to queue2(Of the same type):");

        Car car4 = new Car("Toyota", "Green", 60);
        for (int p = 1; p <= 5; p++) {
            queue2.add(car4);
        }
        System.out.println("" + queue2.toString());

//        Car car5 = new Car("Nissan", "White", 400);
//        queue2.add(car5);
//        System.out.println(queue2.toString());
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
        TrafficQueue queue3 = new TrafficQueue(15);
        System.out.println(queue3.toString());
        Car car6 = new Car("Lexus", "White", 400);

        for (int t = 1; t <= 5; t++) {
            for (int j = 1; j <= 100; j++) {
                queue3.add(car6);
                if (j == 100) {
                    System.out.println(queue3);
                } else {
                    queue3.remove();

                }
            }

        }

    }

}
//************************************************************************SAMPLE OUTPUT 2***************************************************************************************************************
/**
*run:
*Printing Queue 1
*The number of cars in the queue is 3
*Pink Mazda 90
*Black Lamborghini 80
*Blue Benz 10

*Removing car...Pink Mazda 90
*Removing car...Black Lamborghini 80
*The number of cars in the queue is 1
*Blue Benz 10

*Using a For loop and adding 5 cars to queue2(Of the same type):
*The number of cars in the queue is 5
*Green Toyota 60
*Green Toyota 60
*Green Toyota 60
*Green Toyota 60
*Green Toyota 60

*The number of cars in the queue is 6
*White Nissan 400

*The number of cars in the queue is 0

*The number of cars in the queue is 1
*White Lexus 400

*The number of cars in the queue is 2
*White Lexus 400
*White Lexus 400

*The number of cars in the queue is 3

*The number of cars in the queue is 4
*White Lexus 400
*White Lexus 400
*White Lexus 400
*White Lexus 400

*The number of cars in the queue is 5
*White Lexus 400
*White Lexus 400
*White Lexus 400
*White Lexus 400
*White Lexus 400
**/