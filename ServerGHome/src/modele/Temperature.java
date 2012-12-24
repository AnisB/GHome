/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author anisbenyoub
 */
public class Temperature {
    protected int value;

    public Temperature(int aValue)
    {
        value=aValue;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
