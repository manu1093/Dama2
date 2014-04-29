/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Manu
 */
public class Debug {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
	
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GUI1 f=null;
				
					f = new GUI1("dama");
				
				
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
		});
		}
    
}
