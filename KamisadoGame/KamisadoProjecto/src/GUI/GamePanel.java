package GUI;

import Graph.KamisadoGraph;
import Graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static GUI.GameLogic.ValidMove;
import static GUI.GameLogic.checkWhiteWin;
import static Graph.KamisadoGraph.populateAvailableMoves;

public class GamePanel extends JFrame implements ActionListener {

    GameState gameState = GameState.SELECT;

    // short currentPlayer = Constants.PLAYERWHITE;
    GameBoard gb;
    Square selectedSquare = null;
    KamisadoGraph graph = KamisadoGraph.createGraph();
    ArrayList<Vertex> availableMoves = new ArrayList<Vertex>();
    int numTurn=0;
    int fromRow;
    int toRow;
    int fromCol;
    int toCol;
    private static int currPieceX = 0;
    private static int currPieceY = 0;
    private static int PieceToX = 0;
    private static int PieceToY = 0;
    private final LogFrame logDocumantation;
    //rndTurn = 1 -> WhiteTurn
    //rndturn = 0 -> BlackTurn
    private int rndTurn;
    private Piece.PieceType pieceTypeHolder;
    private ImageIcon logo;

    private static Color standingColor;
    private static boolean firstMove = true;
    private static boolean botPlay = true;
    public GamePanel() throws InterruptedException {
        //add logo
        logo = new ImageIcon("KamisadoProjecto/Images/Icons/Logo.png");
        this.setIconImage(logo.getImage());

        gb = new GameBoard(this);

        setTitle("Kamisado game");
        add(gb.getGui());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        //Determine Who Starts
        this.rndTurn = new Random().nextInt(2);
        pieceTypeHolder = rndTurn == 1 ? Piece.PieceType.WHITE : Piece.PieceType.BLACK;
        System.out.println("Now's Turn: " + pieceTypeHolder.toString());
        setSize(8 * 64 + 32, 8 * 64 + 20);
        setVisible(true);
        logDocumantation = new LogFrame();
        if(this.rndTurn == 0 && botPlay)
        {
            Thread.sleep(1000);
            selectedSquare = gb.getKamisadoBoardSquares()[0][3];
            graph.botAfterMovement(graph, gb.getKamisadoBoardSquares()[0][3].getPiece().getColorPiece(),  3 * Constants.BOARD_SIZE + 3);
            makeMove(0,3,3,3, gb.getKamisadoBoardSquares()[0][3].getPiece());
            rndTurn = 1;
        }


    }
    @Override
    public void actionPerformed(ActionEvent e) {

        boolean valid = false;
        Square sourceBtn = (Square) e.getSource();
        if (gameState == GameState.GAME_OVER) {
            return; // Stop further execution of actionPerformed
        }
        if (gameState == GameState.SELECT)
        {
            currPieceX = sourceBtn.getRow();
            currPieceY = sourceBtn.getCol();
            for (fromRow = 0; fromRow < Constants.BOARD_SIZE; fromRow++)
            {
                for ( fromCol = 0; fromCol < Constants.BOARD_SIZE; fromCol++)
                {
                    if ( e.getSource() == gb.getKamisadoBoardSquares()[fromRow][fromCol]
                            &&gb.getKamisadoBoardSquares()[fromRow][fromCol].getPieceImage() != null
                        && sourceBtn.getPiece().getPieceType() == pieceTypeHolder)
                    {
                        selectedSquare = gb.getKamisadoBoardSquares()[fromRow][fromCol];
                        gameState = GameState.MOVE;

                        break;
                    }
                }
            }

        }
        else if (gameState == GameState.MOVE)
        {
            PieceToX = sourceBtn.getRow();
            PieceToY = sourceBtn.getCol();

            valid = ValidMove(gb.getKamisadoBoardSquares(), currPieceX, currPieceY, PieceToX, PieceToY);
            for (toRow = 0; toRow < Constants.BOARD_SIZE; toRow++)
            {
                for (toCol = 0; toCol < Constants.BOARD_SIZE; toCol++)
                {
                    if (e.getSource() == gb.getKamisadoBoardSquares()[toRow][toCol] &&
                            gb.getKamisadoBoardSquares()[toRow][toCol].getPieceImage() == null && valid
                            && (firstMove ? true : standingColor.equals(gb.getKamisadoBoardSquares()[currPieceX][currPieceY].getPiece().getColorPiece()))) {
                        makeMove(currPieceX, currPieceY, toRow, toCol, gb.getKamisadoBoardSquares()[currPieceX][currPieceY].getPiece());
                        gameState = checkWhiteWin(toRow) ? GameState.GAME_OVER : gameState;
                        if (!(gameState == GameState.GAME_OVER)) {
                            try {
                                botMove(gb.getKamisadoBoardSquares()[toRow][toCol].getColor());
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        }
                        else {
                            logDocumantation.addText("<html><br>White Wins! </br> </html>");
                        }
                    }
                    else
                    {
                        gameState = GameState.SELECT;
                    }
                }
            }
        }

    }

    public void buildText(boolean turn, int fromRow, int fromCol, int toRow, int toCol, Piece piece)
    {
        Color pieceColor = piece.getColorPiece();
        String color = getStringColor(pieceColor);
        String player = turn ? "White" : "Black";
        String textToLog = player + " - " + color + " Tower "  + " from [" +
                fromRow + ", " + fromCol + "] to [" + toRow + ", " + toCol + "]";
        logDocumantation.addText(textToLog);
    }

    public String getStringColor(Color pColor)
    {

        if (pColor.equals(Constants.BROWN)) {
            return "BROWN";
        } else if (pColor.equals(Constants.GREEN)) {
            return "GREEN";
        } else if (pColor.equals(Constants.RED)) {
            return "RED";
        } else if (pColor.equals(Constants.YELLOW)) {
            return "YELLOW";
        } else if (pColor.equals(Constants.PINK)) {
            return "PINK";
        } else if (pColor.equals(Constants.PURPLE)) {
            return "PURPLE";
        } else if (pColor.equals(Constants.BLUE)) {
            return "BLUE";
        } else if (pColor.equals(Constants.ORANGE)) {
            return "ORANGE";
        }

        return "UNKNOWN COLOR";
    }

    public void makeMove(int currPieceX,int currPieceY, int PieceToX, int PieceToY, Piece piece)
    {

        if(piece.getPieceType() == Piece.PieceType.BLACK)
        {
            graph.botAfterMovement(graph, gb.getKamisadoBoardSquares()[currPieceX][currPieceY].getPiece().getColorPiece(),  PieceToX * Constants.BOARD_SIZE + PieceToY);
        }
        else
        {
            graph.getVertexById(currPieceX*Constants.BOARD_SIZE + currPieceY).setOccupied(false);
            graph.getVertexById(PieceToX*Constants.BOARD_SIZE + PieceToY).setOccupied(true);
        }
        gb.getKamisadoBoardSquares()[PieceToX][PieceToY].setPieceImage(selectedSquare.getPieceImage());
        gb.getKamisadoBoardSquares()[PieceToX][PieceToY].setOccupied(true);
        gb.getKamisadoBoardSquares()[PieceToX][PieceToY].setPiece(selectedSquare.getPiece());
        selectedSquare.setPieceImage(null);
        selectedSquare.setOccupied(false);
        selectedSquare.setPiece(null);
        buildText(((rndTurn == 1) ? true : false),currPieceX,currPieceY,PieceToX,PieceToY, piece);
        //Switch Turns
        rndTurn = (rndTurn + 1)%2;
        pieceTypeHolder = (rndTurn == 1) ? Piece.PieceType.WHITE : Piece.PieceType.BLACK;
        gameState = GameState.SELECT;
        standingColor = gb.getKamisadoBoardSquares()[PieceToX][PieceToY].getColor();
        firstMove = false;
    }

    public void botMove(Color whiteStandingColor) throws InterruptedException {
        Thread.sleep(1000);
        Random random = new Random();
        ArrayList<Integer> middleMoves = new ArrayList<>();
        ArrayList<Integer> blockMoves = new ArrayList<>();
        ArrayList<Integer> otherMoves = new ArrayList<>();
        ArrayList<Integer> sortedIds = new ArrayList<>();

        availableMoves.clear();
        blockMoves.addAll(populateAvailableMoves(graph, graph.getVertexById(graph.getBotTowerID(whiteStandingColor)), availableMoves));
        int row = (graph.getBotTowerID(whiteStandingColor) - (graph.getBotTowerID(whiteStandingColor) % Constants.BOARD_SIZE)) / Constants.BOARD_SIZE;
        int col = graph.getBotTowerID(whiteStandingColor) % Constants.BOARD_SIZE;
        Piece piece = gb.getKamisadoBoardSquares()[row][col].getPiece();
        System.out.println(graph.getVertexById(graph.getBotTowerID(whiteStandingColor)).getId());
        selectedSquare = gb.getKamisadoBoardSquares()[row][col];

        for (Vertex vertex : availableMoves) {
            sortedIds.add(vertex.getId());
        }

        for (Integer x : sortedIds) {
            if (x > 56 && x < 64) {
                makeMove(row, col, 7, x - 56, piece);
                logDocumantation.addText("<html><br>Black Wins!</html>");
                return;
            } else if (x >= 32 && x < 40 || x >= 24 && x < 32) {
                middleMoves.add(x);
            } else {
                if (!blockMoves.contains(x)) {
                    otherMoves.add(x);
                }
            }
        }

       if(!middleMoves.isEmpty())
       {
           int randomIndex = random.nextInt(middleMoves.size());
           Integer randomId = middleMoves.get(randomIndex);
           makeMove(row, col, (randomId - (randomId%Constants.BOARD_SIZE)) / Constants.BOARD_SIZE, randomId % Constants.BOARD_SIZE, piece);
       }
       else if(!blockMoves.isEmpty())
       {
           blockMoves.removeAll(Collections.singleton(null));
           int randomIndex = random.nextInt(blockMoves.size());
           Integer randomId = blockMoves.get(randomIndex);
           makeMove(row, col, (randomId - (randomId%Constants.BOARD_SIZE)) / Constants.BOARD_SIZE, randomId % Constants.BOARD_SIZE, piece);
       }
       else if(!otherMoves.isEmpty())
       {
           int randomIndex = random.nextInt(otherMoves.size());
           Integer randomId = otherMoves.get(randomIndex);
           makeMove(row, col, (randomId - (randomId%Constants.BOARD_SIZE)) / Constants.BOARD_SIZE, randomId % Constants.BOARD_SIZE, piece);
       }
       else
       {
           makeMove(row,col,row,col, piece);
       }
    }

}
