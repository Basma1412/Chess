package javaapplication8;

import java.awt.*;
import javax.swing.*;

class Board extends JFrame {

    public JButton[][] box = new JButton[8][8];
    SquarePanel panl;
  

    public Board(String Col,String difficulty) {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, dim.width/2, dim.width/2);
        this.setLocationRelativeTo(null);
        Container c = this.getContentPane(); 
        panl = new SquarePanel(Col,difficulty);
        c.add(panl);

    }

   


    List getPlayedMoves() {
        return null;
    }

}

public class JavaApplication8 {

    public static void main(String[] args) {
        WelcomePage chess = new WelcomePage();
        chess.setVisible(true);
    }

}
