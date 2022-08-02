/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrOpzioni.java
 *
 * Created on 21-mag-2010, 15.37.50
 */

package Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import salaprove.Generic;
import salaprove.Strumenti;

/**
 *
 * @author francesco lopez
 */
public class FrOpzioni extends javax.swing.JDialog {

    /** Modello della Jtable per le sale
     */
    MdSStrumenti oMdSStrumenti;
    MdSStrumenti oMdSStrumenti_destra;
    
    private Vector vtDati        = new Vector();
    private Vector vtDati_destra = new Vector();

    public FrOpzioni(int Cod_Sala, String Descr, BigDecimal Costo ) {
        initComponents();
        settaImmaginiPulsanti();
        Default(Cod_Sala,Descr,Costo);
        impostaJTRigheStrumenti_sinistra();
        impostaJTRigheStrumenti_destra();
        caricaStrumenti();

        
        
    }
    /** Creates new form FrOpzioni */
    public FrOpzioni(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

    /*  Imposta le immagini relative ad ogni bottone
     */
    private void settaImmaginiPulsanti(){

         String path = "Images/";

         ImageIcon imiSinistra = new ImageIcon(path + "sinistra.gif");
         this.jbtSinistra.setIcon(imiSinistra);
         ImageIcon imiDestra = new ImageIcon(path + "destra.gif");
         this.jbtDestra.setIcon(imiDestra);
         ImageIcon imiUscita = new ImageIcon(path + "uscita24.gif");
         this.jbtEsci.setIcon(imiUscita);
         

    }

    // Imposta le singole righe della JTable degli strumenti
    private void impostaJTRigheStrumenti_sinistra()
    {
        // JTABLE --> SORTER --> MODELLO
        oMdSStrumenti = new MdSStrumenti();

        // Associa all'oggetto grafico jtable l'oggetto
        // modello appena istanziato
        jtStrumenti_sin.setModel(oMdSStrumenti);

        // Associazione colonne con RENDERER e impostazione proprietà colonne
        // Istanzio l'oggetto render per le colonne
        // L'oggetto render serve per impostare graficamente la tabella
        RdSstrumenti myRenderer = new RdSstrumenti();
        // Imposto le proprietà delle colonne
        // colonnaCOD
	javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_sin.getColumnModel().getColumn(this.oMdSStrumenti.colonnaCOD);
        oColumn.setMaxWidth(0);
        oColumn.setWidth(0);
        oColumn.setMinWidth(0);
        oColumn.setPreferredWidth(0);


        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti.colonnaCOD);
        oColumn.setResizable(false);

        // colonnaDESCR
        //javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_sin.getColumnModel().getColumn(this.oMdSStrumenti.colonnaDESCR);
        oColumn.setPreferredWidth(150);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti.colonnaDESCR);
        oColumn.setResizable(true);

        // colonnaCOSTO
        //javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_sin.getColumnModel().getColumn(this.oMdSStrumenti.colonnaCOSTO);
        oColumn.setPreferredWidth(70);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti.colonnaCOSTO);
        oColumn.setResizable(true);

        jtStrumenti_sin.setRowHeight(20);
        // per lo spostamento delle colonne
        jtStrumenti_sin.getTableHeader().setReorderingAllowed(false);
        jtStrumenti_sin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // per il settaggio del font
        jtStrumenti_sin.setFont(new Font("Dialog", Font.BOLD, 10));
        // per la griglia
        jtStrumenti_sin.setShowGrid(false);
        jtStrumenti_sin.setShowHorizontalLines(false);

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

                int nrighe = oMdSStrumenti.getRowCount();
                if (nrighe>0){
                    if (event.getActionCommand().equals("HOME")){
                        jtStrumenti_sin.setRowSelectionInterval(0,0);
                    }
                    else if (event.getActionCommand().equals("END")){
                        jtStrumenti_sin.setRowSelectionInterval(nrighe-1,nrighe-1);
                    }
                    else if (event.getActionCommand().equals("PGDOWN")){
                        int rigacor = jtStrumenti_sin.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor+15;
                            if (rigacor>nrighe)
                                rigacor = nrighe;

                        }
                        jtStrumenti_sin.setRowSelectionInterval(rigacor-1,rigacor-1);
                    }
                    else if (event.getActionCommand().equals("PGUP")){
                        int rigacor = jtStrumenti_sin.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor-15;
                            if (rigacor<1)
                                rigacor = 1;
                        }
                        jtStrumenti_sin.setRowSelectionInterval(rigacor-1,rigacor-1);

                    }
                    jtStrumenti_sin.scrollRectToVisible(jtStrumenti_sin.getCellRect(jtStrumenti_sin.getSelectedRow(),0, true));

                }
            }
        };

        jtStrumenti_sin.registerKeyboardAction(action, "HOME", stroke_home, JComponent.WHEN_FOCUSED);
        jtStrumenti_sin.registerKeyboardAction(action, "END", stroke_end, JComponent.WHEN_FOCUSED);
        jtStrumenti_sin.registerKeyboardAction(action, "PGDOWN", stroke_pgdown, JComponent.WHEN_FOCUSED);
        jtStrumenti_sin.registerKeyboardAction(action, "PGUP", stroke_pgup, JComponent.WHEN_FOCUSED);

        // Implementa il codice per la selezione delle righe
        ListSelectionModel lsmRow = jtStrumenti_sin.getSelectionModel();



}

