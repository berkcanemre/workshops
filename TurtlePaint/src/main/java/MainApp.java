import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Canvas boyutu iste
        System.out.print("Enter canvas width: ");
        int width = scanner.nextInt();
        System.out.print("Enter canvas height: ");
        int height = scanner.nextInt();

        World world = new World(width, height);
        Turtle turtle = new Turtle(world, 0, 0);

        while (true) {
            System.out.println("\nHome Screen");
            System.out.println("1) Add Shape");
            System.out.println("2) Save Image");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // buffer temizle

            switch (choice) {
                case 1:
                    addShape(scanner, turtle);
                    break;
                case 2:
                    System.out.print("Enter filename to save (e.g., drawing.png): ");
                    String filename = scanner.nextLine();
                    world.saveAs(filename);
                    System.out.println("Image saved as " + filename);
                    break;
                case 0:
                    System.out.println("Exiting application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addShape(Scanner scanner, Turtle turtle) {
        System.out.println("\nWhich shape do you want to draw?");
        System.out.println("1) Square");
        System.out.println("2) Circle");
        System.out.println("3) Triangle");
        System.out.print("Enter choice: ");
        int shapeChoice = scanner.nextInt();

        double sideLength = 0;
        double radius = 0;

        if (shapeChoice == 1 || shapeChoice == 3) {
            System.out.print("Enter side length: ");
            sideLength = scanner.nextDouble();
        } else if (shapeChoice == 2) {
            System.out.print("Enter radius: ");
            radius = scanner.nextDouble();
        }

        System.out.print("Enter border width: ");
        double border = scanner.nextDouble();

        System.out.print("Enter RGB color values (e.g., 255 0 0 for red): ");
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        Color color = new Color(r, g, b);

        System.out.print("Enter X coordinate: ");
        double x = scanner.nextDouble();
        System.out.print("Enter Y coordinate: ");
        double y = scanner.nextDouble();
        Point2D location = new Point2D.Double(x, y);

        Shape shape = null;
        switch (shapeChoice) {
            case 1:
                shape = new Square(turtle, location, color, border, sideLength);
                break;
            case 2:
                shape = new Circle(turtle, location, color, border, radius);
                break;
            case 3:
                shape = new Triangle(turtle, location, color, border, sideLength);
                break;
            default:
                System.out.println("Invalid shape choice.");
                return;
        }

        shape.paint();
        System.out.println("Shape painted!");
    }
}
