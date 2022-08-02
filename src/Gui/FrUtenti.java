package Gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import salaprove.CodiceFiscale;
import salaprove.Generic;
import salaprove.Utente;


public class FrUtenti extends javax.swing.JFrame {

    
    private Vector vtDati = new Vector();
    private java.awt.Color AbilitaSfondo = new java.awt.Color(255, 255, 166);
    private java.awt.Color DisabilitaSfondo = java.awt.Color.white;
    private Utente oUtente;       // Oggetto Utente
   // private ComboBox icVuoto = new ComboBox("  ", null);
    // Flag per permettere non fare aprire 2 volte lo stesso messaggio
    private boolean flMessaggio = false;
    /** Stati della Maschera: statoINDEFINITO, statoBROWSE, statoINSERIMENTO, statoMODIFICA
    */
    private int StatoMaschera = Generic.statoINDEFINITO;
    // Flag a True se sono state effettuate delle modifiche ai campi
    // del record.
    private boolean IsModificato = false;
    // Parametro per testare l'avventuto Controllo
    // della Chiave in fase di Inserimento
    private boolean ChiaveOk = false;
    /** Modello della Jtable per la lista
    */
    MdS oMdS;

    /** Creates new form FrUtenti */
    public FrUtenti() {
        initComponents();
        // Abilita i Campi necessari
        settaAbilitaCampi();
        settaImmaginiPulsanti();
        oUtente = new Utente();
        // Imposta il Titolo della Form
        Default();
        // imposta le righe della jtable di ricerca
	impostaJTRighe();
        // Condizione Iniziale della maschera
	settaStatoMaschera(Generic.statoINDEFINITO);

        // si posiziona inizialmente sul pannello di Ricerca
	jtpSelezionePannello.setSelectedComponent(jpRicerca);
        jtpSelezionePannello.setEnabledAt(1, false);
        
    }

    	 //********** MODELLO PARTICOLARE PER LA GESTIONE DELLA JTABLE **********//
    public class MdS extends javax.swing.table.AbstractTableModel implements java.io.Serializable
    {
        /** Righe del profilo
        */
        public Vector vRigheModello;

        // Colonne del modello (final = Costanti)
        final public int  colonnaCODICE         = 0;
        final public int  colonnaUSER           = 1;
        final public int  colonnaCOGNOME        = 2;
        final public int  colonnaNOME           = 3;
        final public int  colonnaCEL            = 4;
        final public int  colonnaTEL            = 5;
        final public int  colonnaINDIRIZZO      = 6;

        // definisco una variabile di tipo record
        final String[] columnNames = new String[7];

        public MdS()
        {
            // Inserisco nella variabile di tipo record
            // le intestazioni delle colonne
            columnNames[colonnaCODICE]         = "Cod.Fisc.";
            columnNames[colonnaUSER]           = "NickName";
            columnNames[colonnaCOGNOME]        = "Cognome";
            columnNames[colonnaNOME]           = "Nome";
            columnNames[colonnaCEL]            = "Cellulare";
            columnNames[colonnaTEL]            = "Telefono";
            columnNames[colonnaINDIRIZZO]      = "Indirizzo";
            //{{INIT_CONTROLS
		    //}}
        }

        // Imposta la righe associate al modello
        public void setProfilo(Vector vRighe)
        {
            this.vRigheModello = vRighe;
        }

        public Vector getProfilo()
        {
            return this.vRigheModello;
        }

        // Numero colonne JTABLE
        public int getColumnCount()
        {
            return columnNames.length;
        }

        // Numero righe JTABLE
        public int getRowCount()
        {
            if (this.vRigheModello == null)
                return 0;
            else
                return vRigheModello.size();
        }

        // Intestazione colonne JTABLE
        public String getColumnName(int col)
        {
            return columnNames[col];
        }

        // valore celle JTABLE
        // Legge il valore contenuto in una cella
        public Object getValueAt(int row, int col)
        {
            // row   =   riga
            // col   =   colonna

            // mi posizioni sulla riga desiderata
            String[] Valori = (String[])vRigheModello.elementAt(row);

            // mi posiziono sulla colonna desiderata
            switch (col)
            {

                case colonnaCODICE:
                    return Valori[colonnaCODICE];
                case colonnaUSER:
                    return Valori[colonnaUSER];
                case colonnaCOGNOME:
                    return Valori[colonnaCOGNOME];
                case colonnaNOME:
                    return Valori[colonnaNOME];
                case colonnaCEL:
                    return Valori[colonnaCEL];
                case colonnaTEL:
                    return Valori[colonnaTEL];
                case colonnaINDIRIZZO:
                    return Valori[colonnaINDIRIZZO];
                default:
                    return "";
            }
        }

        /*
        * JTable uses this method to determine the default renderer/
        * editor for each cell.  If we didn't implement this method,
        * then the last column would contain text ("true"/"false"),
        * rather than a check box.
        */
        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }

