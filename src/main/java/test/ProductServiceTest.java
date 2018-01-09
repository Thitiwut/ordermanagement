package test;

import classes.dao.ProductDAO;
import classes.object.Product;
import classes.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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
        String result = productService.AddNewProduct("Apple", "Regular");
        assertEquals("Success", result);
    }

    @Test
    void AddNewProductNotSuccessDuplicatedName(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("Duplicate", "Regular");
        assertEquals("Duplicate", result);
    }

    @Test
    void AddNewProductNotSuccessUnhandledInternalError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("CAUSE_ERROR", "Regular");
        assertEquals("Internal_Error", result);
    }

    @Test
    void AddNewProductNotSuccessUnhandledSQLError(){
        ProductService productService = new ProductService();
        productService.setProductDAO(new MockProductDAO());
        String result = productService.AddNewProduct("CAUSE_SQLERROR", "Regular");
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


    /* Mock Object */
    private class MockProductDAO implements ProductDAO{

        public boolean InsertProduct(String product_name, String product_type) throws SQLException{
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
    }
}