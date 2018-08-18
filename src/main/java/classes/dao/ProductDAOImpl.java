package classes.dao;

import classes.model.Product;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO{

    private static DataSource datasource;

    public ProductDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public boolean InsertProduct(String product_name, String product_type, int supplier_id, double price) throws NamingException,SQLException {
        Connection connection = datasource.getConnection();
        if(product_name != null && product_type != null){
            PreparedStatement statement = connection.prepareStatement
                    ("START TRANSACTION;" +
                            "INSERT INTO `OrderManagementDB`.`product` (`product_name`, `product_type`) VALUES (?, ?);" +
                            "SET @product_key = LAST_INSERT_ID();" +
                            "INSERT INTO `OrderManagementDB`.`price` (`product_id`, `supplier_id`) VALUES (@product_key, ?);" +
                    "COMMIT;");
            statement.setString(1, product_name);
            statement.setString(2,product_type);
            statement.setInt(3,supplier_id);
            statement.executeUpdate();
            connection.close();
            return true;
        }else return false;
    }

    public boolean DeleteProduct(int product_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `OrderManagementDB`.`product` WHERE `product_id`=?;");
        statement.setInt(1,product_id);
        statement.executeUpdate();
        connection.close();
        return true;
    }

    public boolean UpdateProduct(int product_id, String new_product_name) throws NamingException,SQLException{
        Connection connection = datasource.getConnection();
        if(new_product_name != null){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `OrderManagementDB`.`product` SET `product_name`=? WHERE `product_id`=?;");
            statement.setString(1,new_product_name);
            statement.setInt(2,product_id);
            statement.executeUpdate();
            connection.close();
            return true;
        }else connection.close(); return false;
    }

    public Product GetProductByName(String product_name) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `OrderManagementDB`.product WHERE `product_name` = ?;");
        statement.setString(1, product_name);

        ResultSet rs = statement.executeQuery();

        Product product = null;
        if(rs.next()) {
            product = new Product();
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_number(rs.getString("product_number"));
            product.setProduct_type(rs.getString("product_type"));
            product.setPackage_component(rs.getString("package_component"));
        }
        connection.close();
        return product;
    }

    public Product GetProductByID(int product_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderManagementDB.product WHERE product_id=?;");
        statement.setInt(1, product_id);
        ResultSet rs = statement.executeQuery();
        rs.next();

        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_number(rs.getString("product_number"));
        product.setProduct_type(rs.getString("product_type"));
        product.setPackage_component(rs.getString("package_component"));
        product.setPrice(rs.getDouble("price"));

        connection.close();
        return product;
    }

    public ArrayList<Product> GetAllProduct(int supplier_id) throws NamingException, SQLException{
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "        OrderManagementDB.`product`.`product_id` AS product_id,\n" +
                "        OrderManagementDB.`product`.`product_name` AS product_name,\n" +
                "        OrderManagementDB.`product`.`product_number` AS product_number,\n" +
                "        OrderManagementDB.`product`.`product_type` AS product_type,\n" +
                "        OrderManagementDB.`product`.`package_component` AS package_component,\n" +
                "        IFNULL(ANY_VALUE(OrderManagementDB.`price`.`price`),\n" +
                "                0) AS price,\n" +
                "\t\tOrderManagementDB.`price`.supplier_id AS supplier_id\n" +
                "    FROM\n" +
                "        (OrderManagementDB.`product`\n" +
                "        LEFT JOIN OrderManagementDB.`price` ON ((OrderManagementDB.`product`.`product_id` = OrderManagementDB.`price`.`product_id`)))\n" +
                "    WHERE\n" +
                "        OrderManagementDB.`price`.`price_id` IN (SELECT \n" +
                "                MAX(OrderManagementDB.`price`.`price_id`) AS price_id\n" +
                "            FROM\n" +
                "                OrderManagementDB.`price`\n" +
                "            GROUP BY OrderManagementDB.`price`.`product_id`\n" +
                "            ORDER BY OrderManagementDB.`price`.`product_id` DESC)\n" +
                "            AND supplier_id = ?\n" +
                "    GROUP BY OrderManagementDB.`price`.`product_id`\n" +
                "    ORDER BY OrderManagementDB.`price`.`product_id` ASC");
        statement.setInt(1, supplier_id);
        ResultSet rs = statement.executeQuery();

        ArrayList<Integer> product_id_list = new ArrayList<>();
        ArrayList<Product> product_list = new ArrayList<>();
        while(rs.next()){
            Product product = new Product();
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_number(rs.getString("product_number"));
            product.setProduct_type(rs.getString("product_type"));
            product.setPackage_component(rs.getString("package_component"));

            PreparedStatement statement2 = connection.prepareStatement( "SELECT OrderManagementDB.`price`.`price` FROM OrderManagementDB.price \n" +
                            "WHERE product_id = ? AND active_date <= now() \n" +
                            "ORDER BY active_date DESC LIMIT 1");
            statement2.setInt(1, rs.getInt("product_id"));
            ResultSet rs2 = statement2.executeQuery();
            rs2.next();
            product.setPrice(rs2.getDouble("price"));
            product_list.add(product);
            statement2.close();
            rs2.close();
        }

        rs.close();
        statement.close();
        connection.close();
        return product_list;
    }
}
