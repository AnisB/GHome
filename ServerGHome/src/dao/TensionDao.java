/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Luminosite;
import modele.Tension;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class TensionDao {

    public TensionDao() {
    }

    public void create(Tension temp) throws Exception {
        JpaUtil.getEntityManager().persist(temp);
    }

    public Tension getLastTens(String id) {
        try {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Tension c WHERE c.capID=:id :ASC");
            List<Tension> t = (List<Tension>) query.setParameter("id", id).getResultList();
            return t.get(0);
        } catch (Exception ex) {
        }
        return new Tension(id, new Float(99.0));

    }
}
