
package roadvehicle;

/**
 * QueueFullException.java
 * Represents a Subclass of Exception which takes an integer in its Constructor and 
 * String as well passing the String to its Super Classes constructor to display the message
 */
public class QueueFullException extends Exception {
/**
     * Instance Variables
     */
    private String message; // the message to be displayed
    private int queueSize;  // the size of the Queue
    
    /**
     *
     * @param queueSize the Size of the Queue
     * @param message   the message to be displayed
     */
    public QueueFullException(int queueSize, String message) {
        this.queueSize = queueSize;
        this.message = message;

    }
    
    /**
     * Returns the Message and QueueSize
     * @return the message and queueSize
     */
    @Override
    public String getMessage(){
  
        return(this.message + " " + this.queueSize);
    
    }
    
    /* A test of the QueueFull Exception  program
    *
    */
    public static void main(String[] args) {
    
    QueueFullException qfe = new QueueFullException(20,"Queue Full: max capacity");
    
    System.out.println("Testing QueueFull Exception");
    
    System.out.println(qfe.getMessage());
    
    
    }
  
}

//******************************************************************Sample Output***********************************************************
/*run:
Testing QueueFull Exception
Queue Full: max capacity 20*/