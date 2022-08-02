/////////////////////////////////////////////////////////////////////////
//              Popup di avviso prenotazioni: ogni 15 minuti           //
/////////////////////////////////////////////////////////////////////////
package Gui;

import java.awt.Frame;
import java.util.Vector;
import salaprove.Prenotazione;
import salaprove.Utente;

/**
 *
 * @author francesco lopez
 */
public class FrPopupAutomatico extends javax.swing.JDialog {

    //  contiene i dati delle prenotazioni che sono tra l'intervallo
    private static Vector vtDati = new Vector();

        public FrPopupAutomatico()
	{
	     initComponents();
	}

	public FrPopupAutomatico(String sTitle)
	{
		this();
		setTitle(sTitle);
	}

	public FrPopupAutomatico(Vector vtTemp)
	{
		this();
		vtDati = vtTemp;
		// carica le prenotazioni appena lette dalla base dati
		caricaPrenotazioni();

	}

    /** Creates new form FrPopupAutomatico */
    public FrPopupAutomatico(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        lprenotazione = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lprenotazione.setText("jLabel1");
        scroll.setViewportView(lprenotazione);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrPopupAutomatico dialog = new FrPopupAutomatico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /** Carica la tabella delle Prenotazioni
    */
    private void caricaPrenotazioni(){

	        
	       int ingrandimento = 100;
	       Utente oUtente = new Utente();



               for (int index = 0; index < vtDati.size(); index ++)
	 	    {
		        Prenotazione oPrenotazioni = (Prenotazione)(vtDati.elementAt(index));

		        /*
                        Utente oUtente2 =  oUtente.getCliente(oPrenotazioni.cf_utente);

                        */
		        // scrive nel testo
		        this.lprenotazione.setText(this.lprenotazione.getText() +
		                                  "Prenotazione alle: " +  oPrenotazioni.ora + "\n" +
		                                  "Sala: " +  oPrenotazioni.cod_sala + "\n" +
		                              //    "Iscritto: " +  ""+ oUtente2.Cognome + " " + oUtente2.Nome + "\n" +
		                                  "Numero Persone: " +  oPrenotazioni.num + "\n" +
		                                  "____________________________\n");


		        // carica fino a 5 prenotazioni e poi inserisce la scroll bar
    		      if(index <=5){
    		          this.setSize(this.getSize().width ,
    		                                  this.getSize().height + ingrandimento ) ;

    		          this.scroll.setSize(this.scroll.getSize().width ,
    		                                  this.scroll.getSize().height + ingrandimento ) ;

    		      }

    		      this.lprenotazione.setSize(this.lprenotazione.getSize().width ,
    		                                  this.lprenotazione.getSize().height + ingrandimento ) ;
                       
            }

	

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lprenotazione;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables

}
