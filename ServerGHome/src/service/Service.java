/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClickDao;
import dao.ClientDao;
import dao.DataDao;
import dao.TempDao;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Click;
import modele.Data;
import modele.Temperature;
import util.JpaUtil;

/**
 *
 * @author anis
 */
public class Service {

    public void addData(Data aData) throws Exception {
        DataDao hisData = new DataDao();
        hisData.create(aData);
    }

    public void addClick(Click aClick) throws Exception {
        ClickDao hisData = new ClickDao();
        hisData.create(aClick);
    }

    public Data findData(Date aDate) throws Exception {
        DataDao theData = new DataDao();
        Data it = new Data();//=theData.find(unClient.getdateNaissance());
        return it;
    }

    public int getNbClick() {
        ClickDao hisData = new ClickDao();
        JpaUtil.openEntityManager();
        int nbClick = hisData.getNBClick();
        JpaUtil.closeEntityManager();
        return nbClick;
    }

    public void addClient(String id, String mdp) throws Exception {
        ClientDao hisClient = new ClientDao();
        hisClient.create(id, mdp);
    }

    public boolean testClient(String id, String mdp) throws Exception {
        ClientDao hisClient = new ClientDao();
        JpaUtil.openEntityManager();
        boolean value = hisClient.testIfRegistred(id, mdp);
        JpaUtil.closeEntityManager();
        return value;

    }

    public String getData(String conteneur, String idCapteur, String typeData) {
        if (typeData.contains("T")) {
            TempDao td = new TempDao();
            try {
                Temperature T = td.getLastTemp(idCapteur);
                if (T != null) {
                    return new String("G1 " + T.getValue());
                } else {
                    return new String("G0 UNKNOWN");
                }
            } catch (Exception ex) {
                System.out.println("Error while looking for temperature");
                return new String("G0 TNOTFOUND");
            }
        } else if (typeData.contains("C")) {

            ClickDao cd = new ClickDao();
            try {
                Click c = cd.getLastClick(idCapteur);
                if (c != null) {
                    return new String("G1 " + c.getValue());
                } else {
                    return new String("G0 UNKNOWN");
                }

            } catch (Exception ex) {
                return new String("G0 CNOTFOUND");
            }
        }
        return new String("G0 NOTFOUND");

    }

    public void sendOrder(String conteneur, String idCapteur, String typeData,String optionalValue) {
    }

    public void manageData(String typeData, String idCapteur, String value) {
        if (typeData.contains("T")) {
            System.out.println(value);
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
        }

    }
}
