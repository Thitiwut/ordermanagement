package classes.dao;

import classes.model.Product;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO{

    private static DataSource datasource;

    public ProductDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public boolean InsertProduct(String product_name, String product_type, int supplier_id, double price) throws NamingException,SQLException {
        Connection connection = datasource.getConnection();
        if(product_name != null && product_type != null){
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO `OrderManagementDB`.`product` (`product_name`, `product_type`) VALUES (?, ?);" +
                            "INSERT INTO `OrderManagementDB`.`price` (`product_id`, `supplier_id`) VALUES (LAST_INSERT_ID(), ?);");
            statement.setString(1,product_name);
            statement.setString(2,"regular");
            statement.setInt(3,supplier_id);
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public boolean DeleteProduct(int product_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `OrderManagementDB`.`product` WHERE `product_id`=?;");
        statement.setInt(1,product_id);
        statement.executeQuery();
        connection.close();
        return true;
    }

    public boolean UpdateProduct(int product_id, String new_product_name) throws NamingException,SQLException{
        Connection connection = datasource.getConnection();
        if(new_product_name != null){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `OrderManagementDB`.`product` SET `product_name`=? WHERE `product_id`=?;");
            statement.setString(1,new_product_name);
            statement.setInt(2,product_id);
            statement.executeQuery();
            connection.close();
            return true;
        }else connection.close(); return false;
    }

    public Product GetProductByName(String product_name) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM OrderManagementDB.product WHERE product_name=?;");
        statement.setString(1, product_name);

        ResultSet rs = statement.executeQuery();
        rs.next();

        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_number(rs.getString("product_number"));
        product.setProduct_type(rs.getString("product_type"));
        product.setPackage_component(rs.getString("package_component"));

        connection.close();
        return product;
    }

    public Product GetProductByID(int product_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM OrderManagementDB.product WHERE product_id=?;");
        statement.setInt(1, product_id);
        ResultSet rs = statement.executeQuery();
        rs.next();

        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_number(rs.getString("product_number"));
        product.setProduct_type(rs.getString("product_type"));
        product.setPackage_component(rs.getString("package_component"));
        product.setPrice(rs.getDouble("price"));

        connection.close();
        return product;
    }

    public ArrayList<Product> GetAllProduct(int supplier_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT OrderManagementDB.product.product_id," +
                "OrderManagementDB.product.product_name," +
                "OrderManagementDB.product.product_number," +
                "OrderManagementDB.product.product_type," +
                "OrderManagementDB.product.package_component," +
                "OrderManagementDB.price.price " +
                "FROM OrderManagementDB.product " +
                "LEFT JOIN OrderManagementDB.price ON OrderManagementDB.product.product_id = OrderManagementDB.price.product_id " +
                "WHERE supplier_id = ?;");
        statement.setInt(1, supplier_id);
        ResultSet rs = statement.executeQuery();

        ArrayList product_list = new ArrayList<Product>();
        while(rs.next()){
            Product product = new Product();
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_number(rs.getString("product_number"));
            product.setProduct_type(rs.getString("product_type"));
            product.setPackage_component(rs.getString("package_component"));
            product.setPrice(rs.getDouble("price"));
            product_list.add(product);
        }

        connection.close();
        return product_list;
    }
}
