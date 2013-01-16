/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import java.util.Iterator;
import java.util.List;
import modele.home.*;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
/**
 *
 * @author anisbenyoub
 */
public class Accessor {
     static public Lieu fullfill(Document document) {
        Element root = document.getRootElement();
        Lieu endroit= new Lieu();
        endroit.setMonType(root.attribute("type").getValue());
        endroit.setNomLieu(root.attribute("nom").getValue());
        
        
        for ( Iterator i = root.elementIterator(); i.hasNext(); ) 
           {
                  Element row = (Element) i.next();
                  if(row.getQualifiedName().equals("piece"))
                  {
                      Piece nouvellePiece = new Piece(Integer.parseInt(row.attribute("id").getValue()),
                                                    row.attribute("nom").toString(),
                                                    Integer.parseInt(row.attribute("posx").getValue()),
                                                    Integer.parseInt(row.attribute("posy").getValue()),
                                                    Float.valueOf(row.attribute("etage").getValue()));
                      Iterator itr2 = row.elementIterator();
                      while(itr2.hasNext())
                      {
                            Element subObjet=(Element) itr2.next();
                            if(subObjet.getQualifiedName().equals("capteur"))
                            {
                                Capteur newCapteur = new Capteur(Integer.parseInt(subObjet.attribute("id").getValue()),
                                                    subObjet.attribute("type").toString(),nouvellePiece);
                                nouvellePiece.addCapteur(newCapteur);
                            }
                            else if(subObjet.getQualifiedName().equals("actionneur"))
                            {
                                Actionneur newActionneur = new Actionneur(Integer.parseInt(subObjet.attribute("id").getValue()),
                                                    subObjet.attribute("type").toString(),nouvellePiece);
                                nouvellePiece.addActionneur(newActionneur);
                            }
                            
                  
                      }
                      endroit.addPiece(nouvellePiece);
                  }
                  else if(row.getQualifiedName().equals("acces"))
                  {
                      Acces nouvelAcces= new Acces(Integer.parseInt(row.attribute("id").getValue()),
                                                    row.attribute("nom").toString(),
                                                    Integer.parseInt(row.attribute("posx").getValue()),
                                                    Integer.parseInt(row.attribute("posy").getValue()),
                                                    Float.valueOf(row.attribute("etage").getValue()));
                      
 
                    Iterator itr2 = row.elementIterator();
                      while(itr2.hasNext())
                      {
                            Element subObjet=(Element) itr2.next();
                            if(subObjet.getQualifiedName().equals("capteur"))
                            {
                                Capteur newCapteur = new Capteur(Integer.parseInt(subObjet.attribute("id").getValue()),
                                                    subObjet.attribute("type").toString(),nouvelAcces);
                                nouvelAcces.addCapteur(newCapteur);
                            }
                            else if(subObjet.getQualifiedName().equals("actionneur"))
                            {
                                Actionneur newActionneur = new Actionneur(Integer.parseInt(subObjet.attribute("id").getValue()),
                                                    subObjet.attribute("type").toString(),nouvelAcces);
                                nouvelAcces.addActionneur(newActionneur);
                            }
                            else if(subObjet.getQualifiedName().equals("piecevoisine"))
                            {
                                  nouvelAcces.addVoisin(endroit.getPieceByID(Integer.parseInt(subObjet.attribute("id").getValue())));
                            }
                      }
                      endroit.addAcces(nouvelAcces);

                    }

                  }

             
                  return endroit;
    }

    
}