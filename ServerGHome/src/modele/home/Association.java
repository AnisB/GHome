/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.util.ArrayList;
import java.util.List;
import service.Service;

/**
 *
 * @author anisbenyoub
 */
public class Association {
    
    List<String> myAttributes;
    List<String> myTest;
    Service serviceManager = new Service();
    Integer decalage=0;

    
    public Association()
    {
        myAttributes = new ArrayList<String>();
    }
    public Association(List<String> some)
    {
        myAttributes = new ArrayList<String>();
        for(String s : some)
        {
            myAttributes.add(s);
        }
    }
    
    public boolean test()
    {
        String s = serviceManager.getData(myAttributes.get(1),myAttributes.get(2),myAttributes.get(3));
        if(myAttributes.get(3).equals("Exact"))
        {
            return s.equals(myAttributes.get(4));

        }
        else if(myAttributes.get(3).equals("Range"))
        {
            decalage=1;
            Integer i=Integer.valueOf(s);
            Integer lb=Integer.valueOf(myAttributes.get(5));
            Integer ub=Integer.valueOf(myAttributes.get(6));
            return ((i>lb)&&(i<ub));
        }
        else if(myAttributes.get(3).equals("Inf"))
        {
            Integer i=Integer.valueOf(s);
            Integer lb=Integer.valueOf(myAttributes.get(5));
            return ((i>lb));
        }
        else if(myAttributes.get(3).equals("Sup"))
        {
            Integer i=Integer.valueOf(s);
            Integer ub=Integer.valueOf(myAttributes.get(5));
            return ((i<ub));
        }
        return false;
    }
    
    public void execute()
    {
        serviceManager.sendOrder(myAttributes.get(7+decalage),myAttributes.get(8+decalage),myAttributes.get(9+decalage));
    }
    
    public String toString()
    {
        String value="";
        
        for(int i=1;i!=myAttributes.size();i++)
        {
            value+=" "+myAttributes.get(i);
        }
        return value;
    }
}
