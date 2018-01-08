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

        public boolean DeleteProduct() {
            return false;
        }

        public boolean UpdateProduct() {
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