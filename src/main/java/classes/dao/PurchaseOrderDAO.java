package classes.dao;

import classes.model.Product;
import classes.model.PurchaseOrder;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderDAO {
    int InsertPurchaseOrder(int po_number, int supplier_id, int customer_branch_id, String order_date, String expect_delivery_date, String status) throws NamingException, SQLException;
    boolean UpdatePurchaseOrder(int po_id, int supplier_id, int customer_branch_id, String expect_delivery_date) throws NamingException, SQLException;
    boolean DeletePurchaseOrder(int po_id) throws NamingException, SQLException;
    PurchaseOrder GetPurchaseOrderByNumber(String po_number) throws NamingException, SQLException;
    ArrayList<Product> GetPurchaseOrderProductsByPOID(int po_id) throws NamingException, SQLException;
}
