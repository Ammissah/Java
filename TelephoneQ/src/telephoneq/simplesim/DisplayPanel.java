package simplesim;

import java.awt.*;
import javax.swing.*;

/** The panel on which the thing being simulated displays itself.
*/
public class DisplayPanel extends JPanel {

  private SimulationFrame parent;

  /** Create a white background, with double buffering provided
  */
  public DisplayPanel(SimulationFrame parent) {
	super();
	this.parent = parent;
	this.setBackground(Color.white);
	this.setVisible(true);
	this.setDoubleBuffered(true);
  }      
  /** Repaint the panel, so the TBS display method starts with
  *   a "blank screen", and redisplay the TBS.
  */
  public void paint(Graphics g) {
	g.setColor(Color.white);
	Dimension bounds = this.getBounds().getSize();
	g.fillRect(0,0, bounds.width, bounds.height);
	if (parent.getTBS() != null) {
          Rectangle r = this.getBounds();
	    parent.getTBS().setBounds(r.x+r.width,r.y+r.height);
          // Avoid 'polar bear in snowstorm' syndrome
          g.setColor(Color.black);
          parent.getTBS().display(g);
         }
   }         
} // DisplayPanel
