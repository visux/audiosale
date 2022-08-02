/*
 * FrLogutente.java
 *
 * Created on 21-nov-2009, 14.18.20
 */

package Gui;

import salaprove.Utente;
import db.SINGLETON;
import java.net.InetAddress;
import javax.swing.ImageIcon;
import salaprove.Generic;

/**
 *
 * @author 
 */
public class FrLogutente extends javax.swing.JDialog {

    // verifica il login corretto
    private static boolean OKverifica = false;

    public FrLogutente() {
        initComponents();
        setTitle("Sala Prove");
        settaImmagini();
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

    /** FrLogutente */
    public FrLogutente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jpCampi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfUtente = new javax.swing.JTextField();
        jtfPassword = new javax.swing.JPasswordField();
        jbtRegistrati = new javax.swing.JButton();
        jpBottoni = new javax.swing.JPanel();
        barra = new javax.swing.JProgressBar();
        jbtEsci = new javax.swing.JButton();
        jbtConnetti = new javax.swing.JButton();
        etichetta = new javax.swing.JLabel();
        lblSfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpCampi.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Utente:");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Password:");

        jtfUtente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtfPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jbtRegistrati.setText("Registrati");
        jbtRegistrati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRegistratiActionPerformed(evt);
            }
        });

        jpBottoni.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtEsci.setText("Esci");
        jbtEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEsciActionPerformed(evt);
            }
        });

        jbtConnetti.setText("Connetti");
        jbtConnetti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConnettiActionPerformed(evt);
            }
        });

        etichetta.setOpaque(true);

        javax.swing.GroupLayout jpBottoniLayout = new javax.swing.GroupLayout(jpBottoni);
        jpBottoni.setLayout(jpBottoniLayout);
        jpBottoniLayout.setHorizontalGroup(
            jpBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etichetta, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addGroup(jpBottoniLayout.createSequentialGroup()
                        .addComponent(jbtConnetti, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(jbtEsci, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpBottoniLayout.setVerticalGroup(
            jpBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtEsci)
                    .addComponent(jbtConnetti))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etichetta, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpCampiLayout = new javax.swing.GroupLayout(jpCampi);
        jpCampi.setLayout(jpCampiLayout);
        jpCampiLayout.setHorizontalGroup(
            jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCampiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCampiLayout.createSequentialGroup()
                        .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpBottoni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpCampiLayout.createSequentialGroup()
                                .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(118, 118, 118)
                                .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpCampiLayout.createSequentialGroup()
                                        .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))
                                    .addComponent(jtfUtente, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCampiLayout.createSequentialGroup()
                        .addComponent(jbtRegistrati, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jpCampiLayout.setVerticalGroup(
            jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCampiLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUtente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCampiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jbtRegistrati)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpCampi.setBounds(0, 0, 519, 290);
        jDesktopPane1.add(jpCampi, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lblSfondo.setBounds(0, 0, 520, 310);
        jDesktopPane1.add(lblSfondo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtRegistratiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRegistratiActionPerformed
        FrUtenti oFrUtenti = new FrUtenti();
        oFrUtenti.setVisible(true);
}//GEN-LAST:event_jbtRegistratiActionPerformed

    private void jbtEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEsciActionPerformed
        Generic.chiudiProgramma();
    }//GEN-LAST:event_jbtEsciActionPerformed

    private void jbtConnettiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConnettiActionPerformed
        AvviaLogin();
        

}//GEN-LAST:event_jbtConnettiActionPerformed

    /**
    *  Verifica dell'utente e della password
    */
    private void AvviaLogin(){
        if(controllo())
        {
            StatoLogin("Connessione in corso...");

            jbtConnetti.setEnabled(false);
            connetti();

            if(isLoginOK())
            {
                    this.dispose();
                    FrPrenotazioni oFr = new FrPrenotazioni();
                    oFr.setVisible(true);
                    OKverifica = false;

            } else
            {
                StatoBarra(0);
                jbtConnetti.setEnabled(true);
            }
        } else
        {
            StatoLogin("Il collegamento e\' stato annullato... Controllare la connessione ai dati.");
        }

    }

    /**
     * Verra' richiamato all'interno della main
     * @return
     */
    private static boolean isLoginOK()
    {
        return OKverifica;
    }

    /**
     * Messaggi di sistema nella maschera del login
     * @param msg
     */
    void StatoLogin(String msg)
    {

        etichetta.setText(msg);
        etichetta.paint(etichetta.getGraphics());

    }

    private void StatoBarra(int i)
    {
        barra.setValue(i);
        barra.update(barra.getGraphics());
    }


     /**
     * Verifica i campi della gui
     * @return
     */
    private boolean controllo(){

        if(Generic.strVuota(jtfUtente.getText()))
        {
            Generic.notifica("Inserire l\'Utente prima di connettersi al software!", 3);
            jtfUtente.requestFocus();
            return false;
        }
        if(Generic.strVuota(jtfPassword.getText()))
        {
            Generic.notifica("Inserire la Password prima di connettersi al software!", 3);
            jtfPassword.requestFocus();
            return false;
        } else
        {
            return true;
        }


    }

    private void connetti()
    {

        
        for(int i = 0; i < 101; i += 2)
        {
            // init della batta di stato
            StatoBarra(i);
            try
            {
                Thread.sleep(20L);
            }
            catch(Exception exception) { }
            if(i == 20)
            {
                StatoLogin("Caricamento delle impostazioni di sistema");
                if(!Generic.initProprieta())
                {
                    StatoLogin("File proprieta' di sistema non trovato");
                    Generic.notifica("File impostazioni non trovato", 2);
                    return;
                }
            }
            if(i == 40)
            {
                StatoLogin("Inizializzazione ambiente");
                if(!Generic.initAmbiente())
                {
                    StatoLogin("Problemi nell'inizializzazione ambiente");
                    return;
                }

            }
            if(i == 50)
            {
                StatoLogin("Connessione in corso...");
                try
                {
                    SINGLETON.initStartUpConnDBase(Generic.prConnessione);
                    SINGLETON.setAliasConnDBaseDefault("audio");
                }
                catch(Exception e)
                {
                    StatoLogin("Connessione al server fallita");
                    Generic.notifica("Connessione al server fallita: " + e.getMessage(), 2);
                    return;
                }
                StatoLogin("Connessione al server riuscita");

            }
            if(i == 60)
            {
                StatoLogin("Verifica delle credenziali di accesso...");
                
                Generic.oUtente = null;
                String iplocale = "Sconosciuto";
                try
                {
                    iplocale = InetAddress.getLocalHost().toString();
                }
                catch(Exception exception1) { }
                char pass[] = jtfPassword.getPassword();
                String spassw = new String(pass);
                pass = null;
                if(jtfUtente.getText().trim().equals("admin") && spassw.trim().equals("operatore"))
                {

                    Generic.oUtente = new Utente();
                    Generic.oUtente.cf_utente = "OperatoreGenerico";
                    Generic.oUtente.nome = "Operatore Generico";
                    Generic.oUtente.cognome = "Operatore Generico";
                    Generic.oUtente.tipologia  = 0;
                   
                    
                } else
                {
                    try
                    {
                        Generic.oUtente = new Utente(jtfUtente.getText(), spassw);
                        i += 10;
                        StatoBarra(i);
                        
                    }
                    catch(Exception e)
                    {
                        StatoLogin(e.getMessage());

                        return;
                    }
                }
                StatoLogin("Utente/Password verificati!");

            }
            if(i == 80)
            {
               
                StatoLogin("Ambiente impostato");
                OKverifica = true;
            }
        }

            
    }

     private void settaImmagini() {
        // percorso
	String path = "Images/";

	ImageIcon sfd        = new ImageIcon(path + "musica.jpg");
	lblSfondo.setIcon(sfd);
        ImageIcon imiUscita = new ImageIcon(path + "uscita24.gif");
        this.jbtEsci.setIcon(imiUscita);
        ImageIcon imiConn = new ImageIcon(path + "connetti24.gif");
        this.jbtConnetti.setIcon(imiConn);
        

    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barra;
    private javax.swing.JLabel etichetta;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtConnetti;
    private javax.swing.JButton jbtEsci;
    private javax.swing.JButton jbtRegistrati;
    private javax.swing.JPanel jpBottoni;
    private javax.swing.JPanel jpCampi;
    private javax.swing.JPasswordField jtfPassword;
    private javax.swing.JTextField jtfUtente;
    private javax.swing.JLabel lblSfondo;
    // End of variables declaration//GEN-END:variables

}