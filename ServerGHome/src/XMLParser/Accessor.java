/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import java.util.Iterator;
import modele.home.*;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 *
 * @author anisbenyoub
 */
public class Accessor {

    static public Lieu fullfill(Document document) {

        Element root = document.getRootElement();
        //Creation du lieu
        Lieu endroit = new Lieu();

        //Récupération des attributs de ce lieu
        endroit.setMonType(root.attribute("type").getValue());
        endroit.setNomLieu(root.attribute("nom").getValue());
        endroit.setNbEtage(Integer.valueOf(root.attribute("nbEtage").getValue()));
        endroit.cityCode=root.attribute("code").getValue();


        //Parcours de toutes les sous entitées du lieu
        for (Iterator i = root.elementIterator(); i.hasNext();) {

            //Si c'est une piece
            Element row = (Element) i.next();
            
            //C'est une pièce
            if (row.getQualifiedName().equals("piece")) {
                
                //Création de la pièce avec les bons attributs
                Piece nouvellePiece = new Piece(Integer.parseInt(row.attribute("id").getValue()),
                        row.attribute("nom").getValue(),
                        Integer.parseInt(row.attribute("posx").getValue()),
                        Integer.parseInt(row.attribute("posy").getValue()),
                        Integer.valueOf(row.attribute("etage").getValue()),
                        Integer.parseInt(row.attribute("largex").getValue()),
                        Integer.parseInt(row.attribute("largey").getValue()));
                
                // Traitement des sous élèments de cette pièce
                Iterator itr2 = row.elementIterator();
                while (itr2.hasNext()) {
                    Element subObjet = (Element) itr2.next();
                    if (subObjet.getQualifiedName().equals("capteur")) {
                        Capteur newCapteur = new Capteur(subObjet.attribute("id").getValue(),
                                subObjet.attribute("type").getValue(), nouvellePiece);
                        nouvellePiece.addCapteur(newCapteur);
                    } else if (subObjet.getQualifiedName().equals("actionneur")) {
                        Actionneur newActionneur = new Actionneur(subObjet.attribute("id").getValue(),
                                subObjet.attribute("type").getValue(), nouvellePiece);
                        nouvellePiece.addActionneur(newActionneur);
                    }


                }
                endroit.addPiece(nouvellePiece);
                //C'est un acces
            } else if (row.getQualifiedName().equals("acces")) {

                Acces nouvelAcces = new Acces(Integer.parseInt(row.attribute("id").getValue()),
                        row.attribute("nom").getValue(),
                        Integer.parseInt(row.attribute("posx").getValue()),
                        Integer.parseInt(row.attribute("posy").getValue()),
                        Integer.valueOf(row.attribute("etage").getValue()),
                        Integer.parseInt(row.attribute("taillex").getValue()),
                        Integer.parseInt(row.attribute("tailley").getValue()));


                Iterator itr2 = row.elementIterator();
                while (itr2.hasNext()) {
                    Element subObjet = (Element) itr2.next();
                    if (subObjet.getQualifiedName().equals("capteur")) {
                        Capteur newCapteur = new Capteur(subObjet.attribute("id").getValue(),
                                subObjet.attribute("type").getValue(), nouvelAcces);
                        nouvelAcces.addCapteur(newCapteur);
                    } else if (subObjet.getQualifiedName().equals("actionneur")) {
                        Actionneur newActionneur = new Actionneur(subObjet.attribute("id").getValue(),
                                subObjet.attribute("type").getValue(), nouvelAcces);
                        nouvelAcces.addActionneur(newActionneur);
                    } else if (subObjet.getQualifiedName().equals("piecevoisine")) {
                        nouvelAcces.addVoisin(endroit.getPieceByID(Integer.parseInt(subObjet.attribute("id").getValue())));
                    }
                }

                endroit.addAcces(nouvelAcces);

            }

        }
        return endroit;
    }
}
