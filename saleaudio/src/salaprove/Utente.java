

package salaprove;

import db.ConnDBaseException;
import db.SINGLETON;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import salaprove.Generic;


public class Utente {

    public String cf_utente;
    public String user;
    public String password;
    public String nome;
    public String cognome;
    public String indirizzo;
    public String telefono;
    public String cel;
    public int tipologia;
    public boolean IsNew;

    public Utente()
    {
        // variabili settate al richiamo del costruttore principale
        cf_utente = "";
        user = "";
        password ="";
        nome="";
        cognome="";
        indirizzo="";
        telefono="";
        cel="";
        tipologia =-1;
        IsNew = true;
    }

    public Utente(String sUtente, String sPassword)
        throws Exception
    {
        this(sUtente);
        if(Generic.strVuota(sPassword))
            throw new Exception("La Password Utente deve essere fornita!");
        sPassword = CryptStr(sPassword);
        if(!sPassword.trim().equals(password.trim()))
            throw new Exception("Password Utente non riconosciuta!");
        else
            return;
    }

    public Utente(String sUtente)
        throws Exception
    {
        this();
        if(Generic.strVuota(sUtente))
            throw new Exception("L' Identificativo Utente deve essere fornito!");

        String sqlGetRecords = "Select * from T_UTENTI Where user = '" + sUtente.trim() + "'";

        Statement sqlStmtGetRecords = null;
        ResultSet rs = null;
        try
        {
            sqlStmtGetRecords = SINGLETON.getConnection().createStatement();
            rs = sqlStmtGetRecords.executeQuery(sqlGetRecords);
            if(rs.next())
            {
                caricaRes(rs, this);

                
            } else
            {
                throw new Exception("");
            }
        }
        catch(ConnDBaseException connectdbexception)
        {
            throw new Exception("Connessione non disponibile");
        }
        catch(Exception exception)
        {
            throw new Exception("Utente '" + sUtente.trim() + "' non riconosciuto");
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.close();
                if(sqlStmtGetRecords != null)
                    sqlStmtGetRecords.close();
                SINGLETON.chiudiConnessione();
            }
            catch(Exception exception2) { }
        }
        
    }


    /** Utile per la fase di ricerca degli Utenti
    */
    public static Vector getListaInVettore(String sCondizione)
    {
        Vector vtLista = new Vector();
        if(Generic.strVuota(sCondizione))
            sCondizione = " 1=1";


        String istrSql =" Select * FROM T_UTENTI " +
                        " WHERE " + sCondizione;

        
        Statement Statem = null;
        ResultSet res = null;
        try
        {
            // connessione
            java.sql.Connection Connessione = SINGLETON.getConnection();
            Statem = Connessione.createStatement();
            res = Statem.executeQuery(istrSql);

            while (res.next())
            {
                Utente oAppo = new Utente();
                caricaRes(res, oAppo);
                vtLista.addElement(oAppo);
            }


        }
        catch(Exception e)
        {
            System.out.println("Problemi in Utente.getListaInVettore:");
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
    private static void caricaRes(ResultSet rs, Utente oUtenteLettura)
        throws Exception
    {
        try
        {

            String utente = rs.getString("cf_utente");
                if(rs.wasNull())
                    oUtenteLettura.cf_utente = "";
                else
                    oUtenteLettura.cf_utente = utente.trim();

                String userTemp = rs.getString("USER");
                if(rs.wasNull())
                    oUtenteLettura.user = "";
                else
                    oUtenteLettura.user = userTemp.trim();



                 String nomeTemp = rs.getString("nome");
                if(rs.wasNull())
                    oUtenteLettura.nome = "";
                else
                    oUtenteLettura.nome = nomeTemp.trim();

                String cogn = rs.getString("cognome");
                if(rs.wasNull())
                    oUtenteLettura.cognome = "";
                else
                    oUtenteLettura.cognome = cogn.trim();

                String cellulare = rs.getString("cel");
                if(rs.wasNull())
                    oUtenteLettura.cel = "";
                else
                    oUtenteLettura.cel = cellulare.trim();

                String tel = rs.getString("telefono");
                if(rs.wasNull())
                    oUtenteLettura.telefono = "";
                else
                    oUtenteLettura.telefono = tel.trim();

                String ind = rs.getString("indirizzo");
                if(rs.wasNull())
                    oUtenteLettura.indirizzo = "";
                else
                    oUtenteLettura.indirizzo = ind.trim();

                String passwd = rs.getString("passwd");
                if(rs.wasNull())
                    oUtenteLettura.password = "";
                else
                    oUtenteLettura.password = passwd.trim();


                oUtenteLettura.tipologia = rs.getInt("cod_tipologia");
                if(rs.wasNull())
                    oUtenteLettura.tipologia = -1;

                oUtenteLettura.IsNew = false;
        }
        catch(Exception e)
        {
            throw new Exception("Utente. Errore in caricares(): " + e.getMessage());
        }
    }

    private  Utente getUtente(String sCodFisc)
        throws Exception
    {

        if(Generic.strVuota(sCodFisc))
            throw new Exception("Codice Fscale deve essere fornito!");

        String sqlGetRecords = "Select * from t_utenti Where cf_utente = '" + sCodFisc.trim() + "'";
        
        Statement sqlStmtGetRecords = null;
        ResultSet rs = null;
        Utente oUtenteLettura;
        try
        {
            sqlStmtGetRecords = SINGLETON.getConnection().createStatement();
            rs = sqlStmtGetRecords.executeQuery(sqlGetRecords);
            if(rs.next())
            {
                oUtenteLettura = new Utente();


                String utente = rs.getString("cf_utente");
                if(rs.wasNull())
                    oUtenteLettura.cf_utente = "";
                else
                    oUtenteLettura.cf_utente = utente.trim();

                String userTemp = rs.getString("USER");
                if(rs.wasNull())
                    oUtenteLettura.user = "";
                else
                    oUtenteLettura.user = userTemp.trim();



                 String nomeTemp = rs.getString("nome");
                if(rs.wasNull())
                    oUtenteLettura.nome = "";
                else
                    oUtenteLettura.nome = nomeTemp.trim();

                String cogn = rs.getString("cognome");
                if(rs.wasNull())
                    oUtenteLettura.cognome = "";
                else
                    oUtenteLettura.cognome = cogn.trim();

                String cellulare = rs.getString("cel");
                if(rs.wasNull())
                    oUtenteLettura.cel = "";
                else
                    oUtenteLettura.cel = cellulare.trim();

                String tel = rs.getString("telefono");
                if(rs.wasNull())
                    oUtenteLettura.telefono = "";
                else
                    oUtenteLettura.telefono = tel.trim();

                String ind = rs.getString("indirizzo");
                if(rs.wasNull())
                    oUtenteLettura.indirizzo = "";
                else
                    oUtenteLettura.indirizzo = ind.trim();

                String passwd = rs.getString("passwd");
                if(rs.wasNull())
                    oUtenteLettura.password = "";
                else
                    oUtenteLettura.password = passwd.trim();


                oUtenteLettura.tipologia = rs.getInt("cod_tipologia");
                if(rs.wasNull())
                    oUtenteLettura.tipologia = -1;

                oUtenteLettura.IsNew = false;

            } else
            {
                throw new Exception("");
            }
        }
        catch(ConnDBaseException connectdbexception)
        {
            throw new Exception("Connessione non disponibile");
        }
        catch(Exception exception)
        {
            throw new Exception("Codice Fiscale '" + sCodFisc.trim() + "' non riconosciuto");
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.close();
                if(sqlStmtGetRecords != null)
                    sqlStmtGetRecords.close();
                SINGLETON.chiudiConnessione();
            }
            catch(Exception exception2) { }
        }

        return oUtenteLettura;

    }

    /**
     *  Metodo di criptaggio password
     * @param sSource
     * @return
     */
    public static String CryptStr(String sSource)
    {
        String sDest = null;
        if(Generic.strVuota(sSource))
            return "-1";
        char cvDest[] = sSource.toCharArray();
        int i = 0;
        int iOffSet = 1;
        for(; i < sSource.length(); i++)
        {
            char cSource = cvDest[i];
            int iSource = Character.getNumericValue(cSource) + iOffSet;
            String Appo = String.valueOf(Character.forDigit(iSource, 36));
            if(!Generic.strVuota(sDest))
                sDest = sDest.trim() + Appo.trim();
            else
                sDest = Appo.trim();
            int iLenDest = sDest.length();
            int k = 1;
            int j = 1;
            for(; k < iLenDest; k++)
                j += k;

            sDest = sDest.trim() + String.valueOf(Character.forDigit(j, 36));
        }

        if(!Generic.strVuota(sDest))
            sDest = sDest.toUpperCase();
        return sDest;
    }

    /**
     *  Metodo di decriptaggio password
     * @param sSource
     * @return
     */
    public static String decryptStr(String sSource)
    {
        String sDest = null;
        if(Generic.strVuota(sSource))
            return "-1";
        String sPassToFind = sSource;
        String sOri = " ";
        int cntChar = 1;
        for(boolean flBreakFind = false; !flBreakFind;)
        {
            for(int i = 0; i <= 36; i++)
            {
                String sTry = sOri.trim() + String.valueOf(Character.forDigit(i, 36));
                String sTryCrypt = CryptStr(sTry.trim());
                cntChar++;
                if(!sPassToFind.startsWith(sTryCrypt.trim()))
                    continue;
                sOri = sTry.trim();
                if(!sTryCrypt.trim().equals(sPassToFind.trim()))
                    continue;
                flBreakFind = true;
                sDest = sOri;
                break;
            }

        }

        return sDest;
    }

    /** Eliminazione di un utente
    */
    public boolean eliminaRecord()
    {
        boolean valore = false;
        Statement pStatem = null;
        try
        {
            if(!IsNew)
            {
                SINGLETON.iniziaTransazione();

                String istrSql = "DELETE FROM T_UTENTI "+
                                  " WHERE cf_utente = '" + this.cf_utente + "' ";

                pStatem = SINGLETON.getConnection().createStatement();
                // elimina i dati
                if( pStatem.executeUpdate(istrSql) == 0){

                    throw new Exception("problemi nella cancellazione dell'utente!");

                }

                pStatem.close();


                SINGLETON.confermaTransazione();


            }
            valore = true;
        }
        catch(Exception e)
        {
            SINGLETON.annullaTransazione();
        }
        finally
        {
            try
            {
                if(pStatem != null)
                    pStatem.close();
                SINGLETON.chiudiConnessione();
            }
            catch(Exception exception1) { }
        }
        return valore;
    }

     /** verifica i campi chiave
    */
    private boolean verificaDati()
    {
        boolean Fl_Ok = true;

        if(Fl_Ok && Generic.strVuota(cf_utente) &&
                    Generic.strVuota(user) &&
                    Generic.strVuota(password) )
        {
            Generic.notifica("Valorizzare correttamente i dati utenti!", 3);
            Fl_Ok = false;
        }
        return Fl_Ok;
    }

    /** registrazione Utenti
    */
    public boolean registra()
    {
        boolean valore = false;
        if(!verificaDati())
            return valore;

        PreparedStatement pStatem = null;
        Statement Statem1 = null;
        try
        {
            SINGLETON.iniziaTransazione();
            String istrSql = "";
            if(IsNew)
                istrSql = "INSERT INTO T_UTENTI (" + "cf_utente,"   +
                                                                                   "USER,"          +
                                                                                   "nome,"          +
                                                                                   "cognome,"       +
                                                                                   "passwd,"        +
                                                                                   "telefono,"      +
                                                                                   "cel,"           +
                                                                                   "indirizzo,"     +
                                                                                   "cod_tipologia"  +
                                                                                    ")" +
                                                            " VALUES (?,?,?,?,?,?,?,?,?)";
            else
                istrSql = "UPDATE T_UTENTI SET " + "cf_utente = ?,"   +
                                                                                 "USER = ?,"     +
                                                                                 "nome = ?,"     +
                                                                                 "cognome = ?,"     +
                                                                                 "passwd = ?,"     +
                                                                                 "telefono = ?,"     +
                                                                                 "cel = ?,"     +
                                                                                 "indirizzo = ?,"     +
                                                                                 "cod_tipologia = ? "     +
                                                                                 " WHERE cf_utente = '" + cf_utente + "' ";


            pStatem = SINGLETON.getConnection().prepareStatement(istrSql);

            // codice fiscale dell'utente
            if(!Generic.strVuota(cf_utente))
                pStatem.setString(1, cf_utente.trim());
            else
                pStatem.setNull(1, 1);

            // user
            if(!Generic.strVuota(user))
                pStatem.setString(2, user.trim());
            else
                pStatem.setNull(2, 1);

            // nome
            if(!Generic.strVuota(nome))
                pStatem.setString(3, nome.trim());
            else
                pStatem.setNull(3, 1);

            // cognome
            if(!Generic.strVuota(cognome))
                pStatem.setString(4, cognome.trim());
            else
                pStatem.setNull(4, 1);

            // passwd
            if(!Generic.strVuota(password))
                pStatem.setString(5, password.trim());
            else
                pStatem.setNull(5, 1);

            // telefono
            if(!Generic.strVuota(telefono))
                pStatem.setString(6, telefono.trim());
            else
                pStatem.setNull(6, 1);

            // cel
            if(!Generic.strVuota(cel))
                pStatem.setString(7, cel.trim());
            else
                pStatem.setNull(7, 1);

            // indirizzo
            if(!Generic.strVuota(indirizzo))
                pStatem.setString(8, indirizzo.trim());
            else
                pStatem.setNull(8, 1);

            // cod_tipologia
            pStatem.setInt(9, tipologia);

            // adesso registra
            if(pStatem.executeUpdate() == 0)
                throw new Exception("Problemi in fase di Registrazione nella classe degli Utenti!");


            IsNew = false;
            SINGLETON.confermaTransazione();
            valore = true;
        }
        catch(Exception e)
        {

            SINGLETON.annullaTransazione();
        }
        finally
        {
            try
            {
                if(pStatem != null)
                    pStatem.close();
                SINGLETON.chiudiConnessione();
            }
            catch(Exception exception1) { }
        }
        return valore;
    }
}

