import classes.dao.PricingDAO;
import classes.dao.ProductDAO;
import classes.model.Product;
import classes.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void AddNewProductSuccess(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("Apple", "Regular", 1, 100.00);
        assertEquals("Success", result);
    }

    @Test
    void AddNewProductNotSuccessDuplicatedName(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("Duplicate", "Regular", 1, 100.00);
        assertEquals("Duplicate", result);
    }

    @Test
    void AddNewProductNotSuccessUnhandledInternalError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("CAUSE_ERROR", "Regular", 1, 100.00);
        assertEquals("Internal_Error", result);
    }

    @Test
    void AddNewProductNotSuccessUnhandledSQLError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("CAUSE_SQLERROR", "Regular", 1, 100.00);
        assertEquals("SQL_Error", result);
    }

    @Test
    void DeleteProductSuccess(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.DeleteProduct(1);
        assertEquals("Success", result);
    }

    @Test
    void DeleteProductNotSuccessUnhandledInternalError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.DeleteProduct(2);
        assertEquals("Internal_Error", result);
    }

    @Test
    void DeleteProductNotSuccessUnhandledSQLError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.DeleteProduct(3);
        assertEquals("SQL_Error", result);
    }

    @Test
    void EditProductSuccess(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.EditProduct(1, "New_Name");
        assertEquals("Success", result);
    }

    @Test
    void EditProductNotSuccessDuplicatedName(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.EditProduct(1, "Duplicate");
        assertEquals("Duplicate", result);
    }

    @Test
    void EditProductNotSuccessUnhandledInternalError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.EditProduct(1, "CAUSE_ERROR");
        assertEquals("Internal_Error", result);
    }

    @Test
    void EditProductNotSuccessUnhandledSQLError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.EditProduct(1, "CAUSE_SQLERROR");
        assertEquals("SQL_Error", result);
    }

    @Test
    void GetProductListSuccess(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String json_string = productService.GetProductList(1);
        String expected_json_string = "[{\"product_id\":1,\"product_name\":\"ส้มสายน้ำผึ้ง#84\",\"product_type\":\"Regular\",\"product_number\":\"175474\",\"product_price\":100.0},{\"product_id\":2,\"product_name\":\"ส้มสายน้ำผึ้ง#72\",\"product_type\":\"Regular\",\"product_number\":\"175474\",\"product_price\":100.0},{\"product_id\":3,\"product_name\":\"ส้มสายน้ำผึ้ง#105\",\"product_type\":\"Regular\",\"product_number\":\"175474\",\"product_price\":100.0}]";
        assertEquals(expected_json_string, json_string);
    }

    @Test
    void SetNewProductPriceSuccess(){
        ProductService productService = new ProductService();
        productService.setPriceDAO(new MockPriceDAO());
        String result = productService.SetNewProductPrice(1, 1, "11/12/2018", 100.00);
        assertEquals("Success", result);
    }


    /* Mock Object */
    private class MockProductDAO implements ProductDAO{

        public boolean InsertProduct(String product_name, String product_type, int supplier_id, double price) throws SQLException{
            if(product_name == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(product_name == "CAUSE_SQLERROR") throw new SQLException();

            if(product_name != null && product_type != null){
                return true;
            }

            return false;
        }

        public boolean DeleteProduct(int product_id) throws SQLException{
            if(product_id == 1){
                return true;
            }else if(product_id == 2) {
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }else if(product_id == 3){
                throw new SQLException();
            }else return false;
        }

        public boolean UpdateProduct(int product_id, String new_product_name) throws SQLException{
            if(new_product_name == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(new_product_name == "CAUSE_SQLERROR") throw new SQLException();

            if(new_product_name != null){
                return true;
            }

            return false;
        }

        public Product GetProductByName(String product_name) {
            if(product_name == "Duplicate"){
                return new Product();
            }
            return null;
        }

        public Product GetProductByID(int product_id) {
            return null;
        }

        public ArrayList GetAllProduct(int supplier_id) throws NamingException, SQLException {
            ArrayList product_list = new ArrayList<Product>();
            product_list.add(new Product(1, "ส้มสายน้ำผึ้ง#84", "175474", "Regular", 100.00));
            product_list.add(new Product(2, "ส้มสายน้ำผึ้ง#72", "175474", "Regular", 100.00));
            product_list.add(new Product(3, "ส้มสายน้ำผึ้ง#105", "175474", "Regular",100.00));
            return product_list;
        }
    }

    private class MockPriceDAO implements PricingDAO{
        public boolean InsertPrice(int product_id, int supplier_id, String active_date, double price) throws NamingException, SQLException {
            if(active_date == "CAUSE_ERROR"){
                int[] test_array = null;
                int test_value = test_array[1] + test_array[1];
                return false;
            }

            if(active_date == "CAUSE_SQLERROR") throw new SQLException();

            if(active_date != null){
                return true;
            }

            return false;
        }
    }
}