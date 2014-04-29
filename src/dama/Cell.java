/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import java.util.ArrayList;


public class Cell {//implementa un modo per mettere il massimo variabile il massimo
                    //basta estendere una classe che implementa qif(y*8+x>63)  throw new CellaInesistenteException();
               
	private final int x;                                        
	private final int y;
	
	public Cell(int x,int y) throws CellaInesistenteException{
            if((x<0||x>7)||(y<0||y>7))
                throw new CellaInesistenteException();
            this.x=x;
            this.y=y; 
	}
       
        private char getColor(){
            if(y%2==0)
                if((y*8+x)%2==0)
                    return ' ';
                else
                    return 'x';
            else
                if((y*8+x)%2!=0)
                    return ' ';
                else
                    return 'x';
                
            
        }
        
        public Cell(Cell c){
           
            this.x=c.x;
            this.y=c.y;
        }
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
         public boolean isNera(){
            return getColor()==' ';
        }
        
	public boolean isBianca(){
            return getColor()=='x';
	}
        @Override
        public boolean equals(Object o){
            if(o instanceof Cell ){
                Cell c=(Cell) o;
                return c.x==this.x&&c.y==this.y;
            }
            else
                return false;
        }

    @Override
    public int hashCode() {
        return y*8+x;
    }
    
    public ArrayList<Cell> celleAdiacentiAlte(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
            a.add(new Cell (x-1, y-1));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(x+1, y-1));   
            }catch(CellaInesistenteException e){}
            
            return a;
        }      
       public ArrayList<Cell> celleAdiacentiBasse(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
            a.add(new Cell(x+1, y+1));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(x-1, y+1));
            }catch(CellaInesistenteException e){}
            return a;
        }
       public ArrayList<Cell> celleVicineBasse(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
            a.add(new Cell(x+2, y+2));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(x-2, y+2));
            }catch(CellaInesistenteException e){}
            return a;
        }
        public ArrayList<Cell> celleVicineAlte(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
            a.add(new Cell(x-2, y-2));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(x+2, y-2));
            }catch(CellaInesistenteException e){}
            return a;
        }
        public ArrayList<Cell> celleVicine(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
                Cell c=new Cell(x+2, y+2);
                a.add(c);
            }catch(CellaInesistenteException e){
                
            }
            try{
             Cell c=new Cell(x-2, y+2);
                a.add(c);
            }catch(CellaInesistenteException e){
            }
            try{
             Cell c=new Cell(x+2, y-2);
                a.add(c);
            }catch(CellaInesistenteException e){
            }
            try{
            Cell c=new Cell(x-2, y-2);
                a.add(c);
            }catch(CellaInesistenteException e){
            }
            return a;
        }
        public ArrayList<Cell> celleAdiacenti(){
            ArrayList <Cell> a=new ArrayList <Cell>();
            try{
            a.add(new Cell(this.getX()+1, this.getY()+1));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(this.getX()-1, this.getY()-1));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(this.getX()-1, this.getY()+1));
            }catch(CellaInesistenteException e){}
            try{
            a.add(new Cell(this.getX()+1, this.getY()-1));
            }catch(CellaInesistenteException e){}
            return a;
        }
        public boolean centro(){
            return (x>0&&x<7&&y>0&&y<7);
               
        }
        public Cell middleCell(Cell c){
            try{
            int mx;
            if(this.x<c.x)
                mx=this.x+1;
            else
                mx=c.x+1;
            int my;
            if(this.y<c.y)
                my=this.y+1;
            else
                my=c.y+1;
            return new Cell(mx, my);
            }catch(CellaInesistenteException e){ return null;}
        }
}
