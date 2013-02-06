/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Temperature;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class TempDao {
     public TempDao(){
        
        }
         public void create(Temperature temp) throws Exception  {

        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(temp);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }
       public Temperature getLastTemp(String id)
       {   
           try{
            JpaUtil.openEntityManager();
            JpaUtil.getEntityManagerTransaction().begin();
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Temperature c WHERE c.capID=:id :ASC");
            List<Temperature> t = (List<Temperature>)query.setParameter("id", id).getResultList();
                    JpaUtil.closeEntityManager();
            return t.get(0);
           }
           catch(Exception ex){
           }
           
                JpaUtil.closeEntityManager();
                return new Temperature(id, new Float(99.0));

       }
    

}
