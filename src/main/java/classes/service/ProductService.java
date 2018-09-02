package classes.service;

import classes.dao.PricingDAO;
import classes.dao.PricingDAOImpl;
import classes.dao.ProductDAO;
import classes.dao.ProductDAOImpl;
import classes.model.Product;
import classes.object.DataSourceProvider;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO = new ProductDAOImpl(new DataSourceProvider().GetDataSource());
    private PricingDAO pricingDAO = new PricingDAOImpl(new DataSourceProvider().GetDataSource());

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void setPriceDAO(PricingDAO pricingDAO) {this.pricingDAO = pricingDAO; }

    public String AddNewProduct(String product_name, Integer product_id, String product_type, int supplier_id, double price) {
        try {
            Product product = productDAO.GetProductByName(product_name);
            if (product != null) {
                return "Duplicate";
            }
            if (productDAO.InsertProduct(product_name, product_id, product_type, supplier_id, price)) {
                return "Success";
            } else return "Failed";
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error|"+e.getMessage();
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

    public String GetProductList(int supplier_id) {
        try {
            ArrayList product_list = productDAO.GetAllProduct(supplier_id);
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

    public String SetNewProductPrice(int product_id, int supplier_id, String active_date, double price) {
        try {
            if(pricingDAO.InsertPrice(product_id, supplier_id, active_date, price)){
                return "Success";
            }
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
        return "Failed";
    }
}