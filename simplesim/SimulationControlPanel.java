package simplesim;


import java.awt.*;
import javax.swing.*;

/**
 * Control panel which allows the user to step through simulations,
 * or run them continuously.
 */
public class SimulationControlPanel extends JPanel {

  /**
   * Add the relevant controls to the panel.
   */
  public SimulationControlPanel(SimulationEventHandler handler) {
	// Arange the controls from left to right
	this.setLayout(new FlowLayout());

	// Step, Go, and Stop buttons
	JButton stepButton = new JButton( "Step" );
	stepButton.setToolTipText("Perform a single simulation step");
	this.add( stepButton );
	stepButton.addActionListener(handler);
	JButton goButton = new JButton( "Go" );
	goButton.setToolTipText("Start continuous simulation");
	this.add( goButton );
	goButton.addActionListener(handler);
	JButton stopButton = new JButton( "Stop" );
	stopButton.setToolTipText("Stop continuous simulation");
	stopButton.addActionListener(handler);
	this.add( stopButton );
	JButton resetButton = new JButton( "Reset" );
	resetButton.setToolTipText("Reset the simulation");
	this.add( resetButton );
	resetButton.addActionListener(handler);

	// Use a slider to control simulation speed.	
	JSlider speedControl = new JSlider(JSlider.HORIZONTAL,0,300,60);
	speedControl.setMajorTickSpacing(60);
	speedControl.setMinorTickSpacing(20);
	speedControl.setPaintTicks(true);
	speedControl.setPaintLabels(true);
	speedControl.setToolTipText("Simulation speed in steps per minute (minimum 5spm)");
	this.add(speedControl);
	speedControl.addChangeListener(handler);
	this.setVisible(true);
  }      
} // SimulationControlPanel
