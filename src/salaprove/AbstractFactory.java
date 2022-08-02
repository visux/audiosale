/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;
import db.*;
/**
 *
 * @author Ge
 */
public abstract  class AbstractFactory {

   
 

    public  AbstractFactory() {
       
       
    }

    public abstract Utente createUtente(String id);


    public abstract Gruppo createGroup(String id);


    public abstract Prova createProva();


    
}
/**
 * 
 * @author Ge
 * Classe factory annidata utente
 */
     class utenteFactory  extends AbstractFactory{

       
        private utenteFactory instance=null;
        
        private utenteFactory(){
            
        }

        public utenteFactory getInstance(){
            if(instance==null){
                instance=new utenteFactory();
            }
            return instance;
        }
       

    @Override
    public Utente createUtente(String id) {

        Utente res=null;
        ConnessioneUtente conn=null;
        try{
           String[] UtFields=conn.getInstance().readStudente(id);
           res=new Utente();
           //da settare i valori presi dal db;
        }catch(Exception e){e.printStackTrace();}
       return res;
    }

    @Override
    public Gruppo createGroup(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Prova createProva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   


}//utenteFactory

     /**
      *
      * Classe factory annidata per i gruppi
      * @author Ge
      */
     class gruppoFactory extends AbstractFactory{
        private gruppoFactory instance=null;

        private gruppoFactory(){
         
        }

        public gruppoFactory getInstance(){
            if(instance==null){
                instance=new gruppoFactory();
            }
            return instance;
        }

       

    @Override
    public Utente createUtente(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Gruppo createGroup(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Prova createProva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
 }//gruppFactory
/**
 *
 * Factory Prova
 * @author Ge
 */
    class provaFactory extends AbstractFactory {

        private String _userId;
        private provaFactory instance=null;

        private provaFactory(){
         
        }

        public provaFactory getInstance(){
            if(instance==null){
                instance=new provaFactory();
            }
            return instance;
        }
       

    @Override
    public Utente createUtente(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Gruppo createGroup(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Prova createProva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    }

