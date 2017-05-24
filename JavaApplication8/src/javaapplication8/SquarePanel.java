package javaapplication8;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Move {

    Square square;
    ArrayList<Square> validMoves;

    public Move(Square square, ArrayList<Square> validMoves) {
        this.square = square;
        this.validMoves = validMoves;
    }

    public void setValidMoves(ArrayList<Square> s) {
        validMoves = s;
    }

    public void setSquare(Square s) {
        square = s;
    }

    public ArrayList<Square> getValidMoves() {
        return this.validMoves;
    }

    public Square setSquare() {
        return this.square;
    }
}

class Location {

    private int i;
    private int j;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Location(int a, int b) {
        i = a;
        j = b;
    }
}

class Square extends JButton {

    int a;
    int b;
    Piece piece;

    public Square(int a, int b, Piece piece) {
        this.a = a;
        this.b = b;
        this.piece = piece;
    }

    public Square(int a, int b) {
        this.a = a;
        this.b = b;
        this.piece = null;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }
}

final class SquarePanel extends JPanel {

    public Square[][] squares = new Square[8][8];
    String Col;
    String difficulty;
    static boolean old = false;
    static Piece nIcon;
    static Piece temp;
    Square originSquare;
    static boolean machineTurn = false;
    ArrayList<Square> userValidMoves;
    ArrayList<Square> computerValidMoves;
    boolean valid = false;
    public int[][] SquresWithNumbers = new int[8][8];
    static boolean active = true;

    Square randomSquare = null;
    private Square dest;
    private Square src;
    private String color = "";
    private ArrayList<Move> getComputerchildrenmoves;
    private ArrayList<Move> getUserchildrenmoves;
    private Evaluation eval;
    private double score;
    private double v;
    ArrayList<Move> getPossibleMoves;
    Destination destinationMove;
    int globalDepth;
    int leafNodes;
    int cutoffs;

    /*
    
     •	The maximum depth.
     •	The number of leaf nodes evaluated and their utility values
     •	The number of cutoffs that happened and the level.

    
     */
    public static void setActive(boolean active) {
        SquarePanel.active = active;
    }

    public void setWhitePieces(Piece[] whitePieces) {
        whitePieces[0] = new Rook();
        whitePieces[1] = new Knight();
        whitePieces[2] = new Bishop();
        whitePieces[4] = new King();
        whitePieces[3] = new Queen();
        whitePieces[5] = new Bishop();
        whitePieces[6] = new Knight();
        whitePieces[7] = new Rook();

        for (int i = 8; i < 16; i++) {

            whitePieces[i] = new Pawn();
        }

        for (int i = 0; i < 16; i++) {
            whitePieces[i].setWhite();
        }

    }

    public void setBlackPieces(Piece[] blackPieces) {
        blackPieces[0] = new Rook();
        blackPieces[1] = new Knight();
        blackPieces[2] = new Bishop();
        blackPieces[4] = new King();
        blackPieces[3] = new Queen();
        blackPieces[5] = new Bishop();
        blackPieces[6] = new Knight();
        blackPieces[7] = new Rook();

        for (int i = 8; i < 16; i++) {

            blackPieces[i] = new Pawn();
        }

        for (int i = 0; i < 16; i++) {
            blackPieces[i].setBlack();
        }

    }

    public void getSquare(Location oldLocation, Location newLocation) {

        int iold = oldLocation.getI();
        int jold = oldLocation.getJ();
        int inew = newLocation.getI();
        int jnew = newLocation.getJ();
        Icon old = squares[iold][jold].getIcon();
        resetSquare(oldLocation);
        squares[inew][jnew].setIcon(old);

    }

    public void setPieceOnSquare(Square square, Piece piece) {
        square.setPiece(piece);
        if (piece != null) {
            square.setIcon(new ImageIcon(piece.getImage()));
        } else {
            square.setIcon(null);
        }
    }

