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

    Map<Integer, List<Piece>> mesPieces;
    Map<Integer, List<Acces>> mesAcces;

    public enum TypeLieu {

        MAISON,
        BUREAU,
        USINE,
        ATELIER;
    }
    TypeLieu monType;
    String nomLieu;
    public int nombreEtage;
    public String cityCode;

    public Lieu() {
        mesPieces = new HashMap<Integer, List<Piece>>();
        mesAcces = new HashMap<Integer, List<Acces>>();
    }

    public Map<Integer, List<Acces>> getMesAcces() {
        return mesAcces;
    }

    public Map<Integer, List<Piece>> getMesPieces() {
        return mesPieces;
    }

    public void addPiece(Piece newPiece) {
        mesPieces.get(new Integer(newPiece.etage.intValue())).add(newPiece);

    }

    public void setNbEtage(Integer nb) {
        nombreEtage = nb;
        for (int i = 0; i <= nb; i++) {
            mesPieces.put(i, new ArrayList<Piece>());
            mesAcces.put(i, new ArrayList<Acces>());

        }


    }

    public String toXML() {
        String total = "<lieu nom=\"" + nomLieu + "\" type=\"" + monType + "\" nbEtage=\"" + nombreEtage + "\">\n";
        for (int i = 0; i < nombreEtage; i++) {
            for (Piece p : mesPieces.get(i)) {
                total += p.toXml();
            }
        }
        for (int i = 0; i < nombreEtage; i++) {
            for (Acces p : mesAcces.get(i)) {
                total += p.toXml();
            }
        }
        total += "</lieu>\n";

        return total;
    }

    public void addAcces(Acces newAcces) {
        mesAcces.get(newAcces.etage).add(newAcces);
    }

    public TypeLieu getMonType() {
        return monType;
    }

    public void setMonType(String type) {

        type=type.toLowerCase();
        if(type.equals("maison"))
        {
             monType=TypeLieu.MAISON;
        }
        else if(type.equals("bureau"))
        {
             monType=TypeLieu.BUREAU;
        }
        else if(type.equals("presence"))
        {
             monType=TypeLieu.ATELIER;
        }
        else if(type.equals("contact"))
        {
             monType=TypeLieu.USINE;
        }
        else
        {
            monType=TypeLieu.MAISON;
        }
    
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public List<Piece> getListPieces() {
        List<Piece> l = new ArrayList<Piece>();
        for (int i = 0; i < mesPieces.size(); i++) {
            for (Piece p : mesPieces.get(i)) {
                l.add(p);
            }
        }
        return l;
    }

    public List<Acces> getListAcces() {
        List<Acces> l = new ArrayList<Acces>();
        for (int i = 0; i < mesAcces.size(); i++) {
            for (Acces p : mesAcces.get(i)) {
                l.add(p);
            }
        }
        return l;
    }

    public Piece getPieceByID(Integer id) {
        List<Piece> l = getListPieces();
        for (Piece p : l) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
