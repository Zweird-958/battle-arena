package org.example.battlearena;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Application {
    private static final int GRID_SIZE = 8;
    private static final int CELL_SIZE = 80;

    private List<Player> players = new ArrayList<>();
    private Player selectedPlayer = null;
    private int currentPlayerIndex = 0; // Indice du joueur actif
    private Label turnLabel = new Label(); // Texte pour afficher le tour du joueur

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        // Création de la grille
        Rectangle[][] gridCells = new Rectangle[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setFill((row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY);
                cell.setStroke(Color.BLACK);
                int finalRow = row, finalCol = col;

                // Ajout de l'événement de clic
                cell.setOnMouseClicked(event -> handleMove(finalRow, finalCol));

                gridCells[row][col] = cell;
                gridPane.add(cell, col, row);
            }
        }

        // Ajout des joueurs (on les place aux coins)
        players.add(new Player(0, 0, Color.RED));
        players.add(new Player(0, GRID_SIZE - 1, Color.BLUE));
        players.add(new Player(GRID_SIZE - 1, 0, Color.GREEN));
        players.add(new Player(GRID_SIZE - 1, GRID_SIZE - 1, Color.YELLOW));

        for (Player player : players) {
            gridPane.add(player.getShape(), player.getCol(), player.getRow());
        }

        // Ajout d'un texte en haut pour indiquer quel joueur joue
        updateTurnLabel();

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(turnLabel);
        root.setCenter(gridPane);

        // Configuration de la scène
        Scene scene = new Scene(root, GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE + 40);
        primaryStage.setTitle("Battle Arena - Tour par tour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Met à jour le texte affichant quel joueur joue
    private void updateTurnLabel() {
        Player currentPlayer = players.get(currentPlayerIndex);
        turnLabel.setText("Au tour du joueur " + (currentPlayerIndex + 1) + " de jouer !");
        turnLabel.setTextFill(currentPlayer.getColor());
        turnLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    }

    // Gestion des déplacements
    private void handleMove(int row, int col) {
        Player currentPlayer = players.get(currentPlayerIndex);

        // Vérifie si le déplacement est en ligne droite (comme une tour)
        if (currentPlayer.getRow() == row || currentPlayer.getCol() == col) {
            System.out.println("Déplacement du joueur !");
            currentPlayer.moveTo(row, col);
            nextTurn(); // Passe au joueur suivant
        } else {
            System.out.println("Déplacement invalide !");
        }
    }

    // Passe au joueur suivant
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Tour suivant
        updateTurnLabel(); // Met à jour l'affichage du joueur actif
    }

    public static void main(String[] args) {
        launch(args);
    }
}
