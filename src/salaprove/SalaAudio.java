/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;
import java.util.*;
import java.math.*;
import java.sql.*;
import db.*;
/**
 *
 * @author Ge
 */
public class SalaAudio {
  // variabili private
    private String _nome;
    private String DBNAME="AUDIO";
    private String _indirizzo;
    private String _Orario;
    private LinkedList _Sale;
    private LinkedList _Corsi;
    private IConnDBase cn;
    //costruttore
    //deve prendere dal database le sue variabili nome indirizzo e orario
    public SalaAudio(){
       try{
           cn.getConnection();
           //inizializzare campi privati da db
           _Sale=new LinkedList<Sala>();
           _Corsi=new LinkedList<Corso>();//inizzializzati anche da db
       }catch(Exception e){}
    }
    
    
    public boolean registraSala(Sala s){
        boolean ris=false;
        int count=_Sale.size();
        for(int i=0;i<count;i++){
            if(_Sale.get(i).equals(null)){
                _Sale.add(i, s);
                ris=true;
            }
        }//for
        return ris;
    } 
    
    public boolean eliminaSala(Sala s){
        boolean ris=false;
        int dim=_Sale.size();
        for(int i=0;i<dim;i++){
            if(_Sale.get(i).equals(s)){
                _Sale.remove(i);
                ris=true;
            }
        }
        return ris;
    }
    
    public Sala ricercaSala(Sala s){
        Sala ris=null;
        int dim=_Sale.size();
        for(int i=0;i<dim;i++){
        }
        return ris;
        }
    
}//class
