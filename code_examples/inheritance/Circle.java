import java.awt.*;

public class Circle  extends Shape{
    
    private double radius;
    
    public Circle(double radius, Point position, Color color) {
        super(position, color);
        this.radius = radius;
    }
    
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
    public double area() {
        return Math.PI * radius * radius;
    }
    
    public String toString() {
        return "Circle, radius " + radius + ", " +  this.getPosition();
    }
    
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(getPosition().x, getPosition().y, (int)radius*2, (int)radius*2);
    }
    
    public static void main(String[] args) {
        Circle c = new Circle(5.0, new Point(22,84), Color.red);
        System.out.println(c);
    }
} // Circle
