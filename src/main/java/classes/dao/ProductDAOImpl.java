package classes.dao;

import classes.object.Product;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO{
    public boolean InsertProduct(String product_name, String product_type) throws NamingException,SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        if(product_name != null && product_type != null){
            return true;
        }else return false;
    }

    public boolean DeleteProduct(){
        return false;
    }

    public boolean UpdateProduct(){
        return false;
    }

    public Product GetProductByName(String product_name) {
        Product product = new Product();
        product.setProduct_id(0);
        product.setProduct_name("Apple");
        product.setProduct_type("Regular");
        product.setPackage_component("");
        return product;
    }

    public Product GetProductByID(int product_id) {
        Product product = new Product();
        product.setProduct_id(0);
        product.setProduct_name("Apple");
        product.setProduct_type("Regular");
        product.setPackage_component("");
        return product;
    }
}
