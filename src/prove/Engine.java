/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prove;


import javax.swing.JOptionPane;
import albero.GenericArbitroTree;
import albero.GenericTavolaTree;
import albero.Node;
import dama.*;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Engine {
	private Arbitro ar;
	private boolean mangiato;	
	private int turno;
        private static final int pedina=10;
        private static final int damone=30;
        private  ArrayList <Tavola> history;
        private int d=0;
        private ArrayList<Arbitro> pm;
        /*
        private int user;//mapperemo in modo diverso la gui
	*/
	public Engine(int t){
            ar=new Arbitro(t);
            turno=t;
            mangiato=false;
            history=new ArrayList<>();
            pm=new ArrayList<>();
	}
        public Engine(Engine e){//da sisitemare pm
            ar=new Arbitro(e.ar);
            turno=e.turno;
            mangiato=e.mangiato;
            this.history=new ArrayList<>();
            this.history.addAll(e.history);
            pm=new ArrayList<>();
            pm.addAll(e.pm);
	}
        /*
	public boolean isUserColorWhite(){
		if(user==1)
			return false;
		else
			return true;
	}
	public char getUserColor(){
		if(user==1)
			return 'n';
		else
			return 'b';
	}
        */
	
	
	public int receivedinput(Tavola t,Cell ce) {//0 mezza mossa 1 mossa intera //stampa tavolata
		
		//boolean f=true;
		
                if(!(ar.inseritaSource())){
                        //gestione stallo(se esiste il metodo c'è)
                        if(this.turnoBianco())
                            this.ar=new Arbitro(Arbitro.turnoBianco);
                        if(this.turnoNero())
                            this.ar=new Arbitro(Arbitro.turnoNero);
                        //moveSource[0]==-1
                        //moveSource[0]=i;
                        //moveSource[1]=j;                        
                        ar.setSource(ce);
                        //f=false;
                        if(!ar.controlSource(t)){
                                ar.resettaMossa();
                                JOptionPane.showMessageDialog(null,"casella sorgente non valida");
                                return 2;
                        }else
                                return 0;//seleziona la pedina col giallo
                }
                if(mangiato){//sono in una fase della magiata
                    ar.setDestination(ce);
                    /*
                    for(Arbitro c:pm)
                        if(!c.getDestination().equals(ar.getDestination())){//deve essere una mangiata 
                                JOptionPane.showMessageDialog(null,"obbligatorio proseguire con la mangiata");
                                ar.resettaDestinazione();
                                r=0;
                                }
                    */
                    boolean b=false;
                    for(Arbitro c:pm)//pedine con mangiata massima
                                if(c!=null)
                                    if(ar.getDestination().equals(c.getDestination())){
                                        b=true;
                                        
                                     }
                            if(pm.get(0)!=null&&!b){
                                JOptionPane.showMessageDialog(null,"obbligatorio proseguire con la mangiata");									
                                //t.setPedina(' ', this.mangiabili(t));//x soffiata
                                ar.resettaMossa();
                                return 0;
                            }
                    
                }
                if(ar.inseritaSource()&&!ar.inseritaDestinazione()){
                        //if(ar.controlVictory(t))
                        //JOptionPane.showMessageDialog(null,ar.getTurnoToString()+" ha vinto");
                        //moveSource[0]!=-1
                        //t.setText(t.getText(moveSource[0], moveSource[1]), i, j);
                        //t.setText(' ',moveSource[0], moveSource[1]);
                        //resettaMossa();
                        //aggiorna();
                        //moveDestination[0]=i;
                        //moveDestination[1]=j;
                    
                        if(ar.getSource().getX()==1&&ar.getSource().getY()==5)
                                 System.out.print("");//per debug
                        //boolean c=false;
                        //if(this.mangiabili(t)!=null)//NON CANCELLARE
                                //c=true;	//NON CANCELLARE
                        ar.setDestination(ce);
                        //controllo che non sia una fase della magniata
                          Arbitro temp=new Arbitro(ar);
                          temp.setDestination(ar.getSource());
                          Node root=new Node(temp,0);//deve essere 0;            
                          this.creaAlberoMangiatePossibili(t, root);
                          
                            if(!ar.control(t)){
                                JOptionPane.showMessageDialog(null,"mossa non valida1");
                                ar.resettaMossa();
                                return 2;
                            }//mangiabilip se non ci sono da array con un elemento null
                            boolean b=false;
                    
                        if(t.getNPedine()==6)
                            System.out.println("");
                    
                            for(Arbitro c:this.mangiabiliP(t))//pedine con mangiata massima
                                if(c!=null)
                                    if(ar.getSource().equals(c.getSource())&&ar.getDestination().equals(c.getDestination())){
                                        b=true;
                                        
                                     }
                            if(this.mangiabiliP(t).get(0)!=null&&!b){
                                JOptionPane.showMessageDialog(null,"obbligatorio mangiare");									
                                //t.setPedina(' ', this.mangiabili(t));//x soffiata
                                ar.resettaMossa();
                                return 2;
                            }
                            	
                        
                }

                						
                if(ar.inseritaSource()&&ar.inseritaDestinazione()){
                        this.history.add(new Tavola(t));
                        
                        int r;
                        if(ar.controlMangiata(t)){
                            r=gestisciMangiata(t,this.ar);
                        }else{						
                                t.swapCelle(ar.getSource(), ar.getDestination());//t.setText(t.getText(moveSource[0], moveSource[1]), moveDestination[0], moveDestination[1]);
                                if(avvenutaPromozione(ar,t))
                                        try {
                                            t.promuoviPedina(cellaPedinaPromossa(ar,t));
                                } catch (CellaVuotaException ex) {
                                    System.out.println("non trovo pedina da promouvere"); 
                                }
                                //turno++
                                ar.resettaMossa();
                                r=1;
                        }
    //                    if(ar.controlStallo(t)&&!ar.controlVictory(t))
                      //      JOptionPane.showMessageDialog(null, "patta");
                        
                        
                        
                        //if(ar.controlVictory(t))
                         //gestione vittoria  
                       
                        return r;
                }
                return 9;		


        }
	public boolean avvenutaPromozione(Arbitro ar,Tavola t){//metti nell'arbitro
		return cellaPedinaPromossa(ar,t)!=null;
	}
	public Cell cellaPedinaPromossa(Arbitro ar,Tavola t){try {
            //metti nell'arbitro
            if(t.getPedina(ar.getDestination()).isDamone())
                return null;
            } catch (CellaVuotaException ex) {    }
                if(this.turnoBianco())
                    if(ar.getDestination().getY()==0)
                        return ar.getDestination();
            
			if(ar.turnoNero())
				if(ar.getDestination().getY()==7)
					return ar.getDestination();
		
		
		return null;
	}
	public Cell mangiabili(Tavola t) {//
		Arbitro arr=new Arbitro(ar);
			for(Cell c:t){
                            try{
				if(arr.isMyPedina(t.getPedina(c))){//è del mio turno					
					if(getSimulaMangiata(t,c)!=null)
						return this.getSimulaMangiata(t,c);
				}
                            }catch(IllegalArgumentException e){} catch (CellaVuotaException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        }
		return null;
					
	}
        public ArrayList<Arbitro> mangiabiliP(Tavola t) {//torna tutte le celle che possono mangiare
		Arbitro arr=null;
                if(this.turnoBianco())
                    arr=new Arbitro(Arbitro.turnoBianco);
                if(this.turnoNero())
                    arr=new Arbitro(Arbitro.turnoNero);
                ArrayList <Node> mp=new ArrayList<>();//mangiate possibili
			for(Cell c:t){//guarda tutte le celle e trova le mangiate massime per ogni pedina
                            try{
				if(arr.isMyPedina(t.getPedina(c))){//è del mio turno					
                                    Arbitro temp=new Arbitro(arr);
                                    temp.setSource(c);
                                    temp.setDestination(c);
                                    Node root=new Node(temp,0);//deve essere 0;            
                                    this.creaAlberoMangiatePossibili(t, root);                                    
                                    if(!root.getChildren().isEmpty()){//può mangiare
                                        ArrayList<Node>a=this.trovaMangiataMassimaPerUnaPedina(t, root);
                                        a=this.Qualcosa(a);
                                        mp.addAll(a);
                                    }
                                    
                                    
				}
                            }catch(IllegalArgumentException e){} catch (CellaVuotaException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            //troviamo le magiate migliori
                           
                            
                        }
                         Node p=new Node(null,0);
                            for(Node n:mp)
                                p.addFiglio(n);
                            ArrayList<Arbitro> mm=new ArrayList<>();
                             ArrayList<Node>a=this.trovaMangiataMassimaPerUnaPedina(t, p);
                            
                            for(Node n:this.Qualcosa(a)){
                                GenericArbitroTree g=new GenericArbitroTree(n);
                                mm.add(g.getArbitro());
                            }
		return mm;
					
	}
	public ArrayList<Node> trovaMangiataMassimaPerUnaPedina(Tavola t,Node root) {//torna un array di nodi con massima mangiata
            ArrayList<Node> r=new ArrayList();            
            int v=0;
            int max=0;
            Node nMax=root;//nodo con mangiata massima
           
            for(Node n:root.getChildren()){//candidati alle mangiate migliori                                 
                   if(max<n.getId()){
                       max=n.getId();
                       nMax=n;
                   }
               }
            boolean pu=true;
            for(Node n:root.getChildren()){
                GenericArbitroTree g=new GenericArbitroTree(n);                
                
                    if(n.getId()==max){
                        try {
                        if(t.getPedina(g.getArbitro().getSource().middleCell( g.getArbitro().getDestination())).isDamone()){
                            v++;//conto i nodi con punteggio massimo
                            r.add(n);//salvo i nodi con punteggio massimo
                            pu=false;
                        }
                            
                        
                } catch (CellaVuotaException ex) {
                   
                }
            }
            }
            if(pu){
                
                for(Node n:root.getChildren())
                    if(n.getId()==max){
                        ArrayList<Node>a=this.trovaMangiataMassimaPerUnaPedina(t,n);
                        r.addAll(a);
                    }
                if(root.getChildren().isEmpty())
                    r.add(root);
            }
            return r;
        }
        
        public ArrayList<Node> Qualcosa(ArrayList<Node> p){//torna il padre del nodo più basso
            ArrayList a=new ArrayList<>();
            int min=3000;
            for(Node n:p){
                if(n.getNodeDepth()<min)
                    min=n.getNodeDepth();
            }
            for(Node n:p){
                if(min==n.getNodeDepth()){
                    Node root=n;
                    while(root.getNodeDepth()>1)
                        root=root.getFatherN();
                    a.add(root);
                }
            }
            return a;
        }
        
        public ArrayList<Node> QualcosaF(ArrayList<Node> p){//torna il nodo più basso che torvo
            ArrayList a=new ArrayList<>();
            int min=3000;
            for(Node n:p){
                if(n.getNodeDepth()<min)
                    min=n.getNodeDepth();
            }
            for(Node n:p){
                if(min==n.getNodeDepth()){
                    Node root=n;                    
                    a.add(root);
                }
            }
            return a;
        }
                    
            
            
            /*
            int k=0;
           for(Node n:root.getChildren())//quanti candidati ci sono
               if(n.getId()==max)
                   k++;
           if(k==0){
               return r;
           }
           if(k==1){
                r.add(new GenericArbitroTree(nMax).getArbitro().getDestination());
                return r;
           }
          
           
            for(Node n:root.getChildren()){
                    GenericArbitroTree g2=new GenericArbitroTree(n);
                    try {
                        if(t.getPedina(g2.getArbitro().mangiata(t)).isDamone()&&n.getId()==max){//da mettere a posto
                            pu=true;
                            //r.add(g2.getArbitro().getDestination());
                            r.add(n);
                        }
                    } catch (CellaVuotaException ex) {System.out.println("problema in massima mangiata");}
                }
            if(!pu){
                for(Node n:root.getChildren()){
                    if(n.getId()==max)
                        //r.add(new GenericArbitroTree(nMax).getArbitro().getDestination());
                        r.add(nMax);
                }        
            }
            */
           
        public Cell getSimulaMangiata(Tavola t,Cell s){//true se la mossa ÃƒÂ¨ una mangiata e si puÃƒÂ² fare
		Arbitro temp=new Arbitro(ar);
                temp.setSource(s);
		//posso provare a mangiare
		Cell b=null;
                for(Cell c:s.celleVicine()){
                    temp.setDestination(c);
                    if(temp.controlMangiata(t))
                        return temp.mangiante(t);
                }
		return b;
	}

	private GenericArbitroTree creaAlberoMangiatePossibili(Tavola t,Node root) {//per ora torna una pedina qualsiasi ache può mangiare            
		GenericArbitroTree g=new GenericArbitroTree(root);
                Cell d=g.getArbitro().getDestination();                 //se ci sono mette nell'arbitro l'ultima mangiata
                Arbitro temp=new Arbitro(g.getArbitro());
                temp.setSource(d);
                for(Cell c:d.celleVicine()){//se non ci sno celle che possono mangiare torna arbitro equivalente;
                    
                    temp.setDestination(c);
                    if(temp.controlMangiata(t)){
                        
                        int v=0;
                         try {
                            if(t.getPedina(temp.mangiata(t)).isDamone())
                            v=Engine.damone;
                            else
                         v=Engine.pedina;
                            } catch (CellaVuotaException ex) { }
                         Node child=new Node(new Arbitro(temp),v);
                        try{
                        root.addFiglio(child);
                        }catch(NullPointerException e){System.out.println("problema");}
                        Tavola t2=new Tavola(t);
                       
                        t2.removePezzo(temp.mangiata(t2));
                        
                        
                        t2.swapCelle(temp.getSource(), temp.getDestination());//l'arbitro si collega alle celle di t e non t2
                       // temp.setSource(temp.getDestination());
                        
                        creaAlberoMangiatePossibili(t2,child); 
                       
                
                
                
		
	}
                    
                       
                }
                
               
               
                int max=0;
                if(!root.getChildren().isEmpty()){
                        for(Node a:root.getChildren()){
                           if(a.getId()>max)
                               max=a.getId();
                       }
                        root.setId(max+root.getId());
                        
                }
               return g;
                
               
        }
         
	private int gestisciMangiata(Tavola t,Arbitro ar){
            
            Cell c=ar.mangiata(t);
            
                t.removePezzo(c);//togli le pedine		
           
            t.swapCelle(ar.getSource(), ar.getDestination());
            //t.setText(t.getText(ar.getSource()), ar.getDestination());//t.setText(t.getText(moveSource[0], moveSource[1]), moveDestination[0], moveDestination[1]);
            //t.setText(' ',ar.getSource());
            //System.out.print("mangiaato");       
            Arbitro temp=new Arbitro(ar);
            temp.setSource(ar.getDestination());
            Node root=new Node(temp,0);//deve essere 0;            
            this.creaAlberoMangiatePossibili(t, root);
            
            
            if(!root.getChildren().isEmpty()){//la pedina può mangiare ancora
                    pm.clear();
                    ar.setSource(ar.getDestination());
                    ar.resettaDestinazione();
                    for(Node n:Qualcosa(this.trovaMangiataMassimaPerUnaPedina(t, root)))
                        this.pm.add(new GenericArbitroTree(n).getArbitro());
                    
                    mangiato=true;
                    return 0;
            }else{
                    if(avvenutaPromozione(ar,t))
                            try {
                                t.promuoviPedina(cellaPedinaPromossa(ar,t));//cambia pedina incriminata in damone
                    } catch (CellaVuotaException ex) { }//cambia pedina incriminata in damone
                    
                    ar.resettaMossa();
                    mangiato=false;
                    return 1;
            }
            
	}
		
		
	 public boolean turnoBianco(){
		return turno%2==0;		
	}
	public boolean turnoNero(){
		return turno%2==1;
        }
	public void nextTurn(){
            turno++;            
        }
	public String getTurnoToString(){
		
		if(turno%2==0)
			return "turno del bianco";
		else
			return "turno del nero";
	}
	public Arbitro getArbitro(){
            return this.ar;
        }
        private int getPunteggio(Pedina p){
            if(p.isDamone())
                return Engine.damone;
            else
                return Engine.pedina;
        }
        public Tavola mossaPc(Tavola t){
            try {
                if(t.getPedina(new Cell(4,6))!=null)
                    System.out.println("");
            } catch (CellaInesistenteException | CellaVuotaException ex) {} 

            this.history.add(t);
            Node root=new Node(t,0);
            this.creaAlberoDelleTavole(root, 2);
            Tavola m=this.calcolaMossa(root);
            /*
            if(controlPatta(m)){
                System.out.println("");
                for(Node n:root.getChildren())
                    if(n.getId()==-3000)
                        System.out.println("");
            }
            */
            turno=1;
           
            
            
            return m;
            
        }
        public Node creaAlberoDelleTavole(Node root,int depth){
            //da mettere nelle chiamate per rendre il progeamma più veloce
            if(depth==0)
                return root;
            GenericTavolaTree g=new GenericTavolaTree(root);
            Tavola t=g.getTavola();
            for(Cell s:t){
                try {
                    if(this.isMyPedina(t.getPedina(s))){
                        for(Arbitro a:this.mangiabiliP(t))
                            try{//tutte le mangiate possibili
                                if(a.getSource().equals(s)){
                                    //buttalo fuori prima
                                    ArrayList <Node> b= new ArrayList<>();
                                    b.add(new Node(a,0));                                
                                    Node n=qualcosaltro(root,b,t);
                                    this.nextTurn();
                                    this.creaAlberoDelleTavole(n,depth-1);
                                    this.nextTurn();
                                }
                            }catch(NullPointerException e){
                                for(Cell d:s.celleAdiacenti()){//mossa normale
                                    Arbitro r=new Arbitro(this.turno);
                                    r.setSource(s);
                                    r.setDestination(d);
                                    if(r.control(t)){
                                        Tavola t2=new Tavola(t);
                                        t2.swapCelle(s, d);
                                        if(this.avvenutaPromozione(r,t2))
                                            t2.promuoviPedina(this.cellaPedinaPromossa(r,t2));
                                        Node n=new Node(t2,this.punteggioTavola(t2));
                                        root.addFiglio(n);
                                       
                                        this.nextTurn();                                        
                                        this.creaAlberoDelleTavole(n,depth-1);
                                        this.nextTurn();
                                    }
                                }
                            
                            }
                    }
                } catch (CellaVuotaException ex) {
                    
                }
            }
            return root;
        }
        public boolean isMyPedina(Pedina p) throws CellaVuotaException{
            if(p!=null){
                return (this.turnoBianco()&&p.isBianca())||(this.turnoNero()&&p.isNera());
            }else throw new IllegalArgumentException();
        }
//crea un albero delle tavole in caso di mangiata
        private Node qualcosaltro(Node root,ArrayList<Node> mangiate,Tavola t){
            for(Node h:mangiate){
                Arbitro a=new GenericArbitroTree(h).getArbitro();
                Tavola t2=new Tavola(t);
                t2.swapCelle(a.getDestination(), a.getSource());
                t2.removePezzo(a.mangiata(t));
                Node n=new Node(a,0);
                this.creaAlberoMangiatePossibili(t2,n);
                //si può procedere conm la mangiata
                if(!n.getChildren().isEmpty()){
                   ArrayList<Node>b=this.trovaMangiataMassimaPerUnaPedina(t2,n);
                    ArrayList <Node> c= this.Qualcosa(b);
                    return qualcosaltro(root,c,t2);
                }else{//mangiata finita
                    if(this.avvenutaPromozione(a,t2))
                        try {
                            t2.promuoviPedina(this.cellaPedinaPromossa(a,t2));
                    } catch (CellaVuotaException ex) {}
                    Node n2=new Node(t2,this.punteggioTavola(t2));
                    root.addFiglio(n2);
                    return n2;
                }
            }
            return null;
            
            
        }
        
        public int punteggioTavola(Tavola t){//il turno deve essere giusto
            int r=0;
            for(Cell c:t){
                try {
                    if(this.turnoNero()){
                        if(this.isMyPedina(t.getPedina(c))){
                            if(c.centro())
                                r+=5;
                            else
                                r+=3;
                            if(t.getPedina(c).isDamone())
                                r+=30;
                            else
                                r+=10;

                        }else{
                            //conta se mangia pedine
                            if(t.getPedina(c).isDamone())
                                r-=60;
                            else
                                r-=20;
                                
                            
                        }
                        if(controlPatta(t))
                            r=-3000;
                    }

                    } catch (CellaVuotaException ex) {

                    }
                if(this.turnoBianco())
                    try {
                        if(this.isMyPedina(t.getPedina(c))){
                          if(t.getPedina(c).isDamone())
                              r-=30;
                          else
                              r-=10;
                           if(c.centro())
                                r-=5;
                            else
                                r-=3;
                        }else{
                            //conta se mangia pedine
                            if(t.getPedina(c).isDamone())
                                r+=120;
                            else
                                r+=40;
                                
                            
                        }
                            
                } catch (CellaVuotaException ex) {}
            }
            return r;
        }
        public boolean controlVictory(Tavola t){//controlla se this ha vinto
           
            for(Cell s:t)
                try{
                if(!this.isMyPedina(t.getPedina(s)))
                            return false;
                }catch (CellaVuotaException e){}
               
            return true;
        }
        public Tavola calcolaMossa(Node root){
            Node s=root.getChildren().get(0);
            //int mp=root1.foglieN().size();
            int mp=root.getChildren().size();
            
            Stack <ArrayList<Node>> mosse=new Stack<>();
                   
            while(mp>0){
                int nMin=3000;
                ArrayList <Node> mosseMiglioritemp=new ArrayList<>();  
                //trova il punteggio della mossa piu vantaggiosa per l'altro giocatore
                for(Node n:root.foglieN()){
                    if(nMin>n.getId())
                        nMin=n.getId();
                }
                int k=0;
                for(Node n:root.foglieN()){
                    if(n.getId()==nMin){
                        //me le segno in caso siano le meno peggio
                        mosseMiglioritemp.add(new Node(n.getFatherN()));
                        n.setId(3000);//tolgo le mosse con tale punteggio
                        k++;
                        //non considero più le mosse peggiori
                        for(Node h:n.getFatherN().getChildren())
                            h.setId(3000);
                    }
                }
                mosse.push(mosseMiglioritemp);
                mp-=k; 
            }
            while(!mosse.empty()){
            ArrayList <Node> mosseMiglioritemp=mosse.pop();
            Node max=new Node(null,-3000);           
            boolean b=false;
            //cerco la mossa migliore per me tra le meno peggio
            for(Node n:mosseMiglioritemp)
                if(n.getId()>max.getId()){
                    Tavola t=new GenericTavolaTree(n).getTavola();
                    if(!this.controlPattaBlocco(t)){
                        max=n;
                        s=n;
                        b=true;
                } 
                }
            for(Node n:root.getChildren())// cerca possibile mossa vincente
                if(n.getChildren().isEmpty()){
                    if(this.controlVictory(new GenericTavolaTree(n).getTavola())){
                        s=n; 
                        b=true;
                    }
                }
            if(b)
                break;
           
            }
            return new GenericTavolaTree(s).getTavola();
        }
        public boolean historyIsEmpty(){
            return this.history.isEmpty();
        }
        public Tavola lastMove(){
            mangiato=false;
            return this.history.remove(this.history.size()-1);
            
            
            
        }
        //mosse possibili avendo la sorgente
        public ArrayList<Cell> getPossiblyMoves(Tavola t,Cell s){
            ArrayList <Cell> r=new ArrayList<>();
            try{
                ArrayList <Arbitro> p;
                if(mangiato)
                    p=pm;
                else
                    p=this.mangiabiliP(t);
                for(Arbitro a:p)
                    if(a.getSource().equals(s))
                          r.add(a.getDestination());
                
            }catch(NullPointerException e){
                for(Cell c:s.celleAdiacenti()){
                    Arbitro a=new Arbitro(turno);
                    a.setSource(s);
                    a.setDestination(c);
                    if(a.control(t))
                        r.add(c);
                }
                    
                }   
            return r;
        }
        public boolean controlPattaKV(Tavola t){//t è la mossa che va controllata     
            if(controlVictory(t)){
                return false;
            }
            this.history.add(t);
            int  np=this.history.get(this.history.size()-1).getNPedine();
            for(int i=this.history.size()-1;i>=0;i--){
                if(this.history.get(i).getNPedine()>np)
                        break;
                int k=0;                
                for(int j=this.history.size()-1;j>=0;j--){
                    if(this.history.get(j).getNPedine()>np)
                        break;
                    if(this.history.get(i).equals(this.history.get(j)))
                        k++;                    
                }
                if(k>=4)//+1 rispetto a quello che deve essere
                    return true;
                
            }
           return false;
        }        
        //t è la mossa che va controllata    
        public boolean controlPattaBlocco(Tavola t){ 
            if(controlVictory(t)){
                return false;
            }            
            
            this.nextTurn();
           
            for(Cell c:t){
                try {
                    Pedina p=t.getPedina(c);
                    if(this.isMyPedina(p))
                        if(!this.getPossiblyMoves(t, c).isEmpty())
                            return false;
                } catch (CellaVuotaException ex) {}
                
            }
            
            this.nextTurn();
            return true;
        }
        public boolean controlPatta(Tavola t){//t è la mossa che va controllata     
            
            return this.controlPattaKV(t)||this.controlPattaBlocco(t);
        }
	}	
				

