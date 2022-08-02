/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;
import java.util.*;
/**
 *
 * @author Ge
 */
public class Sala implements IPersistant,IComponent{

    private String _nome;
    private String _piano;
    private double _mq;
    private List _prenotazioni;

   public Sala(){
       super();
    }

    public Sala(String nome,String piano,double mq){
        this._nome=nome;
        this._mq=mq;
        this._piano=piano;
        _prenotazioni=new ArrayList<Prenotazione>();
    }
    /*
     * Controlla che la sala in questo momento è libera
     * bisogna fare anche un is Free che controlli la sala in orario specificato
     */
    private boolean isFree()throws Exception{
        boolean res=false;
        return res;
    }

    private boolean isFree(String ora,String data)throws Exception{
        boolean res=false;
        return res;
    }

    public Prenotazione[] get(){
        Prenotazione[] result=(Prenotazione[]) _prenotazioni.toArray();
        return result;
    }

    public void add(Prenotazione p)throws Exception{
        if(isFree()==true){
           _prenotazioni.add(p);
        }
        throw new Exception("Sala non libera");
    }
   public boolean set(String[] argm) throws Exception{
       boolean result=false;

       switch(argm.length){

           case 1: _nome=argm[0];result=true; return result;
           case 2: _nome=argm[0];_mq=Double.parseDouble(argm[1]);result=true;return result;
           case 3: _nome=argm[0];_mq=Double.parseDouble(argm[1]);_piano=argm[2];result=true;return result;
           default: throw new Exception("Arguments null");
       }
  }

   public boolean Remove(Prenotazione p){
       boolean res=false;
       return res;
   }

   public void Save() throws Exception{


   }


   public void Load()throws Exception{

   }

    public void add(Object toAdd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean Remove(Object ToRem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
