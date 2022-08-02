/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

import Gui.FrPopupAutomatico;
import db.SINGLETON;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import salaprove.Prova;


public class Prenotazione {

    public String data        = "";
    public String ora         = "";
    public String cf_utente   = "";
    public int codice         = -1;
    public int    num         = -1;
    public int    tipo        = -1;
    public BigDecimal start   = new BigDecimal("0");
    public BigDecimal fine    = new BigDecimal("0");
    public int    cod_sala    = -1;

    public boolean IsNuovo  = true;
    private Prova _Prova ;

    public Prenotazione(){
        _Prova = new Prova();
    }

    /** Utile per la fase di ricerca dei Tavoli
    */
    public static Vector getListaInVettore(String sCondizione)
    {
        Vector vtLista = new Vector();
        if(Generic.strVuota(sCondizione))
            sCondizione = " 1=1";



        String istrSql = "Select * From T_PRENOTAZIONI " +
                                             " Where " + sCondizione;
       // controllo delle prenotazioni
        System.out.println(istrSql);
        Statement Statem = null;
        ResultSet res = null;
        Prenotazione oPren;
        try
        {
            // connessione
            Statem= SINGLETON.getConnection().createStatement();
            res = Statem.executeQuery(istrSql);
            while (res.next())
            {
                oPren= new Prenotazione();
                caricaRes(res, oPren);
                vtLista.addElement(oPren);
            }


        }
        catch(Exception e)
        {
            if(vtLista == null){
                 System.out.println("Problemi in Prenotazioni.getListaInVettore:");
            }
        }
        finally
        {
            try
            {
                 if (res!=null) res.close();
                if (Statem!=null) Statem.close();
                SINGLETON.chiudiConnessione();
            }
            catch(Exception exception1) { }
        }
        return vtLista;
    }

    /** caricamento del resulset
    */
    private static void caricaRes(ResultSet res, Prenotazione jObj)
        throws Exception
    {
        try
        {

            // carica la data di prenotazione
            String DataPren = res.getString("data");
            if(res.wasNull())
                jObj.data = "";
            else
                jObj.data= DataPren.trim();

            // carica ora di inizio prenotazione
            String OraPren = res.getString("ora");
            if(res.wasNull())
                jObj.ora = "";
            else
                jObj.ora= OraPren.trim();

            // carica il codice fiscale dell'utente
            String CodFisc = res.getString("cf_utente");
            if(res.wasNull())
                jObj.cf_utente = "";
            else
                jObj.cf_utente= CodFisc.trim();

            // codice della prenotazione
            jObj.codice = res.getInt("codice");
            if(res.wasNull())
                jObj.codice = -1;

             // tipo di prenotazione
            jObj.tipo = res.getInt("tipo");
            if(res.wasNull())
                jObj.tipo = -1;

            // codice della prenotazione
            jObj.num = res.getInt("num_persone");
            if(res.wasNull())
                jObj.num = -1;

            // se l'ha caricato esiste allora
            jObj.IsNuovo = false;
        }
        catch(Exception e)
        {
            throw new Exception("Prenotazioni. Errore in caricares(): " + e.getMessage());
        }
    }


    


}
