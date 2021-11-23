import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author Neel Gada
 * 
 * @version 2021-09-26
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, MyRectangle> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, MyRectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, MyRectangle> pair) {
        list.insert(pair);
        System.out.println("Rectangle inserted: " + "(" + pair.getKey() + ", "
            + pair.getValue().toString() + ")");
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, MyRectangle> rectangle = list.remove(name);
        if (rectangle == null) {
            System.out.println("Rectangle not found: (" + name + ")");
        }
        else {
            System.out.println("Rectangle removed: (" + name + ", " + rectangle
                .getValue().toString() + ")");
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {

        KVPair<String, MyRectangle> rectangle = list.removeByValue(
            new MyRectangle(x, y, w, h));
        if (rectangle == null) {
            System.out.println("Rectangle not found: (" + x + ", " + y + ", "
                + w + ", " + h + ")");
        }
        else {
            System.out.println("Rectangle removed: (" + rectangle.getKey()
                + ", " + rectangle.getValue().toString() + ")");
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList
     * Iterator for this
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        MyRectangle rec = new MyRectangle(x, y, w, h);
        System.out.println("Rectangles intersecting region (" + rec.toString()
            + "):");
        Iterator<KVPair<String, MyRectangle>> a = list.iterator();
        while (a.hasNext()) {
            KVPair<String, MyRectangle> pair1 = a.next();
            MyRectangle check1 = (MyRectangle)(pair1.getValue());
            if (check1.intersects(rec)) {
                System.out.println("(" + pair1.getKey() + ", " + pair1
                    .getValue().toString() + ")");
            }
        }
    }


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList
     * Iterators for this
     */
    public void intersections() {
        System.out.println("Intersections pairs:");
        Iterator<KVPair<String, MyRectangle>> a = list.iterator();
        while (a.hasNext()) {
            KVPair<String, MyRectangle> pair1 = a.next();
            MyRectangle check1 = (MyRectangle)(pair1.getValue());
            Iterator<KVPair<String, MyRectangle>> b = list.iterator();
            while (b.hasNext()) {
                KVPair<String, MyRectangle> pair2 = b.next();
                MyRectangle check2 = (MyRectangle)(pair2.getValue());
                if (check1.intersects(check2) && !pair1.equals(pair2)) {
                    System.out.println("(" + pair1.getKey() + ", " + pair1
                        .getValue().toString() + " | " + pair2.getKey() + ", "
                        + pair2.getValue().toString() + ")");
                }
            }
        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, MyRectangle>> search = list.search(name);
        if (search.size() > 0) {
            System.out.println("Rectangles found:");
            for (KVPair<String, MyRectangle> pair : search) {
                System.out.println("(" + name + ", " + pair.getValue() + ")");
            }
        }
        else {
            System.out.println("Rectangles not found: " + name);
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
