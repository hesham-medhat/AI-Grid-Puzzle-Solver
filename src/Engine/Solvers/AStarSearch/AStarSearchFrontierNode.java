package Engine.Solvers.AStarSearch;

public class AStarSearchFrontierNode implements Comparable<AStarSearchFrontierNode> {
    /* Instance Variables */

    private int depth;

    private int predictedPathToGoal;

    private String stateString;

    private String parentState;


    /* Constructors */

    public AStarSearchFrontierNode(int depth, int predictedPathToGoal, String stateString, String parentState) {
        this.depth = depth;
        this.predictedPathToGoal = predictedPathToGoal;
        this.stateString = stateString;
        this.parentState = parentState;
    }


    /* Core Methods */

    /**
     * Returns the predicted cost to reach goal as depth + predictedPathToGoal
     * @return Predicted cost to reach goal
     */
    public int getPredictedCost() {
        return predictedPathToGoal + depth;
    }

    @Override
    public int compareTo(AStarSearchFrontierNode aStarSearchFrontierNode) {
        return this.getPredictedCost() - aStarSearchFrontierNode.getPredictedCost();
    }


    /* Getters */

    public int getDepth() {
        return depth;
    }

    public String getParentState() {
        return parentState;
    }

    public String toString() {
        return stateString;
    }
}
