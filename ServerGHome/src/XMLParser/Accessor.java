/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import java.util.List;
import org.dom4j.Document;

/**
 *
 * @author anisbenyoub
 */
public class Accessor {
     public void bar(Document document) {
        List list = document.selectNodes( "/piece" );
    }

    
}
