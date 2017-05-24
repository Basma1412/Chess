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
public class King extends Piece {

    public King()
    {
        this.name="King";
    }
    
    
    
//    public int setNum(String num) {
//        String temp = num.concat("5");
//        int hh = Integer.parseInt(temp);
//        return hh;
//    }

    public void setBlack() {
        this.image = "..\\pictures\\black\\king.png";
    }

    public void setWhite() {
        this.image = "..\\pictures\\white\\king.png";
    }

    @Override
    public ArrayList<Square> getValidMoves(Square[][] squares, Location current_location) {

        int x = current_location.getI();
        int y = current_location.getJ();

        int maxX = squares[0].length;
        int maxY = squares.length;
        ArrayList<Square> valid_moves = new ArrayList<>();

        int forward = y + 1;
        int backward = y - 1;
        int up = x - 1;
        int down = x + 1;
        
        if (userOwnership){
            valid_moves.add(squares[x][y]);
        }

        if (down < maxX && valid(squares, down, y, userOwnership)) {
            valid_moves.add(squares[down][y]);
        }
        if (up >= 0 && valid(squares, up, y, userOwnership)) {
            valid_moves.add(squares[up][y]);
        }
        if (forward < maxX && valid(squares, x, forward, userOwnership)) {
            valid_moves.add(squares[x][forward]);
        }
        if (forward < maxX && down < maxX && valid(squares, down, forward, userOwnership)) {
            valid_moves.add(squares[down][forward]);
        }
        if (forward < maxX && up >= 0 && valid(squares, up, forward, userOwnership)) {
            valid_moves.add(squares[up][forward]);
        }
        if (backward >= 0 && valid(squares, x, backward, userOwnership)) {
            valid_moves.add(squares[x][backward]);
        }

        if (backward >= 0 && up >= 0 && valid(squares, up, backward, userOwnership)) {
            valid_moves.add(squares[up][backward]);
        }
        if (backward >= 0 && down < maxX && valid(squares, down, backward, userOwnership)) {
            valid_moves.add(squares[down][backward]);
        }

        return valid_moves;
    }

}
