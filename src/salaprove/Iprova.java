/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

/**
 *
 * @author Ge
 */
public interface Iprova {

    /*
     * Metodi pe rmanipolare l'oggetto prova

     */

    public void setUserId(String userId);

    public void setDurata(float durata);

    public void setTariffa(float tariffa);

    public String getUserId();

    public float getDurata();

    public float getTariffa();

    public int add(Noleggio nol)throws Exception;

    public boolean remove(int pos,Noleggio nol);

    public float calcolaCosto();
}
