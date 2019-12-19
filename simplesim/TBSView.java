package simplesim;

import java.awt.Graphics;

/** Interface which should be implemented by any class
 * which implements a view of a TBS - i.e. a way of
 * displaying it to the user.
 */

public interface TBSView {

  /** 
   * Display the TBS on the screen. This is called whenever the display
   * needs to be redrawn, including when the GUI is initialised and
   * after every simstep().
   */	
  public abstract void display(Graphics g);      
         
  /** 
   * Set the bounds within which the TBS is displayed, 0...maxX by 0..maxY
   * This is called before each call to display, so that if e.g. the
   * window is resized, display() can use this information to size
   * the representation of the TBS accordingly.
   */
  public abstract void setBounds(int maxX, int maxY);      
   
} // TBSView
