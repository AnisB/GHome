/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import modele.home.Lieu;
import org.dom4j.Document;
import org.dom4j.DocumentException;

/**
 *
 * @author anisbenyoub
 */
public class Crafter {
    public Crafter(String aPath) throws DocumentException
    {
        Document aDoc=XMLReader.read(aPath);
        Lieu newLieu = Accessor.fullfill(aDoc);
        //newLieu.print();
    }
    
}
