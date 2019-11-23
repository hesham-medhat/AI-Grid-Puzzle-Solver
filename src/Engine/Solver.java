package Engine;

public abstract class Solver {
    /* Instance Variables */

    /**
     * 2D Dimensions of the problem
     */
    protected int rowDimension, columnDimension;

    protected String agentName;

    protected String solutionState;


    /* Constructors */

    public Solver(int rowDimension, int columnDimension) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        initializeSolutionState();
    }

    public Solver(int rowDimension, int columnDimension, String agentName) {
        this.rowDimension = rowDimension;
        this.columnDimension = columnDimension;
        this.agentName = agentName;
        initializeSolutionState();
    }


    /* Core Methods */

    public abstract Solution solve(String startingState);

    /**
     * Return whether given position is valid assuming 0-indexing. True if valid, and false otherwise.
     * @param row row position.
     * @param column column position.
     */
    protected boolean isValidPosition(int row, int column) {
        return row >= 0 && column >= 0 && row < rowDimension && column < columnDimension;
    }

    protected boolean isSolution(String state) {
        return solutionState.equals(state);
    }

    protected String moveBlockCoordinates(String state, int r1, int r2, int c1, int c2) {
        int firstIndex, secondIndex;
        firstIndex = r1 * columnDimension + c1;
        secondIndex = r2 * columnDimension + c2;

        return moveBlockIndices(state, firstIndex, secondIndex);
    }

    protected String moveBlockIndices(String state, int firstIndex, int secondIndex) {
        if (state == null) {
            throw new IllegalArgumentException("Passed state as null");
        }

        int lowerIndex = Math.min(firstIndex, secondIndex);
        int higherIndex = Math.max(firstIndex, secondIndex);

        StringBuilder sb = new StringBuilder(state.length());

        return sb.append(state.substring(0, lowerIndex))
                .append(state.charAt(higherIndex))
                .append(state.substring(lowerIndex + 1, higherIndex))
                .append(state.charAt(lowerIndex))
                .append(state.substring(higherIndex + 1))
                .toString();
    }

    protected String moveZeroLeft(String state, int zeroRow, int zeroColumn) {
        if (state == null) {
            throw new IllegalArgumentException("Passed state as null");
        }

        if (zeroRow == 0) {
            return null;// Invalid move
        }
        return moveBlockCoordinates(state, zeroRow, zeroRow - 1, zeroColumn, zeroColumn);
    }

    protected String moveZeroRight(String state, int zeroRow, int zeroColumn) {
        if (state == null) {
            throw new IllegalArgumentException("Passed state as null");
        }

        if (zeroRow == rowDimension - 1) {
            return null;// Invalid move
        }
        return moveBlockCoordinates(state, zeroRow, zeroRow + 1, zeroColumn, zeroColumn);
    }

    protected String moveZeroUp(String state, int zeroRow, int zeroColumn) {
        if (state == null) {
            throw new IllegalArgumentException("Passed state as null");
        }

        if (zeroColumn == 0) {
            return null;// Invalid move
        }
        return moveBlockCoordinates(state, zeroRow, zeroRow, zeroColumn - 1, zeroColumn);
    }

    protected String moveZeroDown(String state, int zeroRow, int zeroColumn) {
        if (state == null) {
            throw new IllegalArgumentException("Passed state as null");
        }

        if (zeroColumn == columnDimension - 1) {
            return null;// Invalid move
        }
        return moveBlockCoordinates(state, zeroRow, zeroRow, zeroColumn + 1, zeroColumn );
    }

    protected int getRowCoordinate(int index) {
        return index / columnDimension;
    }

    protected int getColumnCoordinate(int index) {
        return index % columnDimension;
    }

    /**
     * Used by constructors to initialize solutionState by deducing the shape of the solution state from dimensions
     */
    private void initializeSolutionState() {
        int blocks = rowDimension * columnDimension;
        StringBuilder sb = new StringBuilder(blocks);
        for (int blockNumber = 0; blockNumber < blocks; blockNumber++) {
            sb.append((char) (('0') + blockNumber));
        }
        solutionState = sb.toString();
    }


    /* Getters */

    public int getXDimension() {
        return rowDimension;
    }

    public int getYDimension() {
        return columnDimension;
    }

    public String getAgentName() {
        return agentName;
    }


    /* Setters */

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getSolutionState() {
        return solutionState;
    }
}
