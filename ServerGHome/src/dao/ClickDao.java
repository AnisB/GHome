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

    public ClickDao() {
    }

    public void create(Click aClick) throws Exception {
        JpaUtil.getEntityManager().persist(aClick);
    }

    public int getNBClick() {
        Query query = (Query) JpaUtil.getEntityManager().createQuery("select COUNT(o) from Click o");
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }

    public Click getLastClick(String Theid) {
        try {
            System.out.println("l'id est:" + Theid);
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Click c WHERE c.buttonID=:id ORDER BY c.mHeure DESC");
            List<Click> t = query.setParameter("id", Theid).getResultList();
            if (!t.isEmpty()) {
                return t.get(0);
            }
        } catch (Exception ex) {
           
        }
         return new Click(Theid, -1);
    }
}
