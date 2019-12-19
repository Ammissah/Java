
package roadvehicle;
import java.awt.*;
/**
 * GoodsVehicle.java A concrete class which extends RoadVehicle Class Represents
 * the type of goods vehicle and the maximum weight in kilos
 */
public class GoodsVehicle extends RoadVehicle {

    private String type; //the type of Goods Vehicle
    private int weight;  // the weight of the Goods Vehicle
    private String vehicleCharacter;
   

    /**
     *
     * @param colour the colour of the Goods Vehicle
     * @param type the type of the Goods Vehicle
     * @param weight the weight of the Goods vehicle
     */
    public GoodsVehicle(String colour, String type, int weight) {
        super(colour);         // explicit call to super Class Road Vehicle Constructor
        this.type = type;
        this.weight = weight;
        this.vehicleCharacter = "G";
      
    }

  

    /**
     * Returns the weight of the Goods Vehicle
     *
     * @return weight of the Goods Vehicle
     */
    public int getWeight() {
        return weight;
    }

  

    /**
     * Returns the type of the Goods Vehicle
     *
     * @return type of the Goods Vehicle
     */
    public String getType() {
        return type;
    }

    @Override
    public String getVehicleCharacter() {
        return this.vehicleCharacter;
    }



    @Override
    public String toString() {
        return getColour() + " " + getType() + " " + "max weight" + " " + "=" + " " + getWeight();
    }

    /**
     * Test Program for Goods Vehicle
     *
     * @param args
     */
    public static void main(String[] args) {
         // initial Values assigned to Constructor

        GoodsVehicle goodsVehicle1 = new GoodsVehicle("Green", "Van", 4200);
        GoodsVehicle goodsVehicle2 = new GoodsVehicle("Black", "tanker", 3200);

        System.out.println(goodsVehicle1.toString());
        System.out.println(goodsVehicle2.toString());

    }
}

//*************************************************************************SAMPLE OUTPUT*********************************************
//run:
//Green Van max weight = 4200
//Black tanker max weight = 3200