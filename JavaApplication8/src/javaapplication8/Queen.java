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
public class Queen extends Piece {

    public Queen()
    {
        this.name="Queen";
    }
   
    public void setBlack() {
        this.image = "..\\pictures\\black\\queen.png";
    }

    public void setWhite() {
        this.image = "..\\pictures\\white\\queen.png";
    }

//    public int setNum(String num){
//        String temp = num.concat("4");
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

        ArrayList<Square> valid_moves = new ArrayList<>();
//left

        if (userOwnership) {
            valid_moves.add(squares[x][y]);
       }
        for (int a = y - 1; a >= 0; a--) {
            if (valid(squares, x, a, userOwnership)) {
                valid_moves.add(squares[x][a]);
                if (validOnOpponent(squares, x, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }

        }
//down
        for (int a = x + 1; a < maxX; a++) {
            if (valid(squares, a, y, userOwnership)) {
                valid_moves.add(squares[a][y]);
                if (validOnOpponent(squares, a, y, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }
//up
        for (int a = x - 1; a >= 0; a--) {
            if (valid(squares, a, y, userOwnership)) {
                valid_moves.add(squares[a][y]);
                if (validOnOpponent(squares, a, y, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }
//right
        for (int a = y + 1; a < maxY; a++) {
            if (valid(squares, x, a, userOwnership)) {
                valid_moves.add(squares[x][a]);
                if (validOnOpponent(squares, x, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }

//diagonal up left
        for (int a = y - 1, b = x - 1; a >= 0 && b >= 0; a--, b--) {
            if (valid(squares, b, a, userOwnership)) {
                valid_moves.add(squares[b][a]);
                 if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }

        }
//diagonal right down
        for (int a = y + 1, b = x + 1; a < maxY && b < maxX; a++, b++) {
            if (valid(squares, b, a, userOwnership)) {
                valid_moves.add(squares[b][a]);
                 if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }
//diagonal up right 
        for (int a = y + 1, b = x - 1; a < maxY && b >= 0; a++, b--) {
            if (valid(squares, b, a, userOwnership)) {
                valid_moves.add(squares[b][a]);
                 if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }
//diagonal down left
        for (int a = y - 1, b = x + 1; a >= 0 && b < maxX; a--, b++) {
            if (valid(squares, b, a, userOwnership)) {
                valid_moves.add(squares[b][a]);
                 if (validOnOpponent(squares, b, a, userOwnership)){
                    break;
                }
            } else {
                break;
            }
        }
////diagonal up left
//        for (int a = y, b = x; a < maxY && b >= 0; a++, b--) {
//            if (valid(squares, b, a, userOwnership))
//                valid_moves.add(squares[b][a]);
//            else break;
//        }
////diagonal right down
//        for (int a = y, b = x; a >= 0 && b < maxX; a--, b++) {
//            if (valid(squares, b, a, userOwnership))
//                valid_moves.add(squares[b][a]);
//            else break;
//        }
////diagonal up right 
//        for (int a = y, b = x; a >= 0 && b >= 0; a--, b--) {
//            if (valid(squares, b, a, userOwnership))
//                valid_moves.add(squares[b][a]);
//            else break;
//        }
////diagonal down left
//        for (int a = y, b = x; a < maxY && b < maxX; a++, b++) {
//            if (valid(squares, b, a, userOwnership))
//                valid_moves.add(squares[b][a]);
//            else break;
//        }

        return valid_moves;
    }

}
