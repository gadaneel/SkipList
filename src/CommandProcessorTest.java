import student.TestCase;

/**
 * The purpose of this class is to test CommandProcessor class
 * with all possible commands and scenarios
 * 
 * @author Neel Gada (neelg@vt.edu)
 * 
 * @version 2021-09-26
 */

public class CommandProcessorTest extends TestCase {

    // Testing CommandProcessor class
    private CommandProcessor cmdProcessor;

    /**
     * Initializing the instance of CommandProcessor class
     */
    public void setUp() {
        // Initializing
        this.cmdProcessor = new CommandProcessor();
    }


    /**
     * Testing constructor of CommandProcessor
     */
    public void testCommandProcessor() {
        assertNotNull(this.cmdProcessor);
    }


    /**
     * Testing possible commands
     */
    public void testProcessor() {
        // Invalid insert, remove, regionsearch commands
        this.cmdProcessor.processor("insert abc       1         20  -7 0");
        this.cmdProcessor.processor("insert abc       0 0     3 -4");
        this.cmdProcessor.processor("insert     def       0 -20 -3 4");
        this.cmdProcessor.processor("insert def       -1 0 0 4");
        this.cmdProcessor.processor("insert     def       1024 1024 1 1");
        this.cmdProcessor.processor("insert     abc       21    2120 3  4");
        this.cmdProcessor.processor("remove -1 20 -3 4");
        this.cmdProcessor.processor("remove 1 20 5 -4");
        this.cmdProcessor.processor("remove 1 -20 3 4");
        this.cmdProcessor.processor("remove 1 20 50 1024");
        this.cmdProcessor.processor("remove 1111 20     3   4");
        this.cmdProcessor.processor("remove 1024 1024 0 0");
        this.cmdProcessor.processor("regionsearch   5   20 0 -13");
        this.cmdProcessor.processor("regionsearch 1 2 -11 -13");
        this.cmdProcessor.processor("regionsearch 52 50 17 -13");
        String[] systemOut = systemOut().getHistory().split("\n");
        // Check output for invalid commands
        for (int i = 1; i <= 15; i++) {
            assert (systemOut[systemOut.length - i].indexOf(
                "Rectangle rejected:") == 0);
        }
        String command = "insert           r1  15   15   5   5 ";
        // Adding horizontal tab to command
        command += (char)9;
        System.out.println(command);
        this.cmdProcessor.processor(command);
        this.cmdProcessor.processor("search r1");
        this.cmdProcessor.processor("search r2");
        this.cmdProcessor.processor("insert r2       -1 -20 3 4");
        this.cmdProcessor.processor("remove r1");
        this.cmdProcessor.processor("remove 1 1 1 1");
        this.cmdProcessor.processor("remove aa");
        this.cmdProcessor.processor("remove 1 1 1 1");
        this.cmdProcessor.processor("invalid command");
        this.cmdProcessor.processor("search z");
        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 11],
            "Rectangle inserted: (r1, 15, 15, 5, 5)");
        assertEquals(systemOut[systemOut.length - 9], "(r1, 15, 15, 5, 5)");
        assertEquals(systemOut[systemOut.length - 8],
            "Rectangles not found: r2");
        assertEquals(systemOut[systemOut.length - 7],
            "Rectangle rejected: (r2, -1, -20, 3, 4)");
        assertEquals(systemOut[systemOut.length - 6],
            "Rectangle removed: (r1, 15, 15, 5, 5)");
        assertEquals(systemOut[systemOut.length - 5],
            "Rectangle not found: (1, 1, 1, 1)");
        assertEquals(systemOut[systemOut.length - 4],
            "Rectangle not found: (aa)");
        assertEquals(systemOut[systemOut.length - 3],
            "Rectangle not found: (1, 1, 1, 1)");
        assertEquals(systemOut[systemOut.length - 2], "Unrecognized Command");
        assertEquals(systemOut[systemOut.length - 1],
            "Rectangles not found: z");
        this.cmdProcessor.processor("regionsearch 50 50 -11 -13");
        this.cmdProcessor.processor("regionsearch 50 50 50 50");
        this.cmdProcessor.processor("regionsearch 0 0 100 100");
        this.cmdProcessor.processor("search rr");
        this.cmdProcessor.processor("insert r12 108 136 -55 -103");
        this.cmdProcessor.processor("insert r12 108 136 55 103");
        this.cmdProcessor.processor("insert r14 120 117 93 706");
        this.cmdProcessor.processor("insert r15 120 117 93 706");
        this.cmdProcessor.processor("intersections");
        this.cmdProcessor.processor("dump");
        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 12],
            "(r12, 108, 136, 55, 103 | r14, 120, 117, 93, 706)");
        assertEquals(systemOut[systemOut.length - 11],
            "(r12, 108, 136, 55, 103 | r15, 120, 117, 93, 706)");
        assertEquals(systemOut[systemOut.length - 10],
            "(r14, 120, 117, 93, 706 | r12, 108, 136, 55, 103)");
        assertEquals(systemOut[systemOut.length - 9],
            "(r14, 120, 117, 93, 706 | r15, 120, 117, 93, 706)");
        assertEquals(systemOut[systemOut.length - 8],
            "(r15, 120, 117, 93, 706 | r12, 108, 136, 55, 103)");
        assertEquals(systemOut[systemOut.length - 7],
            "(r15, 120, 117, 93, 706 | r14, 120, 117, 93, 706)");
        assert (systemOut[systemOut.length - 3].indexOf(
            "(r14, 120, 117, 93, 706)") > 0);
        assertEquals(systemOut[systemOut.length - 1], "SkipList size is: 3");
    }

}
