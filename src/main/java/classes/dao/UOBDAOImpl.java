package classes.dao;

import classes.model.UOB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UOBDAOImpl implements UOBDAO {

    private static DataSource datasource;

    public UOBDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public boolean InsertUOB(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        if(fax_date != null && payee != null && paid_date != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public boolean UpdateUOB(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        if(fax_date != null && payee != null && paid_date != null){
            PreparedStatement statement = connection.prepareStatement("UPDATE INTO XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public UOB GetUOB(String po_number) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE name");
        ResultSet rs = statement.executeQuery();
        rs.next();

        UOB uob = new UOB();
        uob.setUob_id(rs.getInt("uob_id"));
        uob.setUob_number(rs.getInt("uob_number"));
        uob.setPayee(rs.getString("payee"));
        uob.setFax_date(rs.getString("fax_date"));
        uob.setPo_number(rs.getString("po_number"));
        uob.setDirect_dc(rs.getString("direct_dc"));
        uob.setRemark(rs.getString("remark"));
        return uob;
    }
}