// Imposta le singole righe della JTable degli strumenti
    private void impostaJTRigheStrumenti_destra()
    {
        // JTABLE --> SORTER --> MODELLO
        oMdSStrumenti_destra = new MdSStrumenti();

        // Associa all'oggetto grafico jtable l'oggetto
        // modello appena istanziato
        jtStrumenti_dex.setModel(oMdSStrumenti_destra);

        // Associazione colonne con RENDERER e impostazione proprietà colonne
        // Istanzio l'oggetto render per le colonne
        // L'oggetto render serve per impostare graficamente la tabella
        RdSstrumenti myRenderer = new RdSstrumenti();
        // Imposto le proprietà delle colonne
        // colonnaCOD
	javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_dex.getColumnModel().getColumn(this.oMdSStrumenti_destra.colonnaCOD);
        oColumn.setMaxWidth(0);
        oColumn.setWidth(0);
        oColumn.setMinWidth(0);
        oColumn.setPreferredWidth(0);


        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti_destra.colonnaCOD);
        oColumn.setResizable(false);

        // colonnaDESCR
        //javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_dex.getColumnModel().getColumn(this.oMdSStrumenti_destra.colonnaDESCR);
        oColumn.setPreferredWidth(150);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti_destra.colonnaDESCR);
        oColumn.setResizable(true);

        // colonnaCOSTO
        //javax.swing.table.TableColumn oColumn=null;
        oColumn = this.jtStrumenti_dex.getColumnModel().getColumn(this.oMdSStrumenti_destra.colonnaCOSTO);
        oColumn.setPreferredWidth(70);
        oColumn.setCellRenderer(myRenderer);
        oColumn.setModelIndex(this.oMdSStrumenti_destra.colonnaCOSTO);
        oColumn.setResizable(true);

        jtStrumenti_dex.setRowHeight(20);
        // per lo spostamento delle colonne
        jtStrumenti_dex.getTableHeader().setReorderingAllowed(false);
        jtStrumenti_dex.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // per il settaggio del font
        jtStrumenti_dex.setFont(new Font("Dialog", Font.BOLD, 10));
        // per la griglia
        jtStrumenti_dex.setShowGrid(false);
        jtStrumenti_dex.setShowHorizontalLines(false);

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

                int nrighe = oMdSStrumenti_destra.getRowCount();
                if (nrighe>0){
                    if (event.getActionCommand().equals("HOME")){
                        jtStrumenti_dex.setRowSelectionInterval(0,0);
                    }
                    else if (event.getActionCommand().equals("END")){
                        jtStrumenti_dex.setRowSelectionInterval(nrighe-1,nrighe-1);
                    }
                    else if (event.getActionCommand().equals("PGDOWN")){
                        int rigacor = jtStrumenti_dex.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor+15;
                            if (rigacor>nrighe)
                                rigacor = nrighe;

                        }
                        jtStrumenti_dex.setRowSelectionInterval(rigacor-1,rigacor-1);
                    }
                    else if (event.getActionCommand().equals("PGUP")){
                        int rigacor = jtStrumenti_dex.getSelectedRow();
                        if (rigacor!=-1){
                            rigacor = rigacor-15;
                            if (rigacor<1)
                                rigacor = 1;
                        }
                        jtStrumenti_dex.setRowSelectionInterval(rigacor-1,rigacor-1);

                    }
                    jtStrumenti_dex.scrollRectToVisible(jtStrumenti_dex.getCellRect(jtStrumenti_dex.getSelectedRow(),0, true));

                }
            }
        };

        jtStrumenti_dex.registerKeyboardAction(action, "HOME", stroke_home, JComponent.WHEN_FOCUSED);
        jtStrumenti_dex.registerKeyboardAction(action, "END", stroke_end, JComponent.WHEN_FOCUSED);
        jtStrumenti_dex.registerKeyboardAction(action, "PGDOWN", stroke_pgdown, JComponent.WHEN_FOCUSED);
        jtStrumenti_dex.registerKeyboardAction(action, "PGUP", stroke_pgup, JComponent.WHEN_FOCUSED);

        // Implementa il codice per la selezione delle righe
        ListSelectionModel lsmRow = jtStrumenti_dex.getSelectionModel();



}

 /** Imposta il default per la maschera
 */
 private void Default(int Cod_Sala, String Descr, BigDecimal Costo){

       // imposta il calendario alla Data odierna
       this.lblNome.setText("SALA: " + Cod_Sala + " " + Descr);
       this.lblCosto.setText("COSTO: " + Costo.toString());

 }


