package classes.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PricingDAOImpl implements PricingDAO{

    public boolean InsertPrice(int product_id, int supplier_id, String active_date, double price) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        if(active_date != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }
}
