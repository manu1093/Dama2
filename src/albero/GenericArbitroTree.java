/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package albero;

import java.util.ArrayList;
import dama.Arbitro;
/**
 *
 * @author Manu
 */
public class GenericArbitroTree extends Node {

    public GenericArbitroTree(Object elemento,int id) {
        super(elemento,id);
    }
    
    public GenericArbitroTree(Node i){
        super(i.elemento,i.id);
    }
    
    public void setTavola(Arbitro t) {
        super.elemento=t; 
    }

    
    public dama.Arbitro getArbitro() {
        dama.Arbitro a=null;
        if(super.elemento instanceof Arbitro)
           a=(dama.Arbitro) super.elemento;
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
            s+=new GenericArbitroTree(this.children.get(i)).getArbitro()+" ";            
            if(i==this.children.size()-1&&f){
                s+="\n";
            }
        }
        for(int i=0;i<this.children.size();i++){
                        
            if(i==this.children.size()-1){
                s+=new GenericArbitroTree(this.children.get(i)).creaStringa(true);
            }else
                s+=new GenericArbitroTree(this.children.get(i)).creaStringa(false);
            
        }
        return s;
    }
    
    
    public ArrayList<Arbitro> foglie(){
        ArrayList<Arbitro> a=new ArrayList<>();
        for(Node n:super.foglieN())
            a.add((Arbitro) n.elemento);
        return a;
    }
    public ArrayList<Arbitro> getDeeperNodes(){
        ArrayList<Arbitro> a=new ArrayList<>();
        for(Node n:super.getDeeperNodesN())
            a.add((Arbitro) n.elemento);
        return a;
    }
    public Arbitro getFather(){
        return (Arbitro) super.father.elemento;
    }
    
}
