/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modele.admin.Client;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class ClientDao {
     public ClientDao(){
        
        }
         public void create(String id,  String mdp,Client.ClientType t) throws Exception  {

        Client newC = new Client(id,mdp,t);
        JpaUtil.getEntityManager().persist(newC);
    }
        public void create(String id,  String mdp,String t) throws Exception  {

        Client newC = new Client(id,mdp,t);
        JpaUtil.getEntityManager().persist(newC);
    }
       public boolean testIfRegistred(String id, String mdp)
       {   
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Client c WHERE c.id=:id AND c.mdp=:mdp");
            Client c = (Client)query.setParameter("id", id).setParameter("mdp", mdp).getSingleResult();
            if(c!=null)
                return true;
            else
                return false;
       }
       public List<Client> getAll()
       {   
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Client c");
            List<Client> c = query.getResultList();
            if(c!=null)
                return c;
            else
                return new ArrayList<Client>();
       }
       public boolean checkIDExists(String id)
       {
           Client c=null;
           try
           {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Client c WHERE c.id=:id");
            c = (Client)query.setParameter("id", id).getSingleResult();
           }
           catch (Exception ex)
           {
               System.out.println("Exception");
           }
            if(c!=null)
                return true;
            else
                return false;
       }
    
       public void deleteUser(String id)
       {
                     try
           {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("DELETE  from Client c WHERE c.id=:id");
            query.setParameter("id", id).executeUpdate();
           }
           catch (Exception ex)
           {
               
       }
       }
}
