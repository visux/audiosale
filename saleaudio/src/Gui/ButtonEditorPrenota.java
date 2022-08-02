
package Gui;

import Gui.FrPrenotazioni;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;


//public class ButtonEditorPrenota extends AbstractCellEditor implements TableCellEditor{
public class ButtonEditorPrenota extends DefaultCellEditor {
    protected JButton button;
    private JTable tabella;
    private String    label;
    private boolean   isPushed;

    /** costruttore principale //JCheckBox checkBox
    */
    public ButtonEditorPrenota(JCheckBox checkBox, final JTable tabella) {

      super(checkBox);
      
      button = new JButton();
      this.tabella=tabella;
      button.setOpaque(true);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            /*
            System.out.println("Elimino le righe");
            int i = tabella.getSelectedRow();
            ((DefaultTableModel)tabella.getModel()).removeRow(i);

            System.out.println("ridipingo la tabella");
            tabella.repaint();
            */
            System.out.println("risistemo il tasto");
            fireEditingStopped();

            System.out.println("esco dal metodo\n\n");


        }
      });

      

	}


  /** editor del componente
  */
  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }

    //label = (value ==null) ? "PRENOTATO" : value.toString();
    label = "PRENOTATO";
    button.setText( label );
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed)  {
     // button.setBackground(new Color(200,200,200));
      settaPrenotazione();

      //
      //
      //JOptionPane.showMessageDialog(button ,label + ": ok!" );
      // System.out.println(label + ": Ouch!");
    }

    isPushed = false;
    return new String( label ) ;
  }


  public boolean stopCellEditing() {
      isPushed = false;


      return super.stopCellEditing();
  }


  protected void fireEditingStopped() {
    super.fireEditingStopped();

  }

  private boolean settaPrenotazione(){
      System.out.println(label + ": OK Prenotato!");
     
     return true;
  }

}
