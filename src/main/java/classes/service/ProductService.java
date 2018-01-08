package classes.service;

import classes.dao.ProductDAO;
import classes.dao.ProductDAOImpl;
import classes.object.Product;

import java.sql.SQLException;

public class ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    public String AddNewProduct(String product_name, String product_type) {
        try {
            Product product = productDAO.GetProductByName(product_name);
            if (product != null) {
                return "Duplicate";
            }
            if (productDAO.InsertProduct(product_name, product_type)) {
                return "Success";
            } else return "Failed";
        }catch (SQLException ex){
            return "SQL_Error";
        }catch (Exception ex){
            return "Internal_Error";
        }
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}