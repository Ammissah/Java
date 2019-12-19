package roadvehicle;
import java.awt.*;
/**
 * FireEngine.java A Concrete Class of Emergency Vehicle Represents the colour
 * of a Fire Engine and Whether its code is Red or Not
 */
public class FireEngine extends EmergencyVehicle {

    private String vehicleCharacter;
   

    /**
     *
     * @param colour the colour of the Fire Engine
     * @param codeBlue the codeBle of the Fire Engine
     */
    public FireEngine(String colour, boolean codeBlue) {
        super(colour, codeBlue);  // explicit call to super Class Emergency Vehicle Constructor
        this.vehicleCharacter = "F";
    

    }

    /**
     * Second Constructor which takes no arguments
     */
    public FireEngine() {

        this("Red", true);  // defines the second constructor in terms of the first constructor
    }

    @Override
    public String getVehicleCharacter() {
        return this.vehicleCharacter;
    }


    @Override
    public String toString() {
        if (this.getCode() == true) {
            return "Fire engine: CODEBLUE!";
        } else {
            return "Green fire engine";
        }
    }

    /**
     * test program
     *
     * @param args
     */
    public static void main(String[] args) {
        // initial Values assigned to Constructor

        FireEngine fireEngine1 = new FireEngine("Red", true);
        FireEngine fireEngine2 = new FireEngine("Green", false);

        System.out.println(fireEngine1.toString());
        System.out.println(fireEngine2.toString());

    }

}
//**************************************************************************SAMPLE OUTPUT*****************************************************
//run:
//Fire engine: CODEBLUE!
//Green fire engine
