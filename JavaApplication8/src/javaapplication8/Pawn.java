package javaapplication8;

import java.util.ArrayList;

import java.util.ArrayList;

public class Pawn extends Piece {

     public Pawn()
    {
        this.name="Pawn";
    }
   
    public void setBlack() {
        this.image = "..\\pictures\\black\\pawn.png";
    }

    public void setWhite() {
        this.image = "..\\pictures\\white\\pawn.png";
    }
    
//    public int setNum(String num){
//        String temp = num.concat("6");
//        int hh = Integer.parseInt(temp);
//        return hh;
//    }
    
    

    @Override
    public ArrayList<Square> getValidMoves(Square[][] squares, Location current_location) {
        int x = current_location.getI();
        int y = current_location.getJ();

        int maxX = squares[0].length;
        int maxY = squares.length;
        ArrayList<Square> valid_moves = new ArrayList<>();
        if (userOwnership == false) {
            int up = x + 1;
            int diaglefX = x + 1;
            int diaglefY = y - 1;
            int diagRightX = x + 1;
            int diagRightY = y + 1;
            if (x == 1) {
                for (int a = x+1; a < 4; a++) {
                    if (valid(squares, a, y, userOwnership)){
                        if (validOnOpponent(squares, a, y, userOwnership))
                            break;
                        else
                            valid_moves.add(squares[a][y]);
                    }else break;
                    
                }
                
                 if (diaglefX<8 && diaglefY>=0 && valid(squares, diaglefX, diaglefY, userOwnership) && validOnOpponent(squares, diaglefX, diaglefY, userOwnership))
                    valid_moves.add(squares[diaglefX][diaglefY]);
                if (diagRightX<8 && diagRightY<8 && valid(squares, diagRightX, diagRightY, userOwnership) && validOnOpponent(squares, diagRightX, diagRightY, userOwnership))
                    valid_moves.add(squares[diagRightX][diagRightY]);
                
            } else {
                if (up<8 && valid(squares, up, y, userOwnership) && !validOnOpponent(squares, up, y, userOwnership))
                    valid_moves.add(squares[up][y]);
                if (diaglefX<8 && diaglefY>=0 && valid(squares, diaglefX, diaglefY, userOwnership) && validOnOpponent(squares, diaglefX, diaglefY, userOwnership))
                    valid_moves.add(squares[diaglefX][diaglefY]);
                if (diagRightX<8 && diagRightY<8 && valid(squares, diagRightX, diagRightY, userOwnership) && validOnOpponent(squares, diagRightX, diagRightY, userOwnership))
                    valid_moves.add(squares[diagRightX][diagRightY]);
                
                
                
            }

        } else {
            valid_moves.add(squares[x][y]);
            int upme = x - 1;
            int diaglefXme = x - 1;
            int diaglefYme = y - 1;
            int diagRightXme = x - 1;
            int diagRightYme = y + 1;
            if (x == 6) {
                for (int a = x-1; a > 3; a--) {
                    if ( valid(squares, a, y, userOwnership)){
                        if (validOnOpponent(squares, a, y, userOwnership)){
                            break;
                        }else{
                            valid_moves.add(squares[a][y]);
                        }
                    }
                    else
                        break;
                }
                if (diaglefXme>=0 && diaglefYme>=0 &&  valid(squares, diaglefXme, diaglefYme, userOwnership) && validOnOpponent(squares, diaglefXme, diaglefYme, userOwnership))
                    valid_moves.add(squares[diaglefXme][diaglefYme]);
                
                if (diagRightXme>=0 && diagRightYme<8 &&  valid(squares, diagRightXme, diagRightYme, userOwnership) && validOnOpponent(squares, diagRightXme, diagRightYme, userOwnership))
                    valid_moves.add(squares[diagRightXme][diagRightYme]);
                
                
            } else {
                if (upme>=0 &&  valid(squares, upme, y, userOwnership) && !validOnOpponent(squares, upme, y, userOwnership))
                    valid_moves.add(squares[upme][y]);
                
                if (diaglefXme>=0 && diaglefYme>=0 &&  valid(squares, diaglefXme, diaglefYme, userOwnership) && validOnOpponent(squares, diaglefXme, diaglefYme, userOwnership))
                    valid_moves.add(squares[diaglefXme][diaglefYme]);
                
                if (diagRightXme>=0 && diagRightYme<8 &&  valid(squares, diagRightXme, diagRightYme, userOwnership) && validOnOpponent(squares, diagRightXme, diagRightYme, userOwnership))
                    valid_moves.add(squares[diagRightXme][diagRightYme]);
            }
        }
        return valid_moves;
    }

}
