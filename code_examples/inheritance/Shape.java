import java.awt.*;
import java.util.Random;

public abstract class Shape {
    
    private Color color;
    private Point position;
    
    public Shape(Point position, Color color) {
        this.position = position;
        this.color = color;
    }
    
    public Shape() {
        this(new Point(0,0), Color.black);
    }
    
    public Color getColor() {
        return color;
    }
    public void setColor(Color newColor) {
        color = newColor;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point newPosition) {
        position = newPosition;
    }
    public String toString() {
        return "position (" + position.getX() + ", " + position.getY() + ")";
    }
    
    public abstract double area();
    public abstract double perimeter();
    
    public static void main(String[] args) {
        
        Shape s;
        // The next two lines "toss a coin"
        Random gen = new Random();
        if (gen.nextInt()%2 == 0) {
            s = new Rectangle(3.0, 4.0, new Point(34,87), Color.blue);
        }
        else {
            s = new Circle(5.0, new Point(34,98), Color.red);
        }
        System.out.println(s + "\n" + "area = " + s.area()
        + ", perimeter = " + s.perimeter());
    }
    
} // Shape
