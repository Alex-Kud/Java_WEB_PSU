import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
/**
 * A class for creating menus and interacting with users
 */
public class Menu {
    static int[] figureNumber = new int[5];
    /**
     * List of shapes
     */
    private final List<Shape> shape = new ArrayList<>();
    /**
     *  Start menu operation
     */
    public void start() {
        Scanner in = new Scanner(System.in);
        while (true) {
            printMainMenu();
            int type_command = -1; // The type of command that the user entered
            if (in.hasNextInt()) type_command = in.nextInt();
            while (type_command <= 0 || type_command > 7) {
                System.out.println("Mistake! Enter the value again");
                if (!in.hasNextInt())
                    in.next();
                if (in.hasNextInt())
                    type_command = in.nextInt();
            }
            int type_figure = -1; // The type of figure that the user entered
            if (type_command == 1 || type_command == 3) {
                printFigureTypeMenu();
                if (in.hasNextInt()) type_figure = in.nextInt();
                while (type_figure <= 0 || type_figure > 5) {
                    System.out.println("Mistake! Enter the value again");
                    if (!in.hasNextInt())
                        in.next();
                    if (in.hasNextInt())
                        type_figure = in.nextInt();
                }
            }
            switch (type_command) {
                // Create
                case 1:
                    switch (type_figure) {
                        // Create Circle
                        case 1:
                            System.out.println("Enter radius:");
                            double r = getUserDoubleInput();
                            shape.add(new Circle(r));
                            break;
                        // Create Triangle
                        case 2:
                            System.out.println("Enter first side:");
                            double triangle_1 = getUserDoubleInput();
                            System.out.println("Enter second side:");
                            double triangle_2 = getUserDoubleInput();
                            System.out.println("Enter third side:");
                            double triangle_3 = getUserDoubleInput();
                            if (triangle_1 + triangle_2 <= triangle_3 ||
                                triangle_1 + triangle_3 <= triangle_2 ||
                                triangle_2 + triangle_3 <= triangle_1)
                                System.out.println("These sides do not form a Triangle");
                            else
                                shape.add(new Triangle(triangle_1, triangle_2, triangle_3));
                            break;
                        // Create Square
                        case 3:
                            System.out.println("Enter the side of the square:");
                            double a4 = getUserDoubleInput();
                            shape.add(new Square(a4));
                            break;
                        // Create Ellipse
                        case 4:
                            System.out.println("Enter Semi-Major Axis of the Ellipse:");
                            double ellipse_1 = getUserDoubleInput();
                            System.out.println("Enter Semi-Minor Axis of the Ellipse:");
                            double ellipse_2 = getUserDoubleInput();
                            shape.add(new Ellipse(ellipse_1, ellipse_2));
                            break;
                        // Create Hexagon
                        case 5:
                            System.out.println("Enter the side of the Hexagon:");
                            double hexagon = getUserDoubleInput();
                            shape.add(new Hexagon(hexagon));
                            break;
                    }
                    break;
                // Sort by square increase
                case 2:
                    Collections.sort(shape);
                    if (shape.size() == 0)
                        System.out.println("You have not created shapes yet");
                    for (int i = 0; i < shape.size(); ++i)
                        shape.get(i).showInformation();
                    break;
                // Square
                case 3:
                    System.out.println("-------------------------------");
                    int count = 0;
                    switch (type_figure) {
                        case 1:
                            System.out.println("Squares of circles");
                            for (int i = 0; i < shape.size(); ++i) {
                                if (shape.get(i) instanceof Circle) {
                                    System.out.println(shape.get(i).name + ": " + shape.get(i).getSquare());
                                    count++;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Squares of triangles");
                            for (int i = 0; i < shape.size(); ++i) {
                                if (shape.get(i) instanceof Triangle) {
                                    System.out.println(shape.get(i).name + ": " + shape.get(i).getSquare());
                                    count++;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Squares of squares");
                            for (int i = 0; i < shape.size(); ++i) {
                                if (shape.get(i) instanceof Square) {
                                    System.out.println(shape.get(i).name + ": " + shape.get(i).getSquare());
                                    count++;
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Squares of ellipses");
                            for (int i = 0; i < shape.size(); ++i) {
                                if (shape.get(i) instanceof Ellipse) {
                                    System.out.println(shape.get(i).name + ": " + shape.get(i).getSquare());
                                    count++;
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Squares of hexagons");
                            for (int i = 0; i < shape.size(); ++i) {
                                if (shape.get(i) instanceof Hexagon) {
                                    System.out.println(shape.get(i).name + ": " + shape.get(i).getSquare());
                                    count++;
                                }
                            }
                            break;
                    }
                    if (count == 0)
                        System.out.println("No shapes of the specified type have been created");
                    System.out.println("-------------------------------");
                    break;
                // Count the average perimeter of shapes

                case 4:
                    int quantity = 0, sum_perimeters = 0;
                    for (int i = 0; i < shape.size(); ++i) {
                        if (shape.get(i) instanceof Hexagon) {
                            quantity++;
                            sum_perimeters += shape.get(i).getPerimeter();
                        }
                    }
                    if (quantity > 0)
                        System.out.println("Average perimeter: " + sum_perimeters/quantity);
                    else
                        System.out.println("No shapes of the specified type have been created");
                    break;
                // Rename the shape and show information about it
                case 5:
                    System.out.println("Enter required name:"); // искомое имя
                    in.nextLine();
                    String name = in.nextLine();
                    boolean find = false;
                    for (int i = 0; i < shape.size(); ++i) {
                        if (shape.get(i).name.equals(name)) {
                            find = true;
                            shape.get(i).rename();
                            shape.get(i).showInformation();
                            break;
                        }
                    }
                    if (!find)
                        System.out.println("This name was not found");
                    break;
                // Show all shapes
                case 6:
                    if (shape.size() == 0)
                        System.out.println("You have not created shapes yet");
                    for (int i = 0; i < shape.size(); ++i)
                        shape.get(i).showInformation();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("You did something wrong");
                    return;
            }
        }
    }
    // Main menu output
    private void printMainMenu() {
        System.out.println("Choose operation");
        System.out.println("1 - Create");
        System.out.println("2 - Sort by square increase");
        System.out.println("3 - Square");
        System.out.println("4 - Count the average perimeter of shapes");
        System.out.println("5 - Rename the shape and show information about it");
        System.out.println("6 - Show all shapes");
        System.out.println("7 - Finish");
    }
    // Output of the shape type selection menu
    private void printFigureTypeMenu() {
        System.out.println("Choose the shape type");
        System.out.println("1 – Circle");
        System.out.println("2 – Triangle");
        System.out.println("3 – Square");
        System.out.println("4 – Ellipse");
        System.out.println("5 – Hexagon");
    }
    /**
     * Entering a double number
     * @return a double number entered from the keyboard
     */
    private double getUserDoubleInput() {

        Scanner in = new Scanner(System.in);
        double result = -1;
        if (in.hasNextInt()) result = in.nextInt();

        while(result <= 0) {
            System.out.println("Mistake! Enter the value again");
            if (!in.hasNextDouble()) {in.next();}
            if (in.hasNextDouble()) {result = in.nextDouble();}
        }
        return result;
    }
}
