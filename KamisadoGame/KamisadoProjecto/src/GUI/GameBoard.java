package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private JPanel gui = new JPanel(new BorderLayout(3, 3));
    Square[][] kamisadoBoardSquares = new Square[8][8];
    Piece[][] kamisadoPieces = new Piece[2][8];
    // 0 is black
    // 1 is white
    JPanel piecesPanel = new JPanel(new GridLayout(8,8, 1, 1));
    private JPanel kamisadoBoardPositionsPanel;
    GamePanel gamePanel;

    public GameBoard(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initializeGui();
    }

    public void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(0, 0, 0, 0));

        gui.add(new JLabel(" "), BorderLayout.LINE_START);


        kamisadoBoardPositionsPanel = new JPanel(new GridLayout(0, 9));
        kamisadoBoardPositionsPanel.setBorder(new LineBorder(Color.BLACK));


        gui.add(kamisadoBoardPositionsPanel);

        for (int row = 0; row < kamisadoBoardSquares.length; row++) {
            for (int col = 0; col < kamisadoBoardSquares[row].length; col++) {

                kamisadoBoardSquares[row][col] = new Square(row, col, null);
                kamisadoBoardSquares[row][col].addActionListener(gamePanel);
                piecesPanel.add(kamisadoBoardSquares[row][col]);

            }
        }
        initPieces();
        SetColorPieces();
        gui.add(piecesPanel);
        SetColorBoard();
        gamePanel.add(kamisadoBoardPositionsPanel);


    }
    private void SetColorPieces()
    {
        for (int i = 0; i < kamisadoBoardSquares.length; i++) {
            kamisadoBoardSquares[i][i].setColor(Constants.ORANGE);
            kamisadoBoardSquares[i][kamisadoBoardSquares.length - (i + 1)].setColor(Constants.BROWN);
        }
        //COLOR BLUE
        int j=1;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setColor(Constants.BLUE);
            j+=3;
        }

        // COLOR PURPLE
        j=2;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setColor(Constants.PURPLE);
            j+=5;
        }
        // COLOR PINK
        j=11;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setColor(Constants.PINK);
            j--;
        }
        // COLOR YELLOW
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][(i+4)%8].setColor(Constants.YELLOW);
        }
        // COLOR RED
        j=5;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setColor(Constants.RED);
            j+=3;
        }
        // COLOR GREEN
        j=6;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setColor(Constants.GREEN);
            j+=5;
        }
    }
    private void initPieces() {

        // black pieces
        kamisadoBoardSquares[0][0].setPieceImage(ServiceClass.getOrangetowerB());
        kamisadoBoardSquares[0][1].setPieceImage(ServiceClass.getBluetowerB());
        kamisadoBoardSquares[0][2].setPieceImage(ServiceClass.getPurpletowerB());
        kamisadoBoardSquares[0][3].setPieceImage(ServiceClass.getPinktowerB());
        kamisadoBoardSquares[0][4].setPieceImage(ServiceClass.getYellowtowerB());
        kamisadoBoardSquares[0][5].setPieceImage(ServiceClass.getRedtowerB());
        kamisadoBoardSquares[0][6].setPieceImage(ServiceClass.getGreentowerB());
        kamisadoBoardSquares[0][7].setPieceImage(ServiceClass.getBrowntowerB());

        kamisadoBoardSquares[0][0].setPiece(kamisadoPieces[0][0] = new Piece(0,0,kamisadoBoardSquares[0][0].getPieceImage(),Constants.ORANGE, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][1].setPiece(kamisadoPieces[0][1] = new Piece(0,1,kamisadoBoardSquares[0][1].getPieceImage(),Constants.BLUE, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][2].setPiece(kamisadoPieces[0][2] = new Piece(0,2,kamisadoBoardSquares[0][2].getPieceImage(),Constants.PURPLE, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][3].setPiece(kamisadoPieces[0][3] = new Piece(0,3,kamisadoBoardSquares[0][3].getPieceImage(),Constants.PINK, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][4].setPiece(kamisadoPieces[0][4] = new Piece(0,4,kamisadoBoardSquares[0][4].getPieceImage(),Constants.YELLOW, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][5].setPiece(kamisadoPieces[0][5] = new Piece(0,5,kamisadoBoardSquares[0][5].getPieceImage(),Constants.RED, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][6].setPiece(kamisadoPieces[0][6] = new Piece(0,6,kamisadoBoardSquares[0][6].getPieceImage(),Constants.GREEN, Piece.PieceType.BLACK));
        kamisadoBoardSquares[0][7].setPiece(kamisadoPieces[0][7] = new Piece(0,7,kamisadoBoardSquares[0][7].getPieceImage(),Constants.BROWN, Piece.PieceType.BLACK));

        // white pieces
        kamisadoBoardSquares[7][0].setPieceImage(ServiceClass.getBrowntowerW());
        kamisadoBoardSquares[7][1].setPieceImage(ServiceClass.getGreentowerW());
        kamisadoBoardSquares[7][2].setPieceImage(ServiceClass.getRedtowerW());
        kamisadoBoardSquares[7][3].setPieceImage(ServiceClass.getYellowtowerW());
        kamisadoBoardSquares[7][4].setPieceImage(ServiceClass.getPinktowerW());
        kamisadoBoardSquares[7][5].setPieceImage(ServiceClass.getPurpletowerW());
        kamisadoBoardSquares[7][6].setPieceImage(ServiceClass.getBluetowerW());
        kamisadoBoardSquares[7][7].setPieceImage(ServiceClass.getOrangetowerW());

        kamisadoBoardSquares[7][0].setPiece(kamisadoPieces[1][0] = new Piece(7,0,kamisadoBoardSquares[7][0].getPieceImage(),Constants.BROWN, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][1].setPiece(kamisadoPieces[1][1] = new Piece(7,1,kamisadoBoardSquares[7][1].getPieceImage(),Constants.GREEN, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][2].setPiece(kamisadoPieces[1][2] = new Piece(7,2,kamisadoBoardSquares[7][2].getPieceImage(),Constants.RED, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][3].setPiece(kamisadoPieces[1][3] = new Piece(7,3,kamisadoBoardSquares[7][3].getPieceImage(),Constants.YELLOW, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][4].setPiece(kamisadoPieces[1][4] = new Piece(7,4,kamisadoBoardSquares[7][4].getPieceImage(),Constants.PINK, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][5].setPiece(kamisadoPieces[1][5] = new Piece(7,5,kamisadoBoardSquares[7][5].getPieceImage(),Constants.PURPLE, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][6].setPiece(kamisadoPieces[1][6] = new Piece(7,6,kamisadoBoardSquares[7][6].getPieceImage(),Constants.BLUE, Piece.PieceType.WHITE));
        kamisadoBoardSquares[7][7].setPiece(kamisadoPieces[1][7] = new Piece(7,7,kamisadoBoardSquares[7][7].getPieceImage(),Constants.ORANGE, Piece.PieceType.WHITE));

        // refresher
        gamePanel.repaint();
    }

    public final JComponent getGui() {
        return gui;
    }

    public void SetColorBoard() {
        for (int i = 0; i < kamisadoBoardSquares.length; i++) {
            kamisadoBoardSquares[i][i].setBackground(Constants.ORANGE);
            kamisadoBoardSquares[i][kamisadoBoardSquares.length - (i + 1)].setBackground(Constants.BROWN);
        }
        //COLOR BLUE
        int j=1;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setBackground(Constants.BLUE);
            j+=3;
        }

         // COLOR PURPLE
            j=2;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setBackground(Constants.PURPLE);
            j+=5;
        }
        // COLOR PINK
            j=11;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setBackground(Constants.PINK);
            j--;
        }
        // COLOR YELLOW
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][(i+4)%8].setBackground(Constants.YELLOW);
        }
        // COLOR RED
            j=5;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setBackground(Constants.RED);
            j+=3;
        }
        // COLOR GREEN
            j=6;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            kamisadoBoardSquares[i][j%8].setBackground(Constants.GREEN);
            j+=5;
        }
    }

    public void setGui(JPanel gui) {
        this.gui = gui;
    }

    public Square[][] getKamisadoBoardSquares() {
        return kamisadoBoardSquares;
    }

    public void setKamisadoBoardSquares(Square[][] kamisadoBoardSquares) {
        this.kamisadoBoardSquares = kamisadoBoardSquares;
    }

    public JPanel getPiecesPanel() {
        return piecesPanel;
    }

    public void setPiecesPanel(JPanel piecesPanel) {
        this.piecesPanel = piecesPanel;
    }

    public JPanel getKamisadoBoardPositionsPanel() {
        return kamisadoBoardPositionsPanel;
    }

    public void setKamisadoBoardPositionsPanel(JPanel kamisadoBoardPositionsPanel) {
        this.kamisadoBoardPositionsPanel = kamisadoBoardPositionsPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
