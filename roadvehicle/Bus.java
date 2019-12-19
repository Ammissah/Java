
package roadvehicle;
import java.awt.*;
/**
 * Bus.java Bus Class represents the colour and number of Seats in a Bus
 */
public class Bus extends RoadVehicle {

    private int seats = 0; // initial value assigned to seats
    private String vehicleCharacter;
   

    /**
     *
     * @param colour the colour of the Bus
     * @param seats the number of seats in the Bus
     */
    public Bus(String colour, int seats) {
        super(colour);           // explicit call to super Class Road Vehicle
        this.seats = seats;
        this.vehicleCharacter = "B";
        

    }

    /**
     * Sets the number of Seats for the Bus
     *
     * @param seats the seats of the Bus
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Returns the seats
     *
     * @return seats of the Bus
     */
    public int getSeats() {
        return seats;
    }

    @Override
    public String getVehicleCharacter() {
        return this.vehicleCharacter;

    }



    @Override
    public String toString() {

        return getColour() + " " + "bus" + " " + "with" + " " + getSeats() + " " + "seats";

    }

    /**
     * Test Program for the Bus Class
     *
     * @param args
     */
    public static void main(String[] args) {
         // initial Values assigned to Constructor

        Bus bus1 = new Bus("Blue", 30);
        Bus bus2 = new Bus("White", 45);

        System.out.println(bus1.toString());
        System.out.println(bus2.toString());

    }
}

//***********************************************************************SAMPLE OUTPUT************************************************
//run:
//Blue bus with 30 seats
//White bus with 45 seats
