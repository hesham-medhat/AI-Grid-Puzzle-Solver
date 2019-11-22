package Engine.Solvers;

import Engine.Solution;
import Engine.Solver;

import java.util.*;

public class BFSSolver extends Solver {
    /* Instance Variables */
    private long startTimeMillis;

    private int nodesExpanded = 0;

    /* Constructors */

    public BFSSolver(int rowDimension, int columnDimension) {
        super(rowDimension, columnDimension);
        agentName = "BFS Solver";
    }

    public BFSSolver(int rowDimension, int columnDimension, String agentName) {
        super(rowDimension, columnDimension, agentName);
    }


    /* Core Methods */
    @Override
    public Solution solve(String startingState) {
        /* Initialization */
        nodesExpanded = 0;
        startTimeMillis = System.currentTimeMillis();

        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> visitedBy;// Hash map mapping a visited state node to its parent.
        visitedBy = new HashMap<>();

        visitedBy.put(startingState, null);
        if (isSolution(startingState)) {
            return traceSolution(startingState, visitedBy);
        }
        queue.add(startingState);

        String currentState, nextState;
        int zeroIndex, zeroRow, zeroColumn;
        while (!queue.isEmpty()) {
            nodesExpanded++;
            currentState = queue.poll();

            zeroIndex = currentState.indexOf('0');
            zeroColumn = getColumnCoordinate(zeroIndex);
            zeroRow = getRowCoordinate(zeroIndex);

            /* Left */
            nextState = moveZeroLeft(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState, visitedBy);
                }
                queue.add(nextState);
            }

            /* Up */
            nextState = moveZeroUp(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState, visitedBy);
                }
                queue.add(nextState);
            }

            /* Right */
            nextState = moveZeroRight(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState, visitedBy);
                }
                queue.add(nextState);
            }

            /* Down */
            nextState = moveZeroDown(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState, visitedBy);
                }
                queue.add(nextState);
            }
        }

        return new Solution();// Empty solution indicating failure
    }

    private Solution traceSolution(String finalState, HashMap<String, String> parentMap) {
        /* Reconstruct solution from parents */
        Stack<String> solutionStack = new Stack<>();

        String currentState = finalState;
        while (currentState != null) {
            solutionStack.add(currentState);
            currentState = parentMap.get(currentState);
        }

        /* Generate the solutionArray */
        String[] solutionArray = new String[solutionStack.size()];
        for (int i = 0; i < solutionStack.size(); i++) {
            solutionArray[i] = solutionStack.pop();
        }

        long runningTime = System.currentTimeMillis() - startTimeMillis;

        return new Solution(solutionArray, solutionArray.length, nodesExpanded, solutionArray.length, runningTime);
    }
}
