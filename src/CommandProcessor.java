/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Neel Gada
 * 
 * @version 2021-09-26
 */
public class CommandProcessor {

    // the database object to manipulate the
    // Commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * 
     * the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file
     * line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // Removing extra spaces
        line = line.replaceAll("\\s+", " ");
        // Converting to commandArray
        String[] commandArray = line.split(" ");

        switch (commandArray[0]) {
            case "insert":
                // Insert a rectangle
                int x = Integer.parseInt(commandArray[2]);
                int y = Integer.parseInt(commandArray[3]);
                int width = Integer.parseInt(commandArray[4]);
                int height = Integer.parseInt(commandArray[5]);
                if (x >= 0 && y >= 0 && width > 0 && height > 0 && x
                    + width <= 1024 && y + height <= 1024) {
                    data.insert(new KVPair<String, MyRectangle>(commandArray[1],
                        new MyRectangle(x, y, width, height)));
                }
                else {
                    // Invalid input
                    System.out.println("Rectangle rejected: (" + commandArray[1]
                        + ", " + x + ", " + y + ", " + width + ", " + height
                        + ")");
                }
                break;
            case "remove":
                // Remove by name
                if (commandArray.length < 3) {
                    data.remove(commandArray[1]);
                }
                else { // Remove by dimensions
                    int removex = Integer.parseInt(commandArray[1]);
                    int removey = Integer.parseInt(commandArray[2]);
                    int removeWidth = Integer.parseInt(commandArray[3]);
                    int removeHeight = Integer.parseInt(commandArray[4]);
                    if (removex >= 0 && removey >= 0 && removeWidth > 0
                        && removeHeight > 0 && removex + removeWidth <= 1024
                        && removey + removeHeight <= 1024) {
                        data.remove(removex, removey, removeWidth,
                            removeHeight);
                    }
                    else {
                        // Invalid input
                        System.out.println("Rectangle rejected: (" + removex
                            + ", " + removey + ", " + removeWidth + ", "
                            + removeHeight + ")");
                    }
                }
                break;
            case "regionsearch":
                // Find all rectangles in this region
                int regionWidth = Integer.parseInt(commandArray[3]);
                int regionHeight = Integer.parseInt(commandArray[4]);
                if (regionWidth > 0 && regionHeight > 0) {
                    data.regionsearch(Integer.parseInt(commandArray[1]), Integer
                        .parseInt(commandArray[2]), regionWidth, regionHeight);
                }
                else {
                    // regionsearch input Invalid
                    System.out.println("Rectangle rejected: (" + commandArray[1]
                        + ", " + commandArray[2] + ", " + regionWidth + ", "
                        + regionHeight + ")");
                }
                break;
            case "intersections":
                // Find all intersecting rectangles
                data.intersections();
                break;
            case "search":
                // Find all rectangles with this name
                data.search(commandArray[1]);
                break;
            case "dump":
                // Print complete skip list
                data.dump();
                break;
            default:
                System.out.println("Unrecognized Command"); // Unknown command
                break;
        }
    }

}
