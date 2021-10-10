/**
 * Class with ellipse shape
 */
public class Ellipse extends Shape{
    private final double semiMajorAxis;
    private final double semiMinorAxis;
    /**
     * @param el_1 Semi-Major Axis of the Ellipse
     * @param el_2 Semi-Minor Axis of the Ellipse
     */
    public Ellipse(double el_1, double el_2) {
        semiMajorAxis = el_1;
        semiMinorAxis = el_2;
        Menu.figureNumber[3]++;
        name = "Ellipse_" + Menu.figureNumber[3];
    }
    /**
     * @return Square of Ellipse
     */
    @Override
    public double getSquare(){
        return Math.PI*semiMajorAxis*semiMinorAxis;
    }
    /**
     * @return Perimeter of Ellipse
     */
    @Override
    public double getPerimeter(){
        return 4 * ((Math.PI * semiMajorAxis*semiMinorAxis + Math.pow(semiMajorAxis-semiMinorAxis,2))/(semiMajorAxis+semiMinorAxis));
    }
    /**
     * Output information about Ellipse
     */
    @Override
    public void showInformation() {
        System.out.println("-------------------------------");
        System.out.println("Shape name: " + name);
        System.out.println("Type: Ellipse" );
        System.out.println("Semi major axis: " + semiMajorAxis);
        System.out.println("Semi minor axis: " + semiMinorAxis);
        System.out.println("Square: " + getSquare());
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("-------------------------------");
    }
}

