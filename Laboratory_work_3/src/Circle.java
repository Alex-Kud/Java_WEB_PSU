/**
 * Class with circle shape
 */
public class Circle extends Shape{
    private final double radius;
    /**
     * @param r Radius of the Circle
     */
    public Circle(double r) {
        radius = r;
        Menu.figureNumber[0]++;
        name = "Circle_" + Menu.figureNumber[0];
    }
    /**
     * @return Square of Circle
     */
    @Override
    public double getSquare(){
        return Math.PI*radius*radius;
    }
    /**
     * @return Perimeter of Circle
     */
    @Override
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    /**
     * Output information about Circle
     */
    @Override
    public void showInformation() {
        System.out.println("-------------------------------");
        System.out.println("Shape name: " + name);
        System.out.println("Type: Circle" );
        System.out.println("Radius: " + radius);
        System.out.println("Square: " + getSquare());
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("-------------------------------");
    }
}
