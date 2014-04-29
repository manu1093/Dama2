/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;


public class Pedina {
	public static final char bianco='b';
	public static final char nero='n';
	protected final char color;
		
	public Pedina(char color){
		if(color=='b'||color=='n')
			this.color=color;
		else throw new IllegalArgumentException();
	}
	public Pedina(Pedina p){
           
            this.color=p.color;
        }
	public char getColor(){
		return this.color;
	}
	public boolean isBianca(){
            return color=='b';
					
	}
	public boolean isNera(){
            return color=='n';
					
	}
	public boolean isDamone(){
            return this instanceof Damone;
		
	}
        @Override
        public boolean equals(Object o){   
            if( o instanceof Pedina){
                Pedina p=(Pedina) o;
                return p.color==this.color&&p.isDamone()==this.isDamone();
            }
            else 
                return false;
        }
      
}
