/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;
import db.SINGLETON;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Ge
 */
public class Strumenti {

            public  int       Cod_Strumento    = -1;
            public String     Descr            = "";
            public BigDecimal Costo            = new BigDecimal("0");


            public Strumenti()
            {

            }


            /**   ricava i dati dalla tabella delle sale
            **/
            public static Vector getLista(String sqlCondizione)  {

              Vector  vtDati = new Vector();

              if(Generic.strVuota(sqlCondizione))
                     sqlCondizione = " 1=1";

              String istrSql = "SELECT * FROM T_STRUMENTI " +
                                  " WHERE " + sqlCondizione;

              Strumenti oggStrumenti;
              Statement sqlStmt = null;
              ResultSet rs = null;
              try{


                        sqlStmt= SINGLETON.getConnection().createStatement();
                        rs = sqlStmt.executeQuery(istrSql);

                        while(rs.next())
                        {

                             oggStrumenti = new Strumenti();

                             int Cod_Strumento = rs.getInt("cod_strumento");
                             if(!rs.wasNull())
                                oggStrumenti.Cod_Strumento = Cod_Strumento;

                            String  Descr = rs.getString("descr");
                            if(!rs.wasNull())
                                oggStrumenti.Descr = Descr.trim();
                            else
                                oggStrumenti.Descr = "";

                             BigDecimal Costo = rs.getBigDecimal("costo");
                             if(!rs.wasNull())
                                oggStrumenti.Costo = Costo;


                               vtDati.add( oggStrumenti);
                        }


                      }catch(Exception e){
                              e.printStackTrace();
                      }finally{

                           try{

                              if (rs!=null) rs.close();
                              if (sqlStmt!=null) sqlStmt.close();
                              SINGLETON.chiudiConnessione();

                           }catch(Exception e1){ }
                      }



                      return vtDati;

            }


}
