/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prove;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Manu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFrame f=new JFrame();
       f.setLayout(new FlowLayout());
       JButton mt=new JButton("metti/togli");
       final JButton s=new JButton("scrivi");
       final ActionListener a=new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("ciao");
           }
           
       };
       mt.addActionListener(new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
             if(s.getActionListeners().length==0){
                 s.addActionListener(a);
             }else
                 s.removeActionListener(a);
           }
       });
       s.addActionListener(a);
       f.add(mt);f.add(s);
       f.setVisible(true);
       f.setSize(200,200);
       
    }
    
}
