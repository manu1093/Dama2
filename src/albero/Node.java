/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package albero;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author Manu
 */
public class Node{
    protected int id;
    protected Object elemento=null;
    protected Node father;
    protected  ArrayList <Node> children=new ArrayList <Node>();

    public Node(Object elemento,int id) {
        this.id=id;
           
        this.elemento=elemento;
    }
    public Node(Node n){
        this.id=n.id;
        this.elemento=n.elemento;
        
        for(Node h:n.children){
            this.addFiglio(new Node(h));
        }
    }
    
    public final void addFiglio(Node nodoVicino){
        nodoVicino.father=this;
        children.add(nodoVicino);
        
    }
    
public ArrayList<Node> foglieN(){
        ArrayList<Node>r=new ArrayList<>();
        for(Node n:this.children){
            if(n.children.isEmpty()){
                
                r.add(n);
            }
            else{
                
                ArrayList<Node>a=n.foglieN();
                r.addAll(a);
            }
        }
        return r;        
    }

    public ArrayList<Node> getDeeperNodesN(){
        return this.getNLevelNodes(this.getDepht(),0);        
    }
    private ArrayList<Node> getNLevelNodes(int n,int i){
        ArrayList <Node>a=new ArrayList<>();
        for(Node no:this.children){
            if(n==i+1)
                a.add(no);
            
            if(i<n)
               a.addAll(no.getNLevelNodes(n, i+1));              
            
        }
        return a;
    }
   public int getDepht(){//ok
        return this.getDepthPrivate(0);
    }
    private int getDepthPrivate(int Initialdepth){
        int m=Initialdepth;
       for(Node n:this.children){ 
            int t= n.getDepthPrivate(Initialdepth+1);
            if(t>m)
                m=t;   
        }
       return m;
    }
    public Node getFatherN(){
        return this.father;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public ArrayList<Node> getChildren(){
        return this.children;
    }
    
    public int getNodeDepth(){
        int k=0;
        if(father!=null){
            k=1+this.father.getNodeDepth();            
        }
        return k;
    }
}
