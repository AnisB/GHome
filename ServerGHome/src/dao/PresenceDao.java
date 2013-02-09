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

    public PresenceDao() {
    }

    public void create(Presence aPresence) throws Exception {
        JpaUtil.getEntityManager().persist(aPresence);
    }

    public Presence getPresence(String Theid) {
        try {
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT p from Presence p WHERE c.capID=:id ORDER BY c.mHeure DESC");
            List<Presence> t = query.setParameter("id", Theid).getResultList();
            if (!t.isEmpty()) {
                return t.get(0);
            }
        } catch (Exception ex) {
        }
        return new Presence(Theid, -1);

    }
}
