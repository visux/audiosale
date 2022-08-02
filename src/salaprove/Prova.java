/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;
import java.math.*;
import java.util.*;
/**
 *
 * @author Ge
 */
public class Prova implements Iprova,IObserver {

    private String _userId;
    private float _durata;
    private float _tariffa;
    private List _noleggi;
    private List _observerFineProva;

     public Prova(){

     }

    public Prova(String userId,float durata,float tariffa){
        this._durata=durata;
        this._tariffa=tariffa;
        this._userId=userId;
        _noleggi=new ArrayList<Noleggio>();
        _observerFineProva=new ArrayList<ObserverFineProva>();

    }


    public void setUserId(String userId){
        _userId=userId;
    }

    public String getUserId(){
        return _userId;
    }

    public void setDurata(float Durata){
        _durata=Durata;
    }
    public float getDurata(){
        return _durata;
    }
    public void setTariffa(float Tariffa){
        _tariffa=Tariffa;
    }
    public float getTariffa(){
        return _tariffa;
    }

    public int add(Noleggio nol)throws Exception{
        int ris=0;
        Iterator i=_noleggi.iterator();
        int pos=0;
        while(i.hasNext()){
            pos++;
            if(i.equals(null)){
                _noleggi.add(nol);
                ris=pos;
                return ris;
            }
        }

        throw new Exception ("Non c'è spazio disponibile");

    }

    public boolean remove(int pos,Noleggio nol){
        boolean ris=false;
        if(_noleggi.get(pos).equals(nol)){
            _noleggi.remove(pos);
            ris=true;
        }
        return ris;
    }

    public float calcolaCosto(){
        float ris=(this._durata*this._tariffa);
        return ris;
    }

        public int attach(ObserverFineProva ob){
            _observerFineProva.add(ob);
            int pos=_observerFineProva.indexOf(ob);
            return pos;

        }
}
