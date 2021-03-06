/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anisbenyoub
 */
public class Acces extends Conteneur {

    public List<Piece> mesVoisins;
    public int taillex;
    public int tailley;
    public Integer etage;

    public Acces(Integer id, String nom, Integer x, Integer y, Integer etage, int taillex, int tailley) {
        super();
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.etage = etage;
        this.mesVoisins = new ArrayList<Piece>();
        this.taillex = taillex;
        this.tailley = tailley;
    }

    public List<Piece> getMesVoisins() {
        return mesVoisins;
    }

    public void addVoisin(Piece unVoisin) {
        this.mesVoisins.add(unVoisin);
    }
        public String toXml()
    {
        String toReturn="<acces id=\""+id+"\" nom=\""+nom+"\" posx=\""+x+"\" posy=\""+y+"\" etage=\""+etage+"\" taillex=\""+taillex+"\" tailley=\""+tailley+"\">\n";
	for(Capteur c : mesCapteurs)
        {
            toReturn+=c.toXml();
        }
	for(Actionneur a : mesActionneurs)
        {
            toReturn+=a.toXml();
        }
	toReturn+="</acces>\n";
        return toReturn;
        
    }
}
