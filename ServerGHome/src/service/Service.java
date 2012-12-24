/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DataDao;
import java.util.Date;
import modele.Data;



/**
 *
 * @author anis*/
 
public class Service {
    
        public void addData( Data aData) throws Exception  {
            DataDao hisData = new DataDao();
            hisData.create(aData);
    }
        
         public Data  findData(Date aDate) throws Exception
         {
             DataDao theData= new DataDao();
             Data it= new Data() ;//=theData.find(unClient.getdateNaissance());
             return it;
                     
            
         }

}
