package GUI;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    private int row, col;
    private ImageIcon PieceImage;
    private Color color;
    private Piece piece;
    private boolean occupied;


    public Square(int row, int col, ImageIcon pieceImage) {
        this.row = row;
        this.col = col;
        this.PieceImage = pieceImage;
        setIcon(pieceImage);
        this.occupied = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ImageIcon getPieceImage() {
        return PieceImage;
    }

    public void setPieceImage(ImageIcon pieceImage) {
        PieceImage = pieceImage;
        setIcon(pieceImage);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        if(piece == null)
        {
            this.piece = null;
            return;
        }
        this.piece = new Piece(row, col, PieceImage, piece.getColorPiece(),piece.getPieceType());
    }

}
