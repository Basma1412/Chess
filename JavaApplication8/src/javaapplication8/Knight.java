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
public class Knight extends Piece {
 
    public Knight()
    {
        this.name="Knight";
    }
    public void setBlack() {
        this.image = "..\\pictures\\black\\knight.png";
    }

//    public int setNum(String num){
//        String temp = num.concat("2");
//        int hh = Integer.parseInt(temp);
//        return hh;
//    }
    public void setWhite() {
        this.image = "..\\pictures\\white\\knight.png";
    }
    
    @Override
    public ArrayList<Square> getValidMoves(Square[][] squares, Location current_location) {
        int x = current_location.getI();
        int y = current_location.getJ();

        int maxX = squares[0].length;
        int maxY = squares.length;

        ArrayList<Square> valid_moves = new ArrayList<>();

        int Xrightup = x - 1;
        int Yrightup = y + 2;

        int Xleftup = x - 1;
        int Yleftup = y - 2;

        int Xupright = x - 2;
        int Yupright = y + 1;

        int Xupleft = x - 2;
        int Yupleft = y - 1;

        int Xrightdown = x + 1;
        int Yrightdown = y + 2;

        int Xleftdown = x + 1;
        int Yleftdown = y - 2;

        int Xdownright = x + 2;
        int Ydownright = y + 1;

        int Xdownleft = x + 2;
        int Ydownleft = y - 1;
        
        if (userOwnership){
            valid_moves.add(squares[x][y]);
        }

        if (Xrightup >= 0 && Xrightup < maxX && Yrightup >= 0 && Yrightup < maxY) {
            Square tempSquare = squares[Xrightup][Yrightup];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xleftup >= 0 && Xleftup < maxX && Yleftup >= 0 && Yleftup < maxY) {
            Square tempSquare = squares[Xleftup][Yleftup];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xupright >= 0 && Xupright < maxX && Yupright >= 0 && Yupright < maxY) {
            Square tempSquare = squares[Xupright][Yupright];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xupleft >= 0 && Xupleft < maxX && Yupleft >= 0 && Yupleft < maxY) {
            Square tempSquare = squares[Xupleft][Yupleft];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xrightdown >= 0 && Xrightdown < maxX && Yrightdown >= 0 && Yrightdown < maxY) {
            Square tempSquare = squares[Xrightdown][Yrightdown];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xleftdown >= 0 && Xleftdown < maxX && Yleftdown >= 0 && Yleftdown < maxY) {
            Square tempSquare = squares[Xleftdown][Yleftdown];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xdownright >= 0 && Xdownright < maxX && Ydownright >= 0 && Ydownright < maxY) {
            Square tempSquare = squares[Xdownright][Ydownright];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }
        if (Xdownleft >= 0 && Xdownleft < maxX && Ydownleft >= 0 && Ydownleft < maxY) {
            Square tempSquare = squares[Xdownleft][Ydownleft];
            Piece temp = tempSquare.getPiece();
            if ((temp==null)||temp.userOwnership != userOwnership) {
                valid_moves.add(tempSquare);
            }
        }

        return valid_moves;
    }

}
