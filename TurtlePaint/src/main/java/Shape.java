import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Shape {
    protected Turtle turtle;
    protected Point2D location;
    protected Color color;
    protected double border;

    public Shape(Turtle turtle, Point2D location, Color color, double border) {
        this.turtle = turtle;
        this.location = location;
        this.color = color;
        this.border = border;
    }

    public abstract void paint();
}