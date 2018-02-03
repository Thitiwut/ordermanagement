import classes.dao.BranchDAO;
import classes.object.Branch;
import classes.service.BranchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BranchServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GetAllSupplierSuccess(){
        BranchService branchService = new BranchService();
        branchService.setBranchDAO(new MockBranchDAO());
        String result = branchService.GetAllBranch();
        assertEquals("[{\"branch_id\":1,\"branch_owner_id\":1,\"branch_name\":\"วังน้อย\",\"branch_number\":576,\"branch_address\":\"ADDRESS#1\"},{\"branch_id\":2,\"branch_owner_id\":2,\"branch_name\":\"BRANCH-A\",\"branch_number\":557,\"branch_address\":\"ADDRESS#2\"}]", result);
    }

    /* Mock Object */
    private class MockBranchDAO implements BranchDAO{
        public ArrayList<Branch> GetAllBranch() throws SQLException, NamingException {
            ArrayList supplier_list = new ArrayList<Branch>();
            supplier_list.add(new Branch(1, 1, "วังน้อย", 576, "ADDRESS#1"));
            supplier_list.add(new Branch(2, 2, "BRANCH-A", 557, "ADDRESS#2"));
            return supplier_list;
        }
    }
}