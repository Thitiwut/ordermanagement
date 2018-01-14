package classes.dao;

import classes.object.Product;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO{
    public boolean InsertProduct(String product_name, String product_type) throws NamingException,SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        if(product_name != null && product_type != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public boolean DeleteProduct(int product_id) throws NamingException, SQLException{
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM XXXXXXXXXXXXXXXX");
        statement.executeQuery();
        connection.close();
        return true;
    }

    public boolean UpdateProduct(int product_id, String new_product_name) throws NamingException,SQLException{
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        if(new_product_name != null){
            PreparedStatement statement = connection.prepareStatement("UPDATE XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else connection.close(); return false;
    }

    public Product GetProductByName(String product_name) throws NamingException, SQLException{
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE name");
        ResultSet rs = statement.executeQuery();

        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_number(rs.getString("product_number"));
        product.setProduct_type(rs.getString("product_type"));
        product.setPackage_component(rs.getString("package_component"));
        return product;
    }

    public Product GetProductByID(int product_id) throws NamingException, SQLException{
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE id");
        ResultSet rs = statement.executeQuery();

        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_number(rs.getString("product_number"));
        product.setProduct_type(rs.getString("product_type"));
        product.setPackage_component(rs.getString("package_component"));
        return product;
    }

    public ArrayList<Product> GetAllProduct() throws NamingException, SQLException{
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE id");
        ResultSet rs = statement.executeQuery();

        ArrayList product_list = new ArrayList<Product>();
        while(rs.next()){
            Product product = new Product();
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_number(rs.getString("product_number"));
            product.setProduct_type(rs.getString("product_type"));
            product.setPackage_component(rs.getString("package_component"));
            product_list.add(product);
        }
        return product_list;
    }
}
