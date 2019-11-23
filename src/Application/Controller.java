package Application;

import Engine.Solver;
import Engine.Solution;
import Engine.Solvers.DFSSolver;
import Engine.Solvers.BFSSolver;
import Engine.Solvers.AStarSearch.AStarSearchSolver;
import Engine.HeuristicStrategies.ManhattanDistanceHeuristicStrategy;
import Engine.HeuristicStrategies.EuclideanDistanceHeuristicStrategy;

import Engine.Solvers.DFSSolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {
    ObservableList<String> algorithmsList =
            FXCollections.observableArrayList("DFS", "BFS", "AStar Manhattan", "AStar Euclidean");

    @FXML
    private ChoiceBox<String> algorithmPicker;

    @FXML
    private TextArea startStateTextArea;

    @FXML
    private Button grid0;
    @FXML
    private Button grid1;
    @FXML
    private Button grid2;
    @FXML
    private Button grid3;
    @FXML
    private Button grid4;
    @FXML
    private Button grid5;
    @FXML
    private Button grid6;
    @FXML
    private Button grid7;
    @FXML
    private Button grid8;

    private Button[] gridall;

    int currentState = 0;
    Solution sol;

    @FXML
    private void next() {
        if (currentState + 1 < sol.getPath().length) {
            currentState += 1;
            renderState(sol.getPath()[currentState]);
        }
    }

    @FXML
    private void prev() {
        if (currentState > 0) {
            currentState -= 1;
            renderState(sol.getPath()[currentState]);
        }
    }

    @FXML
    Button solveButton;

    @FXML
    private void initialize() {
        algorithmPicker.setItems(algorithmsList);
        algorithmPicker.setValue("DFS");
        gridall = new Button[] {
                grid0, grid1, grid2,
                grid3, grid4, grid5,
                grid6, grid7, grid8};

        solveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                solveGrid();
            }
        });
    }

    private void renderState(String state) {
        for (int i = 0; i < state.length(); i++) {
            gridall[i].setText(String.valueOf(state.charAt(i)));
        }
    }

    @FXML
    private void solveGrid() {
        currentState = 0;
        Solver solver;
        String startState = startStateTextArea.getText();
        switch (algorithmPicker.getValue()) {
            case "DFS":
                solver = new DFSSolver(3, 3, "A");
                break;
            case "BFS":
                solver = new BFSSolver(3, 3, "B");
                break;
            case "AStar Manhattan":
                solver = new AStarSearchSolver(3, 3, "C",
                        new ManhattanDistanceHeuristicStrategy(3, 3, "012345678"));
                break;
            case "AStar Euclidean":
                solver = new AStarSearchSolver(3, 3, "D",
                        new EuclideanDistanceHeuristicStrategy(3, 3, "012345678"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + algorithmPicker.getValue());
        }

        sol = solver.solve(startState);
        renderState(startState);
    }
}