//********** MODELLO PARTICOLARE PER LA GESTIONE DELLA JTABLE **********//
private class MdSStrumenti extends javax.swing.table.AbstractTableModel implements java.io.Serializable
{
        /** Righe del profilo
        */
        private Vector vRigheModello;

        // Colonne del modello (final = Costanti)
        final private int  colonnaCOD          = 0;
        final private int  colonnaDESCR        = 1;
        final private int  colonnaCOSTO        = 2;


        // definisco una variabile di tipo record
        final String[] columnNames = new String[3];

        private MdSStrumenti()
        {
            // Inserisco nella variabile di tipo record
            // le intestazioni delle colonne
            columnNames[colonnaCOD]            = "Codice";
            columnNames[colonnaDESCR]          = "Strumento";
            columnNames[colonnaCOSTO]          = "Costo";

        }

        // Imposta la righe associate al modello
        private void setProfilo(Vector vRighe)
        {
            this.vRigheModello = vRighe;
        }

        private Vector getProfilo()
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
            Object[] Valori = (Object[])vRigheModello.elementAt(row);

            // mi posiziono sulla colonna desiderata
            switch (col)
            {

                case colonnaCOD:
                    return Valori[colonnaCOD];
                 case colonnaDESCR:
                    return Valori[colonnaDESCR];
                case colonnaCOSTO:
                    return Valori[colonnaCOSTO];
                default:
                    return "";
            }
        }


        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }

        /* Determina quali sono le celle editabili, e quando devono diventare editabili
         **/
        public boolean isCellEditable(int row, int col) {

            Object sVt[] = (Object[])vRigheModello.elementAt(row);
            //sPFOR = sVt[col].toString();
            //return col == 0 || col == 1 || col == 2 || col == 3 || StatoMaschera == 2 || StatoMaschera == 1;

            return false;
        }

        /**********************************************/
        /* Serve per impostare il valore ad una cella */
        /**********************************************/
        public void setValueAt(Object value, int row, int col)
        {
            Object oVal[] = (Object[])vRigheModello.elementAt(row);
                 switch (col)
                 {

                   case colonnaCOD:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaCOD] = "";
                        }else{
                            oVal[colonnaCOD] = value.toString();
                        }
                        break;
                    case colonnaDESCR:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaDESCR] = "";
                        }else{
                            oVal[colonnaDESCR] = value.toString();
                        }
                        break;

                    case colonnaCOSTO:
                        if (value == null)
                        {
                            // Default
                            oVal[colonnaCOSTO] = "";
                        }else{
                            oVal[colonnaCOSTO] = value.toString();
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
        private void aggiorna()
        {
            fireTableDataChanged(); // Tell the listeners a new table has arrived.
        }

        /***********************************************/
        /*  Rinfresca la JTABLE per la riga cancellata */
        /***********************************************/
        private void cancella(int riga){
            this.fireTableRowsDeleted(riga, riga);
        }


    }

 /**  carica le sale
    */
    private void caricaStrumenti(){


	    //String sCondizione = creaCondizioneRicerca();
            String sCondizione = "";
    	    vtDati.removeAllElements();
    	    vtDati = Strumenti.getLista(sCondizione);

    	     if (vtDati.size() <= 0){
    	       Generic.notifica("Nessun Record trovato per la Selezione impostata!", 2);
    	       return;
    	    }

	        Vector vtElementi = new Vector();
    		for (int index = 0; index < vtDati.size(); index ++)
	 	{
		        Strumenti oStrumenti = (Strumenti)(vtDati.elementAt(index));
                        // dichiaro una variabile stringa di record
	                String[] Valori = new String[5];
		        // carico la tabella con i dati del vettore
    		        Valori[oMdSStrumenti.colonnaCOD]       = "" + oStrumenti.Cod_Strumento;
                        Valori[oMdSStrumenti.colonnaDESCR]     = "" + oStrumenti.Descr;
                        Valori[oMdSStrumenti.colonnaCOSTO]     = "" + oStrumenti.Costo;

                        vtElementi.addElement(Valori);
                }

		// Aggiunge gli Elementi del Vettore al Modello della JTable
		oMdSStrumenti.setProfilo(vtElementi);

		// Aggiorna la JTable
                oMdSStrumenti.aggiorna();

                // mi posiziono sulla prima riga della tabella
                if(jtStrumenti_sin.getRowCount() > 0){
                  jtStrumenti_sin.setRowSelectionInterval(0,0);
                }
                jtStrumenti_sin.requestFocus();


    }

     // Classe per gestire il renderer della JTable
    // in teoria deve essere una classe di render per ogni
    // colonna.
    // In questo caso siccome tutte le colonne sono di tipo testo
    // viene usata una sola classe di render su tutte le colonne.
    private class RdSstrumenti extends javax.swing.JTextField implements javax.swing.table.TableCellRenderer
    {
        // Impostazione dei colori di sfondo
        private Color COLOR_ROSSO    = new Color(254,95,90);
        private Color COLOR_GIALLO   = new Color(255,255,166);
        private Color COLOR_VERDE    = new Color(104,240,162);
        private Color COLOR_BIANCO   = java.awt.Color.white;
        private Color COLOR_AZZURRO  = new Color(217,230,249);
        private Color COLOR_ARANCIO  = new Color(255,151,81);
        private Color COLOR_VERDINO  = new Color(202,255,176);


	    public RdSstrumenti()
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
                    setBackground(COLOR_BIANCO);
                    setForeground(table.getForeground());
                }
                else
                {
                    // le righe dispari le metto con il colore azzurro
                    setBackground(COLOR_BIANCO);
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
                if (colreale == oMdSStrumenti.colonnaCOD){
                    this.setHorizontalAlignment(RIGHT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdSStrumenti.colonnaDESCR){
                    this.setHorizontalAlignment(LEFT);
                    this.setText(value.toString());
                }
                else if (colreale == oMdSStrumenti.colonnaCOSTO){
                    this.setHorizontalAlignment(RIGHT);
                    this.setText(value.toString());
                }
                return this;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jbtEsci = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtStrumenti_dex = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtDestra = new javax.swing.JButton();
        jbtSinistra = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtStrumenti_sin = new javax.swing.JTable();
        jpUtente = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtEsci.setText("Ok");
        jbtEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEsciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(535, Short.MAX_VALUE)
                .addComponent(jbtEsci, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jbtEsci)
                .addContainerGap())
        );

        jtStrumenti_dex.setModel(new javax.swing.table.DefaultTableModel(
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
        jtStrumenti_dex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtStrumenti_dexMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtStrumenti_dex);

        jbtDestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDestraActionPerformed(evt);
            }
        });

        jbtSinistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSinistraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtSinistra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtDestra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jbtSinistra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtDestra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jtStrumenti_sin.setModel(new javax.swing.table.DefaultTableModel(
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
        jtStrumenti_sin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtStrumenti_sinMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtStrumenti_sin);

        jpUtente.setBackground(new java.awt.Color(255, 255, 166));
        jpUtente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Informazioni    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        javax.swing.GroupLayout jpUtenteLayout = new javax.swing.GroupLayout(jpUtente);
        jpUtente.setLayout(jpUtenteLayout);
        jpUtenteLayout.setHorizontalGroup(
            jpUtenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUtenteLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );
        jpUtenteLayout.setVerticalGroup(
            jpUtenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpUtente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jpUtente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );

        jpUtente.getAccessibleContext().setAccessibleName("   Informazioni   ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEsciActionPerformed
        //this.setVisible(false);
        this.dispose();
}//GEN-LAST:event_jbtEsciActionPerformed

    private void jbtSinistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSinistraActionPerformed
                  rimuovi();
}//GEN-LAST:event_jbtSinistraActionPerformed

    private void jbtDestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDestraActionPerformed
                  aggiungi();
}//GEN-LAST:event_jbtDestraActionPerformed

