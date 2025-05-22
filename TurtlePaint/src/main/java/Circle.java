import java.awt.*;
import java.awt.geom.Point2D;
public class Circle extends Shape {

    private double radius;

    public Circle(Turtle turtle, Point2D location, Color color, double border, double radius) {
        super(turtle, location, color, border);
        this.radius = radius;
    }

    @Override
    public void paint() {
        turtle.setColor(color);
        turtle.setPenWidth(border);
        turtle.penDown();
        turtle.goTo(location);

        double circumference = 2 * Math.PI * radius;
        int steps = 120;
        double stepLength = circumference / steps;
        double stepAngle = 360.0 / steps;

        for (int i = 0; i < steps; i++) {
            turtle.forward(stepLength);
            turtle.turnRight(stepAngle);
        }

        turtle.penUp();
    }
}