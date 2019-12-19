package simplesim;

/**  
* Exception which is thrown when a step of simulation fails 
* for any reason. A String giving the reason for failure
* should be passed to the constructor.
*/

public class SimulationException extends /* checked */ Exception {

 public SimulationException(String problem) {
    super(problem);
 }

} // SimulationException
