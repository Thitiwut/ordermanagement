package classes.dao;

import classes.object.Supplier;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO {
    ArrayList<Supplier> GetAllSupplier() throws SQLException, NamingException;
}
