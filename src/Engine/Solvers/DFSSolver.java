package Engine.Solvers;

import Engine.Solution;
import Engine.Solver;

public class DFSSolver extends Solver {
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
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