private void rimuovi(){
   aggiungiRigaSinistra();
   // elimina le righe della jtable
   eliminaRigaDestra(this.jtStrumenti_dex.getSelectedRow());
}
private void aggiungi(){
      aggiungiRigaDestra();
      // elimina le righe della jtable
      eliminaRigaSinistra(this.jtStrumenti_sin.getSelectedRow());
}
    private void jtStrumenti_sinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtStrumenti_sinMouseClicked
        // doppio click
        if(evt.getClickCount()==2){
             aggiungi();
	}
    }//GEN-LAST:event_jtStrumenti_sinMouseClicked

    private void jtStrumenti_dexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtStrumenti_dexMouseClicked
        // doppio click
        if(evt.getClickCount()==2){
             rimuovi();
	}
    }//GEN-LAST:event_jtStrumenti_dexMouseClicked

        /** Elimina la riga selezionata
	*/
	public void eliminaRigaSinistra(int rigaselezionata){


	            Vector vtEliminaTemp = new Vector();
                    Object Valori[] = new Object[3];

                    for (int i=0; i < jtStrumenti_sin .getRowCount(); i++){

                       // elimina la riga selezionata e quindi non lo inserisce nel vettore
                       if(i != rigaselezionata){
                            Valori = new Object[3];
                            Valori[oMdSStrumenti.colonnaCOD] = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaCOD);
                            Valori[oMdSStrumenti.colonnaDESCR] = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaDESCR);
                            Valori[oMdSStrumenti.colonnaCOSTO] = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaCOSTO );
                            vtEliminaTemp.addElement(((Object) (Valori)));
                       }
                    }

                    oMdSStrumenti.setProfilo(vtEliminaTemp);
                    oMdSStrumenti.aggiorna();


                    if(jtStrumenti_sin.getRowCount() > 0)
                    {
                        jtStrumenti_sin.setRowSelectionInterval(jtStrumenti_sin.getRowCount() - 1, jtStrumenti_sin.getRowCount() - 1);
                        jtStrumenti_sin.editCellAt(jtStrumenti_sin.getSelectedRow(), 0);
                    }
                    jtStrumenti_sin.requestFocus();



    }


    /** Aggiunge uno strumento a destra
    */
    private void aggiungiRigaDestra(){


	   Vector vtElementi = new Vector();


           for (int i=0; i < this.jtStrumenti_dex.getRowCount(); i++){

                 Object Valori[] = new Object[3];
                 Valori[oMdSStrumenti_destra.colonnaCOD]   = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaCOD);
                 Valori[oMdSStrumenti_destra.colonnaDESCR] = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaDESCR);
                 Valori[oMdSStrumenti_destra.colonnaCOSTO] = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaCOSTO).toString().replace('.', ',');

                 vtElementi.addElement(((Object) (Valori)));
           }

	   Object Valori[] = new Object[3];

	    
           Valori[oMdSStrumenti_destra.colonnaCOD]   = jtStrumenti_sin.getValueAt(this.jtStrumenti_sin.getSelectedRow(), oMdSStrumenti.colonnaCOD);
           Valori[oMdSStrumenti_destra.colonnaDESCR] = jtStrumenti_sin.getValueAt(this.jtStrumenti_sin.getSelectedRow(), oMdSStrumenti.colonnaDESCR);
           Valori[oMdSStrumenti_destra.colonnaCOSTO] = jtStrumenti_sin.getValueAt(this.jtStrumenti_sin.getSelectedRow(), oMdSStrumenti.colonnaCOSTO);

           vtElementi.addElement(((Object) (Valori)));

	   // Aggiunge gli Elementi del Vettore al Modello della JTable
	   oMdSStrumenti_destra.setProfilo(vtElementi);

	   // Aggiorna la JTable
           oMdSStrumenti_destra.aggiorna();

           // mi posiziono sulla prima riga della tabella
           if(jtStrumenti_dex.getRowCount() > 0){
              jtStrumenti_dex.setRowSelectionInterval(0,0);
            }
            jtStrumenti_dex.requestFocus();

	}

    /** Elimina la riga selezionata
	*/
	public void eliminaRigaDestra(int rigaselezionata){


	            Vector vtEliminaTemp = new Vector();
                    Object Valori[] = new Object[3];

                    for (int i=0; i < jtStrumenti_dex .getRowCount(); i++){

                       // elimina la riga selezionata e quindi non lo inserisce nel vettore
                       if(i != rigaselezionata){
                            Valori = new Object[3];
                            Valori[oMdSStrumenti_destra.colonnaCOD] = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaCOD);
                            Valori[oMdSStrumenti_destra.colonnaDESCR] = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaDESCR);
                            Valori[oMdSStrumenti_destra.colonnaCOSTO] = jtStrumenti_dex.getValueAt(i, oMdSStrumenti_destra.colonnaCOSTO );
                            vtEliminaTemp.addElement(((Object) (Valori)));
                       }
                    }

                    oMdSStrumenti_destra.setProfilo(vtEliminaTemp);
                    oMdSStrumenti_destra.aggiorna();


                    if(jtStrumenti_dex.getRowCount() > 0)
                    {
                        jtStrumenti_dex.setRowSelectionInterval(jtStrumenti_dex.getRowCount() - 1, jtStrumenti_dex.getRowCount() - 1);
                        jtStrumenti_dex.editCellAt(jtStrumenti_dex.getSelectedRow(), 0);
                    }
                    jtStrumenti_dex.requestFocus();



    }

    /** Aggiunge uno strumento a destra
    */
    private void aggiungiRigaSinistra(){


	   Vector vtElementi = new Vector();


           for (int i=0; i < this.jtStrumenti_sin.getRowCount(); i++){

                 Object Valori[] = new Object[3];
                 Valori[oMdSStrumenti.colonnaCOD]   = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaCOD);
                 Valori[oMdSStrumenti.colonnaDESCR] = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaDESCR);
                 Valori[oMdSStrumenti.colonnaCOSTO] = jtStrumenti_sin.getValueAt(i, oMdSStrumenti.colonnaCOSTO).toString().replace('.', ',');

                 vtElementi.addElement(((Object) (Valori)));
           }

	   Object Valori[] = new Object[3];


           Valori[oMdSStrumenti.colonnaCOD]   = jtStrumenti_dex.getValueAt(this.jtStrumenti_dex.getSelectedRow(), oMdSStrumenti_destra.colonnaCOD);
           Valori[oMdSStrumenti.colonnaDESCR] = jtStrumenti_dex.getValueAt(this.jtStrumenti_dex.getSelectedRow(), oMdSStrumenti_destra.colonnaDESCR);
           Valori[oMdSStrumenti.colonnaCOSTO] = jtStrumenti_dex.getValueAt(this.jtStrumenti_dex.getSelectedRow(), oMdSStrumenti_destra.colonnaCOSTO);

           vtElementi.addElement(((Object) (Valori)));

	   // Aggiunge gli Elementi del Vettore al Modello della JTable
	   oMdSStrumenti.setProfilo(vtElementi);

	   // Aggiorna la JTable
           oMdSStrumenti.aggiorna();

           // mi posiziono sulla prima riga della tabella
           if(jtStrumenti_sin.getRowCount() > 0){
              jtStrumenti_sin.setRowSelectionInterval(0,0);
            }
            jtStrumenti_sin.requestFocus();

	}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrOpzioni dialog = new FrOpzioni(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtDestra;
    private javax.swing.JButton jbtEsci;
    private javax.swing.JButton jbtSinistra;
    private javax.swing.JPanel jpUtente;
    private javax.swing.JTable jtStrumenti_dex;
    private javax.swing.JTable jtStrumenti_sin;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblNome;
    // End of variables declaration//GEN-END:variables

}
