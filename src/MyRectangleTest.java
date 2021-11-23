import student.TestCase;

/**
 * MyRectangle class Test
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class MyRectangleTest extends TestCase {

    // Creating an instance of MyRectangle class
    private MyRectangle rectangle;

    /**
     * setUp the instance of MyRectangle
     */
    public void setUp() {
        // Initializing the instance of MyRectangle class
        this.rectangle = new MyRectangle(0, 0, 10, 10);
    }


    /**
     * Testing constructor of MyRectangle
     */
    public void testMyRectangle() {
        assertNotNull(rectangle);
    }


    /**
     * Test toString method of MyRectangle class
     */
    public void testToString() {
        assertEquals(rectangle.toString(), "0, 0, 10, 10");
    }
}
