import java.awt.Color;
import java.awt.geom.Point2D;
public class Triangle extends Shape {

    private double sideLength;

    public Triangle(Turtle turtle, Point2D location, Color color, double border, double sideLength) {
        super(turtle, location, color, border);
        this.sideLength = sideLength;
    }

    @Override
    public void paint() {
        turtle.setColor(color);
        turtle.setPenWidth(border);
        turtle.penDown();
        turtle.goTo(location);

        for (int i = 0; i < 3; i++) {
            turtle.forward(sideLength);
            turtle.turnRight(120);
        }

        turtle.penUp();
    }
}
