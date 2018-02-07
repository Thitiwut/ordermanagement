package classes.dao;

import classes.model.Supplier;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO{

    private static DataSource datasource;

    public SupplierDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public ArrayList<Supplier> GetAllSupplier() throws SQLException, NamingException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderManagementDB.supplier;");
        ResultSet rs = statement.executeQuery();

        ArrayList supplier_list = new ArrayList<Supplier>();
        while(rs.next()){
            Supplier supplier = new Supplier();
            supplier.setSupplier_id(rs.getInt("supplier_id"));
            supplier.setSupplier_name(rs.getString("supplier_name"));
            supplier_list.add(supplier);
        }
        return supplier_list;
    }
}
