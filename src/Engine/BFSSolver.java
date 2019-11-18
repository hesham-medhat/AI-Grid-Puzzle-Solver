package Engine;

import java.util.*;

public class BFSSolver extends Solver {
    /* Instance Variables */

    private HashSet<String> visited;
    private Queue<String> queue;


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
        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> visitedBy;// Hash map mapping a visited state node to its parent.
        visitedBy = new HashMap<>();

        visitedBy.put(startingState, null);
        if (isSolution(startingState)) {
            traceSolution(startingState);
        }
        queue.add(startingState);

        String currentState, nextState;
        int zeroIndex, zeroRow, zeroColumn;
        while (!queue.isEmpty()) {
            currentState = queue.poll();

            zeroIndex = currentState.indexOf('0');
            zeroColumn = getColumnCoordinate(zeroIndex);
            zeroRow = getRowCoordinate(zeroIndex);

            /* Left */
            nextState = moveZeroLeft(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState);
                }
                queue.add(nextState);
            }

            /* Up */
            nextState = moveZeroUp(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState);
                }
                queue.add(nextState);
            }

            /* Right */
            nextState = moveZeroRight(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState);
                }
                queue.add(nextState);
            }

            /* Down */
            nextState = moveZeroDown(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited
                visitedBy.put(nextState, currentState);// Mark visited and record parent for backtracking
                if (isSolution(nextState)) {
                    return traceSolution(nextState);
                }
                queue.add(nextState);
            }
        }

        return new Solution();// Empty solution indicating failure
    }

    private Solution traceSolution(String finalState) {
        ArrayList<String> solutionList = new ArrayList<>();
        // TODO: Trace solution
        return null;
    }
}
