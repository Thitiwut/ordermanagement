import classes.dao.POProductDAO;
import classes.dao.PurchaseOrderDAO;
import classes.dao.UOBDAO;
import classes.model.POProduct;
import classes.model.Product;
import classes.model.PurchaseOrder;
import classes.model.UOB;
import classes.service.PurchaseOrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseOrderServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void AddNewPurchaseOrderSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.AddNewPurchaseOrder(123456, 1, 1, "11/11/2018", "11/11/2018", "Pending");
        assertEquals("{\"status\":\"Success\",\"inserted_id\":\""+1+"\",\"action\":\"new_purchase_order\"}", result);
    }

    @Test
    void AddNewPurchaseOrderNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.AddNewPurchaseOrder(123456,1, 1, "11/11/2018", "11/11/2018", "CAUSE_ERROR");
        assertEquals("{\"status\":\"Internal_Error\",\"action\":\"new_purchase_order\"}", result);
    }

    @Test
    void AddNewPurchaseOrderNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.AddNewPurchaseOrder(123456,1, 1, "11/11/2018", "11/11/2018", "CAUSE_SQLERROR");
        assertEquals("{\"status\":\"SQL_Error\",\"action\":\"new_purchase_order\"}", result);
    }

    @Test
    void EditPurchaseOrderSuccess() {
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.EditPurchaseOrder(1, 1, 1,"11/11/2018");
        assertEquals("Success", result);
    }

    @Test
    void EditPurchaseOrderNotSuccessUnhandledInternalError() {
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.EditPurchaseOrder(0, 1, 1,"11/11/2018");
        assertEquals("Internal_Error", result);
    }

    @Test
    void EditPurchaseOrderNotSuccessUnhandledSQLError() {
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.EditPurchaseOrder(999, 1, 1,"11/11/2018");
        assertEquals("SQL_Error", result);
    }

    @Test
    void EditPOProductSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.EditPOProduct(1,1, 20.00,100.00);
        assertEquals("Success", result);
    }

    @Test
    void EditPOProductNotSuccessUnhandledInternalError() {
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.EditPOProduct( 9999, 1,1.00,100.00);
        assertEquals("Internal_Error", result);
    }

    @Test
    void EditPOProductNotSuccessUnhandledSQLError() {
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.EditPOProduct( 9998, 1,1.00,100.00);
        assertEquals("SQL_Error", result);
    }

    @Test
    void GetPurchaseOrderByNumberSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.GetPurchaseOrderByNumber("1");
        assertEquals("{\"po_id\":1,\"supplier_id\":27781,\"supplier_name\":\"นลินี\",\"po_number\":\"999.1\",\"customer_branch_id\":46,\"customer_branch_name\":\"กาญจนบุรี\",\"order_date\":\"11/11/2018\",\"expect_delivery_date\":\"19/11/2018\",\"status\":\"Processing\",\"po_product\":[{\"po_product_id\":1,\"order_amount\":20.0,\"product_id\":1,\"product_name\":\"ส้มสายน้ำผึ้ง#84\",\"product_type\":\"Regular\",\"product_number\":\"175474\",\"product_price\":100.0},{\"po_product_id\":1,\"order_amount\":10.0,\"product_id\":1,\"product_name\":\"ส้มสายน้ำผึ้ง#4\",\"product_type\":\"Regular\",\"product_number\":\"175475\",\"product_price\":150.0}]}", result);
    }

    @Test
    void AddNewPOProductToPurchaseOrderSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.AddProductToPurchaseOrder(1, 1, 1,100.00);
        assertEquals("{\"status\":\"Success\",\"inserted_id\":\""+1+"\",\"action\":\"add_purchase_order_product\"}", result);
    }

    @Test
    void AddNewPOProductToPurchaseOrderNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.AddProductToPurchaseOrder(9999, 1, 1,100.00);
        assertEquals("{\"status\":\"Internal_Error\",\"action\":\"add_purchase_order_product\"}", result);
    }

    @Test
    void AddNewPOProductToPurchaseOrderNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.AddProductToPurchaseOrder(9998, 1, 1,100.00);
        assertEquals("{\"status\":\"SQL_Error\",\"action\":\"add_purchase_order_product\"}", result);
    }

    @Test
    void CancelPurchaseOrderSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.CancelPurchaseOrder(1);
        assertEquals("Success", result);
    }

    @Test
    void CancelPurchaseOrderNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.CancelPurchaseOrder(9999);
        assertEquals("Internal_Error", result);
    }

    @Test
    void CancelPurchaseOrderNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPurchaseOrderDAO(new MockPurchaseOrderDAO());
        String result = purchaseOrderService.CancelPurchaseOrder(9998);
        assertEquals("SQL_Error", result);
    }

    @Test
    void DeletePOProductFromPurchaseOrderSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.DeletePOProductFromPurchaseOrder(1);
        assertEquals("Success", result);
    }

    @Test
    void DeletePOProductFromPurchaseOrderNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.DeletePOProductFromPurchaseOrder(9999);
        assertEquals("Internal_Error", result);
    }

    @Test
    void DeletePOProductFromPurchaseOrderNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setPoProductDAO(new MockPOProductDAO());
        String result = purchaseOrderService.DeletePOProductFromPurchaseOrder(9998);
        assertEquals("SQL_Error", result);
    }

    @Test
    void EnterUOBDocumentSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.EnterUOBDocument("11/12/2018","payee","11/12/2018",1212, "remark", "");
        assertEquals("Success", result);
    }

    @Test
    void EnterUOBDocumentNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.EnterUOBDocument("CAUSE_ERROR","payee","11/12/2018",1212, "remark", "");
        assertEquals("Internal_Error", result);
    }

    @Test
    void EnterUOBDocumentNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.EnterUOBDocument("CAUSE_SQLERROR","payee","11/12/2018",1212, "remark", "");
        assertEquals("SQL_Error", result);
    }

    @Test
    void EnterUOBDocumentNotSuccessNullValueFound(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.EnterUOBDocument(null,"payee","11/12/2018",1212, "remark", "");
        assertEquals("Failed", result);
    }

    @Test
    void UpdateUOBDocumentSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.UpdateUBODocument("11/12/2018","payee","11/12/2018",1212,"remark","1");
        assertEquals("Success", result);
    }

    @Test
    void UpdateUOBDocumentNotSuccessUnhandledInternalError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.UpdateUBODocument("CAUSE_ERROR","payee","11/12/2018",1212,"remark","1");
        assertEquals("Internal_Error", result);
    }

    @Test
    void UpdateUOBDocumentNotSuccessUnhandledSQLError(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.UpdateUBODocument("CAUSE_SQLERROR","payee","11/12/2018",1212,"remark","1");
        assertEquals("SQL_Error", result);
    }

    @Test
    void UpdateUOBDocumentNotSuccessNullValueFound(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.UpdateUBODocument(null,"payee","11/12/2018",1212,"remark","1");
        assertEquals("Failed", result);
    }

    @Test
    void GetUBODetailSuccess(){
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        purchaseOrderService.setUOBDAO(new MockUOBDAO());
        String result = purchaseOrderService.GetUOBDetail("1");
        assertEquals("{\"uob_id\":1,\"uob_number\":14977,\"payee\":\"payee\",\"fax_date\":\"11/11/2018\",\"po_number\":\"999.1\",\"direct_dc\":\"NA\",\"remark\":\"remark\"}", result);
    }

    /* Mock Object */
    private class MockPurchaseOrderDAO implements PurchaseOrderDAO{
        public int InsertPurchaseOrder(int po_number, int supplier_id, int customer_branch_id, String order_date, String expect_delivery_date, String status) throws SQLException {
            if(status == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return 0;
            }

            if(status == "CAUSE_SQLERROR") throw new SQLException();

            if(order_date != null && expect_delivery_date != null && status != null){
                return 1;
            }
            return 0;
        }

        public boolean UpdatePurchaseOrder(int po_number, int supplier_id, int customer_branch_id, String expect_delivery_date) throws NamingException, SQLException {
            if(po_number == 0){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(po_number == 999) throw new SQLException();

            if(expect_delivery_date != null){
                return true;
            }
            return false;
        }

        public boolean DeletePurchaseOrder(int po_id) throws SQLException {
            if(po_id == 9999){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(po_id == 9998) throw new SQLException();

            return true;
        }

        public PurchaseOrder GetPurchaseOrderByNumber(String po_number) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPo_id(1);
            purchaseOrder.setSupplier_id(27781);
            purchaseOrder.setSupplier_name("นลินี");
            purchaseOrder.setPo_number("999.1");
            purchaseOrder.setCustomer_branch_id(46);
            purchaseOrder.setCustomer_branch_name("กาญจนบุรี");
            purchaseOrder.setOrder_date("11/11/2018");
            purchaseOrder.setExpect_delivery_date("19/11/2018");
            purchaseOrder.setStatus("Processing");
            return purchaseOrder;
        }

        public ArrayList<Product> GetPurchaseOrderProductsByPOID(int po_id) throws NamingException, SQLException {
            ArrayList product_list = new ArrayList<Product>();
            product_list.add(new POProduct(1,1, "ส้มสายน้ำผึ้ง#84", "175474", "Regular", 100.00, 20));
            product_list.add(new POProduct(1,1, "ส้มสายน้ำผึ้ง#4", "175475", "Regular", 150.00, 10));
            return product_list;
        }
    }

    private class MockPOProductDAO implements POProductDAO{

        public int InsertPOProduct(int product_id, int po_id, Double order_amount, double order_price) throws SQLException {
            if(product_id == 9999){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return 0;
            }

            if(product_id == 9998) throw new SQLException();

            return 1;
        }

        @Override
        public int InsertPOProduct(int product_id, int po_id, int order_amount, double order_price) throws NamingException, SQLException {
            return 0;
        }

        public boolean DeletePOProduct(int po_product_id) throws SQLException {
            if(po_product_id == 9999){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(po_product_id == 9998) throw new SQLException();

            return true;
        }

        public boolean UpdatePOProduct(int po_product_id, int new_product_id, Double order_amount, double order_price) throws SQLException {
            if(po_product_id == 9999){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(po_product_id == 9998) throw new SQLException();

            return true;
        }
    }

    private class MockUOBDAO implements UOBDAO{

        public boolean InsertUOB(String fax_date, String payee, String paid_date, int uob_number, String remark, String po_number) throws NamingException, SQLException {
            if(fax_date == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(fax_date == "CAUSE_SQLERROR") throw new SQLException();

            if(fax_date != null && payee != null && paid_date != null){
                return true;
            }
            return false;
        }

        public boolean UpdateUOB(String fax_date, String payee, String paid_date, int uob_number, String remmark, String po_number) throws NamingException, SQLException {
            if(fax_date == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(fax_date == "CAUSE_SQLERROR") throw new SQLException();

            if(fax_date != null && payee != null && paid_date != null){
                return true;
            }
            return false;
        }

        public UOB GetUOB(String po_number) {
            UOB uob = new UOB();
            uob.setUob_id(1);
            uob.setUob_number(14977);
            uob.setPayee("payee");
            uob.setFax_date("11/11/2018");
            uob.setPo_number("999.1");
            uob.setDirect_dc("NA");
            uob.setRemark("remark");
            return uob;
        }
    }
}