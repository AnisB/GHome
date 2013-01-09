/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

/**
 *
 * @author anisbenyoub
 */
public class Capteur {

    public enum Type {

        INTERRUPTEUR,
        TEMPERATURE,
        POSITION,
        LUMINOSITE,
        AUTRE;
    }
    Integer id;
    Piece lieu;
    Type monType;
}
