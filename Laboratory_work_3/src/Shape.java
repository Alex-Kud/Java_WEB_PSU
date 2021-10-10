import java.util.Scanner;
/**
 * Abstract class of geometric shapes
 */
public abstract class Shape implements Comparable<Shape>{
    protected String name;

    public Shape() { }
    /**
     *   Output of information about the shape
     */
    public abstract void showInformation();
    /**
     * @return Square of shape
     */
    public abstract double getSquare();
    /**
     *   Rename shape
     */
    public void rename(){
        System.out.println("Enter new name:");
        Scanner in = new Scanner(System.in);
        this.name = in.nextLine();
    }
    /**
     * @return Perimeter of shape
     */
    public abstract double getPerimeter();
    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.getSquare(), other.getSquare());
    }
}
