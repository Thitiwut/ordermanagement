package classes.dao;

import classes.model.POProduct;
import classes.model.Product;
import classes.model.PurchaseOrder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {

    private static DataSource datasource;

    public PurchaseOrderDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public int InsertPurchaseOrder(int po_number, int supplier_id, int customer_branch_id, String order_date, String expect_delivery_date, String status) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        if(order_date != null && expect_delivery_date != null && status != null){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `OrderManagementDB`.`purchase_order` " +
                    "(`po_number`, `supplier_id`, `branch_id`,`expect_delivery_date`, `status`) " +
                    "VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS );
            statement.setInt(1,po_number);
            statement.setInt(2,supplier_id);
            statement.setInt(3,customer_branch_id);
            statement.setString(4,expect_delivery_date);
            statement.setString(5,status);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int inserted_key = rs.getInt(1);
            connection.close();
            return inserted_key;
        }else return 0;
    }

    public boolean UpdatePurchaseOrder(int po_id, int supplier_id, int customer_branch_id, String expect_delivery_date) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();

        if(expect_delivery_date != null){
            PreparedStatement statement = connection.prepareStatement("UPDATE `OrderManagementDB`.`purchase_order` " +
                    "SET `supplier_id`= ?, `branch_id`=?, `expect_delivery_date`=? " +
                    "WHERE `po_id`=?;");
            statement.setInt(1,supplier_id);
            statement.setInt(2,customer_branch_id);
            statement.setString(3,expect_delivery_date);
            statement.setInt(4,po_id);
            statement.executeUpdate();
            connection.close();
            return true;
        }else return false;
    }

    public boolean DeletePurchaseOrder(int po_id) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `OrderManagementDB`.`purchase_order` WHERE `po_id`=?;");
        statement.setInt(1,po_id);
        statement.executeUpdate();
        connection.close();
        return true;
    }

    public PurchaseOrder GetPurchaseOrderByNumber(String po_number) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT OrderManagementDB.purchase_order.po_id, OrderManagementDB.purchase_order.supplier_id,\n" +
                "OrderManagementDB.supplier.supplier_name, CONCAT(OrderManagementDB.branch.branch_number,\".\",OrderManagementDB.purchase_order.po_number) AS po_number,\n" +
                "OrderManagementDB.purchase_order.branch_id, OrderManagementDB.branch.branch_name, OrderManagementDB.purchase_order.order_date,\n" +
                "OrderManagementDB.purchase_order.expect_delivery_date, OrderManagementDB.purchase_order.status\n" +
                "FROM OrderManagementDB.purchase_order\n" +
                "LEFT JOIN OrderManagementDB.supplier ON OrderManagementDB.purchase_order.supplier_id = OrderManagementDB.supplier.supplier_id\n" +
                "LEFT JOIN OrderManagementDB.branch ON OrderManagementDB.purchase_order.branch_id = OrderManagementDB.branch.branch_id\n" +
                "WHERE OrderManagementDB.purchase_order.po_number = ?;");
        statement.setString(1,po_number);
        ResultSet rs = statement.executeQuery();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        while(rs.next()) {
            purchaseOrder.setPo_id(rs.getInt("po_id"));
            purchaseOrder.setSupplier_id(rs.getInt("supplier_id"));
            purchaseOrder.setSupplier_name(rs.getString("supplier_name"));
            purchaseOrder.setPo_number(rs.getString("po_number"));
            purchaseOrder.setCustomer_branch_id(rs.getInt("branch_id"));
            purchaseOrder.setCustomer_branch_name(rs.getString("branch_name"));
            purchaseOrder.setOrder_date(rs.getString("order_date"));
            purchaseOrder.setExpect_delivery_date(rs.getString("expect_delivery_date"));
            purchaseOrder.setStatus(rs.getString("status"));
        }

        connection.close();
        return purchaseOrder;
    }

    public ArrayList<Product> GetPurchaseOrderProductsByPOID(int po_id) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT OrderManagementDB.po_product.po_product_id, OrderManagementDB.po_product.product_id,\n" +
                "OrderManagementDB.product.product_name, OrderManagementDB.product.product_number,\n" +
                "OrderManagementDB.product.product_type, OrderManagementDB.po_product.order_price,\n" +
                "OrderManagementDB.po_product.order_amount\n" +
                "FROM OrderManagementDB.po_product\n" +
                "LEFT JOIN OrderManagementDB.product ON OrderManagementDB.po_product.product_id = OrderManagementDB.product.product_id\n" +
                "WHERE OrderManagementDB.po_product.po_id = ?;");
        statement.setInt(1,po_id);
        ResultSet rs = statement.executeQuery();

        ArrayList<Product> product_list = new ArrayList<>();
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
        connection.close();
        return product_list;
    }

    public ArrayList<PurchaseOrder> GetPurchaseOrderList(String po_number, String status, String supplier_name, String branch_number) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT CONCAT(OrderManagementDB.branch.branch_number,\".\",OrderManagementDB.purchase_order.po_number) AS po_number, status, supplier_name, branch_name \n" +
                "FROM OrderManagementDB.purchase_order \n" +
                "LEFT JOIN OrderManagementDB.supplier ON OrderManagementDB.purchase_order.supplier_id = OrderManagementDB.supplier.supplier_id \n" +
                "LEFT JOIN OrderManagementDB.branch ON OrderManagementDB.purchase_order.branch_id = OrderManagementDB.branch.branch_id \n" +
                "WHERE CONCAT(OrderManagementDB.branch.branch_number,\".\",OrderManagementDB.purchase_order.po_number) LIKE ? and status LIKE ? and supplier_name LIKE ? and branch_name LIKE ?");
        statement.setString(1, "%" + po_number + "%");
        statement.setString(2,"%" + status + "%");
        statement.setString(3,"%" + supplier_name + "%");
        statement.setString(4, "%" + branch_number + "%");
        ResultSet rs = statement.executeQuery();

        ArrayList<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        while(rs.next()){
            PurchaseOrder po = new PurchaseOrder();
            po.setPo_number(rs.getString("po_number"));
            po.setStatus(rs.getString("status"));
            po.setSupplier_name(rs.getString("supplier_name"));
            po.setCustomer_branch_name(rs.getString("branch_name"));
            purchaseOrderList.add(po);
        }
        connection.close();
        return purchaseOrderList;
    }

    @Override
    public boolean UpdatePOStatus(Integer po_id, String status) throws NamingException, SQLException {
        Connection connection = datasource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE `OrderManagementDB`.`purchase_order` " +
                    "SET `status`= ? " +
                    "WHERE `po_id`= ?;");
            statement.setString(1,status);
            statement.setInt(2,po_id);
            statement.executeUpdate();
            connection.close();
            return true;
    }
}
