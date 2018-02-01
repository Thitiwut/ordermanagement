package classes.dao;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface PricingDAO {
    boolean InsertPrice(int product_id, int supplier_id, String active_date, double price) throws NamingException, SQLException;
}
