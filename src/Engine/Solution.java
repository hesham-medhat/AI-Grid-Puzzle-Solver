package Engine;

public class Solution {
    /* Instance Variables */

    /**
     * Solution path consisting of string states representation from start to finish.
     */
    private String[] path;

    private boolean successful;


    /* Constructors */

    public Solution(String[] path, boolean successful) {
        this.path = path;
        this.successful = successful;
    }

    /**
     * Common constructor for the happy successful scenario.
     * @param path successful path consisting of string states representation from start to finish.
     */
    public Solution(String[] path) {
        this.path = path;
        this.successful = true;
    }

    /**
     * Assuming failure since no path is given.
     */
    public Solution() {
        successful = false;
    }


    /* Getters */

    public String[] getPath() {
        return path;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
