package simplesim;

/**  
* Interface which must be implemented by any class which
* provides the underlying functionality (model) of a
* ThingBeingSimulated.
*/

public interface TBSModel  {

  /** 
  * Perform a step of simulation. If this fails for some reason,
  * a SimulationException is thrown. This has the effect of showing
  * a dialog to the user containing the result of getMessage().
  * Therefore when the exception is thrown it should be initialised with
  * a suitable message string, typically dirived from a TBS-specific exception.
  */
  public void simstep() throws SimulationException;

  /** 
   * Return a TBS which is the desired result when the user hits reset,
   * typically A TBS in an initial state.
  */
  public abstract ThingBeingSimulated reset();      

} // TBSModel
