/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

public class CellaNonVuotaException extends Exception{
	public CellaNonVuotaException(){
		super("la cella non è vuota");
	}
}
