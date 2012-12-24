/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author anisbenyoub
 */
public class Click  extends Data {
    
    protected int buttonID;
    public Click(int aButtonID)
    {
        buttonID=aButtonID;
    }

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }
}
