/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;


public class Damone extends Pedina{

	public Damone(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	public Damone(Damone d){
            super(d.color);
        }
        public Damone(Pedina p){
            super(p.color);
        }
        public char getColor(){
		return (""+super.color).toUpperCase().charAt(0);
	}
        
}
