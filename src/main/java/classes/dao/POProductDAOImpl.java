package classes.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class POProductDAOImpl implements POProductDAO{

    private static DataSource datasource;

    public POProductDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public int InsertPOProduct(int product_id, int po_id, int order_amount, double order_price) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO `OrderManagementDB`.`po_product` " +
                "(`product_id`, `po_id`, `order_amount`, `order_price`) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,product_id);
        statement.setInt(2,po_id);
        statement.setInt(3,order_amount);
        statement.setDouble(4,order_price);
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        int inserted_key = rs.getInt(1);
        connection.close();
        return inserted_key;
    }

    public boolean DeletePOProduct(int po_product_id) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `OrderManagementDB`.`po_product` WHERE `po_product_id`=?;");
        statement.setInt(1,po_product_id);
        statement.executeUpdate();
        connection.close();
        return true;
    }

    public boolean UpdatePOProduct(int po_product_id, int new_product_id, Double order_amount, double order_price) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE `OrderManagementDB`.`po_product` " +
                "SET `product_id`=?, `order_amount`=?, `order_price`=? WHERE `po_product_id`=?;");
        statement.setInt(1,new_product_id);
        statement.setDouble(2,order_amount);
        statement.setDouble(3,order_price);
        statement.setInt(4,po_product_id);
        statement.executeUpdate();
        connection.close();
        return true;
    }
}