        /* Determina quali sono le celle editabili, e quando devono diventare editabili
         **/
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        /**********************************************/
        /* Serve per impostare il valore ad una cella */
        /**********************************************/
        public void setValueAt(Object value, int row, int col)
        {
             String[] oVal = (String[])vRigheModello.elementAt(row);

                 switch (col)
                 {

                   case colonnaCODICE:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaCODICE] = "";
                        }else{
                            oVal[colonnaCODICE] = value.toString();
                        }
                        break;
                    case colonnaUSER:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaUSER] = "";
                        }else{
                            oVal[colonnaUSER] = value.toString();
                        }
                        break;
                    case colonnaCOGNOME:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaCOGNOME] = "";
                        }else{
                            oVal[colonnaCOGNOME] = value.toString();
                        }
                        break;
                    case colonnaNOME:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaNOME] = "";
                        }else{
                            oVal[colonnaNOME] = value.toString();
                        }
                        break;
                    case colonnaCEL:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaCEL] = "";
                        }else{
                            oVal[colonnaCEL] = value.toString();
                        }
                        break;
                    case colonnaTEL:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaTEL] = "";
                        }else{
                            oVal[colonnaTEL] = value.toString();
                        }
                        break;
                    case colonnaINDIRIZZO:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaINDIRIZZO] = "";
                        }else{
                            oVal[colonnaINDIRIZZO] = value.toString();
                        }
                        break;
                    default:
                        System.out.println("SET RIGHE NON EDITABILI");

                }
                fireTableCellUpdated(row, col);
        }

        /************************/
        /* Rinfresca la JTABLE  */
        /************************/
        public void aggiorna()
        {
            fireTableDataChanged(); // Tell the listeners a new table has arrived.
        }

        /***********************************************/
        /*  Rinfresca la JTABLE per la riga cancellata */
        /***********************************************/
        public void cancella(int riga){
            this.fireTableRowsDeleted(riga, riga);
        }

	    //{{DECLARE_CONTROLS
	    //}}
    }
    //*********** FINE CLASSE SUPPORTO **********//

    // Classe per gestire il rendere della JTable
    // in teoria deve essere una classe di render per ogni
    // colonna.
    // In questo caso siccome tutte le colonne sono di tipo testo
    // viene usata una sola classe di render su tutte le colonne.
    public class RdS extends javax.swing.JTextField implements javax.swing.table.TableCellRenderer
    {
        // Impostazione dei colori di sfondo
        private Color COLOR_GIALLO   = new Color(255,255,166);
        private Color COLOR_VERDE    = new Color(104,240,162);

	    public RdS()
	    {
	        super();
	    }

	    /******************************************************/
        /* Il metodo viene richiamato dalla JTABLE in fase di */
        /* popolamento celle                                  */
        /******************************************************/
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column){
            /**************************************************************************/
            /* table      = Nome della tabella                                        */
            /* value      = Valore della cella                                        */
            /* isSelected = true  cella selezionata                                   */
            /*              false cella non selezionata                               */
            /* hasFocus   = true  ha il fuoco                                         */
            /*              false non ha il fuoco                                     */
            /* row        = riga                                                      */
            /* column     = posizione della colonna a video.                          */
            /*              Nel caso di drag and drop colonne è necessario convertire */
            /*              la colonna a video nella colonna reale.                   */
            /*    NB: in fase di init JTABLE è necessario utilizzare (setModelIndex)  */
            /**************************************************************************/

            // Prelevo il valore reale della colonna e la metto in colreale
            javax.swing.table.TableColumn tc = table.getColumnModel().getColumn(column);
            int colreale = tc.getModelIndex();
            //mr = (ModListaPresenti)table.getModel();

            // Colore delle righe
            if (isSelected)
            {
                // Imposto il colore di default
                setBackground(COLOR_GIALLO);
                setForeground(table.getSelectionForeground());
            }
            else
            {
                if ((row % 2) == 0)
                {
                    // le righe pari le metto con lo sfondo bianco
                    setBackground(COLOR_VERDE);
                    setForeground(table.getForeground());
                }
                else
                {
                    // le righe dispari le metto con il colore azzurro
                    setBackground(COLOR_VERDE);
                    setForeground(table.getForeground());
                }
            }
            setFont(table.getFont());

            // Formattazione dei valori
            if (value==null)
            {
                return null;
            }
            else{
                // Con switch problemi di costanti
                if (colreale == oMdS.colonnaCODICE){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaUSER){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaCOGNOME){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaNOME){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaCEL){
                    this.setHorizontalAlignment(RIGHT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaTEL){
                    this.setHorizontalAlignment(RIGHT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdS.colonnaINDIRIZZO){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                return this;
            }
	    }

        
    }

    /**
     * Le abilitazioni dei campi dipendono dallo stato in cui si trova la maschera
     **/
    private void settaAbilitaCampi(){
        // Imposta le Abilitazioni
        jtfCodFisc.setEnabled((StatoMaschera == Generic.statoINSERIMENTO) && !ChiaveOk  ? true : false);
        jtfUtente.setEnabled((StatoMaschera == Generic.statoINSERIMENTO) && !ChiaveOk  ? true : false);
        jpfPassword1.setEnabled((StatoMaschera == Generic.statoINSERIMENTO) && !ChiaveOk  ? true : false);
        jpfPassword2.setEnabled((StatoMaschera == Generic.statoINSERIMENTO) && !ChiaveOk  ? true : false);


        jtfNome.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);
        jtfCognome.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);
        jtfIndirizzo.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);
        jtfTelefono.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);
        jtfCel.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);
        jcbTipologie.setEnabled(((StatoMaschera == Generic.statoINSERIMENTO) && ChiaveOk) || (StatoMaschera == Generic.statoMODIFICA) ? true : false);


        jtfCodFisc.setBackground(jtfCodFisc.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfUtente.setBackground(jtfUtente.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jpfPassword1.setBackground(jpfPassword1.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jpfPassword2.setBackground(jpfPassword2.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfNome.setBackground(jtfNome.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfCognome.setBackground(jtfCognome.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfIndirizzo.setBackground(jtfIndirizzo.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfTelefono.setBackground(jtfTelefono.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);
        jtfCel.setBackground(jtfCel.isEnabled() ? AbilitaSfondo : DisabilitaSfondo);



    }

    /**
     * Imposta lo Stato della Maschera
     **/
    private void settaStatoMaschera(int NewStato){
        StatoMaschera = NewStato;

        // Reimposta le Abilitazioni di Campi e Bottoni
        settaAbilitaCampi();
        settaAbilitaPulsanti();

        jtpSelezionePannello.setEnabledAt(0, (StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO) || (StatoMaschera == Generic.statoBROWSE) || (StatoMaschera == Generic.statoINDEFINITO));
        jtpSelezionePannello.setEnabledAt(1, (StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO) || (StatoMaschera == Generic.statoBROWSE) || (StatoMaschera == Generic.statoINDEFINITO));
        jtpSelezionePannello.setEnabledAt(2, (StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO) || (StatoMaschera == Generic.statoBROWSE) || (StatoMaschera == Generic.statoINDEFINITO));

        // In caso di Inserimento o Modifica, si porta sulla Tab di Dettaglio
        if ((StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO)){
        	jtpSelezionePannello.setSelectedComponent(jpDettaglio);
        }

        

    }

    /**
     * Le abilitazioni dei bottoni dipendono dallo stato in cui si trova la maschera
     **/
    private void settaAbilitaPulsanti(){
        jbtNuovo.setEnabled((StatoMaschera == Generic.statoBROWSE) || (StatoMaschera == Generic.statoINDEFINITO) ? true : false);
        jbtElimina.setEnabled((StatoMaschera == Generic.statoBROWSE) ? true : false);
        jbtModifica.setEnabled((StatoMaschera == Generic.statoBROWSE) ? true : false);
        jbtAnnulla.setEnabled(((StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO)) ? true : false);
        jbtRegistra.setEnabled(((StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO)) ? true : false);
        jbtRicerca.setEnabled((StatoMaschera == Generic.statoBROWSE) || (StatoMaschera == Generic.statoINDEFINITO) ? true : false);
        jbtAvanti.setEnabled((StatoMaschera == Generic.statoINSERIMENTO) && !ChiaveOk ? true : false);
        jbtPassword.setEnabled(((StatoMaschera == Generic.statoMODIFICA) || (StatoMaschera == Generic.statoINSERIMENTO)) ? true : false);

    }

     /**   Verifica del codice fiscale
     */
     private void verificaCodiceFiscale(){

	  // Se il campo non e' una stringa vuota allora verifica il cod fiscale
	  if(!Generic.strVuota(this.jtfCodFisc.getText())){
	     CodiceFiscale oC = new CodiceFiscale();
		 String verificaEffettuata = oC.ControllaCF(this.jtfCodFisc.getText());
		 // Se il messaggio non e' vuoto sicuramente esiste qualcosa che non va
	     if (!Generic.strVuota(verificaEffettuata)){
	          Generic.notifica("" + verificaEffettuata,3 );
	          // svuota il campo
	          this.jtfCodFisc.setText("");
	     }
	  }

     }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpSelezionePannello = new javax.swing.JTabbedPane();
        jpDettaglio = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        jpSale = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jpfPassword1 = new javax.swing.JPasswordField();
        jpfPassword2 = new javax.swing.JPasswordField();
        jbtPassword = new javax.swing.JButton();
        jtfUtente = new javax.swing.JTextField();
        jtfCodFisc = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jcbTipologie = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jtfCognome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfIndirizzo = new javax.swing.JTextField();
        jbtAvanti = new javax.swing.JButton();
        jtfTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfCel = new javax.swing.JTextField();
        jpListaUtenti = new javax.swing.JPanel();
        jpLista = new javax.swing.JPanel();
        scrollLista = new javax.swing.JScrollPane();
        jTableModello = new javax.swing.JTable();
        jpRicerca = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jcbFindIndirizzo = new javax.swing.JComboBox();
        jcbFindNick = new javax.swing.JComboBox();
        jcbFindCognome = new javax.swing.JComboBox();
        jcbFindNome = new javax.swing.JComboBox();
        jcbFindCodice = new javax.swing.JComboBox();
        jtfFindCodice = new javax.swing.JTextField();
        jtfFindNome = new javax.swing.JTextField();
        jtfFindCognome = new javax.swing.JTextField();
        jtfFindNick = new javax.swing.JTextField();
        jtfFindIndirizzo = new javax.swing.JTextField();
        jchbFindCodice = new javax.swing.JCheckBox();
        jchbNome = new javax.swing.JCheckBox();
        jchbCognome = new javax.swing.JCheckBox();
        jchbNick = new javax.swing.JCheckBox();
        jchbIndirizzo = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jbtNuovo = new javax.swing.JButton();
        jbtModifica = new javax.swing.JButton();
        jbtElimina = new javax.swing.JButton();
        jbtRicerca = new javax.swing.JButton();
        jbtEsci = new javax.swing.JButton();
        jbtAnnulla = new javax.swing.JButton();
        jbtRegistra = new javax.swing.JButton();
        jpUtente = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCognome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIstema di Prenotazione Sale Audio");

        jpSale.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Codice Fiscale:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Username:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Ridigitare per Conferma:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Digitare la Password:");

        jpfPassword1.setSelectionColor(new java.awt.Color(204, 0, 0));
        jpfPassword1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpfPassword1MousePressed(evt);
            }
        });
        jpfPassword1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpfPassword1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpfPassword1FocusLost(evt);
            }
        });
        jpfPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpfPassword1KeyTyped(evt);
            }
        });

        jpfPassword2.setSelectionColor(new java.awt.Color(204, 0, 0));
        jpfPassword2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpfPassword2MousePressed(evt);
            }
        });
        jpfPassword2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpfPassword2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpfPassword2FocusLost(evt);
            }
        });
        jpfPassword2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpfPassword2KeyTyped(evt);
            }
        });

        jbtPassword.setText("Password");
        jbtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpfPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpfPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jpfPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jpfPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        jtfUtente.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfUtente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfUtenteMousePressed(evt);
            }
        });
        jtfUtente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfUtenteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfUtenteFocusLost(evt);
            }
        });
        jtfUtente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfUtenteKeyTyped(evt);
            }
        });

        jtfCodFisc.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfCodFisc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfCodFiscMousePressed(evt);
            }
        });
        jtfCodFisc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCodFiscFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodFiscFocusLost(evt);
            }
        });
        jtfCodFisc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCodFiscKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpSaleLayout = new javax.swing.GroupLayout(jpSale);
        jpSale.setLayout(jpSaleLayout);
        jpSaleLayout.setHorizontalGroup(
            jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSaleLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45)
                        .addComponent(jtfUtente, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                    .addGroup(jpSaleLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(jtfCodFisc, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpSaleLayout.setVerticalGroup(
            jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSaleLayout.createSequentialGroup()
                .addGroup(jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSaleLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfUtente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfCodFisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpSaleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel25.setBackground(new java.awt.Color(102, 102, 102));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Configurazioni Utente");
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel25.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Gruppo Appartenenza:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Nome / Rag.soc:");

        jtfNome.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfNomeMousePressed(evt);
            }
        });
        jtfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfNomeFocusLost(evt);
            }
        });
        jtfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNomeKeyTyped(evt);
            }
        });

        jtfCognome.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfCognome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfCognomeMousePressed(evt);
            }
        });
        jtfCognome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCognomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCognomeFocusLost(evt);
            }
        });
        jtfCognome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCognomeKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Cognome / Desc:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Indirizzo:");

        jtfIndirizzo.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfIndirizzo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfIndirizzoMousePressed(evt);
            }
        });
        jtfIndirizzo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfIndirizzoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfIndirizzoFocusLost(evt);
            }
        });
        jtfIndirizzo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfIndirizzoKeyTyped(evt);
            }
        });

        jbtAvanti.setBorder(null);
        jbtAvanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAvantiActionPerformed(evt);
            }
        });

        jtfTelefono.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfTelefonoMousePressed(evt);
            }
        });
        jtfTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTelefonoFocusLost(evt);
            }
        });
        jtfTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfTelefonoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Telefono:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Cellulare:");

        jtfCel.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfCel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfCelMousePressed(evt);
            }
        });
        jtfCel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCelFocusLost(evt);
            }
        });
        jtfCel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCelKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfIndirizzo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addComponent(jtfCognome, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp1Layout.createSequentialGroup()
                .addContainerGap(497, Short.MAX_VALUE)
                .addComponent(jbtAvanti, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(441, 441, 441))
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfTelefono)
                    .addComponent(jcbTipologie, 0, 288, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCel, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addComponent(jpSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtAvanti, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipologie, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtfCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jpDettaglioLayout = new javax.swing.GroupLayout(jpDettaglio);
        jpDettaglio.setLayout(jpDettaglioLayout);
        jpDettaglioLayout.setHorizontalGroup(
            jpDettaglioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDettaglioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpDettaglioLayout.setVerticalGroup(
            jpDettaglioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDettaglioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpSelezionePannello.addTab("Dettaglio Utenti", jpDettaglio);

        jTableModello.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableModello.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableModelloMouseClicked(evt);
            }
        });
        jTableModello.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableModelloKeyPressed(evt);
            }
        });
        scrollLista.setViewportView(jTableModello);

        javax.swing.GroupLayout jpListaLayout = new javax.swing.GroupLayout(jpLista);
        jpLista.setLayout(jpListaLayout);
        jpListaLayout.setHorizontalGroup(
            jpListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLista, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        );
        jpListaLayout.setVerticalGroup(
            jpListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLista, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpListaUtentiLayout = new javax.swing.GroupLayout(jpListaUtenti);
        jpListaUtenti.setLayout(jpListaUtentiLayout);
        jpListaUtentiLayout.setHorizontalGroup(
            jpListaUtentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpListaUtentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpListaUtentiLayout.setVerticalGroup(
            jpListaUtentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpListaUtentiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpSelezionePannello.addTab("Lista Utenti", jpListaUtenti);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Codce Fiscale:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("Nome:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Cognome:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel23.setText("NickName:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Indirizzo:");

        jcbFindIndirizzo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LIKE", "UGUALE" }));

        jcbFindNick.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LIKE", "UGUALE" }));

        jcbFindCognome.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LIKE", "UGUALE" }));

        jcbFindNome.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LIKE", "UGUALE" }));

        jcbFindCodice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LIKE", "UGUALE" }));

        jtfFindCodice.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfFindCodice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfFindCodiceMousePressed(evt);
            }
        });
        jtfFindCodice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfFindCodiceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFindCodiceFocusLost(evt);
            }
        });

        jtfFindNome.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfFindNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfFindNomeMousePressed(evt);
            }
        });
        jtfFindNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfFindNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFindNomeFocusLost(evt);
            }
        });

        jtfFindCognome.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfFindCognome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfFindCognomeMousePressed(evt);
            }
        });
        jtfFindCognome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfFindCognomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFindCognomeFocusLost(evt);
            }
        });

        jtfFindNick.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfFindNick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfFindNickMousePressed(evt);
            }
        });
        jtfFindNick.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfFindNickFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFindNickFocusLost(evt);
            }
        });

        jtfFindIndirizzo.setSelectionColor(new java.awt.Color(204, 0, 0));
        jtfFindIndirizzo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfFindIndirizzoMousePressed(evt);
            }
        });
        jtfFindIndirizzo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfFindIndirizzoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFindIndirizzoFocusLost(evt);
            }
        });

        jchbFindCodice.setText(" che contiene");

        jchbNome.setText(" che contiene");

        jchbCognome.setText(" che contiene");

        jchbNick.setText(" che contiene");

        jchbIndirizzo.setText(" che contiene");

        javax.swing.GroupLayout jpRicercaLayout = new javax.swing.GroupLayout(jpRicerca);
        jpRicerca.setLayout(jpRicercaLayout);
        jpRicercaLayout.setHorizontalGroup(
            jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRicercaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpRicercaLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbFindIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRicercaLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbFindNick, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRicercaLayout.createSequentialGroup()
                        .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpRicercaLayout.createSequentialGroup()
                                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19))
                                .addGap(121, 121, 121))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(6, 6, 6)
                        .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbFindCodice, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbFindNome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbFindCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfFindIndirizzo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfFindNick, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfFindCodice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(jtfFindNome, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfFindCognome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchbFindCodice)
                    .addComponent(jchbNome)
                    .addComponent(jchbCognome)
                    .addComponent(jchbNick)
                    .addComponent(jchbIndirizzo))
                .addGap(68, 68, 68))
        );
        jpRicercaLayout.setVerticalGroup(
            jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRicercaLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jtfFindCodice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbFindCodice)
                    .addComponent(jcbFindCodice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jtfFindNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbNome)
                    .addComponent(jcbFindNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jtfFindCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbCognome)
                    .addComponent(jcbFindCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jtfFindNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbNick)
                    .addComponent(jcbFindNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jtfFindIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbIndirizzo)
                    .addComponent(jcbFindIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        jtpSelezionePannello.addTab("Ricerca", jpRicerca);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtNuovo.setText("Nuovo");
        jbtNuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNuovoActionPerformed(evt);
            }
        });

        jbtModifica.setText("Modifica");
        jbtModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModificaActionPerformed(evt);
            }
        });

        jbtElimina.setText("Elimina");
        jbtElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEliminaActionPerformed(evt);
            }
        });

        jbtRicerca.setText("Ricerca");
        jbtRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRicercaActionPerformed(evt);
            }
        });

        jbtEsci.setText("Esci");
        jbtEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEsciActionPerformed(evt);
            }
        });

        jbtAnnulla.setText("Annulla");
        jbtAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnnullaActionPerformed(evt);
            }
        });

        jbtRegistra.setText("Registra");
        jbtRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRegistraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtNuovo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtAnnulla, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtEsci, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtNuovo)
                    .addComponent(jbtModifica)
                    .addComponent(jbtElimina)
                    .addComponent(jbtEsci)
                    .addComponent(jbtRicerca)
                    .addComponent(jbtAnnulla)
                    .addComponent(jbtRegistra))
                .addContainerGap())
        );

        jpUtente.setBackground(new java.awt.Color(255, 255, 166));
        jpUtente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Informazioni Utente di sistema   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        javax.swing.GroupLayout jpUtenteLayout = new javax.swing.GroupLayout(jpUtente);
        jpUtente.setLayout(jpUtenteLayout);
        jpUtenteLayout.setHorizontalGroup(
            jpUtenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUtenteLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(435, 435, 435))
        );
        jpUtenteLayout.setVerticalGroup(
            jpUtenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpSelezionePannello, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpUtente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtpSelezionePannello, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpUtente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEsciActionPerformed
        // chiusura della maschera
        this.dispose();
}//GEN-LAST:event_jbtEsciActionPerformed

    private void jpfPassword1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfPassword1MousePressed
        
}//GEN-LAST:event_jpfPassword1MousePressed

    private void jpfPassword1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpfPassword1FocusGained
        Generic.caretPosition(jpfPassword1);
        
}//GEN-LAST:event_jpfPassword1FocusGained

    private void jpfPassword1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpfPassword1FocusLost
        Generic.impostaColor(jpfPassword1);
        // forma il char e poi lo passa in stringa
        char[] Pass1 = jpfPassword1.getPassword();
        String Password1 = new String(Pass1);
        if (!Generic.strVuota(Password1)){
            jpfPassword2.setEnabled(true);
            jpfPassword2.setBackground(AbilitaSfondo);
        }
}//GEN-LAST:event_jpfPassword1FocusLost

    private void jpfPassword1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfPassword1KeyTyped
        Generic.setLenText(evt, 20);
        
}//GEN-LAST:event_jpfPassword1KeyTyped

    private void jpfPassword2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfPassword2MousePressed
        
}//GEN-LAST:event_jpfPassword2MousePressed

    private void jpfPassword2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpfPassword2FocusGained
       Generic.caretPosition(jpfPassword2);
        
}//GEN-LAST:event_jpfPassword2FocusGained

    private void jpfPassword2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpfPassword2FocusLost
        Generic.impostaColor(jpfPassword2);
        // password1
        char[] Pass1 = jpfPassword1.getPassword();
        String Password1 = new String(Pass1);
        // Password2
        char[] Pass2 = jpfPassword2.getPassword();
        String Password2 = new String(Pass2);

        if (!Generic.strVuota(Password1)){
            if (!Generic.strVuota(jpfPassword2.getPassword().toString())){
                if (!Password1.trim().equals(Password2.trim())){
                    Generic.notifica("La due Password non corrispondono. Ripetere la procedura.", 3);
                    jpfPassword1.setText("");
                    jpfPassword2.setText("");
                    jpfPassword1.requestFocus();
                }
            }
        }
}//GEN-LAST:event_jpfPassword2FocusLost

    private void jpfPassword2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfPassword2KeyTyped
        Generic.setLenText(evt, 20);
       
}//GEN-LAST:event_jpfPassword2KeyTyped

    private void jtfUtenteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfUtenteMousePressed
        
}//GEN-LAST:event_jtfUtenteMousePressed

    private void jtfUtenteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfUtenteFocusGained
       Generic.caretPosition(jtfUtente);
        
}//GEN-LAST:event_jtfUtenteFocusGained

    private void jtfUtenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfUtenteFocusLost
       Generic.impostaColor(jtfUtente);
}//GEN-LAST:event_jtfUtenteFocusLost

    private void jtfUtenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUtenteKeyTyped
        Generic.setLenText(evt,20);
       
}//GEN-LAST:event_jtfUtenteKeyTyped

    private void jtfCodFiscMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfCodFiscMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCodFiscMousePressed

    private void jtfCodFiscFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodFiscFocusGained
       Generic.caretPosition(jtfCodFisc);
    }//GEN-LAST:event_jtfCodFiscFocusGained

    private void jtfCodFiscFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodFiscFocusLost
        Generic.impostaColor(jtfCodFisc);
        if(!flMessaggio){
		   flMessaggio = true;
		   // Verifica del codice fiscale
		   verificaCodiceFiscale();
	}
    }//GEN-LAST:event_jtfCodFiscFocusLost

    private void jtfCodFiscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodFiscKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCodFiscKeyTyped

    private void jtfNomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfNomeMousePressed
        
}//GEN-LAST:event_jtfNomeMousePressed

    private void jtfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNomeFocusGained
        Generic.caretPosition(jtfNome);
       
}//GEN-LAST:event_jtfNomeFocusGained

    private void jtfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNomeFocusLost
       Generic.impostaColor(jtfNome);
}//GEN-LAST:event_jtfNomeFocusLost

    private void jtfNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomeKeyTyped
        Generic.setLenText(evt, 20);
        // ho gestito le modifiche quindi deve chiedere il salvataggio
        IsModificato = true;
}//GEN-LAST:event_jtfNomeKeyTyped

    private void jtfCognomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfCognomeMousePressed
        
}//GEN-LAST:event_jtfCognomeMousePressed

    private void jtfCognomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCognomeFocusGained
        Generic.caretPosition(jtfCognome);
        
}//GEN-LAST:event_jtfCognomeFocusGained

    private void jtfCognomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCognomeFocusLost
        Generic.impostaColor(jtfCognome);
}//GEN-LAST:event_jtfCognomeFocusLost

    private void jtfCognomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCognomeKeyTyped
        Generic.setLenText(evt, 20);
        // ho gestito le modifiche quindi deve chiedere il salvataggio
        IsModificato = true;
}//GEN-LAST:event_jtfCognomeKeyTyped

    private void jtfIndirizzoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfIndirizzoMousePressed
        
}//GEN-LAST:event_jtfIndirizzoMousePressed

    private void jtfIndirizzoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIndirizzoFocusGained
      Generic.caretPosition(jtfIndirizzo);
       
}//GEN-LAST:event_jtfIndirizzoFocusGained

    private void jtfIndirizzoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIndirizzoFocusLost
       Generic.impostaColor(jtfIndirizzo);
}//GEN-LAST:event_jtfIndirizzoFocusLost

    private void jtfIndirizzoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIndirizzoKeyTyped
        Generic.setLenText(evt, 50);
        // ho gestito le modifiche quindi deve chiedere il salvataggio
        IsModificato = true;
}//GEN-LAST:event_jtfIndirizzoKeyTyped

    private void jbtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPasswordActionPerformed
        jpfPassword1.setEnabled(true);
        jpfPassword1.setBackground(AbilitaSfondo);
        jpfPassword1.requestFocus();
}//GEN-LAST:event_jbtPasswordActionPerformed

    private void jbtAvantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAvantiActionPerformed
        // Controlla che la chiave di ricerca sia fornita

        if (controllaKey()){
             String  sUtente = jtfUtente.getText();
             char pass[] = jpfPassword1.getPassword();
             String spassw = new String(pass);
             String  sCodFisc = jtfCodFisc.getText();
            try{
                // Tenta la creazione dell'oggetto Utente
                oUtente = new Utente(sUtente,spassw);
                
                oUtente.IsNew = false;
                

            }catch(Exception e){
                // Crea un oggetto vuoto e valorizza la chiave se va in eccezione
                oUtente = new Utente();

                oUtente.user         = sUtente;
                oUtente.password     = spassw;
                oUtente.cf_utente    = sCodFisc;

            }
            // default della maschera
            Default();
            caricaCampi();
            
            jtpSelezionePannello.setEnabledAt(2, true);

            // Imposta lo Stato del Record
            if (oUtente.IsNew){
                StatoMaschera = Generic.statoINSERIMENTO;
            }else{
                StatoMaschera = Generic.statoMODIFICA;
            }
            // Segnala che il controllo della chiave e' ok
            ChiaveOk = true;

            // Reimposta le abilitazioni
            settaAbilitaCampi();
            settaAbilitaPulsanti();
            // imposto il focus
            jtfNome.requestFocus();
        }
}//GEN-LAST:event_jbtAvantiActionPerformed

    /** Controlla la chiave del Record
    */
    private boolean controllaKey(){


        // Codice Fiscale
        if (Generic.strVuota(jtfCodFisc.getText()))
        {
            Generic.notifica("Inserire Il codice fiscale.", 3);
            jtfCodFisc.requestFocus();
            return false;
        }

        // Username
        if (Generic.strVuota(this.jtfUtente.getText()))
        {
            Generic.notifica("Inserire lo username.", 3);
            jtfUtente.requestFocus();
            return false;
        }


	char[] Pass1 = jpfPassword1.getPassword();
	char[] Pass2 = jpfPassword2.getPassword();
	String Password1 = new String(Pass1);
	String Password2 = new String(Pass2);

	if (!Generic.strVuota(Password1) && !Generic.strVuota(Password2)) {
	        if (!Password1.trim().equals(Password2.trim())){
	            Generic.notifica("La due Password non corrispondono. Ripetere la procedura.", 3);
	            jpfPassword1.setText("");
	            jpfPassword2.setText("");
	            jpfPassword1.requestFocus();
	            return false;
	        }
	    }else {
	        Generic.notifica("E' necessario riscrivere la Password per conferma.!", 3);
	        jpfPassword1.requestFocus();
	        return false;
	    }


       return true;
    }

    /**
     * carica i dati nei campi
     **/
    private void caricaCampi(){



	// Username
	if(!Generic.strVuota(oUtente.user)){
	       jtfUtente.setText("" + oUtente.user);
	}else{
               jtfUtente.setText("");
        }

        // Codice fiscale
        if(!Generic.strVuota(oUtente.cf_utente )){
           jtfCodFisc.setText(oUtente.cf_utente );
        }else{
           jtfCodFisc.setText("");
        }
        
        // nome
        if(!Generic.strVuota(oUtente.nome)){
           jtfNome.setText(oUtente.nome);
        }else{
           jtfNome.setText("");
        }

        // Cognome
        if(!Generic.strVuota(oUtente.cognome)){
           jtfCognome.setText(oUtente.cognome);
        }else{
           jtfCognome.setText("");
        }

        // Indirizzo
        if(!Generic.strVuota(oUtente.indirizzo)){
           jtfIndirizzo.setText(oUtente.indirizzo);
        }else{
           jtfIndirizzo.setText("");
        }

        // Password
        if(!Generic.strVuota(oUtente.password )){
           jpfPassword1.setText(oUtente.password);
        }else{
           jpfPassword1.setText("");
        }

        // Password 2
        if(!Generic.strVuota(oUtente.password )){
           jpfPassword2.setText(oUtente.password);
        }else{
           jpfPassword2.setText("");
        }


        // Telefono
        if(!Generic.strVuota(oUtente.telefono)){
           jtfTelefono.setText(oUtente.telefono);
        }else{
           jtfTelefono.setText("");
        }

        if(!Generic.strVuota(oUtente.cel)){
           jtfCel.setText(oUtente.cel);
        }else{
           jtfCel.setText("");
        }

       
	    // Tipologia ComboBox
	    if(oUtente.tipologia   > 0){
	      //  impostaSelezioneTipologie(oUtente.tipologia);
	    }else{
	      //this.jcbTipologie.setSelectedIndex(0);
	    }


        // Flag modifiche impostato a false
        IsModificato = false;

        // Seleziona il pannello del Dettaglio
        this.jtpSelezionePannello.setSelectedComponent(jpDettaglio);
    }

    private void jbtNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNuovoActionPerformed
        // Segnala che si deve passare il Controllo della Chiave
        ChiaveOk = false;

	// Azzera i Controlli della Form
	//Attiva i pulsanti al momento dell'intervento su di un campo
	azzeraCampi();

        // Crea un oggetto vuoto
        try{
            oUtente = new Utente();

            // Carica eventuali valori di Default
            Default();
            // setta lo stato della maschera
            settaStatoMaschera(Generic.statoINSERIMENTO);
            // imposta il fuoco al campo del codice fiscale
            jtfUtente.requestFocus();

	}catch(Exception e){
                   System.err.println("" + e.getMessage());
		    Generic.notifica("Problemi di inserimento utente!", 3);
        }
    }//GEN-LAST:event_jbtNuovoActionPerformed

    private void jbtAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnnullaActionPerformed
       if (StatoMaschera == Generic.statoMODIFICA){
	    // Ricarica i valori precedenti
            caricaCampi();
            settaStatoMaschera(Generic.statoBROWSE);
	}else{
            // Azzera tutti i valori
            azzeraCampi();
            settaStatoMaschera(Generic.statoINDEFINITO);
	}

    }//GEN-LAST:event_jbtAnnullaActionPerformed

    private void jbtEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEliminaActionPerformed
         if (Generic.notifica("Vuoi cancellare il Record selezionato ?", 1, "Sì", "No",1) == 1){
            if (oUtente.eliminaRecord()){
                Generic.notifica("Il Record è stato Cancellato !", 2);
                azzeraCampi();
                settaStatoMaschera(Generic.statoINDEFINITO);

		        // Disabilita il Pannello della Lista
		        jtpSelezionePannello.setEnabledAt(1, false);
            }else{
                Generic.notifica("Problemi durante la Cancellazione! Riprovare.", 3);
            }
        }else{
            settaStatoMaschera(Generic.statoBROWSE);
	}
    }//GEN-LAST:event_jbtEliminaActionPerformed

    private void jbtModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModificaActionPerformed
        settaStatoMaschera(Generic.statoMODIFICA);
        jtfNome.requestFocus();
    }//GEN-LAST:event_jbtModificaActionPerformed


    /************************************************************
     * Carica tutti i dati nelle variabili prima di registrarli *
     ***********************************************************/
    public void valorizzaGenerale()
    {


        oUtente = new Utente();

        // username
        if (!Generic.strVuota(jtfUtente.getText())){
           oUtente.user = jtfUtente.getText();
        }else{
           oUtente.user     = "";
        }

        // cf_utente
        if (!Generic.strVuota(jtfCodFisc.getText())){
           oUtente.cf_utente = jtfCodFisc.getText();
        }else{
           oUtente.cf_utente     = "";
        }

        // passwd
        
        if (!Generic.strVuota(Utente.CryptStr(jpfPassword1.getText()))){
           oUtente.password = Utente.CryptStr(jpfPassword1.getText());
        }else{
           oUtente.password     = "";
        }

       // Nome
        if (!Generic.strVuota(jtfNome.getText())){
           oUtente.nome = jtfNome.getText();
        }else{
           oUtente.nome     = "";
        }

        // Cognome
        if (!Generic.strVuota(jtfCognome.getText())){
           oUtente.cognome = jtfCognome.getText();
        }else{
           oUtente.cognome     = "";
        }

        // Cel
        if (!Generic.strVuota(jtfCel.getText())){
           oUtente.cel = jtfCel.getText();
        }else{
           oUtente.cel     = "";
        }

        // Telefono
        if (!Generic.strVuota(jtfTelefono.getText())){
           oUtente.telefono = jtfTelefono.getText();
        }else{
           oUtente.telefono     = "";
        }

        // Indirizzo
        if (!Generic.strVuota(jtfIndirizzo.getText())){
           oUtente.indirizzo = jtfIndirizzo.getText();
        }else{
           oUtente.indirizzo     = "";
        }
        /*
        // Tipologia
        if (jcbTipologie.getSelectedIndex() != -1)
           oUtente.tipologia = Categorie.getCodiceDaCombo((ComboBox)jcbTipologie.getSelectedItem());
        else
           oUtente.tipologia = -1;
*/

        // Flag Nuovo
        if(StatoMaschera == 2){
           oUtente.IsNew = false;
           
        }

    }

    private void jbtRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRegistraActionPerformed
         if(!controllaKey())
        {
            return;
        }

        // carica i valori per poi registrarli
        valorizzaGenerale();

        try{
            if (oUtente.registra())
            {
                settaStatoMaschera(Generic.statoBROWSE);
		Generic.notifica("Utente registrato.", 2);
		// Disabilita il Pannello della Lista
		jtpSelezionePannello.setEnabledAt(1, false);

                // Flag modifiche impostato a false
                IsModificato = false;
            }else{
               Generic.notifica("Errore nella fase di registrazione Utente.", 3);
            }
        }catch(Exception e){
            Generic.notifica("Problemi in fase di registrazione Utente!", 3);
            return;
        }
    }//GEN-LAST:event_jbtRegistraActionPerformed

    private void jbtRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRicercaActionPerformed
        if (jtpSelezionePannello.getSelectedComponent() == jpRicerca){
            // svuota la lista
            impostaJTRighe();
            // ricarica la tabella
            caricaCampiLista();
        }else{
	        jtpSelezionePannello.setSelectedComponent(jpRicerca);
	}
    }//GEN-LAST:event_jbtRicercaActionPerformed

    private void jTableModelloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableModelloMouseClicked
         if(evt.getClickCount()==2){
		     caricaRecordSelezione();
         }
    }//GEN-LAST:event_jTableModelloMouseClicked

    /******************************************************************
     * Imposta la Stringa di Ricerca per la selezione nella JTable *
     ******************************************************************/
    private String creaCondizioneRicerca(){
        String sSql="";

        String sFltCodice     = "" ;
        String sFltNick       = "" ;
        String sFltCognome    = "" ;
        String sFltNome       = "" ;
        String sFltIndirizzo  = "" ;

        String sSqlCodice     = "";
        String sSqlNick       = "" ;
        String sSqlCognome    = "" ;
        String sSqlNome       = "" ;
        String sSqlIndirizzo  = "" ;

        boolean fMettiAnd = false;

        if (Generic.strVuota(jtfFindCodice.getText()) &&
            Generic.strVuota(jtfFindNick.getText()) &&
            Generic.strVuota(jtfFindNome.getText()) &&
            Generic.strVuota(jtfFindCognome.getText()) &&
            Generic.strVuota(jtfFindIndirizzo.getText())
            )
        {
            return sSql;
        }

	    sFltCodice    = jcbFindCodice.getSelectedItem()+ " " ;
	    sFltNome      = jcbFindNome.getSelectedItem()+ " " ;
	    sFltCognome   = jcbFindCognome.getSelectedItem()+ " " ;
	    sFltIndirizzo = jcbFindIndirizzo.getSelectedItem()+ " " ;
	    sFltNick      = jcbFindNick.getSelectedItem()+ " " ;


            //FILTRO SUL CODICE
	    if (!jtfFindCodice.getText().equals(""))
	    {
	        if (fMettiAnd == true)
	            sSql = sSql + " AND ";
	        if (jcbFindCodice.getSelectedItem().equals("LIKE")){
	            // LIKE
	            if (jchbFindCodice.isSelected() == true) {
	                //Selezionato che contiene
	                if (!jtfFindCodice.getText().endsWith("%"))
                        jtfFindCodice.setText (jtfFindCodice.getText()+"%");
                    if (!jtfFindCodice.getText().startsWith("%"))
                        jtfFindCodice.setText ("%"+jtfFindCodice.getText());
                    jtfFindCodice.setText(jtfFindCodice.getText());
	            }
	            else{
	                if (jtfFindCodice.getText().startsWith("%"))
                        jtfFindCodice.setText (jtfFindCodice.getText().substring(1));
                    if (!jtfFindCodice.getText().endsWith("%"))
                        jtfFindCodice.setText (jtfFindCodice.getText()+"%");
                    jtfFindCodice.setText(jtfFindCodice.getText());
	            }
	            sSql = sSql + " cf_utente " + sFltCodice + "'" + jtfFindCodice.getText().trim()+ "'";
	        }
	        else {
	            //UGUALE
	            if (jtfFindCodice.getText().endsWith("%"))
                    jtfFindCodice.setText (jtfFindCodice.getText().substring(0, (jtfFindCodice.getText().length()-1)));
                if (jtfFindCodice.getText().startsWith("%"))
                    jtfFindCodice.setText (jtfFindCodice.getText().substring(1));

	            sFltCodice = " = ";
	            sSql = sSql + " cf_utente " + sFltCodice + "'" +jtfFindCodice.getText().trim()+ "'";
	        }
	        fMettiAnd = true;
	    }

            //FILTRO SUL NOME
	    if (!jtfFindNome.getText().equals(""))
	    {
	        if (fMettiAnd == true)
	            sSql = sSql + " AND ";
	        if (jcbFindNome.getSelectedItem().equals("LIKE")){
	            // LIKE
	            if (jchbNome.isSelected() == true) {
	                //Selezionato che contiene
	                if (!jtfFindNome.getText().endsWith("%"))
                        jtfFindNome.setText (jtfFindNome.getText()+"%");
                    if (!jtfFindNome.getText().startsWith("%"))
                        jtfFindNome.setText ("%"+jtfFindNome.getText());
                    jtfFindNome.setText(jtfFindNome.getText());
	            }
	            else{
	                if (jtfFindNome.getText().startsWith("%"))
                        jtfFindNome.setText (jtfFindNome.getText().substring(1));
                    if (!jtfFindNome.getText().endsWith("%"))
                        jtfFindNome.setText (jtfFindNome.getText()+"%");
                    jtfFindNome.setText(jtfFindNome.getText());
	            }
	            sSql = sSql + " nome " + sFltNome + "'" + jtfFindNome.getText().trim()+ "'";
	        }
	        else {
	            //UGUALE
	            if (jtfFindNome.getText().endsWith("%"))
                    jtfFindNome.setText (jtfFindNome.getText().substring(0, (jtfFindNome.getText().length()-1)));
                if (jtfFindNome.getText().startsWith("%"))
                    jtfFindNome.setText (jtfFindNome.getText().substring(1));

	            sFltNome = " = ";
	            sSql = sSql + " nome " + sFltNome + "'" +jtfFindNome.getText().trim()+ "'";
	        }
	        fMettiAnd = true;
	    }

	    //FILTRO SUL COGNOME
	    if (!jtfFindCognome.getText().equals(""))
	    {
	        if (fMettiAnd == true)
	            sSql = sSql + " AND ";
	        if (jcbFindCognome.getSelectedItem().equals("LIKE")){
	            // LIKE
	            if (jchbCognome.isSelected() == true) {
	                //Selezionato che contiene
	                if (!jtfFindCognome.getText().endsWith("%"))
                        jtfFindCognome.setText (jtfFindCognome.getText()+"%");
                    if (!jtfFindCognome.getText().startsWith("%"))
                        jtfFindCognome.setText ("%"+jtfFindCognome.getText());
                    jtfFindCognome.setText(jtfFindCognome.getText());
	            }
	            else{
	                if (jtfFindCognome.getText().startsWith("%"))
                        jtfFindCognome.setText (jtfFindCognome.getText().substring(1));
                    if (!jtfFindCognome.getText().endsWith("%"))
                        jtfFindCognome.setText (jtfFindCognome.getText()+"%");
                    jtfFindCognome.setText(jtfFindCognome.getText());
	            }
	            sSql = sSql + " cognome " + sFltCognome + "'" + jtfFindCognome.getText().trim()+ "'";
	        }
	        else {
	            //UGUALE
	            if (jtfFindCognome.getText().endsWith("%"))
                    jtfFindCognome.setText (jtfFindCognome.getText().substring(0, (jtfFindCognome.getText().length()-1)));
                if (jtfFindCognome.getText().startsWith("%"))
                    jtfFindCognome.setText (jtfFindCognome.getText().substring(1));

	            sFltCognome = " = ";
	            sSql = sSql + " cognome " + sFltCognome + "'" +jtfFindCognome.getText().trim()+ "'";
	        }
	        fMettiAnd = true;
	    }

           //FILTRO SUL NICKNAME
	    if (!jtfFindNick.getText().equals(""))
	    {
	        if (fMettiAnd == true)
	            sSql = sSql + " AND ";
	        if (jcbFindNick.getSelectedItem().equals("LIKE")){
	            // LIKE
	            if (jchbNick.isSelected() == true) {
	                //Selezionato che contiene
	                if (!jtfFindNick.getText().endsWith("%"))
                        jtfFindNick.setText (jtfFindNick.getText()+"%");
                    if (!jtfFindNick.getText().startsWith("%"))
                        jtfFindNick.setText ("%"+jtfFindNick.getText());
                    jtfFindNick.setText(jtfFindNick.getText());
	            }
	            else{
	                if (jtfFindNick.getText().startsWith("%"))
                        jtfFindNick.setText (jtfFindNick.getText().substring(1));
                    if (!jtfFindNick.getText().endsWith("%"))
                        jtfFindNick.setText (jtfFindNick.getText()+"%");
                    jtfFindNick.setText(jtfFindNick.getText());
	            }
	            sSql = sSql + " user " + sFltNick + "'" + jtfFindNick.getText().trim()+ "'";
	        }
	        else {
	            //UGUALE
	            if (jtfFindNick.getText().endsWith("%"))
                    jtfFindNick.setText (jtfFindNick.getText().substring(0, (jtfFindNick.getText().length()-1)));
                if (jtfFindNick.getText().startsWith("%"))
                    jtfFindNick.setText (jtfFindNick.getText().substring(1));

	            sFltNick = " = ";
	            sSql = sSql + " user " + sFltNick + "'" +jtfFindNick.getText().trim()+ "'";
	        }
	        fMettiAnd = true;
	    }


            //FILTRO INDIRIZZO
	    if (!jtfFindIndirizzo.getText().equals(""))
	    {
	        if (fMettiAnd == true)
	            sSql = sSql + " AND ";
	        if (jcbFindIndirizzo.getSelectedItem().equals("LIKE")){
	            // LIKE
	            if (jchbIndirizzo.isSelected() == true) {
	                //Selezionato che contiene
	                if (!jtfFindIndirizzo.getText().endsWith("%"))
                        jtfFindIndirizzo.setText (jtfFindIndirizzo.getText()+"%");
                    if (!jtfFindIndirizzo.getText().startsWith("%"))
                        jtfFindIndirizzo.setText ("%"+jtfFindIndirizzo.getText());
                    jtfFindIndirizzo.setText(jtfFindIndirizzo.getText());
	            }
	            else{
	                if (jtfFindIndirizzo.getText().startsWith("%"))
                        jtfFindIndirizzo.setText (jtfFindIndirizzo.getText().substring(1));
                    if (!jtfFindIndirizzo.getText().endsWith("%"))
                        jtfFindIndirizzo.setText (jtfFindIndirizzo.getText()+"%");
                    jtfFindIndirizzo.setText(jtfFindIndirizzo.getText());
	            }
	            sSql = sSql + " indirizzo " + sFltIndirizzo + "'" + jtfFindIndirizzo.getText().trim()+ "'";
	        }
	        else {
	            //UGUALE
	            if (jtfFindIndirizzo.getText().endsWith("%"))
                    jtfFindIndirizzo.setText (jtfFindIndirizzo.getText().substring(0, (jtfFindIndirizzo.getText().length()-1)));
                if (jtfFindIndirizzo.getText().startsWith("%"))
                    jtfFindIndirizzo.setText (jtfFindIndirizzo.getText().substring(1));

	            sFltIndirizzo = " = ";
	            sSql = sSql + " indirizzo " + sFltIndirizzo + "'" +jtfFindIndirizzo.getText().trim()+ "'";
	        }
	        fMettiAnd = true;
	    }

        return sSql;
    }



    /** Carica la tabella della Jtable
     */
    protected void caricaCampiLista(){


	    String sCondizione = creaCondizioneRicerca();

    	    vtDati.removeAllElements();
    	    vtDati = Utente.getListaInVettore(sCondizione);

    	     if (vtDati.size() <= 0){
    	       Generic.notifica("Nessun Record trovato per la Selezione impostata!", 2);
    	       return;
    	    }

	        Vector vtElementi = new Vector();
    		for (int index = 0; index < vtDati.size(); index ++)
	 	    {
		        Utente oAppo = (Utente)(vtDati.elementAt(index));


    		    // dichiaro una variabile stringa di record
	            String[] Valori = new String[7];
		        // carico la tabella con i dati del vettore
    		    Valori[oMdS.colonnaCODICE]    = "" + oAppo.cf_utente ;
                    Valori[oMdS.colonnaUSER]     = "" + oAppo.user  ;
                    Valori[oMdS.colonnaCOGNOME]   = "" + oAppo.cognome;
                    Valori[oMdS.colonnaNOME]       = "" + oAppo.nome;
                    Valori[oMdS.colonnaCEL]     = "" + oAppo.cel;
                    Valori[oMdS.colonnaTEL]       = "" + oAppo.telefono;
                    Valori[oMdS.colonnaINDIRIZZO]       = "" + oAppo.indirizzo;


                    vtElementi.addElement(Valori);
                  }

		    // Aggiunge gli Elementi del Vettore al Modello della JTable
		    oMdS.setProfilo(vtElementi);

		    // Aggiorna la JTable
                    oMdS.aggiorna();

                    // mi posiziono sulla prima riga della tabella
                    if(jTableModello.getRowCount() > 0){
                      jTableModello.setRowSelectionInterval(0,0);
                    }
                    jTableModello.requestFocus();

                            // Abilita il Pannello della Lista
                            jtpSelezionePannello.setEnabledAt(1, true);
                    jtpSelezionePannello.setSelectedComponent(jpListaUtenti);


    }

    private void jTableModelloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableModelloKeyPressed
        if(evt.getKeyCode()== 10){
		     caricaRecordSelezione();
        }
    }//GEN-LAST:event_jTableModelloKeyPressed

    private void jtfFindCodiceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfFindCodiceMousePressed
       
}//GEN-LAST:event_jtfFindCodiceMousePressed

    private void jtfFindCodiceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindCodiceFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfFindCodice);
        
}//GEN-LAST:event_jtfFindCodiceFocusGained

    private void jtfFindCodiceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindCodiceFocusLost
        Generic.impostaColor(jtfFindCodice);
}//GEN-LAST:event_jtfFindCodiceFocusLost

    private void jtfFindNomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfFindNomeMousePressed
        
}//GEN-LAST:event_jtfFindNomeMousePressed

    private void jtfFindNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindNomeFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfFindNome);
        
}//GEN-LAST:event_jtfFindNomeFocusGained

    private void jtfFindNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindNomeFocusLost
        Generic.impostaColor(jtfFindNome);
}//GEN-LAST:event_jtfFindNomeFocusLost

    private void jtfFindCognomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfFindCognomeMousePressed
        
}//GEN-LAST:event_jtfFindCognomeMousePressed

    private void jtfFindCognomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindCognomeFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfFindCognome);
       
}//GEN-LAST:event_jtfFindCognomeFocusGained

    private void jtfFindCognomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindCognomeFocusLost
        Generic.impostaColor(jtfFindCognome);
}//GEN-LAST:event_jtfFindCognomeFocusLost

    private void jtfFindNickMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfFindNickMousePressed
        
}//GEN-LAST:event_jtfFindNickMousePressed

    private void jtfFindNickFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindNickFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfFindNick);
        
}//GEN-LAST:event_jtfFindNickFocusGained

    private void jtfFindNickFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindNickFocusLost
        Generic.impostaColor(jtfFindNick);
}//GEN-LAST:event_jtfFindNickFocusLost

    private void jtfFindIndirizzoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfFindIndirizzoMousePressed
        
}//GEN-LAST:event_jtfFindIndirizzoMousePressed

    private void jtfFindIndirizzoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindIndirizzoFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfFindIndirizzo);
       
}//GEN-LAST:event_jtfFindIndirizzoFocusGained

    private void jtfFindIndirizzoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFindIndirizzoFocusLost
        Generic.impostaColor(jtfFindIndirizzo);
}//GEN-LAST:event_jtfFindIndirizzoFocusLost

    private void jtfTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfTelefonoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTelefonoMousePressed

    private void jtfTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTelefonoFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfTelefono);
    }//GEN-LAST:event_jtfTelefonoFocusGained

    private void jtfTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTelefonoFocusLost
        Generic.impostaColor(jtfTelefono);
    }//GEN-LAST:event_jtfTelefonoFocusLost

    private void jtfTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTelefonoKeyTyped

    private void jtfCelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfCelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCelMousePressed

    private void jtfCelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCelFocusGained
        ///selezione di un oggetto da tastiera
        Generic.caretPosition(jtfCel);
    }//GEN-LAST:event_jtfCelFocusGained

    private void jtfCelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCelFocusLost
       Generic.impostaColor(jtfCel);
    }//GEN-LAST:event_jtfCelFocusLost

    private void jtfCelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCelKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCelKeyTyped

    /**
     *  Carca il record selezionato
     */
    public void caricaRecordSelezione(){


	        try{
		    int iCurrentRow = jTableModello.getSelectedRow();
		    if (iCurrentRow > -1) {

		            int numeroRecord = cercaIndice (iCurrentRow);
		            oUtente = (Utente)(vtDati.elementAt(numeroRecord));
		            // Carica il Record sulla Form
                            caricaCampi();

                           oUtente.IsNew = false;
	                   // setta lo stato della maschera
                           settaStatoMaschera(Generic.statoBROWSE);

                     }

		 }catch(Exception e){
		 }



	}

        /** Ricerca l'indice nel vettore
	*/
	int cercaIndice (int iCurrentRow){
	    // chiave
	    String iCodice="";

	    int iIndice=0;


           MdS modello = (MdS)jTableModello.getModel();

	    iCodice = jTableModello.getValueAt(jTableModello.getSelectedRow(), dammiIndiceColonna(modello.colonnaCODICE)).toString();
	    for (int index=0; index < vtDati.size(); index ++)
		{
            Utente oAppo = (Utente)(vtDati.elementAt(index));
            if (oAppo.cf_utente .equals(iCodice)){
                iIndice = index;
                break;
            }
        }
        return iIndice;
	}

    /** Ritorna l'Indice della colonna quando viene spostata
    */
    public int dammiIndiceColonna(int Indice){
        for (int i=0;i<this.jTableModello.getColumnCount();i++){
		    javax.swing.table.TableColumn tc = this.jTableModello.getColumnModel().getColumn(i);
            if (tc.getModelIndex()==Indice){
                return i;
            }
        }
        return Indice;


    }

        // Imposta le singole righe della JTable
	private void impostaJTRighe()
	{
	    // JTABLE --> SORTER --> MODELLO
	    oMdS = new MdS();

	    // Associa all'oggetto grafico jtable l'oggetto
	    // modello appena istanziato
	    jTableModello.setModel(oMdS);

	    // Associazione colonne con RENDERER e impostazione proprietà colonne
	    // Istanzio l'oggetto render per le colonne
	    // L'oggetto render serve per impostare graficamente la tabella
        RdS myRenderer = new RdS();

        // Imposto le proprietà delle colonne
        // colonnaCODICE
	javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaCODICE);
        oColumn.setPreferredWidth(160);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaCODICE);
        oColumn.setResizable(true);

        // colonnaUSER
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaUSER);
        oColumn.setPreferredWidth(140);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaUSER);
        oColumn.setResizable(true);

        // colonnaCOGNOME
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaCOGNOME);
        oColumn.setPreferredWidth(140);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaCOGNOME);
        oColumn.setResizable(true);

        // colonnaNOME
        
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaNOME);
        oColumn.setPreferredWidth(140);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaNOME);
        oColumn.setResizable(true);

        // colonnaCEL
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaCEL);
        oColumn.setPreferredWidth(130);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaCEL);
        oColumn.setResizable(true);

        // colonnaTEL
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaTEL);
        oColumn.setPreferredWidth(130);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaTEL);
        oColumn.setResizable(true);

         // colonnaINDIRIZZO
        oColumn = this.jTableModello.getColumnModel().getColumn(this.oMdS.colonnaINDIRIZZO);
        oColumn.setPreferredWidth(150);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdS.colonnaINDIRIZZO);
        oColumn.setResizable(true);

        // Modalità selezione
        jTableModello.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //jTableModello.setCellSelectionEnabled(true);
        //jtFogli.setShowVerticalLines(false);
        //jtFogli.setShowGrid(false);
        jTableModello.setIntercellSpacing(new Dimension(0, 0));
        jTableModello.setAutoResizeMode(jTableModello.AUTO_RESIZE_OFF);
        jTableModello.setShowHorizontalLines(true);

        // Impostazione dei tasti Home, End, PgDown e PgUp
        // Tasti per movimento nella tabella
        KeyStroke stroke_home   = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_HOME, 0);
        KeyStroke stroke_end    = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_END, 0);
        KeyStroke stroke_pgdown = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0);
        KeyStroke stroke_pgup   = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, 0);

        // Implementa l'Ascoltatore per i tasti
        java.awt.event.ActionListener action = new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent event) {

                int nrighe = oMdS.getRowCount();
                if (nrighe>0){
                    if (event.getActionCommand().equals("HOME")){
                        jTableModello.setRowSelectionInterval(0,0);
                    }
                    else if (event.getActionCommand().equals("END")){
                        jTableModello.setRowSelectionInterval(nrighe-1,nrighe-1);
                    }
                    else if (event.getActionCommand().equals("PGDOWN")){
                        int rigacor = jTableModello.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor+15;
                            if (rigacor>nrighe)
                                rigacor = nrighe;

                        }
                        jTableModello.setRowSelectionInterval(rigacor-1,rigacor-1);
                    }
                    else if (event.getActionCommand().equals("PGUP")){
                        int rigacor = jTableModello.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor-15;
                            if (rigacor<1)
                                rigacor = 1;
                        }
                        jTableModello.setRowSelectionInterval(rigacor-1,rigacor-1);

                    }
                    jTableModello.scrollRectToVisible(jTableModello.getCellRect(jTableModello.getSelectedRow(),0, true));

                }
            }
        };

        jTableModello.registerKeyboardAction(action, "HOME", stroke_home, JComponent.WHEN_FOCUSED);
        jTableModello.registerKeyboardAction(action, "END", stroke_end, JComponent.WHEN_FOCUSED);
        jTableModello.registerKeyboardAction(action, "PGDOWN", stroke_pgdown, JComponent.WHEN_FOCUSED);
        jTableModello.registerKeyboardAction(action, "PGUP", stroke_pgup, JComponent.WHEN_FOCUSED);

        // Implementa il codice per la selezione delle righe
        ListSelectionModel lsmRow = jTableModello.getSelectionModel();

     }

   
    // Azzera i campi della maschera
    private void azzeraCampi(){
	    jtfCodFisc.setText("");
            jtfUtente.setText("");
            jpfPassword1.setText("");
            jpfPassword2.setText("");
	    jtfNome.setText("");
	    jtfCognome.setText("");
            jtfIndirizzo.setText("");
            jtfTelefono.setText("");
            jtfCel.setText("");
           // this.jcbTipologie.setSelectedIndex(0);


            impostaJTRighe();
            // Flag modifiche impostato a false
            IsModificato = false;
    }
    /**
     * Centra la Maschera
     * @param b
     */
    public void setVisible(boolean b)
    {
        if(b)
            Generic.centraGui(this);
        super.setVisible(b);
    }

    
    /** Imposta il default per la maschera
    */
    private void Default(){
       this.lblNome.setText("NOME: " + Generic.oUtente.nome);
       this.lblCognome.setText("COGNOME: " + Generic.oUtente.cognome);
    }

    
    /*  Imposta le immagini relative ad ogni bottone
     */
    private void settaImmaginiPulsanti(){

         String path = "Images/";

         ImageIcon imiUscita = new ImageIcon(path + "uscita24.gif");
         this.jbtEsci.setIcon(imiUscita);
         ImageIcon imiInsert = new ImageIcon(path + "inserimento24.gif");
         this.jbtNuovo.setIcon(imiInsert);
         ImageIcon imiRicerca = new ImageIcon(path + "ricerca24.gif");
         this.jbtRicerca.setIcon(imiRicerca);
         ImageIcon imiModifica = new ImageIcon(path + "modifica24.gif");
         jbtModifica.setIcon(imiModifica);
         ImageIcon imiAnnulla = new ImageIcon(path + "annulla24.gif");
         jbtAnnulla.setIcon(imiAnnulla);
         ImageIcon imiRegistra = new ImageIcon(path + "salva24.gif");
         jbtRegistra.setIcon(imiRegistra);
         ImageIcon imiElimina = new ImageIcon(path + "cancella24.gif");
         jbtElimina.setIcon(imiElimina);
         ImageIcon imiAvantiPress = new ImageIcon(path + "radio.png");
         ImageIcon imiAvantiPressed = new ImageIcon(path + "radioPressed.png");
         jbtAvanti.setIcon(imiAvantiPress);
         jbtAvanti.setPressedIcon(imiAvantiPressed);

         ImageIcon folder = new ImageIcon(path + "folder.gif");
         this.jtpSelezionePannello.setIconAt(0, folder);
         this.jtpSelezionePannello.setIconAt(1, folder);
         this.jtpSelezionePannello.setIconAt(2, folder);
    }


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTable jTableModello;
    private javax.swing.JButton jbtAnnulla;
    private javax.swing.JButton jbtAvanti;
    private javax.swing.JButton jbtElimina;
    private javax.swing.JButton jbtEsci;
    private javax.swing.JButton jbtModifica;
    private javax.swing.JButton jbtNuovo;
    private javax.swing.JButton jbtPassword;
    private javax.swing.JButton jbtRegistra;
    private javax.swing.JButton jbtRicerca;
    private javax.swing.JComboBox jcbFindCodice;
    private javax.swing.JComboBox jcbFindCognome;
    private javax.swing.JComboBox jcbFindIndirizzo;
    private javax.swing.JComboBox jcbFindNick;
    private javax.swing.JComboBox jcbFindNome;
    private javax.swing.JComboBox jcbTipologie;
    private javax.swing.JCheckBox jchbCognome;
    private javax.swing.JCheckBox jchbFindCodice;
    private javax.swing.JCheckBox jchbIndirizzo;
    private javax.swing.JCheckBox jchbNick;
    private javax.swing.JCheckBox jchbNome;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jpDettaglio;
    private javax.swing.JPanel jpLista;
    private javax.swing.JPanel jpListaUtenti;
    private javax.swing.JPanel jpRicerca;
    private javax.swing.JPanel jpSale;
    private javax.swing.JPanel jpUtente;
    private javax.swing.JPasswordField jpfPassword1;
    private javax.swing.JPasswordField jpfPassword2;
    private javax.swing.JTextField jtfCel;
    private javax.swing.JTextField jtfCodFisc;
    private javax.swing.JTextField jtfCognome;
    private javax.swing.JTextField jtfFindCodice;
    private javax.swing.JTextField jtfFindCognome;
    private javax.swing.JTextField jtfFindIndirizzo;
    private javax.swing.JTextField jtfFindNick;
    private javax.swing.JTextField jtfFindNome;
    private javax.swing.JTextField jtfIndirizzo;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfTelefono;
    private javax.swing.JTextField jtfUtente;
    private javax.swing.JTabbedPane jtpSelezionePannello;
    private javax.swing.JLabel lblCognome;
    private javax.swing.JLabel lblNome;
    private javax.swing.JScrollPane scrollLista;
    // End of variables declaration//GEN-END:variables

}
