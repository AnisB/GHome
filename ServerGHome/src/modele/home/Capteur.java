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
        CONTACT,
        PRESENCE,
        AUTRE;
    }
    String id;
    Piece lieu;
    Type monType;

    public Capteur(String theid, String type,Conteneur p)
    {
        this.id=theid;
        type=type.toLowerCase();
        if(type.equals("interrupteur"))
        {
             monType=Type.INTERRUPTEUR;
        }
        else if(type.equals("temperature"))
        {
             monType=Type.TEMPERATURE;
        }
        else if(type.equals("presence"))
        {
             monType=Type.PRESENCE;
        }
        else if(type.equals("contact"))
        {
             monType=Type.CONTACT;
        }
        else
        {
            monType=Type.AUTRE;
        }
    }
    public String getId() {
        return id;
    }

    public Type getMonType() {
        return monType;
    }
}
