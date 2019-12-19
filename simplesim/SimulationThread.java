package simplesim;

/**
 * A thread to run continuous simulations.
 * 
 */
public class SimulationThread implements Runnable {

  // The Thread object.
  private Thread simThread;

  // Time in milliseconds between simsteps.
  private int sleepTime = 1000;

  // The frame displaying the simulation.
  private SimulationFrame frame;

  // Flag to say whether simulation is running, set by the stop/go buttons.
  private boolean running = false;
	
  /**
   * Set up a thread to run simulations.
   */
  public SimulationThread(SimulationFrame frame) {
	this.frame = frame;
	this.simThread = new Thread(this);
 	// Start immediately. Waits in run() if necessary.
	simThread.start();
  }      
  /**
   * Run continous simulation
   */
  public void run() {
	try {
 	while (true) {
        synchronized(this) {
	    if (!running) wait();
          try {
	      frame.simstep();
	      frame.repaint();
          }
          catch (SimulationException e) {
            frame.tellUser(e.getMessage());
            running = false;
            wait();
          }
        }
	  Thread.sleep(sleepTime);
	}
	}
	catch (InterruptedException e) {}	
  }      
  /** Set speed of simulation by altering sleeptime. Slowest
   * speed is 5 steps per minute, since a change doesn't take
   * effect until after the current sleep. If you want < 5spm
   * you might as well single-step.
   */
  public void setSimulationSpeed(int stepsPerMinute) {
	if (stepsPerMinute < 5) stepsPerMinute = 5;
	this.sleepTime = 60000 / stepsPerMinute;
  }      
  /**
   * Indicate whether simulation is currently running.\
   */
  public boolean isRunning() { return running;}  

  public void setRunning(boolean onOrOff) { running = onOrOff; }
  
} // SimulationThread
