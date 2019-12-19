package simplesim;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Generic event handler.
 */
public class SimulationEventHandler implements ActionListener, ChangeListener {
	
	// The frame in which the simulation will take place.
	private SimulationFrame frame;
	
	// Remember file and directory we saved to last time.
 	private String fileLastTime = "";
	private String dirLastTime = "";
	
	// The thread for running continuous simulations
	private SimulationThread simThread;
	
  /**
   * Contructor simply associates the handler with a SimulationFrame.
   */
  public SimulationEventHandler(SimulationFrame frame) {
	this.frame = frame;
	this.simThread = new SimulationThread(frame);
  }      
  /**
   * Deal with menu options and simulation control panel controls.
   * This is where all the action really happens.
   */
  public void actionPerformed(ActionEvent ae) {
	// Get the action string which determines which option was selected.
	String action = ae.getActionCommand();

	// Deal with simulation control panel options.
	if (action.equals("Step")) {
         try {
           frame.simstep();
         }
         catch (SimulationException e) {
           frame.tellUser(e.getMessage()); 
         }
      }
	else if (action.equals("Go")) 
	  if (simThread.isRunning())
		frame.tellUser("Simulation already running");
	  else { simThread.setRunning(true);
               // Notify the thread so it can continue
              synchronized(simThread) {simThread.notify();}
       }
	else if (action.equals("Stop")) 
	  if (simThread.isRunning()) 
		simThread.setRunning(false); // So it wait()s
	  else frame.tellUser("No simulation running"); 

	else if (action.equals("Reset")) frame.setTBS(frame.getTBS().reset()); 

	// File menu options
	else if (action.equals("Quit...")) frame.quitOrCancel();
	else if (action.equals("Save as...")) this.saveAsAction();
	else if (action.equals("Save")) this.saveAction();
	else if (action.equals("Open...")) this.openAction();
 
	// Look and feel options
	else if (action.equals("This platform"))
	  frame.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	else if (action.equals("Platform neutral"))
	  frame.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	else if (action.equals("Windows"))
	  frame.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	else if (action.equals("Motif"))
	  frame.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	// Request a repaint whether we need one or not.
	frame.repaint();
  }     
 
  // Utility method to load a serialized TBS.
  // Uses an AWT FileDialog. This should really be a Swing
  // file chooser.
  protected void openAction( ) {
	// Create a file dialog, setting the default file and directory,
	// if available.
	FileDialog loader = new FileDialog(frame, "Load number sequences", FileDialog.LOAD);
	if (!dirLastTime.equals("")) loader.setDirectory(dirLastTime);
	if (!fileLastTime.equals("")) loader.setFile(fileLastTime);
	loader.setVisible(true);

	// Get the name of the file specified by the user.
	String fname =  loader.getFile();
	String dirname = loader.getDirectory();
	if (fname != null ) { // If user didn't hit cancel...
	  try {
		// Try to load the sequences from the file
		FileInputStream fis = new FileInputStream( dirname + fname );
		ObjectInputStream in = new ObjectInputStream( fis );
		ThingBeingSimulated thing = (ThingBeingSimulated)in.readObject();
		frame.setTBS(thing);
		frame.repaint();
		fileLastTime = fname;
		dirLastTime = dirname;
	  } 
	  catch (ClassNotFoundException e) {
		// Presumably this means that...
		frame.warnUser( "File " + dirname + fname + " is not a set of number sequences" );
	  }	
	  catch (IOException e) {
		// while this means that...
		frame.warnUser( "Can't load file " + dirname + fname);
		System.err.println( e.getMessage() );
	  }
	}	
  }      
  // Save a TBS via serialisation, using the default file and
  // directory name if available
  protected void saveAction() {
	if (!fileLastTime.equals("") ) // If we know the filename
	this.saveTo(dirLastTime + fileLastTime);
	// If we don't know the filename, so a Save as.. instead
	else this.saveAsAction();	
  }      
  // Save a TBS via serialization to a named file, and remember
  // the file and directory for current Saves
  protected void saveAsAction() {
	FileDialog saver = new FileDialog(frame, "Save simulation", FileDialog.SAVE);
	if (!dirLastTime.equals("")) saver.setDirectory(dirLastTime);
	if (!fileLastTime.equals("")) saver.setFile(fileLastTime);
	saver.setVisible(true);
	
	// Get the name of the file specified by the user.
	String fname =  saver.getFile();
	String dirname = saver.getDirectory();
	if (fname != null ) // If user didn't hit cancel...
	  this.saveTo(dirname + fname);
  }      
  // Utility method to save a TBS to a named file, 
  // used by Save and Save as
  protected void saveTo(String filename) {
	try {
	  // Try to write the thing being simulated to the file
	  FileOutputStream fos = new FileOutputStream( filename );
	  ObjectOutputStream out = new ObjectOutputStream( fos );
	  out.writeObject(frame.getTBS());
	  out.close();
	} 
	catch (IOException e) {
	  // Tell the user if we couldn't write.
	  frame.warnUser( "Couldn't write to file " + filename );
	  // And print the diagnostic
	  System.err.println( e.getMessage() );
	}
  }      
  /** Deal with change events, from the slider controlling
  * simulation speed
  */
  public void stateChanged(ChangeEvent e) {
	Object source = e.getSource();
	// The source has type Object but we know it's a JSlider really
	JSlider slider = (JSlider)source;
	simThread.setSimulationSpeed(slider.getValue());
  }      
} // SimulationEventHandler
