package roadvehicle;
import java.awt.*;
/**
 * EmergencyVehicle.java An Abstract Class which represents different types of
 * Emergency Vehicle
 */
public abstract class EmergencyVehicle extends RoadVehicle {

    private boolean codeBlue = false;

    private String vehicleCharacter;
  

    /**
     *
     * @param colour the colour of the Emergency Vehicle
     * @param codeBlue the codeBle of the Emergency Vehicle
     */
    public EmergencyVehicle(String colour, boolean codeBlue) {
        super(colour);                // explicit call to super Class Road Vehicle Constructor
        this.codeBlue = codeBlue;
        this.vehicleCharacter = "F";
      

    }

    /**
     * Sets the Colour Code of the Emergency vehicle
     *
     * @param codeBlue the codeBle of the Emergency Vehicle
     */
    public void setCode(boolean codeBlue) {

        this.codeBlue = codeBlue;
    }

    /**
     * Returns the Colour Code of the Emergency vehicle
     *
     * @return the codeBle of the Emergency Vehicle
     */
    public boolean getCode() {
        return codeBlue;
    }

    @Override
    public String getVehicleCharacter() {
        return this.vehicleCharacter;
    }



    @Override  // Overrides toString Abstract Method in Roadvehicle Class
    public String toString() {
        return "Emergency Vehicle ?" + " " + getColour() + " " + getCode();
    }

}
