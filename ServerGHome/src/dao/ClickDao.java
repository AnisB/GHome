/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Click;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class ClickDao {
     public ClickDao(){
        
        }
         public void create(Click aClick) throws Exception  {

        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(aClick);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }
         public int getNBClick()
         {
        Query query = (Query) JpaUtil.getEntityManager().createQuery("select COUNT(o) from Click o");
        Long result = (Long)query.getSingleResult();
        return result.intValue();
    }
         
       public Click getLastClick(String id)
       {   
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Click c WHERE c.capID=:id");
            List<Click> t = query.setParameter("id", id).getResultList();
            if(!t.isEmpty())
                return t.get(0);
            else
                return null;

       }

}
