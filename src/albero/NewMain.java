/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package albero;

import dama.Arbitro;
import java.util.ArrayList;

/**
 *
 * @author Manu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Node a1=new Node(new dama.Arbitro(0),1);
        Node a2=new Node(new dama.Arbitro(0),2);
        Node a3=new Node(new dama.Arbitro(0),3);
        Node a4=new Node(new dama.Arbitro(0),4);
        Node a5=new Node(new dama.Arbitro(0),5);
        Node a6=new Node(new dama.Arbitro(0),6);
        Node a7=new Node(new dama.Arbitro(0),7);
        Node a8=new Node(new dama.Arbitro(0),8);
        Node a9=new Node(new dama.Arbitro(0),9);
        Node a10=new Node(new dama.Arbitro(0),10);
        Node a11=new Node(new dama.Arbitro(0),11);
        Node a12=new Node(new Arbitro(0),12);
        a6.addFiglio(a8);
        a1.addFiglio(a3);
        a2.addFiglio(a4);
        a2.addFiglio(a5);
        a1.addFiglio(a2);
        a5.addFiglio(a9);
        a5.addFiglio(a6);
        a9.addFiglio(a10);
        a10.addFiglio(a11);
        a6.addFiglio(a7);
        a7.addFiglio(a12);
        GenericArbitroTree g=new GenericArbitroTree(a1);
        g.getDepht();
        ArrayList <Node> a=a1.getDeeperNodesN();
        g.foglie();
        System.out.println("");
        
    }
    
}
