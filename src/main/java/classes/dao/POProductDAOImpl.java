package classes.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class POProductDAOImpl implements POProductDAO{

    public boolean InsertPOProduct(int product_id, int po_id, Double order_amount, double order_price) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT  XXXXXXXXXXXXXXXX");
        statement.executeQuery();
        connection.close();
        return true;
    }

    public boolean DeletePOProduct(int po_product_id) throws NamingException, SQLException {
        return false;
    }

    public boolean UpdatePOProduct(int po_product_id, int new_product_id, Double order_amount, double order_price) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE  XXXXXXXXXXXXXXXX");
        statement.executeQuery();
        connection.close();
        return true;
    }
}
