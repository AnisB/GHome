/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClickDao;
import dao.DataDao;
import java.util.Date;
import modele.Click;
import modele.Data;
import util.JpaUtil;



/**
 *
 * @author anis*/
 
public class Service {
    
        public void addData( Data aData) throws Exception  {
            DataDao hisData = new DataDao();
            hisData.create(aData);
    }
                public void addClick( Click aClick) throws Exception  {
            ClickDao hisData = new ClickDao();
            hisData.create(aClick);
    }
        
        
         public Data  findData(Date aDate) throws Exception
         {
             DataDao theData= new DataDao();
             Data it= new Data() ;//=theData.find(unClient.getdateNaissance());
             return it;
         }
    public int getNbClick() {
        ClickDao hisData = new ClickDao();
        JpaUtil.openEntityManager();
        int nbClick = hisData.getNBClick();
        JpaUtil.closeEntityManager();
        return nbClick;
    }
}
