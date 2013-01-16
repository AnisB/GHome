/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import java.util.List;
import modele.home.Lieu;
 
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
        System.out.println(document.asXML());
        Element root = document.getRootElement();
        Lieu endroit= new Lieu();
        System.out.println("hi");
        System.out.print(root.attribute("nom").asXML());
        return endroit;
    }

    
}
