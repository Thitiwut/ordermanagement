package classes.dao;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface POProductDAO {
    int InsertPOProduct(int product_id, int po_id, Double order_amount, double order_price) throws NamingException, SQLException;
    boolean DeletePOProduct(int po_product_id) throws NamingException, SQLException;
    boolean UpdatePOProduct(int po_product_id, int new_product_id, Double order_amount, double order_price) throws NamingException, SQLException;
}
