package Engine.HeuristicStrategies;

import javafx.util.Pair;

import java.util.HashMap;

public abstract class PositionalDifferenceHeuristicStrategy extends HeuristicStrategy {
    /* Instance Variables */
    /* Maps character of block to its coordinates */
    private HashMap<Character, Pair<Integer, Integer>> finalPositionMap;


    /* Constructors */
    public PositionalDifferenceHeuristicStrategy(int rowDimension, int columnDimension, String goalState) {
        super(rowDimension, columnDimension, goalState);
        buildFinalPositionMap();
    }


    /* Core Methods */

    private void buildFinalPositionMap() {
        finalPositionMap = new HashMap<>();
        for (int i = 0; i < goalState.length(); i++) {
            Character block = goalState.charAt(i);
            Pair<Integer, Integer> position = new Pair<>(getRowCoordinate(i), getColumnCoordinate(i));
            finalPositionMap.put(block, position);
        }
    }

    protected int getRowCoordinate(int index) {
        return index / columnDimension;
    }

    protected int getColumnCoordinate(int index) {
        return index % columnDimension;
    }

    protected Pair<Integer, Integer> getFinalPosition(Character block) {
        return finalPositionMap.get(block);
    }
}
