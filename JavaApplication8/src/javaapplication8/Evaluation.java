package javaapplication8;

import java.util.ArrayList;

public class Evaluation {
   private final int pawnBoardUser[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 50, 50, 50, 50, 50, 50, 50, 50},
        { 10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
   
    private final int pawnBoardMachine[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 30, 30, 10,-20,-20, 10, 30, 30},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 10, 10, 20, 30, 30, 20, 10, 10},
        { 50, 50, 50, 50, 50, 50, 50, 50},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
    
    private final int rookBoardUser[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
        
    private final int rookBoardMachine[][]={
        { 0,  0,  0,  5,  5,  0,  0,  0},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
       
    private final int knightBoardUser[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    
    private final int knightBoardMachine[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    
    private final int bishopBoardUser[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {10,  0,  5, 10, 10,  5,  0,10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    
       private final int bishopBoardMachine[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
       
    private final int queenBoardUser[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    
    private final int queenBoardMachine[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    
        private final int kingMidBoardUser[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
        
        private final int kingMidBoardMachine[][]={
        { 20, 30, 10,  0,  0, 10, 30, 20},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30}};
        
    private final int kingEndBoardUser[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
    
    private final int kingEndBoardMachine[][]={
    
        {-50,-30,-30,-30,-30,-30,-30,-50},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-50,-40,-30,-20,-20,-30,-40,-50}};

    

    
    public double evaluate(Square [][] squares, int depth, boolean machineTurn, boolean stalemate){
       // System.out.println("Here it is Evaluation Class ");
       return this.evalMaterial(squares, machineTurn)+ this.evalMobility(squares, depth, machineTurn, stalemate)+ this.evalKingTropism(squares, machineTurn)+ this.evalPosition(squares,machineTurn);
    
    }
    public int evalMaterial(Square[][] squares, Boolean machineTurn){
        int result=0;
        Piece piece;
        if (machineTurn==true)
        {
            
         for (int i=0;i<8;i++)
         {  
          for (int j=0;j<8;j++)
            
           {
               
           //     System.out.println("Square "+"position "+squares[i][j].a+" "+squares[i][j].b);
              // System.out.println("Name of piece is "+squares[i][j].getPiece().getName());
            if(squares[i][j].getPiece()!=null)
            {
                piece=squares[i][j].getPiece();
                if(piece.getName()=="King")
                 {
                    if(!piece.userOwnership)
                        result+=20000;
                    else
                        result-=20000;
                 }
                else if (piece.getName()=="Queen")
                {
                    if(!piece.userOwnership)
                        result+=900;
                    else
                        result-=900;
                }
                else if (piece.getName()=="Rook")
                {
                    if(!piece.userOwnership)
                        result+=500;
                    else
                        result-=500;
                }
                else if (piece.getName()=="Bishop")
                {
                    if(!piece.userOwnership)
                        result+=330;
                    else
                        result-=330;
                }
                else if (piece.getName()=="Knight")
                     {
                    if(!piece.userOwnership)
                        result+=320;
                    else
                        result-=320;
                }
                    
                else if (piece.getName()=="Pawn")
                {
                    if(!piece.userOwnership)
                        result+=100;
                    else
                        result-=100;
                }
            }
          }
         }
        }
        else //user's turn
        {
        
         for (int i=0;i<8;i++)
         {  
          for (int j=0;j<8;j++)
            
           {   
            if (squares[i][j].getPiece()!=null)
           {
             piece=squares[i][j].getPiece();
             if(piece.getName()=="King")
                 {
                    if(piece.userOwnership)
                        result+=20000;
                    else
                        result-=20000;
                 }
                else if (piece.getName()=="Queen")
                {
                    if(piece.userOwnership)
                        result+=900;
                    else
                        result-=900;
                }
                else if (piece.getName()=="Rook")
                {
                    if(piece.userOwnership)
                        result+=500;
                    else
                        result-=500;
                }
                else if (piece.getName()=="Bishop")
                {
                    if(piece.userOwnership)
                        result+=330;
                    else
                        result-=330;
                }
                else if (piece.getName()=="Knight")
                     {
                    if(piece.userOwnership)
                        result+=320;
                    else
                        result-=320;
                }
                    
                else if (piece.getName()=="Pawn")
                {
                    if(piece.userOwnership)
                        result+=100;
                    else
                        result-=100;
                }
            }
          }
         }
        }
        return result;
    
    }
    
      public int evalPosition(Square [][] squares,Boolean machineTurn)
      {
        int result=0;
        Boolean end=isEnd(squares);
        Piece piece;
        if (machineTurn==true)
        {
          for (int i=0;i<8;i++) 
          {
           for (int j=0;j<8;j++)
           {
            if (squares[i][j].getPiece()!=null)
            {
                piece=squares[i][j].getPiece();
               
                if(piece.getName()=="Knight") 
                {
                    if (!piece.userOwnership)
                    {
                        result+=knightBoardMachine[i][j];
                    }
                    else
                    {
                        result-=knightBoardUser[i][j];
                    }
                }
                else if(piece.getName()=="Bishop")
                {
                    if (!piece.userOwnership)
                    { 
                        result+=bishopBoardMachine[i][j];
                    }
                    else
                    { 
                        result-=bishopBoardUser[i][j];
                    }
                }
                else if(piece.getName()=="Rook")
                {
                    if (!piece.userOwnership)
                    {
                        result+=rookBoardMachine[i][j];
                    }
                    else
                    {
                        result+=rookBoardUser[i][j];
                    }
                    
                }
                else if(piece.getName()=="Queen")
                {
                    if (!piece.userOwnership)
                    { 
                        result+=queenBoardMachine[i][j];
                    }
                    else
                    { 
                        result-=queenBoardUser[i][j];
                    }
                }
                else if(piece.getName()=="Pawn")
                {
                    if (!piece.userOwnership)
                    {
                        result+=pawnBoardMachine[i][j];
                    }
                    else
                    {
                        result-=pawnBoardUser[i][j];
                    }
                    
                }
                else if(piece.getName()=="King")
                {
                    if (!piece.userOwnership)
                    {
                        if(end)
                        {
                            result+=kingEndBoardMachine[i][j];
                        }
                        else
                        {
                            result+=kingMidBoardMachine[i][j];
                        }
                           
                    }
                    else 
                        {
                        if(end)
                        {
                            result-=kingEndBoardUser[i][j];
                        }
                        else
                        {
                            result-=kingMidBoardUser[i][j];
                        }
                           
                    }
                    
                }
            }
        }
        }
        }
        else //if user's turn
        {
        for (int i=0;i<8;i++)
        {
          for (int j=0;j<8;j++)
            {   
             if (squares[i][j].getPiece()!=null)
             {
               piece=squares[i][j].getPiece();
               if(piece.getName()=="Knight") 
                {
                    if (piece.userOwnership)
                    {
                        result+=knightBoardUser[i][j];
                    }
                    else
                    {
                        result-=knightBoardMachine[i][j];
                    }
                        
                }
                else if(piece.getName()=="Bishop")
                {
                    if (piece.userOwnership)
                    { 
                        result+=bishopBoardUser[i][j];
                    }
                    else
                    {
                        result-=bishopBoardMachine[i][j];
                    }
                }
                else if(piece.getName()=="Rook")
                {
                    if (piece.userOwnership)
                    {
                        result+=rookBoardUser[i][j];
                    }
                    else 
                    {
                        result-=rookBoardMachine[i][j];
                    }
                    
                }
                else if(piece.getName()=="Queen")
                {
                    if (piece.userOwnership)
                    {
                        result+=queenBoardUser[i][j];
                    }
                    else
                    {
                        result-=queenBoardMachine[i][j];
                    }
                  
                }
                else if(piece.getName()=="Pawn")
                {
                    if (piece.userOwnership)
                    { 
                        result+=pawnBoardUser[i][j];
                    }
                    else 
                    { 
                        result-=pawnBoardMachine[i][j];
                    }
                }
                 else if(piece.getName()=="King")
                {
                    if (piece.userOwnership)
                    {
                        if(end)
                        {
                            result+=kingEndBoardUser[i][j];
                        }
                        else
                        {
                            result+=kingMidBoardUser[i][j];
                        }
                           
                    }
                    else
                    {
                        if(end)
                        {
                            result-=kingEndBoardMachine[i][j];
                        }
                        else
                        {
                            result-=kingMidBoardMachine[i][j];
                        }
                           
                    }
                    
                }
                    
            }
        }
        }
        }
        return result;
    }
      public double evalKingTropism(Square [][] squares, Boolean machineTurn){
          double result=0;
          int kX=0,kY=0;
          Piece piece;
          if (machineTurn)
          {
           for (int i=0;i<8;i++) 
           {
            for (int j=0;j<8;j++)
            {
             if (squares[i][j].getPiece()!=null)
             {
               piece=squares[i][j].getPiece();
               if (piece.getName()=="King")
                {  if (piece.userOwnership)
                    {
                    kX=i;
                    kY=j;
                    }
                }
            }    
          }
          }
          for (int i=0;i<8;i++) 
          {
            for (int j=0;j<8;j++)
            {
              if (squares[i][j].getPiece()!=null)
              {
                piece=squares[i][j].getPiece();
                  
                if (piece.getName()=="Queen" && !(piece.userOwnership))
                {
                    result=-0.8*(Math.min( Math.abs( kX - i ),Math.abs( kY - j)));

                }
                else  if (piece.getName()=="Knight" &&!(piece.userOwnership))
                {
                    result+= 1.2*(5-( Math.abs( kX - i )+Math.abs( kY - j)));

                }
                else  if (piece.getName()=="Rook" &&!(piece.userOwnership))
                {
                    result+= -1.6*(Math.min( Math.abs( kX - i ), Math.abs( kY - j)));

                }
            }
          }
         }
        }
        else   //user's turn
         {
           for (int i=0;i<8;i++) 
           {
            for (int j=0;j<8;j++)
            {
             if (squares[i][j].getPiece()!=null)
             {
                piece=squares[i][j].getPiece();
               
                if (piece.getName()=="King")
                {  if (!piece.userOwnership)
                    {
                    kX=i;
                    kY=j;
                    }
                }
            }    
          }
          }
          for (int i=0;i<8;i++) 
          {
            for (int j=0;j<8;j++)
            {
             if (squares[i][j].getPiece()!=null)
             {       
              piece=squares[i][j].getPiece();
                 
                if (piece.getName()=="Queen" && piece.userOwnership)
                {
                    result=-0.8*(Math.min( Math.abs( kX - i ),Math.abs( kY - j)));

                }
                else  if (piece.getName()=="Knight" &&piece.userOwnership)
                {
                    result+= 1.2*(5-( Math.abs( kX - i )+Math.abs( kY - j)));

                }
                else  if (piece.getName()=="Rook" &&piece.userOwnership)
                {
                    result+= -1.6*(Math.min( Math.abs( kX - i ), Math.abs( kY - j)));

                }
            }
          }
         }
        }
      return result;
      }
    
    public int evalMobility( Square [][] squares, int depth, Boolean machineTurn, boolean stalemate)
 {
     
      
     int result=0;
     int validMovesMachine=0;
     int validMovesUser=0;
    
     Piece piece;
     ArrayList<Square> valid;
    
     Boolean isCheckmate=checkmate(squares,machineTurn);
     
     if(machineTurn==true) 
     {
        for (int i=0; i<8;i++)
        {
         for (int j=0;j<8;j++)
         {
           if (squares[i][j].getPiece()!=null)
           {  
               piece=squares[i][j].getPiece();
               valid=piece.getValidMoves(squares, new Location(i,j));
                  if(!piece.userOwnership)
                    {
                            validMovesMachine+= valid.size();
                    }
                  else 
                  {
                     validMovesUser+=valid.size();
                  }
              }
         }
        }
   
   if(validMovesMachine==0|| isCheckmate) //if checkmate or stalemate
        {
           if(!stalemate)
           { 
               result-=150000*depth;
           }
           else
           {
               result-=200000*depth;
           }
        }
        else
        {   
            if (validMovesUser!=0)
            {
              result = validMovesMachine/validMovesUser;
            }
        }
       
        
    }
     else{ //if user's turn
         for (int i=0; i<8;i++)
        {
         for (int j=0;j<8;j++)
         {
              piece=squares[i][j].getPiece();
             
              if(piece!=null)
              {    
                  valid=piece.getValidMoves(squares, new Location(i,j));
                  
                  if(piece.userOwnership)
                    {
                        validMovesUser+= valid.size();
                    }
                  else 
                  {
                        validMovesMachine+=valid.size();
                  }
              }
         }
        }

     if(validMovesUser==0 || isCheckmate) //if checkmate or stalemate
        {
             if(!stalemate)
           { 
               result-=150000*depth;
           }
           else
           {
               result-=200000*depth;
           }
        }
        else
        {   if (validMovesMachine!=0)
           {
            result= validMovesUser/validMovesMachine;
           }
        }
    }

    return result;
    }



public Boolean isEnd(Square [][] squares) // marking the game as ending when both queens are dead
{
    Boolean end=true;
    Piece piece;
    for (int i=0;i<8;i++)
    {
        for (int j=0;j<8;j++)
        {    
            if (squares[i][j].getPiece()!=null)
            {
                piece=squares[i][j].getPiece();
                if (piece.getName()=="Queen")
                {
                  end=false;
                  return end;
                }
            }
        }
    }
    return end;
}
public boolean checkmate(Square[][] squares, boolean machineturn){
        boolean checkm = false;
        if(!machineturn){   //user playing , checks first if user's king is captured the previous turn --> computer wins
   kingU:  for(int i=0;i<8;i++)
   {
             for(int j=0;j<8;j++)
             {
               if (squares[i][j].getPiece()!=null)
               {
                   
                   if(squares[i][j].getPiece().getName() == "King" && squares[i][j].getPiece().userOwnership == true )
                   {
                       checkm = false;
                       break kingU;
                   }
                   else
                   checkm = true;   
         }   }
       }
      }
       if(machineturn){   //computer playing , checks first if computer's king benn captured in previous turn --> user wins
  kingC:  for(int i=0;i<8;i++){
             for(int j=0;j<8;j++){
                  if (squares[i][j].getPiece()!=null)
                  {  
                      if(squares[i][j].getPiece().getName()== "King" && squares[i][j].getPiece().userOwnership == false )
                      {
                          checkm = false;
                          break kingC;
                      }
                      else 
                      checkm = true;
                  }
         }   
       }
       
       
       } 
    
      return checkm;
    }

}




















