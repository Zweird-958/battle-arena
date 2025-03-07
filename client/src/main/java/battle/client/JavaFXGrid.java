package battle.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class JavaFXGrid extends Application {
    private static final int SIZE = 8;
    private static final int PLAYERS = 4;
    private Label[][] cells = new Label[SIZE][SIZE];
    private GridPane grid;

    @Override
    public void start(Stage primaryStage) {
        grid = new GridPane();

        // Initialisation des cellules
        updateGrid(List.of(
            new Position(0, 1),
            new Position(1, 2),
            new Position(3, 4),
            new Position(5, 6)
        ));

        fetchAndDisplayGameState();


        VBox root = new VBox(10, grid);
        Scene scene = new Scene(root, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grille 8x8 JavaFX");
        primaryStage.show();
    }



    private void updateGrid(List<Position> playerPositions) {
        grid.getChildren().clear();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Label cell = new Label(" ");
                cell.setStyle("-fx-border-color: black; -fx-min-width: 40px; -fx-min-height: 40px; -fx-alignment: center;");
                grid.add(cell, col, row);
                cells[row][col] = cell;
            }
        }

        for (Position pos : playerPositions) {
            cells[pos.y][pos.x].setText("X");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void fetchAndDisplayGameState() {
        try {
            // Appel API (on suppose playerId=1 pour l'exemple)
            List<Player> state = ApiClient.getPlayers();

            List<Position> playerPositions = new ArrayList<>();

            if (state != null) {
                for (Player player : state) {
                    playerPositions.add(new Position(player.getX(), player.getY()));
                }
            }

            updateGrid(playerPositions);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}