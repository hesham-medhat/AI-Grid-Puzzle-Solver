package Engine;

public interface SolvingStrategy {
    Solution solve(String state, int rowDimension, int columnDimension);
}
