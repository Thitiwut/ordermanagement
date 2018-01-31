package classes.dao;

import classes.object.Branch;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchDAO {
    ArrayList<Branch> GetAllBranch() throws SQLException, NamingException;
}
