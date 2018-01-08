package classes.dao;

import classes.object.Product;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface ProductDAO {
    boolean InsertProduct(String product_name, String product_type) throws NamingException, SQLException;

    boolean DeleteProduct();

    boolean UpdateProduct();

    Product GetProductByName(String product_name);

    Product GetProductByID(int product_id);

}
