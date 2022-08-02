

package salaprove;
import db.SINGLETON;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;


public class Sale {
            
            public  int Cod_Sala    = -1;
            public String Descr     = "";
            public short Piano      = -1;
            public BigDecimal Mq    = new BigDecimal("0");
    
    
            public Sale()
            {

            }


            /**   ricava i dati dalla tabella delle sale
            **/
            public static Vector getLista(String sqlCondizione)  {

              Vector  vtDati = new Vector();
              
              if(Generic.strVuota(sqlCondizione))
                     sqlCondizione = " 1=1";

              String istrSql = "SELECT * FROM T_SALE " +
                                  " WHERE " + sqlCondizione;

              Sale oggSale;
              Statement sqlStmt = null;
              ResultSet rs = null;
              try{

             
                        sqlStmt= SINGLETON.getConnection().createStatement();
                        rs = sqlStmt.executeQuery(istrSql);

                        while(rs.next())
                        {

                             oggSale = new Sale();

                             int CodSala = rs.getInt("cod_sala");
                             if(!rs.wasNull())
                                oggSale.Cod_Sala = CodSala;

                            String  Descr = rs.getString("nome");
                            if(!rs.wasNull())
                                oggSale.Descr = Descr.trim();
                            else
                                oggSale.Descr = "";

                             short Piano = rs.getShort("piano");
                             if(!rs.wasNull())
                                oggSale.Piano = Piano;

                             BigDecimal Mq = rs.getBigDecimal("mq");
                             if(!rs.wasNull())
                                oggSale.Mq = Mq;
                            

                               vtDati.add( oggSale);
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
