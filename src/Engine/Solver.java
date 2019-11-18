package Engine;

public abstract class Solver {
    /* Instance Variables */

    /**
     * 2D Dimensions of the problem
     */
    protected int rowDimension, columnDimension;

    protected String agentName;

    protected String solutionState;


    /* Constructors */

    public Solver(int rowDimension, int columnDimension) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        initializeSolutionState();
    }

    public Solver(int rowDimension, int columnDimension, String agentName) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        this.agentName = agentName;
        initializeSolutionState();
    }


    /* Core Methods */

    public abstract Solution solve(String startingState);

    /**
     * Return whether given position is valid assuming 0-indexing. True if valid, and false otherwise.
     * @param row row position.
     * @param column column position.
     */
    protected boolean isValidPosition(int row, int column) {
        return row >= 0 && column >= 0 && row < rowDimension && column < columnDimension;
    }

    protected boolean isSolution(String state) {
        return solutionState.equals(state);
    }

    /**
     * Used by constructors to initialize solutionState by deducing the shape of the solution state from dimensions
     */
    private void initializeSolutionState() {
        int blocks = rowDimension * columnDimension;
        StringBuilder sb = new StringBuilder(blocks);
        for (int blockNumber = 0; blockNumber < blocks; blockNumber++) {
            sb.append((char) (('0') + blockNumber));
        }
        solutionState = sb.toString();
    }


    /* Getters */

    public int getXDimension() {
        return rowDimension;
    }

    public int getYDimension() {
        return columnDimension;
    }

    public String getAgentName() {
        return agentName;
    }


    /* Setters */

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
