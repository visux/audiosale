//////////////////////////////////////////////////////////////////
//                    Classi di Connessione                     //
//////////////////////////////////////////////////////////////////


package db;

public interface IConn {

    public abstract IConnDBase getIConnDBase(String s)
        throws ConnDBaseException;

    public abstract IConnDBase getIDefaultConnDBase()
        throws ConnDBaseException;
}