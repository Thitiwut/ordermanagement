package classes.dao;

import classes.model.Branch;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDAOImpl implements BranchDAO {

    private static DataSource datasource;

    public BranchDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    public ArrayList<Branch> GetAllBranch() throws SQLException, NamingException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderManagementDB.branch;");
        ResultSet rs = statement.executeQuery();

        ArrayList branch_list = new ArrayList<Branch>();
        while(rs.next()){
            Branch branch = new Branch();
            branch.setBranch_id(rs.getInt("branch_id"));
            branch.setBranch_name(rs.getString("branch_name"));
            branch.setBranch_number(rs.getInt("branch_number"));
            branch_list.add(branch);
        }
        connection.close();
        return branch_list;
    }
}
