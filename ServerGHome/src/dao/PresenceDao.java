/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Presence;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class PresenceDao {
     public PresenceDao(){
        
        }
         public void create(Presence aPresence) throws Exception  {

        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(aPresence);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }

         
       public Presence getPresence(String Theid)
       {   
           try
            {
       JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT p from Presence p WHERE c.capID=:id ORDER BY c.mHeure DESC");
            List<Presence> t = query.setParameter("id", Theid).getResultList();
            if(!t.isEmpty())
            {
                        JpaUtil.closeEntityManager();
                return t.get(0);
            }
            }
           catch(Exception ex)
           {
              ex.printStackTrace();
           }
                return new Presence(Theid,-1); 

       }

}
