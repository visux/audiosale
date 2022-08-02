

package salaprove;

import Gui.FrLogutente;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;


public class Generic {

    // per la gestione di applet
    public static AppletContext ac = null;
    public static String codebase;
    public static int FORMAT_DATE = 1;

    protected static java.awt.Color colore = new java.awt.Color(255, 255, 166);
    protected static java.awt.Color abilcolore = java.awt.Color.white;
    // stati della maschera
    public static final int statoINDEFINITO = 0;
    public static final int statoINSERIMENTO = 1;
    public static final int statoMODIFICA = 2;
    public static final int statoBROWSE = 3;

    public static Utente oUtente;
    

    public static Impostazioni prSistema;
    public static Impostazioni prConnessione;
   
    public Generic()
    {
    }

    public static int notifica(String sTesto)
    {
        return notifica(sTesto, 2, "OK", null, null, null, 0);
    }

    public static int notifica(String sTesto, int iTipo)
    {
        return notifica(sTesto, iTipo, "OK", null, null, null, 0);
    }

    public static int notifica(String sTesto, int iTipo, String sOpzione1, String sOpzione2, int iOpzioneDefault)
    {
        return notifica(sTesto, iTipo, sOpzione1, sOpzione2, null, null, iOpzioneDefault);
    }

    public static int notifica(String sTesto, int iTipo, String sOpzione1, String sOpzione2, String sOpzione3, String sOpzione4, int iOpzioneDefault)
    {
        String sTitolo = null;
        int iMessageType = 0;
        if(iTipo == 1)
        {
            sTitolo = " Messaggio";
            iMessageType = 1;
        } else
        if(iTipo == 2)
        {
            sTitolo = " Segnalazione";
            iMessageType = 2;
        } else
        {
            sTitolo = " Errore";
            iMessageType = 0;
        }
        int iNumOpzioni = 0;
        if(sOpzione1 != null)
            iNumOpzioni++;
        if(sOpzione2 != null)
            iNumOpzioni++;
        if(sOpzione3 != null)
            iNumOpzioni++;
        if(sOpzione4 != null)
            iNumOpzioni++;
        Object oOptions[] = new Object[iNumOpzioni];
        iNumOpzioni = -1;
        if(sOpzione1 != null)
        {
            iNumOpzioni++;
            oOptions[iNumOpzioni] = sOpzione1.trim();
        }
        if(sOpzione2 != null)
        {
            iNumOpzioni++;
            oOptions[iNumOpzioni] = sOpzione2.trim();
        }
        if(sOpzione3 != null)
        {
            iNumOpzioni++;
            oOptions[iNumOpzioni] = sOpzione3.trim();
        }
        if(sOpzione4 != null)
        {
            iNumOpzioni++;
            oOptions[iNumOpzioni] = sOpzione4.trim();
        }
        if(iOpzioneDefault > iNumOpzioni || iOpzioneDefault < 1)
            iOpzioneDefault = 1;
        sTesto = sTesto + "      ";
        int replay = JOptionPane.showOptionDialog(null, sTesto, sTitolo, 0, iMessageType, null, oOptions, oOptions[iOpzioneDefault - 1]);
        return replay + 1;
    }

    public static void centraGui(Window w)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = w.getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        w.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Chiusura del Software
     */
    public static void chiudiProgramma()
    {

        System.exit(0);

    }

    /**
     * Chiusura della maschera
     */
    public static void avviaMascheraIniziale()
    {

        FrLogutente oFrLogutente = new FrLogutente();
        oFrLogutente.setModal(true);
        oFrLogutente.setVisible(true);

    }


    /** Mi restituisce un boleano se la stringa è vuota o meno
    */
    public static boolean strVuota(String stringa)
    {
        if(stringa == null)
            return true;
        return stringa.equals("") || stringa.length() == 0;
    }


    public static boolean initProprieta()
    {
        prSistema = new Impostazioni();
        if(!prSistema.caricaImpostazioni(getURL("audio.ini")))
        {
            notifica("File proprieta' di sistema non trovato", 2);
            return false;
        }
        prConnessione = new Impostazioni();
        String nomefileconnect = prSistema.getStringImpostazioni("nomefileconnect");
        if(nomefileconnect.length() == 0)
        {
            System.out.println("Nome file parametri connessione non specificato");
            return false;
        }

        if(!prConnessione.caricaImpostazioni(getURL("" + nomefileconnect)))
        {
            notifica("File proprieta' connessione " + nomefileconnect + " non trovato", 2);
            return false;
        } else
        {
            
            return true;
        }
    }


    /**
     * Gestisce gli url
     * @param nomefile
     * @return
     */
    public static URL getURL(String nomefile)
    {
        URL myURL = null;
        if(!strVuota(nomefile))
            try
            {
                myURL = new URL(codebase + nomefile);
            }
            catch(MalformedURLException e)
            {
                System.out.println(e.getMessage());
            }
        return myURL;
    }

     /** ricava l'anno dalla data
    */
    public static int getAnno(java.util.Date utildata)
    {
        Calendar calendario = Calendar.getInstance();
        calendario.clear();
        calendario.setTime(utildata);
        return calendario.get(1);
    }

