/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package albero;

import dama.Tavola;
import java.util.ArrayList;

/**
 *
 * @author Stefano
 */
public class GenericTavolaTree extends Node {
    
        public GenericTavolaTree(Object elemento,int id) {
        super(elemento,id);
    }
    
    public GenericTavolaTree(Node i){
        super(i.elemento,i.id);
    }
    
    public void setTavola(Tavola t) {
        super.elemento=t; 
    }

    
    public dama.Tavola getTavola() {
        dama.Tavola a=null;
        if(super.elemento instanceof Tavola)
           a=(dama.Tavola) super.elemento;
        return a;
        
    }
    @Override
    public String toString(){
        
        String s="";
        s+=this.elemento+"\n";     
        s+=creaStringa(true);
        
        
       return s;
        
    }
    
    
    private String creaStringa(boolean f){//implementa il tostring per l'arbitro
        String s="";        
        for(int i=0;i<this.children.size();i++){
            s+=new GenericTavolaTree(this.children.get(i)).getTavola()+" ";            
            if(i==this.children.size()-1&&f){
                s+="\n";
            }
        }
        for(int i=0;i<this.children.size();i++){
                        
            if(i==this.children.size()-1){
                s+=new GenericTavolaTree(this.children.get(i)).creaStringa(true);
            }else
                s+=new GenericTavolaTree(this.children.get(i)).creaStringa(false);
            
        }
        return s;
    }
    
    
    public ArrayList<Tavola> foglie(){
        ArrayList<Tavola> a=new ArrayList<>();
        for(Node n:super.foglieN())
            a.add((Tavola) n.elemento);
        return a;
    }
    public ArrayList<Tavola> getDeeperNodes(){
        ArrayList<Tavola> a=new ArrayList<>();
        for(Node n:super.getDeeperNodesN())
            a.add((Tavola) n.elemento);
        return a;
    }
    public Tavola getFather(){
        return (Tavola) super.father.elemento;
    }
}
