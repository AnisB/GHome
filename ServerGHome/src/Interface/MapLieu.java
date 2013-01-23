/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Graphics;
import modele.home.*;

/**
 *
 * @author anisbenyoub
 */
public class MapLieu extends javax.swing.JPanel {

    public int selected;
    public Lieu monLieu;
    Window myWindow;
    /**
     * Creates new form MapLieu
     */
    public MapLieu(Lieu unLieu,Window w) {
        
        initComponents();
        monLieu=unLieu;
        this.setVisible(true);
        selected=-1;
        myWindow=w;
    }

    public void drawMap(Graphics g)
    {
        for(int i=0; i<monLieu.nombreEtage;i++)
        {
          //  System.out.println("Hi"+(0 +400*i)+' '+ 0+' '+ (400+400*i)+' '+ 400);
        g.setColor(Color.lightGray);
        g.fillRect(0 +400*i, 0, 400, 400);
        g.setColor(Color.black);
        
        g.drawRect(0 +400*i, 0, 400, 400);
        g.drawString("Etage "+i, 350+400*i, +350);
        g.setColor(Color.blue);
        for(Piece p:monLieu.getMesPieces().get(i))
        {
            if(!(selected==p.getId()))
            {
                g.setColor(Color.white);
            }
            else
            {
                System.out.println("a");
                g.setColor(Color.RED);
            }            g.fillRect(p.getX()+400*i, p.getY(), p.largeurX, p.largeurY);
            g.setColor(Color.blue);
            g.drawRect(p.getX()+400*i, p.getY(), p.largeurX, p.largeurY);
            g.drawString(p.getNom(), p.getX()+400*i+15, p.getY()+15);
        }
        g.setColor(Color.red);
        for(Acces a:monLieu.getMesAcces().get((i)))
        {
            if(!(selected==a.getId()))
            {
                g.setColor(Color.yellow);
            }
            else
            {
                                System.out.println("a");

                g.setColor(Color.RED);
            }
            g.fillRect(a.getX()+400*i, a.getY(), a.taillex, a.tailley);
            g.setColor(Color.red);
            g.drawRect(a.getX()+400*i, a.getY(), a.taillex, a.tailley);

        }
    }	
    }
    @Override
     public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMap(g);

	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas3 = new java.awt.Canvas();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 433, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        int q = (int)evt.getX()/400;
        System.out.println(q);
        Conteneur c=null;
        myWindow.getList1().removeAll();
        selected=-1;
        for(Piece p:monLieu.getMesPieces().get(q))
        {
            if((p.getX()<(evt.getX()%400))&&(p.getY()<(evt.getY()))&&
                    ((p.getX()+p.largeurX)>(evt.getX()%400))&&(p.getY()+p.largeurY)>(evt.getY()))
            {
                selected=p.getId();
                c=p;
            }
        }
        for(Acces p:monLieu.getMesAcces().get(q))
        {
            if((p.getX()<(evt.getX()%400))&&(p.getY()<(evt.getY()))&&
                    ((p.getX()+p.taillex)>(evt.getX()%400))&&(p.getY()+p.tailley)>(evt.getY()))
            {
                selected=p.getId();
                c=p;
            }
        }
        if(c!=null)
        {
            for(Capteur cP: c.getMesCapteurs())
            {
                myWindow.getList1().add("Type: "+cP.getMonType().toString()+" Id: "+cP.getId());
            }
        }
               
        this.updateUI();

    }//GEN-LAST:event_formMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas3;
    // End of variables declaration//GEN-END:variables
}
