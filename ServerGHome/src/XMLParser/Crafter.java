/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

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
        Accessor.fullfill(aDoc);
    }
    
}
