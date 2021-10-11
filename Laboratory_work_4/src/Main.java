import java.util.Vector;
public class Main {
    /**
     *  This is the starting point of the program
     * @param args command line values
     */
    public static void main(String[] args) {
        Vector <Double> vec = new Vector<>();
        for (int i = 0; i < args.length; ++i)
            vec.add(i, Double.valueOf(args[i]));
        System.out.println("Original vector:");
        for (int i = 0; i < vec.size(); ++i)
            System.out.print(vec.get(i) + " ");
        vec.sort(Double::compareTo);
        System.out.println("\n" + "Sorted vector:");
        for (int i = 0; i < vec.size(); ++i)
            System.out.print(vec.get(i) + " ");
        System.out.println("\n" + "Median value:");
        System.out.println(vec.get(vec.size()/2));
        System.out.println("Average value:");
        double sr_ar = 0;
        for (int i = 0; i < vec.size(); ++i)
            sr_ar += vec.get(i);
        System.out.println(sr_ar / vec.size());
    }
}
