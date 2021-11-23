import student.TestCase;

/**
 * The purpose of this class is to test KVPair class
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class KVPairTest extends TestCase {

    // Creating an instance of KVPair<K,V> class
    private KVPair<String, MyRectangle> kv;

    /**
     * setUp the instance of KVPair
     */
    public void setUp() {
        // Initializing the instance
        this.kv = new KVPair<String, MyRectangle>("rectangle1", new MyRectangle(
            1, 1, 1, 1));
    }


    /**
     * Tests the constructor of KVPair class
     */
    public void testKVPair() {
        assertNotNull(this.kv);
    }


    /**
     * Tests the getValue method
     */
    public void testGetValue() {
        assertEquals(this.kv.getValue().toString(), "1, 1, 1, 1");
    }


    /**
     * Tests the getKey method
     */
    public void testGetKey() {
        assertEquals(this.kv.getKey(), "rectangle1");
    }


    /**
     * Tests the compareTo method to compare KVPair keys
     */
    public void testCompareTo() {
        // Test compare by running all 3 scenarios
        // Key 'a' is less than 'rectangle1'
        // Key 'z' greater than 'rectangle1'
        // Key 'rectangle1 is equal to the key of object created
        KVPair<String, MyRectangle> kvpair1 = new KVPair<String, MyRectangle>(
            "a", new MyRectangle(1, 1, 1, 1));
        assertEquals(this.kv.compareTo(kvpair1) > 0, true);
        kvpair1 = new KVPair<String, MyRectangle>("z", new MyRectangle(1, 1, 1,
            1));
        assertEquals(this.kv.compareTo(kvpair1) < 0, true);
        kvpair1 = new KVPair<String, MyRectangle>("rectangle1", new MyRectangle(
            1, 1, 1, 1));
        assertEquals(this.kv.compareTo(kvpair1) == 0, true);

    }


    /**
     * Tests the toString method of class
     */
    public void testToString() {
        assertEquals(this.kv.toString(), "(rectangle1, 1, 1, 1, 1)");
    }

}
