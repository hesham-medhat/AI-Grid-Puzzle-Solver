package Engine.Solvers;

import Engine.Solution;
import Engine.Solver;

import java.util.HashMap;
import java.util.HashSet;

public class DFSSolver extends Solver {
    /* Instance Variables */

    HashSet<String> visited = new HashSet<>();

    /**
     * Maps state to its parent for backtracking.
     */
    HashMap<String, String> stateParent;

    private String[] solutionArray;

    private int nodesExpanded;

    private int searchDepth;


    /* Constructors */
    public DFSSolver(int rowDimension, int columnDimension) {
        super(rowDimension, columnDimension);
        agentName = "DFS Solver";
    }

    public DFSSolver(int rowDimension, int columnDimension, String agentName) {
        super(rowDimension, columnDimension, agentName);
    }


    /* Core Methods */
    @Override
    public Solution solve(String state) {
        /* Initialization */
        solutionArray = null;
        visited = new HashSet<String>();
        nodesExpanded = searchDepth = 0;

        long startTimeMillis = System.currentTimeMillis();

        solveRecursively(state, 1);

        if (solutionArray == null) {// Did not solve
            return new Solution();// Return empty solution indicating failure
        }
        solutionArray[0] = state;

        long runningTimeMillis = System.currentTimeMillis() - startTimeMillis;

        return new Solution(solutionArray, solutionArray.length, nodesExpanded, searchDepth, runningTimeMillis);
    }

    /**
     * Uses recursive DFS to find the solution.
     * @param state Current state to explore
     * @return Success (true) or failure (false)
     */
    private boolean solveRecursively (String state, int depth) {
        nodesExpanded++;
        searchDepth = Math.max(searchDepth, depth);

        if (isSolution(state)) {// Initiate building the solution
            solutionArray = new String[depth + 1];
            solutionArray[depth] = state;
            return true;
        }

        visited.add(state);

        int zeroIndex = state.indexOf('0');
        int zeroRow = getRowCoordinate(zeroIndex);
        int zeroColumn = getColumnCoordinate(zeroIndex);

        String newState;
        boolean childSuccess = false;

        /* Down */
        newState = moveZeroDown(state, zeroRow, zeroColumn);
        if (newState != null && !visited.contains(newState)) {
            childSuccess = solveRecursively(newState, depth + 1);
        }
        if (childSuccess) {
            solutionArray[depth] = state;
            return true;
        }

        /* Up */
        newState = moveZeroUp(state, zeroRow, zeroColumn);
        if (newState != null && !visited.contains(newState)) {
            childSuccess = solveRecursively(newState, depth + 1);
        }
        if (childSuccess) {
            solutionArray[depth] = state;
            return true;
        }

        /* Left */
        newState = moveZeroLeft(state, zeroRow, zeroColumn);
        if (newState != null && !visited.contains(newState)) {
            childSuccess = solveRecursively(newState, depth + 1);
        }
        if (childSuccess) {
            solutionArray[depth] = state;
            return true;
        }

        /* Right */
        newState = moveZeroRight(state, zeroRow, zeroColumn);
        if (newState != null && !visited.contains(newState)) {
            childSuccess = solveRecursively(newState, depth + 1);
        }
        if (childSuccess) {
            solutionArray[depth] = state;
            return true;
        }

        return false;// Did not reach solution from this state
    }
}
