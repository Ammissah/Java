package simplesim;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The menubar at the top of the frame. 
 * All the action is in the constructor.
 */
public class SimulationMenuBar extends JMenuBar {

  private JMenu fileMenu,  lfMenu;

  /**
   * This constructor sets up the default menu for the menubar at 
   * the top of the frame.
   */
  public SimulationMenuBar ( SimulationEventHandler handler) { 

	// The File menu
	fileMenu = new JMenu("File");
	String[] fileItems = {"Open...", "Save", "Save as...", "Quit..." };
	addItems(fileMenu, fileItems, handler);
	this.add( fileMenu );

	// The look and feel menu.
	lfMenu = new JMenu("Look & feel");
	String[] lfItems = { "This platform", "Platform neutral", "Windows", "Motif" };
	addItems(lfMenu, lfItems, handler);
	this.add(lfMenu);
  }      
  /** 
   * Add items to one of the existing menus 
  */
  public static void addItems(JMenu menu, String[] labels, ActionListener handler) {
	for (int i=0; i < labels.length; i++) {
	  JMenuItem item = new JMenuItem(labels[i]);
	  menu.add(item);
	  item.addActionListener(handler);
	}
  }      

  public JMenu getFileMenu() { return fileMenu; }      
  public JMenu getLookAndFeelMenu() { return lfMenu; }      
} // SimulationMenuBar
