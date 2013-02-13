/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import api.Api;
import dao.*;
import modele.*;
import serverghome.ServerGHome;
import util.JpaUtil;

/**
 *
 * @author anis
 */
public class Service {
protected Api monApi;
// Méthodes relatives aux Clicks
    public void addClick(Click aClick) throws Exception {
        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        ClickDao hisData = new ClickDao();
        hisData.create(aClick);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }

    public int getNbClick() {
        ClickDao hisData = new ClickDao();
        JpaUtil.openEntityManager();
        int nbClick = hisData.getNBClick();
        JpaUtil.closeEntityManager();
        return nbClick;
    }

    // Ajout d'un client
    public void addClient(String id, String mdp) throws Exception {
        ClientDao hisClient = new ClientDao();
        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        hisClient.create(id, mdp);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }

    public boolean testClient(String id, String mdp) throws Exception {
        ClientDao hisClient = new ClientDao();
        JpaUtil.openEntityManager();
        boolean value = hisClient.testIfRegistred(id, mdp);
        JpaUtil.closeEntityManager();
        return value;

    }

    public String getData(String idCapteur, String typeData) {


        // Ouverture de l'entity manager
        JpaUtil.openEntityManager();

        //Dans le cas ou nous sommes dans le cas d'une data de type Temperature
        if (typeData.contains("T")) {
            TempDao td = new TempDao();
            try {
                JpaUtil.openEntityManager();
                Temperature T = td.getLastTemp(idCapteur);

                if (T != null) {
                    JpaUtil.closeEntityManager();

                    return "G1 " + T.getValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 NOTINIT";
                }
            } catch (Exception ex) {
                JpaUtil.closeEntityManager();
                System.out.println("Error while looking for temperature");
                return "G0 TNOTFOUND";
            }
        } // Dans le cas ou nous sommes dans une data de type click
        else if (typeData.contains("C")) {

            ClickDao cd = new ClickDao();
            try {
                Click c = cd.getLastClick(idCapteur);
                if (c != null) {
                    JpaUtil.closeEntityManager();

                    return "G1 " + c.getValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 UNKNOWN";
                }

            } catch (Exception ex) {
                JpaUtil.closeEntityManager();
                return "G0 CNOTFOUND";
            }
        } // Dans le cas ou nous sommes dans le cas d'un contact
        else if (typeData.contains("O")) {
            ContactDao cd = new ContactDao();
            try {
                Contact c = cd.getIsInContact(idCapteur);
                if (c != null) {
                    return "G1 " + c.getValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 UNKNOWN";
                }

            } catch (Exception ex) {
                JpaUtil.closeEntityManager();
                return "G0 CNOTFOUND";
            }
        } // Dans le cas ou nous sommes dans le cas d'une data présence
        else if (typeData.contains("P")) {
            PresenceDao cd = new PresenceDao();
            try {
                Presence c = cd.getPresence(idCapteur);
                if (c != null) {
                    JpaUtil.closeEntityManager();
                    return "G1 " + c.isValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 UNKNOWN";
                }

            } catch (Exception ex) {
                JpaUtil.closeEntityManager();
                return "G0 PNOTFOUND";
            }
        } // Dans le cas ou nous sommes dans le cas d'une data luminosité
        else if (typeData.contains("L")) {
            LuminositeDao ld = new LuminositeDao();
            try {
                Luminosite c = ld.getLastLumi(idCapteur);
                if (c != null) {
                    JpaUtil.closeEntityManager();
                    return "G1 " + c.getValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 UNKNOWN";
                }

            } catch (Exception ex) {
                return "G0 PNOTFOUND";
            }
        } // Dans le cas ou nous sommes dans le cas d'une data tension
        else if (typeData.contains("V")) {
            TensionDao td = new TensionDao();
            try {
                Tension c = td.getLastTens(idCapteur);
                if (c != null) {
                    JpaUtil.closeEntityManager();
                    return "G1 " + c.getValue();
                } else {
                    JpaUtil.closeEntityManager();
                    return "G0 UNKNOWN";
                }

            } catch (Exception ex) {
                JpaUtil.closeEntityManager();
                return "G0 PNOTFOUND";
            }
        }
        JpaUtil.closeEntityManager();
        return "G0 NOTFOUND";
    }

    public void sendOrder(String idCapteur, String typeData, String optionalValue, ServerGHome s) {
        Api  a=s.getApi();
        a.Actionner(typeData,"FF9F1E05", optionalValue);
    }

    public void manageData(String typeData, String idCapteur, String value) throws Exception {
        // Ouverture de l'entity manager
        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        if (typeData.contains("T")) {

            Temperature temp = new Temperature(idCapteur, Float.valueOf(value));
            TempDao td = new TempDao();
            try {
                td.create(temp);
            } catch (Exception ex) {
            }
        } else if (typeData.contains("C")) {

            Click c = new Click(idCapteur, Integer.valueOf(value));
            ClickDao cd = new ClickDao();
            try {
                cd.create(c);
            } catch (Exception ex) {
            }
        } else if (typeData.contains("O")) {

            Contact c = new Contact(idCapteur, Integer.valueOf(value));
            ContactDao cd = new ContactDao();
            try {
                cd.create(c);
            } catch (Exception ex) {
            }
        } else if (typeData.contains("P")) {

            Presence c = new Presence(idCapteur, Integer.valueOf(value));
            PresenceDao cd = new PresenceDao();
            try {
                cd.create(c);
            } catch (Exception ex) {
            }
        } else if (typeData.contains("L")) {

            Luminosite l = new Luminosite(idCapteur, Float.valueOf(value));
            LuminositeDao ld = new LuminositeDao();
            try {
                ld.create(l);
            } catch (Exception ex) {
            }
        } else if (typeData.contains("V")) {

            Tension c = new Tension(idCapteur, Float.valueOf(value));
            TensionDao cd = new TensionDao();
            try {
                cd.create(c);
            } catch (Exception ex) {
            }
        }
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }
}
