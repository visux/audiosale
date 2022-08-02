//////////////////////////////////////////////////////////////////
//                    Classi di Connessione                     //
//////////////////////////////////////////////////////////////////

package db;

public class ConnDBaseNoConnException extends ConnDBaseException{

    public ConnDBaseNoConnException()
    {
    }

    public ConnDBaseNoConnException(String e)
    {
        super(e);
    }
}
