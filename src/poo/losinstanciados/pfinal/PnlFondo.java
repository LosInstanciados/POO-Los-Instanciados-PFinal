/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.losinstanciados.pfinal;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Mariana Villegas
 */
public class PnlFondo extends JPanel{
    
            
    public PnlFondo(){
      //  initComponents();
        
        JPanel p = new JPanel();
        p.setSize(700, 500);
    }
    public void paintComponent(Graphics g){
        
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/fondo2.jpg"));
   g.drawImage(imagenFondo.getImage(), 0, 0, 700, 500, null);

   setOpaque(false);
   super.paintComponent(g);
    }
}
