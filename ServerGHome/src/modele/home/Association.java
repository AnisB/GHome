/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.util.ArrayList;
import java.util.List;
import serverghome.ServerGHome;
import service.Service;

/**
 *
 * @author anisbenyoub
 */
public class Association {
    
    List<String> myAttributes;
    List<String> myTest;
    Service serviceManager = new Service();
    ServerGHome msg ;
    Integer decalage=0;
    Integer nbConditions;
    int tmp=0;

    
    public Association(ServerGHome sg)
    {
        msg=sg;
        myAttributes = new ArrayList<String>();
    }
    public Association(List<String> some,ServerGHome sg)
    {
        msg=sg;
        myAttributes = new ArrayList<String>();
        for(String s : some)
        {
            myAttributes.add(s);
        }
        nbConditions=Integer.valueOf(myAttributes.get(1));
        
    }
    
    public boolean test()
    {
        System.out.println("Association à tester "+myAttributes);
        decalage =0;
        for(int i=0; i!=nbConditions;i++ )
        {
        String s = serviceManager.getData(myAttributes.get(2+decalage),myAttributes.get(3+decalage));
        if(s.contains("G0"))
        {
            System.out.println("Requete invalide");
            return false;
        }
        if(myAttributes.get(4+decalage).equals("Exact"))
        {
            System.out.println("Rendu s "+s);
            System.out.println("G1 "+myAttributes.get(5));
            if( s.equals("G1 "+myAttributes.get(5)))
            {
                decalage=+4;
                tmp=4;
                System.out.println("niquelchrome");
                continue;
            }
            else
            {
                System.out.println("Merde");
                return false;
            }

        }
        else if(myAttributes.get(4).equals("Range"))
        {
            String v =s.split(" ")[1];
            Float is=Float.valueOf(v);
            Float lb=Float.valueOf(myAttributes.get(5+decalage));
            Float ub=Float.valueOf(myAttributes.get(6+decalage));
            System.out.println("LowerBound "+lb);
            System.out.println("UpperBound "+ub);
            if ((is>lb)&&(is<ub))
            {
                decalage=+5;
                tmp=4;
                continue;
            }
            else
            {
                return false;
            }
        }
        else if(myAttributes.get(4).equals("Inf"))
        {
            String v =s.split(" ")[1];
            Integer is=Integer.valueOf(v);
            Integer lb=Integer.valueOf(myAttributes.get(5));
            if ((is>lb))
            {
                decalage=+4;
                tmp=4;
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
                tmp=4;
                continue;
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
        Integer declage2=decalage-tmp;
        serviceManager.sendOrder(myAttributes.get(6+declage2),myAttributes.get(7+declage2),myAttributes.get(8+declage2),msg);
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