    public static String getSDateFromInt(int giorno, int mese, int anno)
    {
        return getSDateFromInt(giorno, mese, anno, 1);
    }

    public static String getSDateFromInt(int giorno, int mese, int anno, int tipo)
    {
        if(giorno != 0 && mese != 0 && anno != 0)
        {
            String giornoS = "";
            String meseS = "";
            String annoS = "";
            if(giorno < 10)
                giornoS = "0" + String.valueOf(giorno);
            else
                giornoS = String.valueOf(giorno);
            if(mese < 10)
                meseS = "0" + String.valueOf(mese);
            else
                meseS = String.valueOf(mese);
            annoS = "0000" + String.valueOf(anno);
            annoS = annoS.substring(annoS.length() - 4);
            switch(tipo)
            {
            case 1: // '\001'
                return giornoS + "/" + meseS + "/" + annoS;

            case 2: // '\002'
                return meseS + "/" + giornoS + "/" + annoS;

            case 3: // '\003'
                return annoS + "/" + meseS + "/" + giornoS;
            }
            return giornoS + "/" + meseS + "/" + annoS;
        } else
        {
            return "";
        }
    }

    public static java.util.Date getUtilFromIntYMD(int data)
    {
        Integer i = new Integer(data);
        String sData = i.toString().trim();
        if(sData.length() == 8)
        {
            String annoS = sData.substring(0, 4);
            String meseS = sData.substring(4, 6);
            String giornoS = sData.substring(6);
            sData = giornoS + "/" + meseS + "/" + annoS;
            return getUtilDateFromS(sData);
        } else
        {
            return null;
        }
    }

    public static java.util.Date getUtilDateFromS(String data)
    {
        return getUtilDateFromS(data, "00:00");
    }

    public static java.util.Date getUtilDateFromS(String datain, String orain)
    {
        if(isDataOK(datain, false) && isOraOK(orain, false))
            try
            {
                int giorno = Integer.parseInt(datain.substring(0, 2));
                int mese = Integer.parseInt(datain.substring(3, 5)) - 1;
                int anno = Integer.parseInt(datain.substring(6));
                int ora = Integer.parseInt(orain.substring(0, 2));
                int minuto = Integer.parseInt(orain.substring(3));
                Calendar calendario = Calendar.getInstance();
                calendario.clear();
                calendario.set(anno, mese, giorno, ora, minuto);
                java.util.Date utildata = calendario.getTime();
                return utildata;
            }
            catch(NumberFormatException numberformatexception)
            {
                return null;
            }
        else
            return null;
    }

    public static boolean isDataOK(String data, boolean ok_null)
    {
        if(!strVuota(data))
        {
            int vDD;
            int vMM;
            int vYY;
            try
            {
                vDD = Integer.parseInt(data.substring(0, 2));
                vMM = Integer.parseInt(data.substring(3, 5));
                vYY = Integer.parseInt(data.substring(6));
            }
            catch(NumberFormatException numberformatexception)
            {
                return false;
            }
            switch(vMM)
            {
            case 4: // '\004'
            case 6: // '\006'
            case 9: // '\t'
            case 11: // '\013'
                if(vDD > 30)
                    return false;
                break;

            case 2: // '\002'
                if(vYY % 4 == 0)
                {
                    if(vYY % 100 == 0 && vYY % 400 != 0)
                    {
                        if(vDD > 28)
                            return false;
                    } else
                    if(vDD > 29)
                        return false;
                } else
                if(vDD > 28)
                    return false;
                break;

            case 1: // '\001'
            case 3: // '\003'
            case 5: // '\005'
            case 7: // '\007'
            case 8: // '\b'
            case 10: // '\n'
            case 12: // '\f'
                if(vDD > 31)
                    return false;
                break;

            default:
                return false;
            }
        } else
        if(!ok_null)
            return false;
        return true;
    }

    public static boolean isOraOK(String ora, boolean ok_null)
    {
        if(!strVuota(ora))
        {
            int vHH;
            int vMN;
            try
            {
                vHH = Integer.parseInt(ora.substring(0, 2));
                vMN = Integer.parseInt(ora.substring(3, 5));
            }
            catch(NumberFormatException numberformatexception)
            {
                return false;
            }
            if(vHH > 23)
                return false;
            if(vMN > 59)
                return false;
        } else
        if(!ok_null)
            return false;
        return true;
    }

        public static int getIntYMDFromUtil(java.util.Date utildata)
    {
        int iYMD = 0;
        if(utildata != null)
        {
            Calendar calendario = Calendar.getInstance();
            calendario.clear();
            calendario.setTime(utildata);
            int iGiorno = calendario.get(5);
            int iMese = calendario.get(2) + 1;
            int iAnno = calendario.get(1);
            iYMD = iAnno * 10000 + iMese * 100 + iGiorno;
        }
        return iYMD;
    }

    /**
     * Ricava il primo giorno del mese corrente
     * @param data
     * @return
     */
    public static java.util.Date getPrimoGiornoMeseCorrente(java.util.Date data)
    {
        return getUtilFromIntYMD((getIntYMDFromUtil(data) / 100) * 100 + 1);
    }

