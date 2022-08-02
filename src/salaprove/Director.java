/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

/**
 *
 * @author Ge
 */
public class Director {

    private AbstractBuilder _build=null;
    private Director _instance=null;

    private  Director() {
        this._build=new AbstractBuilder() {

            @Override
            public Sala CreateSala() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

    }

    public Director getInstance(){
        if(_instance==null){
            _instance=new Director();
        }
        return _instance;
    }


    /*
     * @method  CreateProduct()
     * @return   void
     * Questo metodo gestisce la creazione dell'albero Per il patter composite
     * Vanno inseriti tutti i passi per la creazione delle classi che ci servono siano attive
     */

    public void CreateSaleAudio(){
        
    }
}
