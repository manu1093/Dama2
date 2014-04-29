/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Manu
 */
public class Cammino implements Iterable{
    private final ArrayList <Arbitro> c;
    private Tavola t;
    public Cammino(Tavola t){
        c=new ArrayList<>();  
        this.t=t;
    }
    public Cammino(Arbitro a,Tavola t){
        c=new ArrayList<>();
        c.add(a);
        this.t=t;
    }
    public Arbitro getFirst(){
        return c.get(0);
    }
    
    public Arbitro getLast(){
        return c.get(c.size()-1);
    }
    public void addArbitro(Arbitro a){
        Tavola temp=new Tavola(t);
        
        
            
    }
    @Override
    public Iterator iterator() {
        return this.c.listIterator();
    }
    
}
