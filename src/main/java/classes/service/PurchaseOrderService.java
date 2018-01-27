package classes.service;

import classes.dao.*;
import classes.object.Product;
import classes.object.PurchaseOrder;
import classes.object.UOB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderService {
    private PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAOImpl();
    private POProductDAO poProductDAO = new POProductDAOImpl();
    private UOBDAO uobDAO = new UOBDAOImpl();

    public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
        this.purchaseOrderDAO = purchaseOrderDAO;
    }

    public void setPoProductDAO(POProductDAO poProductDAO) {
        this.poProductDAO = poProductDAO;
    }

    public void setUOBDAO(UOBDAO uobDAO) {
        this.uobDAO = uobDAO;
    }

    public String AddNewPurchaseOrder(int supplier_id, int customer_branch_id, String order_date, String expect_delivery_date, String status) {
        try {
            if(purchaseOrderDAO.InsertPurchaseOrder(supplier_id, customer_branch_id, order_date, expect_delivery_date, status)){
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

    public String EditPurchaseOrder(String po_number, int supplier_id, int customer_branch_id, String expect_delivery_date){
        try {
            if(purchaseOrderDAO.UpdatePurchaseOrder(po_number, supplier_id, customer_branch_id, expect_delivery_date)) {
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

    public String GetPurchaseOrderByNumber(String po_number) {
        if(po_number != null){
            try {
                PurchaseOrder purchaseOrder = purchaseOrderDAO.GetPurchaseOrderByNumber(po_number);
                ArrayList<Product> po_product = purchaseOrderDAO.GetPurchaseOrderProductsByPOID(purchaseOrder.getPo_id());
                purchaseOrder.setPo_product(po_product);
                Gson gson = new Gson();
                String json_string = gson.toJson(purchaseOrder);
                return json_string;
            }catch (SQLException e){
                e.printStackTrace();
                return "SQL_Error";
            }catch (Exception e){
                e.printStackTrace();
                return "Internal_Error";
            }
        }
        return "Failed";
    }

    public String EditPOProduct(int po_product_id, int new_product_id, Double order_amount, double order_price) {
        try {
            if(poProductDAO.UpdatePOProduct(po_product_id, new_product_id, order_amount, order_price)){
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

    public String AddProductToPurchaseOrder(int product_id, int po_id, Double order_amount, double order_price) {
        try {
            if(poProductDAO.InsertPOProduct(product_id, po_id, order_amount, order_price)){
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

    public String CancelPurchaseOrder(int po_id) {
        try {
            if(purchaseOrderDAO.DeletePurchaseOrder(po_id)){
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

    public String DeletePOProductFromPurchaseOrder(int po_product_id) {
        try {
            if(poProductDAO.DeletePOProduct(po_product_id)){
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

    public String EnterUOBDocument(String fax_date, String payee, String paid_date, int uob_number, String remark) {
        try {
            if(uobDAO.InsertUOB(fax_date, payee, paid_date, uob_number, remark)){
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

    public String UpdateUBODocument(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) {
        try {
            if(uobDAO.UpdateUOB(fax_date, payee, paid_date, uob_number, remark, po_number)){
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

    public String GetUOBDetail(String po_number) {
        try {
            if(po_number != null) {
                UOB uob = uobDAO.GetUOB(po_number);
                Gson gson = new Gson();
                String json_string = gson.toJson(uob);
                return json_string;
            }else return "Failed";
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }
}
