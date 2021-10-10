/**
 * Class with triangle shape
 */
public class Triangle extends Shape{
    private final double side_a;
    private final double side_b;
    private final double side_c;
    private final double semiPerimeter;

    /**
     * @param a First side of the Triangle
     * @param b Second side of the Triangle
     * @param c Third side of the Triangle
     */
    public Triangle(double a, double b, double c) {
        side_a = a;
        side_b = b;
        side_c = c;
        semiPerimeter = getPerimeter()/2;
        Menu.figureNumber[1]++;
        name = "Triangle_" + Menu.figureNumber[1];
    }
    /**
     * @return Square of Triangle
     */
    @Override
    public double getSquare(){
        return Math.sqrt(semiPerimeter * (semiPerimeter - side_a) * (semiPerimeter - side_b) * (semiPerimeter - side_c));
    }
    /**
     * @return Perimeter of Triangle
     */
    @Override
    public double getPerimeter(){
        return side_a + side_b + side_c;
    }
    /**
     * Output information about Triangle
     */
    @Override
    public void showInformation() {
        System.out.println("-------------------------------");
        System.out.println("Shape name: " + name);
        System.out.println("Type: Triangle" );
        System.out.println("First side: " + side_a);
        System.out.println("Second side: " + side_b);
        System.out.println("Third side: " + side_c);
        System.out.println("Square: " + getSquare());
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("-------------------------------");
    }
}