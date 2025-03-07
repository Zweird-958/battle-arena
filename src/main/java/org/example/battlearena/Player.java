package org.example.battlearena;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private int row, col;
    private Circle shape;
    private Color color;

    public Player(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.shape = new Circle(30, color);
    }

    public void moveTo(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        shape.setTranslateX(newCol * 80 + 40); // Centrage dans la case
        shape.setTranslateY(newRow * 80 + 40);
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Circle getShape() { return shape; }

    // Nouvelle méthode pour récupérer la couleur du joueur
    public Color getColor() {
        return this.color;
    }
}
