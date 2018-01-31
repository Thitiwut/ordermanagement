package classes.service;

import classes.dao.SupplierDAO;
import classes.dao.SupplierDAOImpl;
import com.google.gson.Gson;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierService {
    private SupplierDAO supplierDAO = new SupplierDAOImpl();

    public void setSupplierDAO(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public String GetAllSupplier() {
        try {
            ArrayList supplier_list = supplierDAO.GetAllSupplier();
            Gson gson = new Gson();
            String json_string = gson.toJson(supplier_list);
            return json_string;
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }
}
