/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tavola implements Iterable<Cell>{//itera su celle piene + comodo
	
	private HashMap <Cell,Pedina> pedine;//stacca pedine da celle
        
        public Tavola(){   
            pedine=new HashMap();
                }
        
        public Tavola(Tavola t){   
            
            //for(Iterator <Cell> i=t.getFullCells();i.hasNext();){
              //  Cell c=i.next();
            this.pedine=new HashMap<>();
            for(Cell c:t){
                try {
                    if(t.getPedina(c).isDamone())
                        this.pedine.put(c, new Damone(t.getPedina(c)));
                    else
                        this.pedine.put(c, new Pedina(t.getPedina(c)));
                } catch (CellaVuotaException ex) {
                    
                }
            }
        }
        
	public void inizio(){
		
                this.pedine.clear();
                        
		int y;
		int f=0;
		for (y=0;y<3;y++)//riempie le prime tre righe
			for(int x=0;x<8;x++)
                            try {
                                if(new Cell(x,y).isNera()){
                                    try{
                                        Cell c=new Cell(x,y);
                                        this.pedine.put(c, new Pedina(Pedina.nero));
                                    }catch(CellaInesistenteException e){System.out.println("cella sbagliata in costruttore tavola");}
                                    
                                }
                    } catch (CellaInesistenteException ex) {
                        Logger.getLogger(Tavola.class.getName()).log(Level.SEVERE, null, ex);
                    }
		for (y=5;y<8;y++){//ultime tre
			for(int x=0;x<8;x++){				
                            try {
                                if(new Cell(x,y).isNera()){
                                    Cell c;
                                    try {
                                        c = new Cell(x,y);
                                        this.pedine.put(c,new Pedina(Pedina.bianco));
                                    } catch (CellaInesistenteException ex) { System.out.println("cella sbagliata in costruttore tavola");}
                                    
                                }
                            } catch (CellaInesistenteException ex) {
                                Logger.getLogger(Tavola.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}
		/*char a='a';
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				tavoliere[i][j]=new Pedina(a);
				a++;
			}
		System.out.println(this.toString());
		System.out.println(this.getText(2, 3));*/
	//this.user=1;
	}
	/*
	public boolean isUserBlack(){
            return user==0;
        }
        public boolean isUserWhite(){
            return user==1;
        }
        */
        
	private char getText(int x,int y) throws CellaInesistenteException{
		//if(i<this.getLunghezza()||j<this.getAltezza()) throw new IllegalArgumentException();		
		if(this.pedine.containsKey(new Cell(x,y)))
                    return this.pedine.get(new Cell(x,y)).getColor();
                else{
                    if(new Cell(x,y).isBianca())
                        return 'x';
                    if(new Cell(x,y).isNera())
                        return ' ';
		}
                return 'J';
        }
	/*
	public Pedina getPedina(int x,int y){
		return this.tavoliere.get(y*8+x).getPedina();
	}
	
	*/
        
	
        
	/*
	public void setPedina(char h,Pedina p){
		for(int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				if(p==this.getPedina(i, j))
					this.setText(h,i,j);
			}
		}
	}
	*/
	public void swapCelle(Cell a,Cell b){
            /*
            Cell at=null;
            Cell bt=null;
            for(Cell c:this){
                if(c.getX()==a.getX()&&c.getY()==a.getY()){
                    at=c;
                }
                if(c.getX()==b.getX()&&c.getY()==b.getY()){
                    bt=c;
                }
            }
            */
            
            Pedina a1=this.pedine.remove(a);            
            Pedina a2=this.pedine.remove(b);
            if(a2!=null)
                this.pedine.put(a, a2);
            if(a1!=null)
                this.pedine.put(b, a1);
            
	}
	
        
        @Override
	public String toString(){
		int y;
		String s="";
		
		for (y=0;y<8;y++){
			s+=y;
			for(int x=0;x<8;x++)
				try {
                                    s+=" | "+this.getText(x, y);
                            } catch (CellaInesistenteException ex) {
                               
                            }
			s+="\n";
		}
		s+=" ";
		for(int i1=0;i1<8;i1++)
			s+=" | "+i1;
		return s;
	}
        
	
	
        
        public void insertPedinaBianca(Cell c) throws CellaNonVuotaException{
            if(!this.pedine.containsKey(c))
                            this.pedine.put(c, new Pedina(Pedina.bianco));
                    else throw new CellaNonVuotaException();
                
	}
	
	public void insertPedinaNera(Cell c) throws CellaNonVuotaException{
		if(!this.pedine.containsKey(c))
                            this.pedine.put(c, new Pedina(Pedina.nero));
                    else throw new CellaNonVuotaException();
	}
        
        public void removePezzo(Cell c) {
		
                    this.pedine.remove(c);
                
	}
        
	public boolean isEmpty(Cell c){//potrebbe non servire
            return !this.pedine.containsKey(c);
	}

    
    public void promuoviPedina(Cell c) throws CellaVuotaException{
		if(!this.pedine.containsKey(c))
			throw new CellaVuotaException();
                else                 
                    this.pedine.put(c, new Damone(this.pedine.get(c).getColor()));
                
	}
	public Pedina getPedina(Cell c) throws CellaVuotaException{//da vedere se togliere
            if(this.pedine.containsKey(c))
                return this.pedine.get(c);
            else
                throw new CellaVuotaException();
	}
	/*
	public char getColor(){//per compatibilità da togliere
		if(p!=null)
			return p.getColor();
		else
			return this.color;
	}
        */
        public boolean containsPezzoBianco(Cell c) throws CellaVuotaException{
		if(this.pedine.get(c)==null)
                   throw new CellaVuotaException(c.getX()+" "+c.getY());
               return this.pedine.get(c).isBianca();
		
		
	}
        public boolean containsPezzoNero(Cell c) throws CellaVuotaException{
		return !this.containsPezzoBianco(c);
		
	}
       
	public boolean containsPedinaBianca(Cell c) throws CellaVuotaException{
		
                    return this.containsPezzoBianco(c)&&!this.containsDamone(c);
		
		
	
        }
	public boolean containsPedinaNera(Cell c) throws CellaVuotaException{
		
                    return !this.containsDamone(c)&&this.containsPezzoNero(c);
		
	}
	public boolean containsDamoneBianco(Cell c) throws CellaVuotaException{
		
                    return this.containsDamone(c)&&this.containsPezzoBianco(c);
	
	}
	public boolean containsDamoneNero(Cell c) throws CellaVuotaException{
		
                    return this.containsDamone(c)&&this.containsPezzoNero(c);
	}
        
	public boolean containsDamone(Cell c) throws CellaVuotaException{
		if(this.pedine.get(c)==null)
                   throw new CellaVuotaException();
               return this.pedine.get(c).isDamone();
	}

    @Override
    public Iterator<Cell> iterator() {
        return this.pedine.keySet().iterator();
    }
    public void load(File f){
        this.pedine.clear();
            try {
                Scanner s=new Scanner(new FileReader(f));
                s.nextLine();
                while(s.hasNext()){
                    String r=s.nextLine();
                    int x=Integer.parseInt(r.split(":")[0]);
                    int y=Integer.parseInt(r.split(":")[1]);
                    Cell c=new Cell(x,y);
                    char p=r.split(":")[2].charAt(0);
                    if(p=='n')
                        this.insertPedinaNera(c);
                    if(p=='N'){
                        this.insertPedinaNera(c);
                        this.promuoviPedina(c);
                    }
                    if(p=='b')
                        this.insertPedinaBianca(c);
                    if(p=='B'){
                        this.insertPedinaBianca(c);
                        this.promuoviPedina(c);
                    }
                    
                }
            } catch (    FileNotFoundException | CellaInesistenteException | CellaNonVuotaException | CellaVuotaException ex) {
                System.out.println("problema caricamento tavola");
            }
    }
    public void save(File f) {
        
        PrintWriter pw=null;
        if(!f.exists())
            try {
                if(!f.createNewFile())
                    System.out.println("il file non può essere creato");
        } catch (IOException ex) {
            
        }
                
         String r1="";
         try {
                Scanner s=new Scanner(f);
                r1=s.nextLine();
                s.close();
                pw = new PrintWriter(f);
                pw.println(r1);
            }catch(FileNotFoundException e){
                System.out.println("boh");
            }
            try {
                for(Cell c:this){
                    if(this.getPedina(c).isDamone())
                        pw.println(c.getX()+":"+c.getY()+(":"+this.getPedina(c).getColor()).toUpperCase());
                    else
                        pw.println(c.getX()+":"+c.getY()+(":"+this.getPedina(c).getColor()));
                }
                 pw.close();
            } catch (  CellaVuotaException ex) {
                System.out.println("problema salvataggio tavola");
            }
           
    }
    public int getNPedine(){
       return  this.pedine.size();
    }
        @Override
    public boolean equals(Object o){
        if(o instanceof Tavola ){
            Tavola t=(Tavola) o;
            if(t.pedine.keySet().size()==this.pedine.keySet().size()){
                for(Cell c:this)
                    try {
                        if(!this.getPedina(c).equals(t.getPedina(c)))
                            return false;
                    } catch (CellaVuotaException ex) {
                       return false;
                    }
                return true;
            }else
                return false;
            
            }else
                return false;
    }
}

