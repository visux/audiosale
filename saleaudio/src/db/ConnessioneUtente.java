/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import java .sql.*;
import salaprove.*;
/**
 *
 * @author Ge
 */
public class ConnessioneUtente {

    private ConnessioneUtente _instance=null;

    private ConnessioneUtente() {

    }

    public ConnessioneUtente getInstance(){
        if(_instance==null){
            this._instance=new ConnessioneUtente();

        }
        return _instance;
    }

    public String[]readStudente(String id){

        String[] res=new String[6];
        try{
               Connection con=db.SINGLETON.getConnection();
               try{
                   PreparedStatement ps=con.prepareStatement("Select * from _tUtenti where userid=?");
                   ps.setString(1, id);
                   ResultSet rs=ps.executeQuery();

                   while(rs.next()){
                        res[0]=rs.getString(1);
                        res[1]=rs.getString(2);

                   }
               }catch(SQLException es){es.printStackTrace();}
        }catch(ConnDBaseException e){e.printStackTrace();}
      return res;
    }


}
