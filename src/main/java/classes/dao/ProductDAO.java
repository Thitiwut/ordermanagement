package classes.dao;

import classes.model.Product;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    boolean InsertProduct(String product_name, Integer product_id, String product_type, int supplier_id, double price) throws NamingException, SQLException;
    boolean DeleteProduct(int product_id) throws NamingException, SQLException;
    boolean UpdateProduct(int product_id, String new_product_name) throws NamingException, SQLException;
    Product GetProductByName(String product_name) throws NamingException, SQLException;
    Product GetProductByID(int product_id) throws NamingException, SQLException;
    ArrayList GetAllProduct(int supplier_id) throws NamingException, SQLException;
}
