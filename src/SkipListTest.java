import student.TestCase;

/**
 * The purpose of this class is to test SkipList class
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class SkipListTest extends TestCase {

    // Creating an instance of SkipList
    private SkipList<String, MyRectangle> skipList;

    /**
     * setUp the instance of SkipList
     */
    public void setUp() {
        // Initializing the instance
        this.skipList = new SkipList<>();
    }


    /**
     * Tests constructor of SkipList class
     */
    public void testConstructor() {
        assertNotNull(skipList);
    }


    /**
     * Tests the search method of SkipList class
     */
    public void testSearch() {
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec1",
            new MyRectangle(0, 0, 1, 1));
        this.skipList.insert(kv);

        // search method should return ArrayList of size 1
        assertEquals(this.skipList.search("rec1").size(), 1);

        // Tests if the key of returned node
        assert (this.skipList.search("rec1").get(0).getKey().equals("rec1"));

        // ArrayList should be of size 0
        assertEquals(this.skipList.search("rec2").size(), 0);
        kv = new KVPair<String, MyRectangle>("rec2", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        assertEquals(this.skipList.search("rec1").size(), 1);
        assertEquals(this.skipList.search("rec2").size(), 1);

    }


    /**
     * Tests the insert method of SkipList class
     */
    public void testInsert() {
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec3",
            new MyRectangle(1, 1, 1, 1));

        // Before Insert
        // search method should return ArrayList of size 1
        assertEquals(this.skipList.search("rec3").size(), 0);
        this.skipList.insert(kv);

        // After insert, Tests size and value of key using search to verify
        assertEquals(this.skipList.search("rec3").size(), 1);
        assert (this.skipList.search("rec3").get(0).getKey().equals("rec3"));
    }


    /**
     * Tests the remove method of SkipList class
     */
    public void testRemove() {
        // Inserting nodes with duplicate key & value
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec4",
            new MyRectangle(0, 0, 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("b", new MyRectangle(0, 0, 1,
            3));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("c", new MyRectangle(0, 0, 1,
            4));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("c", new MyRectangle(0, 0, 1,
            4));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("c", new MyRectangle(0, 0, 1,
            4));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("c", new MyRectangle(0, 0, 1,
            4));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("d", new MyRectangle(0, 0, 1,
            5));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("rec4", new MyRectangle(0, 0, 1,
            1));
        this.skipList.insert(kv);

        // Total 12 records with key = rec4
        assertEquals(this.skipList.search("rec4").size(), 7);

        // Tests size using search after every remove operation
        this.skipList.remove("rec4");
        assertEquals(this.skipList.search("rec4").size(), 6);

        this.skipList.remove("rec4");
        assertEquals(this.skipList.search("rec4").size(), 5);

        this.skipList.remove("rec1");
        assertEquals(this.skipList.search("rec1").size(), 0);

        // Since rec1 is removed, remove will return null
        assertEquals(this.skipList.remove("rec1"), null);

        this.skipList.remove("rec2");
        assertEquals(this.skipList.search("rec2").size(), 0);

        // notinlist key is not present in skiplist
        assertEquals(this.skipList.remove("notinlist"), null);

    }


    /**
     * Tests the removeByValue method of SkipList class
     */
    public void testRemoveByValue() {

        // Inserts record for testing
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec4",
            new MyRectangle(0, 0, 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("a", new MyRectangle(0, 0, 2,
            2));
        kv = new KVPair<String, MyRectangle>("b", new MyRectangle(0, 0, 2,
            2));
        kv = new KVPair<String, MyRectangle>("ab", new MyRectangle(0, 0, 2,
            2));
        kv = new KVPair<String, MyRectangle>("ab", new MyRectangle(0, 0, 2,
            2));
        kv = new KVPair<String, MyRectangle>("abc", new MyRectangle(0, 0, 2,
            2));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("b", new MyRectangle(0, 0, 1,
            3));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("c", new MyRectangle(0, 0, 1,
            4));
        this.skipList.insert(kv);
        kv = new KVPair<String, MyRectangle>("d", new MyRectangle(0, 0, 1,
            5));
        this.skipList.insert(kv);

        // Insert for that values are done with key "rec4" previously
        // Tests key and size of returned search
        assertEquals(this.skipList.removeByValue(new MyRectangle(0, 0, 1, 1))
            .getKey(), "rec4");
        assertEquals(this.skipList.search("rec4").size(), 0);

        // Return null check if rectangle not present in SkipList
        assertEquals(this.skipList.removeByValue(new MyRectangle(100, 100, 100,
            100)), null);
        
        assertEquals(this.skipList.removeByValue(new MyRectangle(0, 0, 1, 4))
            .getKey(), "c");
        assertEquals(this.skipList.search("c").size(), 0);

        this.skipList.removeByValue(new MyRectangle(0, 0, 2, 2));
    }


    /**
     * Tests the dump method of SkipList class
     */
    public void testDump() {
        // Insert records to test dump
        KVPair<String, MyRectangle> kv = new KVPair<String, MyRectangle>("rec1",
            new MyRectangle(0, 0, 1, 1));
        this.skipList.insert(kv);
        this.skipList.insert(kv);
        this.skipList.insert(kv);

        this.skipList.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // Tests the final output line to verify SkipList size
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "SkipList size is: 3");
    }

}