    /**
     *  Restituisce la data e il tempo corrente
     * @return
     */
    public static java.util.Date getCurrentDateTime()
    {
        return Calendar.getInstance().getTime();
    }

    public static String getSTimeFromUtil(java.util.Date utildata)
    {
        if(utildata != null)
        {
            try
            {
                Calendar calendario = Calendar.getInstance();
                calendario.clear();
                calendario.setTime(utildata);
                int ora = calendario.get(11);
                int minuti = calendario.get(12);
                return getSTimeFromInt(ora, minuti);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e);
            }
            return "";
        } else
        {
            return "";
        }
    }

    /** aggiunge i minuti alla data corrente
    */
    public static String aggiungiMinuti(int min)
    {
        String OraFinale = "";
        // si ricava l'ora odierna
        String OraOd = "" + Generic.getSTimeFromUtil(Generic. getCurrentDateTime());
		int ora=0;
        int somma=0;
        // verifica l'ora e' corretta
        if(!Generic.strVuota(OraOd)){
            // ricava i valori dalle stringhe
            String Ore = "" + OraOd.substring(0, 2).trim();
            String Minuti = "" + OraOd.substring(3, 5).trim();
                      int interv =0;
                      int minuto = Integer.parseInt(Minuti);
                      ora = Integer.parseInt(Ore);

                somma = minuto + min;
                int temp=0;
                if(somma > 59){
                       temp = somma-59;
                       temp = temp -1;
                       somma = temp;
                       ora=ora+1;
                }


                if (ora > 23){
                       ora = 0;
                }


        }

        if (ora <= 9 )
              OraOd = "0";
          else
              OraOd = "" ;

        // segno per la composizione della stringa
        String Segno = ":";
        if(somma <= 9){
             Segno=":0";
        }

        return OraFinale = "" + OraOd + "" + ora + Segno + somma ;
       }

    public static String getSTimeFromInt(int ora, int minuti)
    {
        String oraS = "";
        String minutiS = "";
        if(ora < 10)
            oraS = "0" + String.valueOf(ora);
        else
            oraS = String.valueOf(ora);
        if(minuti < 10)
            minutiS = "0" + String.valueOf(minuti);
        else
            minutiS = String.valueOf(minuti);
        return oraS + ":" + minutiS;
    }

     /**
     *  Restituisce la data in formato stringa
     * @return
     */
    public static String getSDateFromUtil(java.util.Date utildata)
    {
        if(utildata != null)
        {
            Calendar calendario = Calendar.getInstance();
            calendario.clear();
            calendario.setTime(utildata);
            int giorno = calendario.get(5);
            int mese = calendario.get(2) + 1;
            int anno = calendario.get(1);
            return getSDateFromInt(giorno, mese, anno);
        } else
        {
            return "";
        }
    }

    /** Ricava il giorno dalla data
	*/
	public static int getGiorno(java.util.Date data)
	{
	    java.util.Calendar calendario = java.util.Calendar.getInstance();
        calendario.clear();
        calendario.setTime(data);
        int igiorno = calendario.get(calendario.DATE );

	    return igiorno;

	}

    public static boolean initAmbiente()
    {
        int formatodate = prSistema.getIntImpostazioni("formatodate");
        if(formatodate == -1)
            formatodate = 1;
        if(formatodate > 3)
            formatodate = 1;
        FORMAT_DATE = formatodate;

        return true;
    }

        /** controlla la lunghezza del testo
    */
    public static void setPropText(KeyEvent event, int len)
    {
        if(event.isConsumed())
            return;
        char c = event.getKeyChar();
        c = Character.toUpperCase(c);
        event.setKeyChar(c);
        JTextComponent source = (JTextComponent)event.getSource();
        if(source.getText().length() >= len && event.getKeyChar() != '\b' && source.getSelectionStart() == source.getSelectionEnd())
        {
            source.getToolkit().beep();
            event.consume();
        }
    }


    /** controlla la lunghezza del testo
    */
    public static void setLenText(KeyEvent event, int len)
    {
        if(event.isConsumed())
            return;
        char c = event.getKeyChar();
        JTextComponent source = (JTextComponent)event.getSource();
        if(source.getText().length() >= len && event.getKeyChar() != '\b' && source.getSelectionStart() == source.getSelectionEnd())
        {
            source.getToolkit().beep();
            event.consume();
        }
    }

        /** Imposta la selezione e il caret position
	*/
	public static void caretPosition(javax.swing.JTextField jtfObj){
	     ///selezione di un oggetto da tastiera

                jtfObj.setBackground(colore);
                if(!strVuota(jtfObj.getText())){
                        jtfObj.setSelectionStart(0);
                        jtfObj.setSelectionEnd(jtfObj.getText().length());
                }


	}

    /** Imposta il colore del campo
    */
    public static void impostaColor(javax.swing.JTextField jtfObj){
    ///selezione di un oggetto da tastiera

    jtfObj.setBackground(colore);

    }

}
