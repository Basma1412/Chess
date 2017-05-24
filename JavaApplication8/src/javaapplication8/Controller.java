
package javaapplication8;

import java.awt.Color;

public class Controller {
    WelcomePage ww = new WelcomePage();
    
    public void startNewGame(String Col,String difficulty){
        Board ss = new Board(Col,difficulty);
        ss.setVisible(true);
    }
    
    
    public void ChooseColor(Color color){
        color = (Color)ww.CBDiffuculty.getSelectedItem();
    }
}
