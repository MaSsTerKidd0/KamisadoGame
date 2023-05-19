package Graph;

import GUI.Piece;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class Vertex {

    //private HashMap<Integer, Edge> adjs;
    private int id;
    private Color colorSquare;
    private boolean isOccupied;
    HashMap<Integer,Edge> blackAdj = new HashMap<>();
    HashMap<Integer,Edge> whiteAdj = new HashMap<>();

    public Vertex(int id, Color colorSquare) {
        this.id = id;
        this.colorSquare = colorSquare;
        this.isOccupied = false;
        this.blackAdj = new HashMap<>();
        this.whiteAdj = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColorSquare() {
        return colorSquare;
    }

    public void setColorSquare(Color colorSquare) {
        this.colorSquare = colorSquare;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public HashMap<Integer, Edge> getBlackAdj() {
        return blackAdj;
    }

    public void setBlackAdj(HashMap<Integer, Edge> blackAdj) {
        this.blackAdj = blackAdj;
    }

    public HashMap<Integer, Edge> getWhiteAdj() {
        return whiteAdj;
    }

    public void setWhiteAdj(HashMap<Integer, Edge> whiteAdj) {
        this.whiteAdj = whiteAdj;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id && isOccupied == vertex.isOccupied && Objects.equals(colorSquare, vertex.colorSquare) && Objects.equals(blackAdj, vertex.blackAdj) && Objects.equals(whiteAdj, vertex.whiteAdj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, colorSquare, isOccupied, blackAdj, whiteAdj);
    }
}