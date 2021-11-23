import student.TestCase;

/**
 * The purpose of this class is to test CommandProcessor class
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class DatabaseTest extends TestCase {

    // Creating an instance of Database class
    private Database data;

    /**
     * setUp the Database
     */
    public void setUp() {
        // Initializing the instance
        this.data = new Database();
    }


    /**
     * Tests the constructor of Database class
     */
    public void testDatabase() {
        assertNotNull(this.data);
    }


    /**
     * Tests the insert method of Database class
     */
    public void testInsert() {
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("abc",
            new MyRectangle(1, 1, 1, 1));
        data.insert(kv);
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Checks output for correct insert
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Rectangle inserted: (abc, 1, 1, 1, 1)");

        // Clear inserted data
        data.remove("abc");
    }


    /**
     * Tests the remove method of Database class
     */
    public void testRemove() {
        // Inserting record for testing remove operation
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>(
            "rectangle1", new MyRectangle(1, 1, 1, 1));
        data.insert(kv);
        // Tests remove by key
        // Record not present
        this.data.remove("abc");

        // record present
        this.data.remove("rectangle1");

        data.insert(kv);

        // Tests remove by coordinates

        // Record not present
        this.data.remove(1, 0, 1, 1);

        // record present
        this.data.remove(1, 1, 1, 1);

        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // Check output for invalid command
        assertEquals(systemOutLogs[systemOutLogs.length - 5],
            "Rectangle not found: (abc)");

        // Check output for valid command
        assertEquals(systemOutLogs[systemOutLogs.length - 4],
            "Rectangle removed: (rectangle1, 1, 1, 1, 1)");

        // Check output for invalid command
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "Rectangle not found: (1, 0, 1, 1)");

        // Check output for valid command
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Rectangle removed: (rectangle1, 1, 1, 1, 1)");

    }


    /**
     * Tests the regionsearch method of Database class
     */
    public void testRegionsearch() {

        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>(
            "rectangle1", new MyRectangle(1, 1, 1, 1));
        this.data.insert(kv);
        // Rectangle inserted in present in the region
        this.data.regionsearch(0, 0, 2, 2);
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Since rectangle "r" inserted above intersects with region (100, 100,
        // 8, 8)
        // it should be printed to logs
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "(rectangle1, 1, 1, 1, 1)");

        // Rectangle not present in mentioned region
        this.data.regionsearch(10, 10, 10, 10);
        systemOutLogs = systemOut().getHistory().split("\n");

        // Since no rectangle is present in mentioned region
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Rectangles intersecting region (10, 10, 10, 10):");

        // Clear inserted data
        this.data.remove("rectangle1");
    }


    /**
     * Tests the intersection in list of Database class
     */
    public void testIntersections() {

        // Inserting a pair of intersecting rectangles
        KVPair<String, MyRectangle> kv1 = new KVPair<String, MyRectangle>(
            "rec1", new MyRectangle(1, 1, 1, 1));
        KVPair<String, MyRectangle> kv2 = new KVPair<String, MyRectangle>(
            "rec2", new MyRectangle(0, 0, 2, 2));
        KVPair<String, MyRectangle> kv3 = new KVPair<String, MyRectangle>(
            "rec3", new MyRectangle(10, 10, 10, 10));
        KVPair<String, MyRectangle> kv4 = new KVPair<String, MyRectangle>(
            "rec4", new MyRectangle(0, 0, 1, 1));
        this.data.insert(kv1);
        this.data.insert(kv2);
        this.data.insert(kv3);
        this.data.insert(kv4);
        this.data.intersections();

        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Check the output for all intersections
        assertEquals(systemOutLogs[systemOutLogs.length - 4],
            "(rec1, 1, 1, 1, 1 | rec2, 0, 0, 2, 2)");
        assertEquals(systemOutLogs[systemOutLogs.length - 3],
            "(rec2, 0, 0, 2, 2 | rec1, 1, 1, 1, 1)");
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "(rec2, 0, 0, 2, 2 | rec4, 0, 0, 1, 1)");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "(rec4, 0, 0, 1, 1 | rec2, 0, 0, 2, 2)");

        // Removing records to test no intersection
        this.data.remove("rec1");
        this.data.remove("rec2");

        this.data.intersections();
        systemOutLogs = systemOut().getHistory().split("\n");
        // Since there is not intersecting pair
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Intersections pairs:");

        // Clearing inserted data
        this.data.remove("rec3");
        this.data.remove("rec4");
    }


    /**
     * Tests the search method of Database class
     */
    public void testSearch() {
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec1",
            new MyRectangle(0, 0, 1, 1));
        data.insert(kv);
        data.search("rec1");
        data.search("abc");
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // results
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "(rec1, 0, 0, 1, 1)");

        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Rectangles not found: abc");

        // Clearing inserted rectangles
        this.data.remove("rec1");
    }


    /**
     * Tests the dump method of Database class
     */
    public void testDump() {
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec1",
            new MyRectangle(0, 0, 1, 1));
        data.insert(kv);
        data.insert(kv);
        data.insert(kv);
        this.data.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Confirm dump method by check size in last line and number of line in
        // between last line and opening link should be equal to size as each
        // node in list will take 1 line
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "SkipList size is: 3");
    }

}
