/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

/**
 *
 * @author anisbenyoub
 */
public class Actionneur {
        public enum Type {

        LUMIERE,
        CHAUFFAGE,
        AUTRE;
    }
    String id;
    Piece lieu;
    Actionneur.Type monType;
    public Actionneur(String theid, String type,Conteneur p)
    {
        this.id=theid;
        type= type.toLowerCase();
        if(type.equals("lumiere"))
        {
             monType=Type.LUMIERE;
        }
        else if(type.equals("chauffage"))
        {
             monType=Type.CHAUFFAGE;
        }
        else
        {
            monType=Type.AUTRE;
        }
    }
    public String getId() {
        return id;
    }

    public Actionneur.Type getMonType() {
        return monType;
    }
    
    public void print()
    {
        System.out.println("Actionneur");
    }
        public String toXml()
    {
        return "<actionneur id=\""+id+"\" type=\""+monType.toString().toLowerCase()+"\"/>\n";
    }
}
