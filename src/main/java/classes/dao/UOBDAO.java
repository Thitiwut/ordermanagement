package classes.dao;

import classes.object.UOB;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface UOBDAO {
    boolean InsertUOB(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) throws NamingException, SQLException;
    boolean UpdateUOB(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) throws NamingException, SQLException;
    UOB GetUOB(String po_number) throws NamingException, SQLException;
}
