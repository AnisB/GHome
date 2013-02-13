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

    public TempDao() {
    }

    public void create(Temperature temp) throws Exception {
        JpaUtil.getEntityManager().persist(temp);
    }

    public Temperature getLastTemp(String id) {
        try {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Temperature c WHERE c.capID=:id ORDER BY c.mHeure DESC");
            List<Temperature> t = (List<Temperature>) query.setParameter("id", id).getResultList();
            System.out.println("list");
            return t.get(0);
        } catch (Exception ex) {
            return null;
        }


    }
}