    public void resetBoard(String Col) {
        this.Col = Col;
        int cnt = 0;
        this.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                SquresWithNumbers[i][j] = 0;
            }
        }
        boolean white = true;
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                cnt++;
                if (cnt == 9) {
                    white = !white;
                    cnt = 1;
                }
                squares[i][j] = new Square(i, j);
                if (white == true) {
                    squares[i][j].setBackground(Color.WHITE);
                    this.add(squares[i][j]);
                    white = !white;

                } else {
                    squares[i][j].setBackground(Color.DARK_GRAY);
                    this.add(squares[i][j]);
                    white = !white;
                }

            }
        }

        Piece[] whitePieces = new Piece[16];
        Piece[] blackPieces = new Piece[16];

        setWhitePieces(whitePieces);
        setBlackPieces(blackPieces);

        int counter1 = 0;
        int counter2 = 0;
        if (Col.equalsIgnoreCase("White")) {

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 8; j++) {
                    setPieceOnSquare(squares[i][j], blackPieces[counter1++]);

                }
            }
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 8; j++) {
                    setPieceOnSquare(squares[1][j], blackPieces[counter1++]);

                }
            }

            for (int i = 7; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    whitePieces[counter2].setPieceToUser();
                    setPieceOnSquare(squares[i][j], whitePieces[counter2++]);

                }
            }

            for (int i = 6; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    whitePieces[counter2].setPieceToUser();
                    setPieceOnSquare(squares[i][j], whitePieces[counter2++]);

                }
            }

        } else {

            counter1 = 0;
            counter2 = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 8; j++) {
                    setPieceOnSquare(squares[i][j], whitePieces[counter2++]);
                }
            }
            for (int i = 1; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    setPieceOnSquare(squares[i][j], whitePieces[counter2++]);

                }
            }

            for (int i = 7; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    blackPieces[counter1].setPieceToUser();
                    setPieceOnSquare(squares[i][j], blackPieces[counter1++]);

                }
            }

            for (int i = 6; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    blackPieces[counter1].setPieceToUser();
                    setPieceOnSquare(squares[i][j], blackPieces[counter1++]);

                }
            }

        }

    }

    public void resetSquare(Location oldLocation) {
        int iold = oldLocation.getI();
        int jold = oldLocation.getJ();

        squares[iold][jold].setIcon(null);
    }

    public SquarePanel(String Col, String difficulty) {

        this.difficulty = difficulty;
        resetBoard(Col);
        playGame();

    }

    public void youWin() {
       // System.out.println("Finished 2 ");
        Container contain;
        JPanel reChange, reChange2;
        JButton reChangeButton;
        contain = this;
        contain.removeAll();
        contain.setLayout(null);

        JLabel image = new JLabel(new ImageIcon("..\\pictures\\win.jpg"));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        image.setSize(dim.width / 2, dim.width / 2);

        contain.add(image);
        validate();
        repaint();
        setVisible(true);

        finished = true;
    }

    public void youLose() {
      //  System.out.println("Finished 2 ");
        Container contain;
        JPanel reChange, reChange2;
        JButton reChangeButton;
        contain = this;
        contain.removeAll();
        contain.setLayout(null);

        JLabel image = new JLabel(new ImageIcon("..\\pictures\\lose.png"));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        image.setSize(dim.width / 2, dim.width / 2);

        contain.add(image);
        validate();
        repaint();
        setVisible(true);

        finished = true;
    }

    boolean finished = false;

    public void playComputer() {
        machineTurn = true;
        ChessEngine();       
        Destination fDest = destinationMove;
        Piece destinationPiece = fDest.Destination.getPiece();
        Route route = new Route(fDest.source, fDest.Destination);
if (fDest.Destination == null) {
            System.out.println("Cann't find any legal move");
        }else{
        if (destinationPiece != null && ("King".equals(destinationPiece.name))) {
            updateState(squares, route);
            if (checkmate(squares, machineTurn));
            {
                youLose();
            }
        } else {

            updateState(squares, route);
            setActive(true);

        }}

    }

    public Square[][] updateState(Square[][] stateSquares, Route route) {
        Square source = route.Source;
        Square destination = route.Destination;
        int a = source.a;
        int b = source.b;
        Square stateSource = stateSquares[a][b];

        int aa = destination.a;
        int bb = destination.b;
        Square stateDestination = stateSquares[aa][bb];

        Piece destinationPiece = stateSource.getPiece();
        setStatePieceOnSquare(stateSquares, stateSource, null);
        setStatePieceOnSquare(stateSquares, stateDestination, destinationPiece);

        return stateSquares;
    }

    public void setStatePieceOnSquare(Square[][] squares, Square square, Piece piece) {
        int a = square.a;
        int b = square.b;
        Square stateSquare = squares[a][b];
        stateSquare.setPiece(piece);
        if (piece != null) {
            square.setIcon(new ImageIcon(piece.getImage()));
        } else {
            square.setIcon(null);
        }
    }

    public Square[][] updateState(Square[][] stateSquares, Square source, Square destination) {
        int a = source.a;
        int b = source.b;
        Square stateSource = stateSquares[a][b];

        int aa = destination.a;
        int bb = destination.b;
        Square stateDestination = stateSquares[aa][bb];

        Piece destinationPiece = stateSource.getPiece();
        setStatePieceOnSquare(stateSquares, stateSource, null);
        setStatePieceOnSquare(stateSquares, stateDestination, destinationPiece);

        return stateSquares;
    }

    public ArrayList<Square> getValidSquares() {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = squares[i][j].getPiece();
                if (!(temp == null) && !temp.userOwnership) {
                    computerSquares.add(squares[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {
//            int randomIndex = ThreadLocalRandom.current().nextInt(0, length);
//            Square destination = computerSquares.get(randomIndex);

            return computerSquares;
        }
    }

    public ArrayList<Square> getValidStateSquares(Square[][] sqrs) {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = sqrs[i][j].getPiece();
                if (!(temp == null) && !temp.userOwnership) {
                    computerSquares.add(sqrs[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {
//            int randomIndex = ThreadLocalRandom.current().nextInt(0, length);
//            Square destination = computerSquares.get(randomIndex);

            return computerSquares;
        }
    }

    public ArrayList<Move> getAllComputerValidStateMoves(Square[][] sqrs) {
        ArrayList<Square> validSquares = getValidStateSquares(sqrs);
        if (validSquares == null) {
            return new ArrayList<>();
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(sqrs, new Location(squareA, squareB));
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public ArrayList<Move> getAllPossibleMoves(Square[][] ss) {
        ArrayList<Square> validSquares = getValidStateSquares(ss);
        ArrayList<Square> validSquaresUsers = getUserValidStateSquares(ss);

        validSquares.addAll(validSquaresUsers);
        if (validSquares == null) {
            return null;
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(ss, new Location(squareA, squareB));
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public ArrayList<Move> getAllComputerValidMoves() {
        ArrayList<Square> validSquares = getValidSquares();
        if (validSquares == null) {
            return null;
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(squares, new Location(squareA, squareB));
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public ArrayList<Square> getUserValidSquares() {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = squares[i][j].getPiece();
                if (!(temp == null) && temp.userOwnership) {
                    computerSquares.add(squares[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {

            return computerSquares;
        }
    }

    public ArrayList<Move> getAllUserValidMoves() {
        ArrayList<Square> validSquares = getUserValidSquares();
        if (validSquares == null) {
            return null;
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(squares, new Location(squareA, squareB));
            if (pieceMoves.contains(validSquare)) {
                pieceMoves.remove(validSquare);
            }
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public ArrayList<Square> getUserValidStateSquares(Square[][] sqrs) {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = sqrs[i][j].getPiece();
                if (!(temp == null) && temp.userOwnership) {
                    computerSquares.add(sqrs[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {

            return computerSquares;
        }
    }

    public ArrayList<Move> getAllUserValidStateMoves(Square[][] sqrs) {
        ArrayList<Square> validSquares = getUserValidStateSquares(sqrs);
        if (validSquares == null) {
            return new ArrayList<>();
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(sqrs, new Location(squareA, squareB));
            if (pieceMoves.contains(validSquare)) {
                pieceMoves.remove(validSquare);
            }
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public ArrayList<Square> getStateValidSquares(Square[][] stateSquares) {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = stateSquares[i][j].getPiece();
                if (!(temp == null) && !temp.userOwnership) {
                    computerSquares.add(stateSquares[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {
            return computerSquares;
        }
    }

    public ArrayList<Move> getStateValidMoves(Square[][] stateSquares) {
        ArrayList<Square> validSquares = getStateValidSquares(stateSquares);
        if (validSquares == null) {
            return null;
        }
        ArrayList<Move> getMoves = new ArrayList<>();
        for (Square validSquare : validSquares) {

            int squareA = validSquare.a;
            int squareB = validSquare.b;
            Piece chosenPiece = validSquare.piece;
            ArrayList<Square> pieceMoves = chosenPiece.getValidMoves(stateSquares, new Location(squareA, squareB));
            if (pieceMoves.isEmpty()) {
                continue;
            }
            Move move = new Move(validSquare, pieceMoves);
            getMoves.add(move);

        }
        return getMoves;
    }

    public Square getMove() {

        ArrayList<Move> test = getAllComputerValidMoves();

        boolean finished = false;
        while (true) {
            ArrayList<Square> temp = getComputerValidMoves();
            if (temp == null) {

                return null;
            } else {
                if (!temp.isEmpty()) {
                    computerValidMoves = temp;
                    break;
                }
            }
        }

        int length = computerValidMoves.size();
        Random ran = new Random();
        int randomNum = ran.nextInt(length);
        Square destination = computerValidMoves.get(randomNum);
        return destination;

    }

    public ArrayList<Square> getComputerValidMoves() {
        ArrayList<Square> getMoves;
        randomSquare = chooseSquare();
        if (randomSquare == null) {
            return null;
        } else {
            int squareA = randomSquare.a;
            int squareB = randomSquare.b;
            Piece randomPiece = randomSquare.piece;
            getMoves = randomPiece.getValidMoves(squares, new Location(squareA, squareB));

            return getMoves;
        }
    }

    public Square chooseSquare() {

        ArrayList<Square> computerSquares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece temp = squares[i][j].getPiece();
                if (!(temp == null) && !temp.userOwnership) {
                    computerSquares.add(squares[i][j]);
                }
            }
        }

        int length = computerSquares.size();

        if (length == 0) {
            System.out.println("Finished");
            return null;
        } else {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, length);
            Square destination = computerSquares.get(randomIndex);

            return destination;
        }
    }

    public void playUser() {
        machineTurn = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int a = i;
                final int b = j;
                valid = false;
                squares[a][b].addActionListener(new PieceActionListener(a, b));
            }
        }

    }

    public void playGame() {

        if (!machineTurn) {
            playUser();
            playComputer();
        } 
        else {
            playComputer();
            playUser();
            playComputer();
        }
    }

    public void callGame() {

        playGame();
    }

    private class PieceActionListener implements ActionListener {

        private int a;
        private int b;

        public PieceActionListener(int a, int b) {
            this.a = a;
            this.b = b;
        }

        protected void doPerformAction(ActionEvent e) {
            if (old == false) {
                boolean userPiece = false;
                Piece pieceonSquare = squares[a][b].getPiece();
                if (pieceonSquare != null) {
                    userPiece = pieceonSquare.userOwnership;
                }

                if (userPiece) {
                    temp = squares[a][b].getPiece();
                    nIcon = temp;
                    originSquare = squares[a][b];
                    setPieceOnSquare(squares[a][b], null);
                    old = true;
                    userValidMoves = nIcon.getValidMoves(squares, new Location(a, b));
                }
            } else {
                boolean userPiece2 = false;
                Piece piece2onSquare = squares[a][b].getPiece();
                if (piece2onSquare != null) {
                    userPiece2 = piece2onSquare.userOwnership;
                }
                if (!userPiece2) {
                    valid = false;
                    for (Square validMove : userValidMoves) {
                        if (squares[a][b].equals(validMove)) {
                            if (!(squares[a][b].equals(originSquare))) {
                                if ((squares[a][b].getPiece() != null) && ("King".equals(squares[a][b].getPiece().name))) {
                                    setPieceOnSquare(squares[a][b], null);
                                    setPieceOnSquare(squares[a][b], nIcon);
                                    if (checkmate(squares, machineTurn)) {
                                        youWin();
                                    }

                                } else {
                                    setPieceOnSquare(squares[a][b], null);
                                    setPieceOnSquare(squares[a][b], nIcon);
                                    valid = true;

                                    setActive(false);
                                    playComputer();
                                }
                            }
//                            break;
                        }

                    }

                    if (!valid) {
                        setPieceOnSquare(originSquare, nIcon);
                    }

                } else {

                    setPieceOnSquare(originSquare, nIcon);

                }

                old = false;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (active) {
                doPerformAction(e);
            }

        }

    }

    public void ChessEngine() {
        int depth = 0;
        color = Col;
        eval = new Evaluation();
        if (difficulty.equalsIgnoreCase("Hard")) {
            depth = 3;
            globalDepth = 3;
        } else if (difficulty.equalsIgnoreCase("Easy")) {
            depth = 2;
            globalDepth = 2;
        }
        Square[][] ss = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ss[i][j] = new Square(squares[i][j].a, squares[i][j].b);
                Piece squarePiece = squares[i][j].getPiece();
                ss[i][j].setPiece(squarePiece);
            }
        }

        Destination alpha = new Destination(null, null, Integer.MIN_VALUE);
        Destination beta = new Destination(null, null, Integer.MAX_VALUE);

        leafNodes = 0;
        System.out.println("Maximum Depth " + globalDepth);

        destinationMove = alphaBeta(ss, depth, alpha, beta, !machineTurn);

    }

    double max(double val, double score) {
        if (val > score) {
            return val;
        } else {
            return score;
        }

    }

    double min(double val, double score) {
        if (val < score) {
            return val;
        } else {
            return score;
        }

    }

    public ArrayList<Route> getAllComputerRoutes(Square[][] stateBoard) {
        getComputerchildrenmoves = getAllComputerValidStateMoves(stateBoard);

        ArrayList<Route> computerRoutes = new ArrayList<>();

        for (Move move : getComputerchildrenmoves) {
            for (Square dst : move.validMoves) {
                Route r = new Route(move.square, dst);
                computerRoutes.add(r);
            }
        }
        return computerRoutes;

    }

    public ArrayList<Route> getAllUserRoutes(Square[][] stateBoard) {
        getUserchildrenmoves = getAllUserValidStateMoves(stateBoard);

        ArrayList<Route> userRoutes = new ArrayList<>();

        for (Move move : getComputerchildrenmoves) {
            for (Square dst : move.validMoves) {
                Route r = new Route(move.square, dst);
                userRoutes.add(r);
            }
        }
        return userRoutes;

    }

    ArrayList<Route> getComputerRoutes;
    ArrayList<Route> getUserRoutes;

    public void printRoutes(ArrayList<Route> routes) {
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    public boolean checkmate(Square[][] squares, boolean machineturn) {
        boolean checkm = false;
        if (!machineturn) {   //user playing , checks first if user's king is captured the previous turn --> computer wins
            kingU:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (squares[i][j].getPiece().getName() == "King" && squares[i][j].getPiece().userOwnership == true) {
                        checkm = false;
                        break kingU;

                    } else {
                        checkm = true;
                    }
                }
            }
        }
        if (machineturn) {   //computer playing , checks first if computer's king benn captured in previous turn --> user wins
            kingC:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((!(squares[i][j].getPiece() == null)) && squares[i][j].getPiece().getName() == "King" && squares[i][j].getPiece().userOwnership == false) {
                        checkm = false;
                        break kingC;
                    } else {
                        checkm = true;
                    }
                }
            }

        }

        return checkm;
    }

    public Destination alphaBeta(Square[][] stateBoard, int depth, Destination alpha, Destination beta, boolean player) {
        getComputerRoutes = getAllComputerRoutes(stateBoard);
        getUserRoutes = getAllUserRoutes(stateBoard);
        Destination bestDest = new Destination(null, null, Integer.MIN_VALUE);
        Destination v = new Destination(null, null, Integer.MIN_VALUE);
        Boolean stalemate=stalemate(squares);
        if (depth == 0 || checkmate(stateBoard, machineTurn)) {
            double heuristic = -eval.evaluate(stateBoard, depth, player, stalemate);
            return new Destination(null, null, heuristic);
        }

        if (!player) {
            for (Route route : getComputerRoutes) {
                Square[][] tempBoard = new Square[8][8];

                
                saveMove(stateBoard,tempBoard);

                Square[][] updatedstateBoard = updateState(stateBoard, route.Source, route.Destination);
                Destination recursion = alphaBeta(updatedstateBoard, depth - 1, alpha, beta, player);

                System.out.println("Node : " + leafNodes + ", utility value : " + recursion.heuristic);
                leafNodes++;

                undoMove(stateBoard, tempBoard);

                if (bestDest.Destination == null || bestDest.heuristic < recursion.heuristic) {
                    bestDest.heuristic = recursion.heuristic;
                    bestDest.source = route.Source;
                    bestDest.Destination = route.Destination;
                }
                if (recursion.heuristic > alpha.heuristic) {
                    alpha.heuristic = recursion.heuristic;
                    alpha.source = recursion.source;
                    alpha.Destination = recursion.Destination;

                }
                if (beta.heuristic <= alpha.heuristic) {
                    bestDest.heuristic = beta.heuristic;
                    bestDest.source = null;
                    bestDest.Destination = null;
                    return bestDest;
                }
            }

            return bestDest;
        } else {


            for (Route route : getUserRoutes) {
                Square[][] tempBoard = new Square[8][8];
               
                saveMove(stateBoard, tempBoard);

                Square[][] updatedstateBoard = updateState(stateBoard, route.Source, route.Destination);
                Destination recursion = alphaBeta(updatedstateBoard, depth - 1, alpha, beta, !player);

               
                undoMove(stateBoard, tempBoard);

                if (bestDest.Destination == null || bestDest.heuristic > recursion.heuristic) {
                    bestDest.heuristic = recursion.heuristic;
                    bestDest.source = recursion.source;
                    bestDest.Destination = recursion.Destination;
                }
                if (recursion.heuristic < beta.heuristic) {
                    beta.heuristic = recursion.heuristic;
                    bestDest.source = recursion.source;
                    bestDest.Destination = recursion.Destination;

                }
                if (beta.heuristic <= alpha.heuristic) {
                    bestDest.heuristic = alpha.heuristic;
                    bestDest.source = null;
                    bestDest.Destination = null;
                    return bestDest;
                }

            }
            return bestDest;

        }

    }
    
    
    public Square[][] saveMove(Square[][] stateBoard,Square[][] tempBoard)
    {
         for (int ii = 0; ii < 8; ii++) {
                    for (int jj = 0; jj < 8; jj++) {
                        tempBoard[ii][jj] = new Square(stateBoard[ii][jj].a, stateBoard[ii][jj].b);
                        Piece squarePiece = stateBoard[ii][jj].getPiece();
                        tempBoard[ii][jj].setPiece(squarePiece);
                    }
                }
         return tempBoard;
    }
    
     public Square[][] undoMove(Square[][] stateBoard,Square[][] tempBoard)
    {
         for (int ii = 0; ii < 8; ii++) {
                    for (int jj = 0; jj < 8; jj++) {
                        stateBoard[ii][jj] = new Square(tempBoard[ii][jj].a, tempBoard[ii][jj].b);
                        Piece squarePiece = tempBoard[ii][jj].getPiece();
                        stateBoard[ii][jj].setPiece(squarePiece);
                    }
                }
         
        return stateBoard;
    }

    public Destination max(Destination v, Destination alphabeta) {
        if (v.heuristic > alphabeta.heuristic) {
            return v;
        } else {
            return alphabeta;
        }
    }

    public Destination min(Destination v, Destination alphabeta) {
        if (v.heuristic > alphabeta.heuristic) {
            return v;
        } else {
            return alphabeta;
        }
    }
 public boolean stalemate (Square[][] squares){
        
        boolean isSafe = true;
        boolean  user = false;
        int indexA=0 , indexB=0 , indexC=0 , indexD=0 ,indexCO=0 , indexDO=0 ;
        int indexX=0 , indexY=0;
        Piece original ;
        Square orig ;
        King king = new King();
        String k = king.name;
        ArrayList<Move> allCVM = new ArrayList<>();
        ArrayList<Move> allUVM = new ArrayList<>();
        ArrayList<Square> CVM = new ArrayList<>();
        ArrayList<Square> UVM = new ArrayList<>();
        ArrayList<Square> aCVM = new ArrayList<>();
        ArrayList<Square> threatSquares = new ArrayList<>();
       
       
       if(!machineTurn){
      
            getking: for(int i=0;i<8;i++){
          for(int j=0;j<8;j++){
              
            if("King".equals(squares[i][j].getPiece().getName())){
               indexA = squares[i][j].a ;
               indexB = squares[i][j].b;
               Piece pieceonSquare = squares[indexA][indexB].getPiece();
             if (pieceonSquare != null) {
                  user = pieceonSquare.userOwnership;
              }
             if(user){
                 break getking;
             }
           } 
         }   
       }
           
           
          allCVM = getAllComputerValidMoves(); 
        
            for (Move allCVM1 : allCVM) {
                CVM = allCVM1.getValidMoves();
                for (int j = 0; j<CVM.size(); j++) {
                    indexC = CVM.get(j).a;
                    indexD = CVM.get(j).b;
                    if (indexA==indexC && indexB==indexD) {
                        isSafe = false;
                        orig = allCVM1.square;
                        if (!threatSquares.contains(orig))
                        {
                            threatSquares.add(orig);
                            
                        }    }
                    CVM.clear();
                }
            }
       allUVM = getAllUserValidMoves();
       for (int i=0;i<allUVM.size();i++){    ///This loop is to see if any of the user's pieces can eliminate any of the piece 
            UVM = allUVM.get(i).getValidMoves(); //causing the threat and remove them from arraylist threatSquares
        
         for(int j=0;j<UVM.size();j++){
            indexX = UVM.get(j).a;
            indexY = UVM.get(j).b;
           for(int h=0;h<threatSquares.size();h++){
               indexCO = threatSquares.get(h).a;
               indexDO = threatSquares.get(h).b;
               if(indexX != indexCO && indexY != indexDO){
                   isSafe = false;  
               }else{
                   threatSquares.remove(h);
                   isSafe = true;
               }
           } 
         }
         UVM.clear();
       }
      
       for(int i=0;i<threatSquares.size();i++){ //This loop checks if any of the pieces causing the threat can be blocked
           orig = threatSquares.get(i);
           CVM = orig.getPiece().getValidMoves(squares, new Location(orig.a, orig.b));//returns arraylist of squares with all valid moves for a square in threatsquares
           for(int j=0;j<CVM.size();j++){
                indexC = CVM.get(j).a;
                indexD = CVM.get(j).b;
               for(int y=0;y<allUVM.size();y++){
               UVM = allUVM.get(y).getValidMoves();
                  for(int h=0;h<UVM.size();h++){
                     indexX = UVM.get(h).a;
                     indexY = UVM.get(h).b;
                     if(indexX != indexC && indexY != indexD){
                        isSafe = false;
                  }
               }
                  UVM.clear();
             } 
           }
                  CVM.clear();
           if(isSafe = false){
            break;
           }
       }
       allCVM.clear(); allUVM.clear();
     }
     if(machineTurn){
       
     getking: for(int i=0;i<8;i++){
             for(int j=0;j<8;j++){
                if(squares[i][j].getPiece()!=null)
                {
                    if("King".equals(squares[i][j].getPiece().getName())){
               indexA = squares[i][j].a ;
               indexB = squares[i][j].b;
               Piece pieceonSquare = squares[indexA][indexB].getPiece();
             if (pieceonSquare != null) {
                  user = pieceonSquare.userOwnership;
              }
               if(!user){
               break getking;
             }
           } 
             }       
         }   
       }
      allUVM = getAllUserValidMoves(); 
        
       for (int i=0; i<allUVM.size();i++ ){
         
         UVM = allUVM.get(i).getValidMoves();
           
         for(int j=0;j<UVM.size();j++){
             indexC = UVM.get(j).a;
             indexD = UVM.get(j).b;
             
            if(indexA==indexC && indexB==indexD) {
               
                isSafe = false;
                orig = allUVM.get(i).square;
                
              if (!threatSquares.contains(orig))
               {
                  threatSquares.add(orig);
                  
               }
            }
              UVM.clear();
         } 
       } 
       allCVM = getAllComputerValidMoves();
       for (int i=0;i<allCVM.size();i++){    ///This loop is to see if any of the user's pieces can eliminate any of the piece 
            CVM = allCVM.get(i).getValidMoves(); //causing the threat and remove them from arraylist threatSquares
        
         for(int j=0;j<CVM.size();j++){
            indexX = CVM.get(j).a;
            indexY = CVM.get(j).b;
           for(int h=0;h<threatSquares.size();h++){
               indexCO = threatSquares.get(h).a;
               indexDO = threatSquares.get(h).b;
               if(indexX != indexCO && indexY != indexDO){
                   isSafe = false;  
               }else{
                   threatSquares.remove(h);
                   isSafe = true;
               }
           } 
         }
         CVM.clear();
       }
      
      for(int i=0;i<threatSquares.size();i++){ //This loop checks if any of the pieces causing the threat can be blocked
           orig = threatSquares.get(i);
           UVM = orig.getPiece().getValidMoves(squares, new Location(orig.a, orig.b));//returns arraylist of squares with all valid moves for a square in threatsquares
           for(int j=0;j<UVM.size();j++){
                indexC = UVM.get(j).a;
                indexD = UVM.get(j).b;
               for(int y=0;y<allCVM.size();y++){
               CVM = allCVM.get(y).getValidMoves();
                  for(int h=0;h<CVM.size();h++){
                     indexX = CVM.get(h).a;
                     indexY = CVM.get(h).b;
                     if(indexX != indexC && indexY != indexD){
                        isSafe = false;
                  }
               }
                  CVM.clear();
             } 
           }
                  UVM.clear();
           if(isSafe = false){
            break;
           }
        } 
     }  
       return isSafe;
    }
}

class Destination {

    Square source;
    Square Destination;
    double heuristic;

    public Destination(Square source, Square dest, double heuristic) {
        this.source = source;
        this.Destination = dest;
        this.heuristic = heuristic;

    }

}

class Route {

    Square Source;
    Square Destination;

    public Route(Square src, Square dst) {
        this.Source = src;
        this.Destination = dst;
    }

    public String toString() {
        return " I am Source" + Source.a + " , " + Source.b + " I am Destination" + Destination.a + " , " + Destination.b;
    }
}
