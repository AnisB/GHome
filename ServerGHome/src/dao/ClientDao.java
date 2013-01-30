/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.Query;
import modele.Click;
import modele.home.Client;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class ClientDao {
     public ClientDao(){
        
        }
         public void create(String id,  String mdp) throws Exception  {

        Client newC = new Client(id,mdp);
        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(newC);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }
       public boolean testIfRegistred(String id, String mdp)
       {   
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Client c WHERE c.ID=:id");
            Client c = (Client)query.setParameter("id", id).getSingleResult();
            if(c!=null)
                return true;
            else
                return false;
       }
    

}
