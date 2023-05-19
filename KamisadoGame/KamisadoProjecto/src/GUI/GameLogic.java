package GUI;

public class GameLogic {

    public static boolean ValidMove(Square[][] board, int startX, int startY, int endX, int endY)
    {

        if (board == null) {
            System.out.println("Invalid move: board is null");
            return false;
        }
        if (board[startX][startY] == null) {
            System.out.println("Invalid move: starting square is null");
            return false;
        }
        if (board[endX][endY] == null) {
            System.out.println("Invalid move: ending square is null");
            return false;
        }

        // Check if the end position is within the bounds of the board
        if (startX < 0 || startX > 7 || startY < 0 || startY > 7) {
            System.out.println("Invalid move: start position out of bounds startx is: " + startX + " startY is: " + startY);
            return false;
        }

        if (endX < 0 || endX > 7 || endY < 0 || endY > 7) {
            System.out.println("Invalid move: end position out of bounds");
            return false;
        }

        // Get the piece at the start position
        Piece piece = board[startX][startY].getPiece();
        if (piece == null) {
            System.out.println("Invalid move: there is no piece at the start position");
            return false;
        }

        // check if the destination is within the bounds of the board
        if (endX < 0 || endX >= board.length || endY < 0 || endY >= board[0].length) {
            return false;
        }
        Piece.PieceType pType = piece.getPieceType();

        //validation
        boolean flag = true;
        // check if the move is diagonal, up, left, or right
        int ABSdx = Math.abs(endX - startX),
                ABSdy = Math.abs(endY - startY),
                rows = endX - startX,
                cols = endY - startY;

        if(pType == Piece.PieceType.WHITE)
        {
            if (rows >= 0)
                return false;
            else if (startX > endX && startY == endY) {
                for (int x = startX - 1; x > endX; x--) {
                    if (board[x][startY].getPiece() != null) {
                        System.out.println("Piece Is in The way: " + x + ", " + startY);
                        return false;
                    }
                }
            }
            else
            {
                //in way to check both diagonals from white prespective
                for (int count = 1, temp = startY + ((startY < endY) ? 1 : -1); count < ABSdx; count++, temp += (startY < endY) ? 1 : -1) {
                    //checking if there is a piece on board on the way to location
                    if (board[startX - count][temp].getPiece() != null) {
                        return false;
                    }
                }
            }


        }

        else if(pType == Piece.PieceType.BLACK)
        {
            if(rows <= 0)
                return false;

            // this loop find if there is a piece in front of black player
            else if (startX < endX && startY == endY) {
                for (int x = startX + 1; x < endX; ++x) {
                    if (board[x][startY].getPiece() != null) {
                        System.out.println("Piece Is in The way: " + x + ", " + startY);
                        return false;
                    }
                }
            }
            else
            {
                //in way to check both diagonals from Black prespective
                for (int count = 1, temp = startY + ((startY > endY) ? -1 : 1); count <  ABSdx ; count++, temp += (startY > endY) ? -1 : 1 ) {
                    //checking if there is a piece on board on the way to location
                    if (board[startX + count][temp].getPiece() != null) {
                        return false;
                    }
                }
            }
        }

        return !(ABSdx != ABSdy && (ABSdx == 0 && ABSdy == 0 || ABSdx > 0 && ABSdy > 0));

    }


    public static boolean checkWhiteWin(int row){
        return row == 0 ;
    }
}
