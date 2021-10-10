/**
 * Class with square shape
 */
public class Square extends Shape{
    private final double x;
    /**
     * @param a Side of the Square
     */
    public Square(double a) {
        x = a;
        Menu.figureNumber[2]++;
        name = "Square_" + Menu.figureNumber[2];
    }
    /**
     * @return Square of Square
     */
    @Override
    public double getSquare(){
        return x*x;
    }
    /**
     * @return Perimeter of Square
     */
    @Override
    public double getPerimeter(){
        return 4*x;
    }
    /**
     * Output information about Square
     */
    @Override
    public void showInformation() {
        System.out.println("-------------------------------");
        System.out.println("Shape name: " + name);
        System.out.println("Type: Square" );
        System.out.println("Side length: " + x);
        System.out.println("Square: " + getSquare());
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("-------------------------------");
    }
}
