package classes.dao;

import classes.object.POProduct;
import classes.object.Product;
import classes.object.PurchaseOrder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {

    public boolean InsertPurchaseOrder(int supplier_id, int customer_branch_id, String order_date, String expect_delivery_date, String status) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        if(order_date != null && expect_delivery_date != null && status != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO XXXXXXXXXXXXXXXX");
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public boolean UpdatePurchaseOrder(String po_number, int supplier_id, int customer_branch_id, String expect_delivery_date) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();

        if(po_number != null && expect_delivery_date != null){
            PreparedStatement statement = connection.prepareStatement("UPDATE  XXXXXXXXXXXXXXXX"); //UPDATE PO Table
            statement.executeQuery();
            connection.close();
            return true;
        }else return false;
    }

    public boolean DeletePurchaseOrder(int po_id) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM XXXXXXXXXXXXXXXX");
        statement.executeQuery();
        connection.close();
        return true;
    }

    public PurchaseOrder GetPurchaseOrderByNumber(String po_number) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE id");
        ResultSet rs = statement.executeQuery();
        rs.next();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPo_id(rs.getInt("po_id"));
        purchaseOrder.setSupplier_id(rs.getInt("supplier_id"));
        purchaseOrder.setSupplier_name(rs.getString("supplier_name"));
        purchaseOrder.setPo_number(rs.getString("po_number"));
        purchaseOrder.setCustomer_branch_id(rs.getInt("customer_branch_id"));
        purchaseOrder.setCustomer_branch_name(rs.getString("customer_branch_name"));
        purchaseOrder.setOrder_date(rs.getString("order_date"));
        purchaseOrder.setExpect_delivery_date(rs.getString("expect_delivery_date"));
        purchaseOrder.setStatus(rs.getString("status"));
        return purchaseOrder;
    }

    public ArrayList<Product> GetPurchaseOrderProductsByPOID(int po_id) throws NamingException, SQLException {
        DataSource datasource = (DataSource) new InitialContext().lookup("OrderManagementDB");
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT FROM XXXXXXXXXXXXXXXX WHERE po_id");
        ResultSet rs = statement.executeQuery();

        ArrayList product_list = new ArrayList<Product>();
        while(rs.next()){
            Product product = new POProduct();
            ((POProduct) product).setPo_product_id(rs.getInt("po_product_id"));
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_number(rs.getString("product_number"));
            product.setProduct_type(rs.getString("product_type"));
            product.setPrice(rs.getDouble("order_price"));
            ((POProduct) product).setOrder_amount(rs.getDouble("order_amount"));
            product_list.add(product);
        }
        return product_list;
    }
}
