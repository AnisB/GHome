/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modele.Data;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class DataDao {
     public DataDao(){
        
        }
         public void create(Data aData) throws Exception  {

        JpaUtil.openEntityManager();
        JpaUtil.getEntityManagerTransaction().begin();
        JpaUtil.getEntityManager().persist(aData);
        JpaUtil.getEntityManagerTransaction().commit();
        JpaUtil.closeEntityManager();
    }

}
