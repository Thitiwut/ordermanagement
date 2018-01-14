package classes.service;

import classes.dao.ProductDAO;
import classes.dao.ProductDAOImpl;
import classes.object.Product;
import com.google.gson.Gson;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public String AddNewProduct(String product_name, String product_type) {
        try {
            Product product = productDAO.GetProductByName(product_name);
            if (product != null) {
                return "Duplicate";
            }
            if (productDAO.InsertProduct(product_name, product_type)) {
                return "Success";
            } else return "Failed";
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }

    public String DeleteProduct(int product_id) {
        try {
            if(productDAO.DeleteProduct(product_id)) {
                return "Success";
            }else return "Failed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_Error";
        } catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }

    public String EditProduct(int product_id, String new_product_name) {
        try {
            Product product = productDAO.GetProductByName(new_product_name);
            if (product != null) {
                return "Duplicate";
            }
            if(productDAO.UpdateProduct(product_id, new_product_name)){
                return "Success";
            }else return "Failed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_Error";
        } catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }

    public String GetProductList() {
        try {
            ArrayList product_list = productDAO.GetAllProduct();
            Gson gson = new Gson();
            String json_string = gson.toJson(product_list);
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