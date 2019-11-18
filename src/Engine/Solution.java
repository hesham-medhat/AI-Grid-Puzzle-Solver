package Engine;

public class Solution {
    /* Instance Variables */

    /**
     * Solution path consisting of string states representation from start to finish.
     */
    private String[] path;

    private boolean successful;

    private int pathCost;

    private int nodesExpanded;

    private int searchDepth;

    private long runningTimeMillis;


    /* Constructors */

    public Solution(String[] path, boolean successful,
                    int pathCost, int nodesExpanded, int searchDepth, long runningTimeMillis) {
        this.path = path;
        this.successful = successful;

        this.pathCost = pathCost;
        this.nodesExpanded = nodesExpanded;
        this.searchDepth = searchDepth;
        this.runningTimeMillis = runningTimeMillis;
    }

    /**
     * Common constructor for the happy successful scenario.
     * @param path successful path consisting of string states representation from start to finish.
     */
    public Solution(String[] path,
                    int pathCost, int nodesExpanded, int searchDepth, long runningTimeMillis) {
        this.path = path;
        this.successful = true;

        this.pathCost = pathCost;
        this.nodesExpanded = nodesExpanded;
        this.searchDepth = searchDepth;
        this.runningTimeMillis = runningTimeMillis;
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

    public int getPathCost() {
        return pathCost;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public long getRunningTimeMillis() {
        return runningTimeMillis;
    }
}
