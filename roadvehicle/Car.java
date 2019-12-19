
package roadvehicle;
import java.awt.*;
/*
 * Car.java    Francis  Kingsworth Prah      COMP70110 Java   Ex4
 * 
 * Represents different Aspects of cars such as colour, make and current fuel levels
 * Also shows assignment of two different variables using the equal sign during object creation
 */
/**
 *
 * A class to represent a car of a specific make,colour and fuel.
 */
public class Car extends RoadVehicle {

    /**
     * Instance Variables
     */
    private String make;      // the make of the car
    private String vehicleCharacter;
   

    /**
     *
     * @param make the make of the car
     * @param colour the colour of the car
     */
    public Car(String make, String colour) {
        super(colour);
        this.make = make;
        this.vehicleCharacter = "C";
  

    }

    /**
     * Returns the make of a car Object
     *
     * @return the make
     */
    public String getMake() {
        return make;
    }

    @Override
    public String getVehicleCharacter() {
        return this.vehicleCharacter;
    }

  

    /**
     * Returns a string representation of the car
     *
     * @return the colour, make and the fuel
     */
    @Override
    public String toString() {
        return this.getColour() + " " + this.getMake();
    }//***************************************************************************************************************************************************

    /**
     * Test the Car Class
     *
     * @param args
     */
    public static void main(String[] args) {
        // initial Values assigned to Constructor

        Car car1 = new Car("Ferrari", "Red");     //car1 first object
        Car car2 = new Car("Volvo", "Blue");      //car2 second object

        Car car3 = car2; //create a new car object called car3 of type car equal to car2

        //----------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.printf("The make,Colour of the Original Car is %s%n", car1.toString());

        car1.setColour("Yellow"); //assign new colour to car1

        System.out.printf("The make, New Colour of the Original Car is %s%n", car1.toString());

        System.out.printf("The current fuel level in the First Car is %s%n", car1.getFuel());

        car2.addFuel(20);// add 20 units of fuel to car2

        car2.setColour("Green"); //set colour of car2 to green

        car3.useFuel(10); //use 10 units of fuel in car3

        System.out.printf("The current fuel level in the second Car is %s%n", car2.getFuel());

        System.out.printf("The current fuel level in the third Car is %s%n", car3.getFuel());

        System.out.printf("The make, colour of the second car(car2)  is %s%n", car2.toString());

        System.out.printf("The make, colour of the third  car(car3)  is %s%n", car3.toString());
    }

}
