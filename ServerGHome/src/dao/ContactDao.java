/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Contact;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class ContactDao {

    public ContactDao() {
    }

    public void create(Contact aContact) throws Exception {

        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(aContact);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }

    public Contact getIsInContact(String Theid) {
        try {
            JpaUtil.openEntityManager();
            JpaUtil.getEntityManagerTransaction().begin();
            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Contact c WHERE c.capID=:id ORDER BY c.mHeure DESC");
            List<Contact> t = query.setParameter("id", Theid).getResultList();
            if (!t.isEmpty()) {
                JpaUtil.closeEntityManager();
                return t.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Contact(Theid, -1);

    }
}
