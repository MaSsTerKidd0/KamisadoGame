package Graph;

import GUI.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class KamisadoGraph {
    // private HashMap<Vertex, List<Vertex>> adjVertices;
    private HashMap<Integer, Vertex> vertices;
    private static HashMap<Color, Integer> blackTowersByColorMap;

    public KamisadoGraph() {
        vertices = new HashMap<>();
        blackTowersByColorMap = new HashMap<>();
    }

    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    public void setAdjVertices(HashMap<Vertex, List<Vertex>> adjVertices) {
        this.vertices = vertices;
    }
    public void addVertex(int id,Color colorSquare) {
        Vertex vertex = new Vertex(id,colorSquare);
        vertices.put(id, vertex);
    }

    public void addEdgeWhite(Vertex v1, Vertex v2)
    {
        vertices.get(v1.getId()).getWhiteAdj().put(v2.getId(), new Edge(v2));
    }
    public void addEdgeBlack(Vertex v1, Vertex v2)
    {
        vertices.get(v1.getId()).getBlackAdj().put(v2.getId(), new Edge(v2));
    }


    public void removeEdge(int sourceId, int destinationId) {
        Vertex source = vertices.get(sourceId);
        Vertex destination = vertices.get(destinationId);
        source.getWhiteAdj().remove(destinationId);
        source.getBlackAdj().remove(destinationId);
        destination.getWhiteAdj().remove(sourceId);
        destination.getBlackAdj().remove(sourceId);
    }

    public static KamisadoGraph createGraph()
    {
        KamisadoGraph graph = new KamisadoGraph();
        int j;
        // Add vertices
        for (int i = 0; i < Constants.BOARD_SIZE; i++)
        {
            graph.addVertex(i * Constants.BOARD_SIZE + i, Constants.ORANGE);
            graph.addVertex(i * Constants.BOARD_SIZE + Constants.BOARD_SIZE - (i + 1), Constants.BROWN);
        }


        // COLOR BLUE
            j=1;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            graph.addVertex(i * Constants.BOARD_SIZE + (j%8),Constants.BLUE);
            j+=3;
        }

        // COLOR PURPLE
            j = 2;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            graph.addVertex(i * Constants.BOARD_SIZE + (j%8),Constants.PURPLE);
            j+=5;
        }
        // COLOR PINK
            j = 11;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            graph.addVertex(i * Constants.BOARD_SIZE + (j%8),Constants.PINK);
            j--;
        }
        // COLOR YELLOW
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            graph.addVertex(i * Constants.BOARD_SIZE + ((i+4)%8),Constants.YELLOW);
        }
        // COLOR RED
            j = 5;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            graph.addVertex(i * Constants.BOARD_SIZE + (j%8),Constants.RED);
            j+=3;
        }

        // COLOR GREEN
            j=6;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
           graph.addVertex(i * Constants.BOARD_SIZE + (j%8),Constants.GREEN);
           j+=5;
        }

        // Connect vertices with diagonal and horizontal edges
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for ( j = 0; j < Constants.BOARD_SIZE; j++) {
                Vertex v1 = graph.getVertexById(i * Constants.BOARD_SIZE + j);
                if (i > 0) {
                    // Connect with diagonal edge going up-left
                    if (j > 0) {
                        Vertex v2 = graph.getVertexById((i - 1) * Constants.BOARD_SIZE + (j - 1));
                        graph.addEdgeWhite(v1, v2);
                    }
                    // Connect with diagonal edge going up-right
                    if (j < Constants.BOARD_SIZE - 1) {
                        Vertex v2 = graph.getVertexById((i - 1) * Constants.BOARD_SIZE + (j + 1));
                        graph.addEdgeWhite(v1, v2);
                    }
                }
                if (i < Constants.BOARD_SIZE - 1) {
                    // Connect with diagonal edge going down-left
                    if (j > 0) {
                        Vertex v2 = graph.getVertexById((i + 1) * Constants.BOARD_SIZE + (j - 1));
                        graph.addEdgeBlack(v1, v2);
                    }
                    // Connect with diagonal edge going down-right
                    if (j < Constants.BOARD_SIZE - 1) {
                        Vertex v2 = graph.getVertexById((i + 1) * Constants.BOARD_SIZE + (j + 1));
                        graph.addEdgeBlack(v1, v2);
                    }
                }
                // Connect with vertical edge going up
                if (i > 0) {
                    Vertex v2 = graph.getVertexById(j + Constants.BOARD_SIZE * (i - 1));
                    graph.addEdgeWhite(v1, v2);
                }
                // Connect with vertical edge going down
                if (i < Constants.BOARD_SIZE - 1) {
                    Vertex v2 = graph.getVertexById(j + Constants.BOARD_SIZE * (i + 1)); // i = 3 j = 3
                    graph.addEdgeBlack(v1, v2);
                }

                // Connect with diagonal edge going up-right
                if (i > 0 && j > 0) {
                    Vertex v2 = graph.getVertexById((i - 1) * Constants.BOARD_SIZE + (j - 1));
                    graph.addEdgeWhite(v1, v2);
                }
                // Connect with diagonal edge going down-right
                if (i < Constants.BOARD_SIZE - 1 && j > 0) {
                    Vertex v2 = graph.getVertexById((i + 1) * Constants.BOARD_SIZE + (j - 1));
                    graph.addEdgeBlack(v1, v2);
                }
            }
        }
        // Mark vertices with i = 0 and i = 7 as occupied
        for (int col = 0; col < Constants.BOARD_SIZE; col++) {
            graph.getVertexById(col).setOccupied(true);
            blackTowersByColorMap.put(graph.getVertexById(col).getColorSquare(), col);
            graph.getVertexById((Constants.BOARD_SIZE - 1) * Constants.BOARD_SIZE + col).setOccupied(true);
        }

        return graph;
    }
    public static ArrayList<Integer> populateAvailableMoves(KamisadoGraph graph, Vertex currentVertex, ArrayList<Vertex> availableMoves) {
        int row = currentVertex.getId() / Constants.BOARD_SIZE;
        int col = currentVertex.getId() % Constants.BOARD_SIZE;
        Integer blockMove = null;
        ArrayList<Integer> blockMoves = new ArrayList<>();
        // Check down-left diagonal edge
        for (int i = row + 1, j = col - 1; i < Constants.BOARD_SIZE && j >= 0; i++, j--) {
            Vertex v = graph.getVertexById(i * Constants.BOARD_SIZE + j);
            if (!v.isOccupied()) {
                availableMoves.add(v);
                blockMove = v.getId();
            } else {
                break;
            }
        }
        blockMoves.add(blockMove);
        // Check down-right diagonal edge
        for (int i = row + 1, j = col + 1; i < Constants.BOARD_SIZE && j < Constants.BOARD_SIZE; i++, j++) {
            Vertex v = graph.getVertexById(i * Constants.BOARD_SIZE + j);
            if (!v.isOccupied()) {
                availableMoves.add(v);
                blockMove = v.getId();
            } else {
                break;
            }
        }
        blockMoves.add(blockMove);
        // Check down vertical edge
        for (int i = row + 1; i < Constants.BOARD_SIZE; i++) {
            Vertex v = graph.getVertexById(i * Constants.BOARD_SIZE + col);
            if (!v.isOccupied()) {
                availableMoves.add(v);
                blockMove = v.getId();
            } else {
                break;
            }
        }
        blockMoves.add(blockMove);
        return blockMoves;
    }




    // Helper method to retrieve a vertex from its id
    public Vertex getVertexById(int id) {
        return vertices.get(id);
    }


    public void botAfterMovement(KamisadoGraph graph, Color standingColor, Integer newId)
    {
        Vertex source = graph.getVertexById(blackTowersByColorMap.get(standingColor));
        Vertex destination = graph.getVertexById(newId);
        blackTowersByColorMap.replace(standingColor, newId);
        source.setOccupied(false);
        destination.setOccupied(true);
    }

    public Integer getBotTowerID(Color standingColor)
    {
        return blackTowersByColorMap.get(standingColor);
    }

}