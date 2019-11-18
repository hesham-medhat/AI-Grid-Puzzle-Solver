package Engine;

public abstract class Solver {
    /* Instance Variables */

    /**
     * 2D Dimensions of the problem
     */
    protected int rowDimension, columnDimension;

    protected String agentName;


    /* Constructors */

    public Solver(int rowDimension, int columnDimension) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
    }

    public Solver(int rowDimension, int columnDimension, String agentName) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        this.agentName = agentName;
    }


    /* Core Methods */

    public abstract Solution solve(String startingState);

    /**
     * Return whether given state is valid assuming 0-indexing. True if valid, and false otherwise.
     * @param row row position.
     * @param column column position.
     */
    protected boolean isValidState(int row, int column) {
        return row >= 0 && column >= 0 && row < rowDimension && column < columnDimension;
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
