package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {
    ObservableList<String> algorithmsList =
            FXCollections.observableArrayList("DFS", "BFS", "AStar Manhattan", "AStar Euclidean");

    @FXML
    private ChoiceBox<String> algorithmPicker;

    @FXML
    private void initialize() {
        algorithmPicker.setValue("DFS");
        algorithmPicker.setItems(algorithmsList);
    }
}
