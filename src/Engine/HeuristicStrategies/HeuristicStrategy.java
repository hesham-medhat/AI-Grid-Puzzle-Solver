package Engine.HeuristicStrategies;

public abstract class HeuristicStrategy {
    /* Instance Variables */

    protected int rowDimension;

    protected int columnDimension;

    protected String goalState;


    /* Constructors */

    public HeuristicStrategy(int rowDimension, int columnDimension, String goalState) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        this.goalState = goalState;
    }

    /* Core Methods */

    public abstract int calculateHeuristic(String state);


    /* Getters */

    public int getRowDimension() {
        return rowDimension;
    }

    public int getColumnDimension() {
        return columnDimension;
    }

    public String getGoalState() {
        return goalState;
    }
}
