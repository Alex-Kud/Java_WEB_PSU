/**
 * Class with hexagon shape
 */
public class Hexagon extends Shape{
    private final double x;
    /**
     * @param a Side of the Hexagon
     */
    public Hexagon(double a) {
        x = a;
        Menu.figureNumber[4]++;
        name = "Hexagon_" + Menu.figureNumber[4];
    }
    /**
     * @return Square of Hexagon
     */
    @Override
    public double getSquare(){
        return (3*Math.sqrt(3)*x*x)/2.0;
    }
    /**
     * @return Perimeter of Hexagon
     */
    @Override
    public double getPerimeter(){
        return 6*x;
    }
    /**
     * Output information about Hexagon
     */
    @Override
    public void showInformation() {
        System.out.println("-------------------------------");
        System.out.println("Shape name: " + name);
        System.out.println("Type: Hexagon" );
        System.out.println("Side length: " + x);
        System.out.println("Square: " + getSquare());
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("-------------------------------");
    }
}

