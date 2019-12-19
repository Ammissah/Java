package simplesim;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Generic frame to display simulations. Has a menu bar at the top and a
 * simulation control panel at the bottom 
 */
public class SimulationFrame extends simplesim.QuitableJFrame {

  // The Thing itself
  private ThingBeingSimulated theThingBeingSimulated;
	
  // The menubar at the top of the frame.
  private SimulationMenuBar menubar;
	
  // The event handler associated with the frame.
  private SimulationEventHandler handler;
	
  // The panel occupying most of the frame, used to display
  // the ThingBeingSimulated.
  private DisplayPanel displayPanel;

  // The panel holding the buttons at the bottom
  private SimulationControlPanel controlPanel;

  /**
   * Construct a SimulationFrame with the given size, and
   * an initial TBS.
   */
  public SimulationFrame(int xpixels, int ypixels,
				ThingBeingSimulated initialThing) {
	super(initialThing.toString(), xpixels, ypixels);

	// setLayout() and add() are applied to the content
	// pane, not the frame itself.
	this.getContentPane().setLayout( new BorderLayout() );
	this.handler = new SimulationEventHandler(this);
	this.menubar = new SimulationMenuBar(handler);
	this.setJMenuBar(menubar);
	this.displayPanel = new DisplayPanel(this);
	this.getContentPane().add( displayPanel, BorderLayout.CENTER);
	this.controlPanel = new SimulationControlPanel(handler);
	this.getContentPane().add( controlPanel, BorderLayout.SOUTH );

	// Set the initial look and feel to be that of the host platform.
	this.setLookAndFeel( UIManager.getSystemLookAndFeelClassName()); 
	this.setTBS(initialThing);
  }      
  public SimulationEventHandler getHandler() { return handler; }      
  public SimulationMenuBar getSimMenuBar() { return menubar; } 
  public DisplayPanel getDisplayPanel() { return displayPanel; }     
  /**
   * Return the current TBS
   */
  public ThingBeingSimulated getTBS() {
	return theThingBeingSimulated;
  }      
  /** Set the look and feel. The argument is the full name of the
  * desired look&feel class, e.g.
  * com.sun.java.swing.plaf.windows.WindowsLookAndFeel
  */
  public void setLookAndFeel(String lookAndFeel) {
	try { 
	  // Set the look and feel for the system..
	  UIManager.setLookAndFeel(lookAndFeel);
	  // .. and tell all the components about it.
	  SwingUtilities.updateComponentTreeUI(this);
	  // Call the layout manager in case things need shuffling around.
	  this.validate();
	  // And display the result of all that.
	  this.repaint();
	  // Indicate success
	  System.out.println("Look and feel set to " + lookAndFeel );
	}
	catch(Exception e) {
	  // Indicate if desired L&F wasn't available for some reason.
	  System.out.println(e);
	}
  }      
  /**
   * Set the TBS, and set its bounds to be that of the display panel.
   */
  public void setTBS(ThingBeingSimulated thing) {
	theThingBeingSimulated = thing;
	Rectangle r = displayPanel.getBounds();
	theThingBeingSimulated.setBounds(r.x+r.width,r.y+r.height);
  }      
  /**
   * Pass the simstep message on to the TBS.
   */
  public void simstep() throws SimulationException {
	getTBS().simstep();
	this.repaint();
  }      
} // SimulationFrame
