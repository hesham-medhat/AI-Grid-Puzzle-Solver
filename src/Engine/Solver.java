package Engine;

public class Solver {
    /* Instance Variables */

    /**
     * 2D Dimensions of the problem
     */
    private int xDimension, yDimension;

    private SolvingStrategy strategy;

    private String agentName;


    /* Constructors */

    public Solver(int xDimension, int yDimension, SolvingStrategy strategy) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.strategy = strategy;
    }

    public Solver(int xDimension, int yDimension, SolvingStrategy strategy, String agentName) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.strategy = strategy;
        this.agentName = agentName;
    }


    /* Core Methods */

    public Solution solve(String startingState) {
        return strategy.solve(startingState, xDimension, yDimension);
    }


    /* Getters */

    public int getXDimension() {
        return xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }

    public SolvingStrategy getStrategy() {
        return strategy;
    }

    public String getAgentName() {
        return agentName;
    }


    /* Setters */

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
