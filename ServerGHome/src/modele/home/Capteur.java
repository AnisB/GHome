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

    public Capteur(int theid, String type,Conteneur p)
    {
        this.id=theid;
        System.out.println(type);
        if(type.equals("Interrupteur"))
        {
             monType=Type.INTERRUPTEUR;
        }
        else if(type.equals("Temperature"))
        {
             monType=Type.TEMPERATURE;
        }
        else if(type.equals("Position"))
        {
             monType=Type.POSITION;
        }
        else if(type.equals("Luminosite"))
        {
             monType=Type.LUMINOSITE;
        }
        else
        {
            monType=Type.AUTRE;
        }
    }
    public Integer getId() {
        return id;
    }

    public Type getMonType() {
        return monType;
    }
    public void print()
    {
        System.out.println("Catpeur");
    }
}
