/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

/**
 *
 * @author Manu
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {

	
	public static void main(String[] args) {
	
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GUI f=null;
				
					f = new GUI("dama");
				
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				
			}
		});
		}
		
		/*//TERMINALE
		Tavola t=new Tavola();
		String s=JOptionPane.showInputDialog("1 o 2 giocatori?");
		if(s.equals("2")){
			boolean gioco=true;
			while(gioco){
				boolean vinto=false;
				while(!vinto){
					
					int r=Integer.parseInt(JOptionPane.showInputDialog(t.toString()+"\nRIGA?"));
					int c=Integer.parseInt(JOptionPane.showInputDialog(t.toString()+"\nCOLONNA?"));
					
					t.mossa("c", r, c);
					if(t.controlloVincita()!=null){
						vinto=true;
						break;
					}
						
					 r=Integer.parseInt(JOptionPane.showInputDialog(t.toString()+"\nRIGA?"));
					 c=Integer.parseInt(JOptionPane.showInputDialog(t.toString()+"\nCOLONNA?"));
					t.mossa("x", r, c);
					if(t.controlloVincita()!=null)
						vinto=true;
				}
				JOptionPane.showMessageDialog(null,"ha vinto il "+t.controlloVincita().toString());
				String risp=JOptionPane.showInputDialog("Giochi ancora?");
				t.reset();
				if(risp.equalsIgnoreCase("no"))
					gioco=false;
			}
			
		}
		
		// TEST
		boolean f=false;
		while(!f){
		String s=JOptionPane.showInputDialog(t.toString());
		if(s.equals("c")){
			int r=Integer.parseInt(JOptionPane.showInputDialog(t.toString()));
			int c=Integer.parseInt(JOptionPane.showInputDialog(t.toString()));
		t.mossa(new Segno (s.charAt(0)), r, c);
		}
		if(s.equals("x")){
			int r=Integer.parseInt(JOptionPane.showInputDialog(t.toString()));
			int c=Integer.parseInt(JOptionPane.showInputDialog(t.toString()));
		t.mossa(new Segno (s.charAt(0)), r, c);
		}
		if(t.controlloVincita()!=null)
			f=true;
		}*/
	}
