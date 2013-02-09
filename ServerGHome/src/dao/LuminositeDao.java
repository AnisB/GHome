/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Luminosite;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class LuminositeDao {

    public LuminositeDao() {
    }

    public void create(Luminosite temp) throws Exception {
        JpaUtil.getEntityManager().persist(temp);
    }

    public Luminosite getLastLumi(String id) {
        try {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Luminosite c WHERE c.capID=:id :ASC");
            List<Luminosite> t = (List<Luminosite>) query.setParameter("id", id).getResultList();
            return t.get(0);
        } catch (Exception ex) {
        }
        return new Luminosite(id, new Float(99.0));

    }
}
