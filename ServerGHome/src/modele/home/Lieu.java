/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anisbenyoub
 */
public class Lieu {
    Map<Integer,List<Piece>> mesPieces;
    Map<Integer,List<Acces>> mesAcces;
    public enum TypeLieu {

        MAISON,
        BUREAU,
        USINE,
        ATELIER,
        AUTRE;
    }
    TypeLieu monType;
    String nomLieu;
    public int nombreEtage;
    
    public Lieu()
    {
        mesPieces= new HashMap<Integer,List<Piece>>();
        mesAcces = new HashMap<Integer,List<Acces>>();
    }

    public Map<Integer,List<Acces>> getMesAcces() {
        return mesAcces;
    }

    public Map<Integer,List<Piece>> getMesPieces() {
        return mesPieces;
    }
    
    public void addPiece(Piece newPiece)
    {
        mesPieces.get(new Integer(newPiece.etage.intValue())).add(newPiece);

    }
    
    public void setNbEtage(Integer nb)
    {
        nombreEtage=nb;
        for(int i= 0 ;i<=nb;i++)
        {
        mesPieces.put(i, new ArrayList<Piece>());
        mesAcces.put(i, new ArrayList<Acces>());

        }


    }
        
        
    public void print()
    {
        /*
        System.out.println("Lieu : nom "+nomLieu);
        for(Piece p : mesPieces)
        {
            p.print();
        }
        
        for(Acces a : mesAcces)
        {
            a.print();
        }
        
        */
    }
    public void addAcces(Acces newAcces)
    {
        mesAcces.get(newAcces.etage).add(newAcces);
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
    
    public Piece getPieceByID(int id)
    {
        /*
        for(Piece piece: mesPieces)
        {
            if(id==piece.getId())
            {
                 return piece;
            }
        }
        * 
        */
        return null;
    }
     
    
    
}
