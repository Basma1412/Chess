package javaapplication8;

import java.util.ArrayList;

/**
 *
 * @author Basma Mohamed
 */
public class Rook  extends Piece {

    public Rook()
    {
        this.name="Rook";
    }
   
    
    public void setBlack()
    {
        this.image="..\\pictures\\black\\rook.png";
    }
    
    
    
    public void setWhite()
    {
        this.image="..\\pictures\\white\\rook.png";
    }
    
//    public int setNum(String num){
//        String temp = num.concat("1");
//        int hh = Integer.parseInt(temp);
//        return hh;
//    }

    @Override
    public ArrayList<Square> getValidMoves(Square[][] squares, Location current_location) {
        int x = current_location.getI();
        int y = current_location.getJ();

        int maxX = squares[0].length;
        int maxY = squares.length;

        int up = x - 1;
        int down = x + 1;
        int left = y-1;
        int right = y+1;

        ArrayList<Square> valid_moves = new ArrayList<>();
        
        if (userOwnership){
            valid_moves.add(squares[x][y]);
        }
//left
        for (int a = left; a >= 0; a--) {
            if ( valid(squares, x, a, userOwnership)){
                valid_moves.add(squares[x][a]);
                if (validOnOpponent(squares, x, a, userOwnership)){
                    break;
                }
            }
            else break;

        }
//down
        for (int a = down; a < maxX; a++) {
            if (valid(squares, a, y, userOwnership)){
                valid_moves.add(squares[a][y]);
                if (validOnOpponent(squares, a, y, userOwnership)){
                    break;
                }
            }
            else break;
        }
//up
        for (int a = up; a > 0; a--) {
            if (valid(squares, a, y, userOwnership)){
                valid_moves.add(squares[a][y]);
                if (validOnOpponent(squares, a, y, userOwnership)){
                    break;
                }
            }
            else break;
        }
//right
        for (int a = right; a < maxY; a++) {
            if (valid(squares, x, a, userOwnership)){
                valid_moves.add(squares[x][a]);
                if (validOnOpponent(squares, x, a, userOwnership)){
                    break;
                }
            }
            else break;
        }
            return valid_moves;

}
    
}