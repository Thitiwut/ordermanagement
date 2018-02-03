import classes.dao.SupplierDAO;
import classes.object.Supplier;
import classes.service.SupplierService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupplierServiceTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GetAllSupplierSuccess(){
        SupplierService supplierService = new SupplierService();
        supplierService.setSupplierDAO(new MockSupplierDAO());
        String result = supplierService.GetAllSupplier();
        assertEquals("[{\"supplier_id\":1,\"supplier_name\":\"SUPPLIER_ABC\"},{\"supplier_id\":2,\"supplier_name\":\"SUPPLIER_XYZ\"}]", result);
    }

    /* Mock Object */
    private class MockSupplierDAO implements SupplierDAO{
        public ArrayList<Supplier> GetAllSupplier() {
            ArrayList supplier_list = new ArrayList<Supplier>();
            supplier_list.add(new Supplier(1,"SUPPLIER_ABC"));
            supplier_list.add(new Supplier(2,"SUPPLIER_XYZ"));
            return supplier_list;
        }
    }

}