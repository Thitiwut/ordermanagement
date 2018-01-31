package classes.service;

import classes.dao.BranchDAO;
import classes.dao.BranchDAOImpl;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchService {
    private BranchDAO branchDAO = new BranchDAOImpl();

    public void setBranchDAO(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    public String GetAllBranch() {
        try {
            ArrayList branch_list = branchDAO.GetAllBranch();
            Gson gson = new Gson();
            String json_string = gson.toJson(branch_list);
            return json_string;
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }
}
