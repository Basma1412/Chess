/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.util.ArrayList;

/**
 *
 * @author Basma Mohamed
 */
public class Bishop  extends Piece{

 
    public Bishop()
    {
        this.name="Bishop";
    }
   
    public void setBlack()
    {
        this.image="..\\pictures\\black\\bishop.png";
    }
    
//     public int setNum(String num){
//        String temp = num.concat("3");
//        int hh = Integer.parseInt(temp);
//        return hh;
//    }
    
    
    public void setWhite()
    {
        this.image="..\\pictures\\white\\bishop.png";
    }
    
    
    @Override
    public ArrayList<Square> getValidMoves(Square[][] squares, Location current_location) {

        int x = current_location.getI();
        int y = current_location.getJ();

        int maxX = squares[0].length;
        int maxY = squares.length;

        ArrayList<Square> valid_moves = new ArrayList<>();
        if (userOwnership){
            valid_moves.add(squares[x][y]);
        }
 //diagonal up left
        for (int a = y-1, b = x-1; a >=0 && b >= 0; a--, b--) {
            if ( valid(squares, b, a, userOwnership)){
                valid_moves.add(squares[b][a]);
                if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            }
            
            else break;
           
        }
//diagonal right down
        for (int a = y+1, b = x+1; a < maxY && b < maxX; a++, b++) {
            if ( valid(squares, b, a, userOwnership)){
                valid_moves.add(squares[b][a]);
                if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            }
            else break;
        }
//diagonal up right 
        for (int a = y+1, b = x-1; a < maxY && b >= 0; a++, b--) {
            if ( valid(squares, b, a, userOwnership)){
                valid_moves.add(squares[b][a]);
                if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            }
            else break;
        }
//diagonal down left
        for (int a = y-1, b = x+1; a >=0 && b < maxX; a--, b++) {
            if ( valid(squares, b, a, userOwnership)){
                valid_moves.add(squares[b][a]);
                if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            }
            else break;
        }

        return valid_moves;
 
    }
    
}
