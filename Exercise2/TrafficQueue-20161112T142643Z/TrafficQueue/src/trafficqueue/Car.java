
package trafficqueue;


/*
 * Car.java    Francis  Kingsworth Prah      COMP70110 Java   Ex1
 * 
 * Represents different Aspects of cars such as colour, make and current fuel levels
 * Also shows assignment of two different variables using the equal sign during object creation
 */




/**
 *
 * A class to represent a car of a specific make,colour and fuel.
 */
public class Car {

    /**
     * Instance Variables
     */
    private String make;      // the make of the car
    private String colour;    //the colour of the car
    private int fuel;         //the fuel in the car
    
    /**
     *
     * @param make the make of the car
     * @param color the colour of the car
     * @param fuel the fuel in the car
     */
    public Car(String make, String color, int fuel)
    {
    this.colour = color;
    this.make = make;
    this.fuel = fuel;
    }
    
    /**
     * Sets a new colour to the car
     * @param newColour the new colour of the car
     */
    public void setColour(String newColour)
    {
        
        this.colour = newColour;
    }
    
    /**
     *Returns the make of a car Object
     * @return the make
     */
    public String getMake()
    {
      return make;
    }
     
    /**
     *Returns the colour of a car object
     * @return the colour
     */
    public String getColour()
    {
      return colour;  
    }
     
    /**
     *Gets the fuel in a car
     * @return the fuel
     */
    public int getFuel()
    {
        
    return fuel;
    }
     
    /**
     *Adds the amount of fuel Used to the current fuel level
     * @param amount the amount 
     */
    public void addFuel(int amount)
    {
        fuel = fuel + amount;
    }
     
    /**
     *Subtracts the amount of fuel from the current fuel level
     * @param amount the amount
     */
    public void useFuel(int amount)
    {
        fuel = fuel- amount;
    }
     
    /**
     *Returns a string representation of the car
     * @return the colour, make and the fuel
     */
   
    public String toString()
    {
        return this.getColour() + " " + this.getMake() + " " + this.getFuel();
    }//***************************************************************************************************************************************************
     
    /**
     *Test the Car Class
     * @param args 
     */
    public static void main(String[] args) {
         // initial Values assigned to Constructor
        
    Car car1 = new Car("Ferrari","Red", 0);     //car1 first object
    Car car2 = new Car("Volvo","Blue", 0);      //car2 second object
    
    Car car3 = car2; //create a new car object called car3 of type car equal to car2
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    System.out.printf("The make,Colour and Current Fuel levelof the Original Car is %s%n", car1.toString());
     
    car1.setColour("Yellow"); //assign new colour to car1
    
    System.out.printf("The make, New Colour and Current Fuel level of the Original Car is %s%n", car1.toString());
     
    System.out.printf("The current fuel level in the First Car is %s%n", car1.getFuel());
     
    car2.addFuel(20);// add 20 units of fuel to car2
    
    car2.setColour("Green"); //set colour of car2 to green
    
    car3.useFuel(10); //use 10 units of fuel in car3
    
    System.out.printf("The current fuel level in the second Car is %s%n", car2.getFuel()); 
    
    System.out.printf("The current fuel level in the third Car is %s%n", car3.getFuel());
     
    System.out.printf("The make, colour and current fuel in the second car(car2)  is %s%n", car2.toString());
          
    System.out.printf("The make,colour and current fuel levelin the third car(car3)  is %s%n", car3.toString());
    }
    
}

//********************************************SAMPLE OUTPUT***********************************************************************************************************************
//run:
//The make,Colour and Current Fuel levelof the Original Car is Red Ferrari 0
//The make, New Colour and Current Fuel level of the Original Car is Yellow Ferrari 0
//The current fuel level in the First Car is 0
//The current fuel level in the second Car is 10
//The current fuel level in the third Car is 10
//The make, colour and current fuel in the second car(car2)  is Green Volvo 10
//The make,colour and current fuel levelin the third car(car3)  is Green Volvo 10

/** Observation: output for car2=car3 */
