package javaapplication8;

import java.util.ArrayList;

public abstract class Piece {

    protected String col;
    protected String name;
    protected int x;
    protected int y;
    protected int piecenumber;
    protected String image;

    protected boolean userOwnership;

//    public void setColor(String color) {
//        col = color;
//        Board ss = new Board(col);
//        ss.setBoard(col);
//        ss.setGame();
//    }
//    
  
    public String getColor()
    {
        return this.col;
    }
    public void setPieceToUser() {
        this.userOwnership = true;
    }

    public String getImage() {
        return this.image;
    }

    public Piece() {
        this.userOwnership = false;
    }

    public Piece(int piecenumber, int x, int y, String image) {
        this.x = x;
        this.y = y;
        this.piecenumber = piecenumber;
        this.image = image;

        this.userOwnership = false;
    }
    public String getName()
    { 
        return this.name;
    }
    public boolean valid(Square squares[][], int a, int b, boolean temp) {
        Piece pieceonSquare = squares[a][b].getPiece();
        if (pieceonSquare == null) {
            return true;
        } else {
            if (pieceonSquare.userOwnership != temp) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validOnOpponent(Square squares[][], int a, int b, boolean temp){
        Piece pieceonSquare = squares[a][b].getPiece();
        if (pieceonSquare != null && pieceonSquare.userOwnership != temp)
            return true;
        return false;
    }

    public abstract void setWhite();

    public abstract void setBlack();

    public abstract ArrayList<Square> getValidMoves(Square[][] squares, Location current_location);

}
