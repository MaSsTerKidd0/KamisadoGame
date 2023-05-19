package GUI;

import javax.swing.*;
import java.awt.*;

public class Piece
{
    private Color colorPiece;
    enum PieceType {
        WHITE, BLACK, NONE
    }

    private PieceType pieceType = PieceType.NONE;


    public Piece(int row, int col, ImageIcon pieceImage,Color colorPiece,PieceType pieceType) {
        this.colorPiece = colorPiece;
        this.pieceType = pieceType;
    }

    public Piece(Piece other) {
        this.colorPiece = other.colorPiece;
        this.pieceType = other.pieceType;
    }

    public Color getColorPiece() {
        return colorPiece;
    }

    public void setColorPiece(Color colorPiece) {
        this.colorPiece = colorPiece;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
