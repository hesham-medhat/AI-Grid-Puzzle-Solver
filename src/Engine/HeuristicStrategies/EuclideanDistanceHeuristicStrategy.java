package Engine.HeuristicStrategies;

import javafx.util.Pair;

public class EuclideanDistanceHeuristicStrategy extends PositionalDifferenceHeuristicStrategy {
    public EuclideanDistanceHeuristicStrategy(int rowDimension, int columnDimension, String goalState) {
        super(rowDimension, columnDimension, goalState);
    }

    @Override
    protected int calculateHeuristicDistanceFromCoordinates(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> finalPosition) {
        int distance = 0;

        distance += Math.ceil(
                Math.sqrt(
                        Math.pow(finalPosition.getKey() - currentPosition.getKey(), 2)
                        ));// Add row distance

        distance += Math.ceil(
                Math.sqrt(
                        Math.pow(finalPosition.getValue() - currentPosition.getValue(), 2)
                ));// Add column distance

        return distance;
    }
}
