import java.awt.Rectangle;

/**
 * This class is responsible for overriding toString function of Rectangle class
 * 
 * @author Neel Gada
 * 
 * @version 2021-09-26
 */

@SuppressWarnings("serial")
public class MyRectangle extends Rectangle {

    /**
     * Constructor for MyRectangle class which uses coordinates to create object
     * 
     * @param x
     *            x coordinate of rectangle's corner
     * @param y
     *            y coordinate of rectangle's corner
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     */
    public MyRectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    /**
     * Overriding Rectangle Class toString method for MyRectangle object
     * 
     * 
     * To print the Rectangle object in desired format
     * 
     * @return
     *         Desired format of rectangle's coordinates
     */
    public String toString() {
        return this.x + ", " + this.y + ", " + this.width + ", " + this.height;
    }
}
