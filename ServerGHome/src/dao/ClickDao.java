/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

}
