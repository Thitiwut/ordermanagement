package classes.dao;

import classes.model.Supplier;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO {
    ArrayList<Supplier> GetAllSupplier() throws SQLException, NamingException;
}
