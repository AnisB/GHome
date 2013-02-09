/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

/**
 *
 * @author anisbenyoub
 */
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReader {

    public static Document read(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);
        return document;
    }
}