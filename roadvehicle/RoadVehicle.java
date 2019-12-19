
package roadvehicle;
import java.awt.*;
/* RoadVehicle.java    Francis  Kingsworth Prah      COMP70110 Java   Ex4
 * 
 * Represents different Aspects of cars such as colour, make and current fuel levels
 * 
 */
/**
 *
 * An Abstract class to represent a car of a specific make,colour and fuel.
 */
public abstract class RoadVehicle {

    /**
     * Instance Variables
     */
    private String colour;    //the colour of the car
    private int fuel;         //the fuel in the car

    /**
     *
     *
     * @param colour the colour of the car
     *
     */
    public RoadVehicle(String colour) {
        this.colour = colour;

        this.fuel = 0;
    }

    /**
     * Sets a new colour to the car
     *
     * @param newColour the new colour of the car
     */
    public void setColour(String newColour) {

        this.colour = newColour;
    }

    /**
     * Returns the colour of a car object
     *
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Gets the fuel in a car
     *
     * @return the fuel
     */
    public int getFuel() {

        return fuel;
    }

    /**
     * Adds the amount of fuel Used to the current fuel level
     *
     * @param amount the amount
     */
    public void addFuel(int amount) {
        fuel = fuel + amount;
    }

    /**
     * Subtracts the amount of fuel from the current fuel level
     *
     * @param amount the amount
     */
    public void useFuel(int amount) {
        fuel = fuel - amount;
    }
    
public abstract String getVehicleCharacter();


    /**
     * Allows extending concrete classes to Override toString
     *
     * @return the colour, make 
     */
    @Override
    public abstract String toString();
    //***************************************************************************************************************************************************

}
