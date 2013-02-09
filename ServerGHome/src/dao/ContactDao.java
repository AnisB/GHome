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
        JpaUtil.getEntityManager().persist(aContact);
    }

    public Contact getIsInContact(String Theid) {
        try {

            Query query = (Query) JpaUtil.getEntityManager().createQuery("SELECT c from Contact c WHERE c.capID=:id ORDER BY c.mHeure DESC");
            List<Contact> t = query.setParameter("id", Theid).getResultList();
            if (!t.isEmpty()) {
                return t.get(0);
            }
        } catch (Exception ex) {
        }
        return new Contact(Theid, -1);

    }
}
