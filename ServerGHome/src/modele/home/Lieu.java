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
public class Lieu {
    List<Piece> mesPieces;
    List<Acces> mesAcces;
    public enum TypeLieu {

        MAISON,
        BUREAU,
        USINE,
        ATELIER,
        AUTRE;
    }
    TypeLieu monType;
    String nomLieu;
    
    public Lieu()
    {
        mesPieces= new ArrayList<Piece>();
        mesAcces = new ArrayList<Acces>();
    }
    
    public void addPiece(Piece newPiece)
    {
        mesPieces.add(newPiece);
    }
        
    public void addAcces(Acces newAcces)
    {
        mesAcces.add(newAcces);
    }

    public TypeLieu getMonType() {
        return monType;
    }

    public void setMonType(String monType) {
        //this.monType = monType;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
    
}
