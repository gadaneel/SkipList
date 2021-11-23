import student.TestCase;

/**
 * The purpose of this class is to test Rectangle1 class
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class Rectangle1Test extends TestCase {

    // Testing Rectangle1 class
    private Rectangle1 rectangle;

    /**
     * setUp the instance of Rectangle1
     */
    public void setUp() {
        // Initializing the instance
        this.rectangle = new Rectangle1();
    }


    /**
     * Testing constructor of Rectangle1 class
     */
    public void testConstructor() {
        assertNotNull(rectangle);
    }


    /**
     * Testing the main method execution
     * 
     */
    public void testValidScenario() {
        // File present in system
        Rectangle1.main(new String[] { "./src/test.txt" });
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Rectangle inserted: (rectangle1, 1, 1, 1, 1)");
    }


    /**
     * Tests the valid scenario by passing a
     * file that does exist in "src" folder
     */
    public void testInvalidScenario() {
        // file.txt not present in system
        Rectangle1.main(new String[] { "file.txt" });
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // Must print Invalid file as file is not present in system
        assertEquals(systemOutLogs[systemOutLogs.length - 1], "Invalid file");

    }

}
