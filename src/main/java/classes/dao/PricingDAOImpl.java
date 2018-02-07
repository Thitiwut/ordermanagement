package classes.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PricingDAOImpl implements PricingDAO{

    private static DataSource datasource;

    public PricingDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public boolean InsertPrice(int product_id, int supplier_id, String active_date, double price) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        if(active_date != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `OrderManagementDB`.`price` " +
                    "(`product_id`, `supplier_id`, `active_date`, `price`) VALUES (?, ?, ?, ?);");
            statement.setInt(1,product_id);
            statement.setInt(2,supplier_id);
            statement.setString(3,active_date);
            statement.setDouble(4,price);
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }
}
