package Engine.Solvers.AStarSearch;

import Engine.HeuristicStrategies.HeuristicStrategy;
import Engine.Solution;
import Engine.Solver;

import java.util.*;

public class AStarSearchSolver extends Solver {
    /* Instance Variables */

    private long startTimeMillis;

    private int nodesExpanded = 0;

    private int maxDepth = 0;

    private HeuristicStrategy heuristicStrategy;



    /* Constructors */

    public AStarSearchSolver(int rowDimension, int columnDimension, HeuristicStrategy heuristicStrategy) {
        super(rowDimension, columnDimension);
        agentName = "A-Star Search Solver";

        this.heuristicStrategy = heuristicStrategy;
    }

    public AStarSearchSolver(int rowDimension, int columnDimension, String agentName,
                             HeuristicStrategy heuristicStrategy) {
        super(rowDimension, columnDimension, agentName);

        this.heuristicStrategy = heuristicStrategy;
    }


    /* Core Methods */

    @Override
    public Solution solve(String startingState) {
        /* Initialization */

        nodesExpanded = 0;
        startTimeMillis = System.currentTimeMillis();
        maxDepth = 0;

        HashMap<String, AStarSearchFrontierNode> frontierSet = new HashMap<>();
        PriorityQueue<AStarSearchFrontierNode> frontierNodes = new PriorityQueue<>();

        HashMap<String, String> visitedBy;// Hash map mapping an explored state node to its parent.
        visitedBy = new HashMap<>();

        /* Build start state */
        AStarSearchFrontierNode startNode = new AStarSearchFrontierNode(0,
                heuristicStrategy.calculateHeuristic(startingState), startingState, null);
        frontierNodes.add(startNode);

        /* Prepare loop variables */
        int zeroIndex, zeroRow, zeroColumn, childDepth;
        String nextState;
        AStarSearchFrontierNode currentFrontierNode;
        while (!frontierNodes.isEmpty()) {
            nodesExpanded++;
            currentFrontierNode = frontierNodes.poll();
            String currentState = currentFrontierNode.toString();

            visitedBy.put(currentState, currentFrontierNode.getParentState());// Add to explored state

            zeroIndex = currentState.indexOf('0');
            zeroColumn = getColumnCoordinate(zeroIndex);
            zeroRow = getRowCoordinate(zeroIndex);

            if (currentFrontierNode.getPredictedCost() == currentFrontierNode.getDepth()) { // Goal state to process
                return traceSolution(currentState, visitedBy);
            }


            /* Construct and add children */

            childDepth = currentFrontierNode.getDepth() + 1;
            /* Left */
            nextState = moveZeroLeft(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited

                /* Add if not in frontier or update parent in frontier if coming from shorter depth */
                AStarSearchFrontierNode nextStateFrontierNode = frontierSet.get(nextState);
                if (nextStateFrontierNode == null) {
                    nextStateFrontierNode = new AStarSearchFrontierNode(childDepth,
                            heuristicStrategy.calculateHeuristic(nextState), nextState, currentState);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                } else if (nextStateFrontierNode.getDepth() > childDepth) {
                    frontierNodes.remove(nextStateFrontierNode);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                }
            }

            /* Up */
            nextState = moveZeroUp(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited

                /* Add if not in frontier or update parent in frontier if coming from shorter depth */
                AStarSearchFrontierNode nextStateFrontierNode = frontierSet.get(nextState);
                if (nextStateFrontierNode == null) {
                    nextStateFrontierNode = new AStarSearchFrontierNode(childDepth,
                            heuristicStrategy.calculateHeuristic(nextState), nextState, currentState);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                } else if (nextStateFrontierNode.getDepth() > childDepth) {
                    frontierNodes.remove(nextStateFrontierNode);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                }
            }

            /* Right */
            nextState = moveZeroRight(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited

                /* Add if not in frontier or update parent in frontier if coming from shorter depth */
                AStarSearchFrontierNode nextStateFrontierNode = frontierSet.get(nextState);
                if (nextStateFrontierNode == null) {
                    nextStateFrontierNode = new AStarSearchFrontierNode(childDepth,
                            heuristicStrategy.calculateHeuristic(nextState), nextState, currentState);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                } else if (nextStateFrontierNode.getDepth() > childDepth) {
                    frontierNodes.remove(nextStateFrontierNode);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                }
            }

            /* Down */
            nextState = moveZeroDown(currentState, zeroRow, zeroColumn);
            if (nextState != null && !visitedBy.containsKey(nextState)) {// Valid and not visited

                /* Add if not in frontier or update parent in frontier if coming from shorter depth */
                AStarSearchFrontierNode nextStateFrontierNode = frontierSet.get(nextState);
                if (nextStateFrontierNode == null) {
                    nextStateFrontierNode = new AStarSearchFrontierNode(childDepth,
                            heuristicStrategy.calculateHeuristic(nextState), nextState, currentState);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                } else if (nextStateFrontierNode.getDepth() > childDepth) {
                    frontierNodes.remove(nextStateFrontierNode);

                    frontierSet.put(nextState, nextStateFrontierNode);
                    frontierNodes.add(nextStateFrontierNode);
                }
            }
        }

        return new Solution();// Return empty solution for failure
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
        for (int i = 0; !solutionStack.empty(); i++) {
            solutionArray[i] = solutionStack.pop();
        }

        long runningTime = System.currentTimeMillis() - startTimeMillis;

        return new Solution(solutionArray, solutionArray.length - 1, nodesExpanded, maxDepth, runningTime);
    }
}
