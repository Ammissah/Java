import java.awt.*;

public class Rectangle extends Shape {
    
    private double width, height;
    
    public Rectangle(double width, double height, Point position, Color color) {
        super(position, color);
        this.width = width;
        this.height = height;
    }
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double perimeter() {
        return (width + height) * 2;
    }
    public double area() {
        return width * height;
    }
    
    public String toString() {
        return "Rectangle, width " + width + ", height " + height + ", "
        +  super.toString();
    }
    
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.drawRect(getPosition().x, getPosition().y, (int)width, (int)height);
    }
    
    public static void main(String[] args) {
        Rectangle r = new Rectangle(2.0, 4.0);
        System.out.println(r);
    }
} // Rectangle
