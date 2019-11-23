package Engine.HeuristicStrategies;

import javafx.util.Pair;

public class ManhattanDistanceHeuristicStrategy extends PositionalDifferenceHeuristicStrategy {

    public ManhattanDistanceHeuristicStrategy(int rowDimension, int columnDimension, String goalState) {
        super(rowDimension, columnDimension, goalState);
    }

    @Override
    public int calculateHeuristicDistanceFromCoordinates(Pair<Integer, Integer> currentPosition,
                                                         Pair<Integer, Integer> finalPosition) {
        int distance = 0;
        distance += Math.abs(finalPosition.getKey() - currentPosition.getKey());// Add row distance
        distance += Math.abs(finalPosition.getValue() - currentPosition.getValue());// Add column distance
        return distance;
    }
}
