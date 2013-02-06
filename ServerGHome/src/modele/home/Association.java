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
    Integer nbConditions;

    
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
        nbConditions=Integer.valueOf(myAttributes.get(1));
        
    }
    
    public boolean test()
    {
         decalage =0;
        for(int i=0; i!=nbConditions;i++ )
        {
        String s = serviceManager.getData(myAttributes.get(2+decalage),myAttributes.get(3+decalage));
        if(s.contains("G0"))
        {
            return false;
        }
        if(myAttributes.get(3+decalage).equals("Exact"))
        {
            if( s.equals("G1 "+myAttributes.get(4)))
            {
                decalage=+4;
                continue;
            }
            else
            {
                return false;
            }

        }
        else if(myAttributes.get(3).equals("Range"))
        {
            String v =s.split(" ")[1];
            Integer is=Integer.valueOf(v);
            Integer lb=Integer.valueOf(myAttributes.get(5+decalage));
            Integer ub=Integer.valueOf(myAttributes.get(6+decalage));
            if ((is>lb)&&(is<ub))
            {
                decalage=+5;
                continue;
            }
            else
            {
                return false;
            }
        }
        else if(myAttributes.get(3).equals("Inf"))
        {
            String v =s.split(" ")[1];
            Integer is=Integer.valueOf(v);
            Integer lb=Integer.valueOf(myAttributes.get(5));
            if ((is>lb))
            {
                decalage=+4;
                continue;
            }
            else
            {
                return false;
            }
        }
        else if(myAttributes.get(3).equals("Sup"))
        {
            String v =s.split(" ")[1];
            Integer is=Integer.valueOf(v);
            Integer ub=Integer.valueOf(myAttributes.get(5));
            if ((is<ub))
            {
                decalage+=4;
            }
            else
            {
                return false;
            }
        }
        }
        return true;
    }
    
    public void execute()
    {
        serviceManager.sendOrder(myAttributes.get(2+decalage),myAttributes.get(3+decalage),myAttributes.get(4+decalage),myAttributes.get(5+decalage));
    }
    
    
    @Override
    public String toString()
    {
        String value="";
        
        for(int i=1;i!=myAttributes.size()-1;i++)
        {
            value+=myAttributes.get(i)+" ";
        }
        value+=myAttributes.get(myAttributes.size()-1);
        return value;
    }
}
