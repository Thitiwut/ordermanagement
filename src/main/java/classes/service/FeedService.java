package classes.service;

import classes.dao.BranchDAO;
import classes.dao.BranchDAOImpl;
import classes.dao.FeedDAO;
import classes.dao.FeedDAOImpl;
import classes.model.Feed;
import classes.object.DataSourceProvider;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedService {
    private FeedDAO feedDAO = new FeedDAOImpl(new DataSourceProvider().GetDataSource());

    public String GetFeed() {
        try {
            ArrayList feed_list = feedDAO.GetFeed();
            Gson gson = new Gson();
            String json_string = gson.toJson(feed_list);
            return json_string;
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error";
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }

    public String AddNewFeed(String feed_action, String feed_po_number, String feed_product, String feed_supplier) {
        try {
            feedDAO.insertNewFeed(feed_action, feed_po_number, feed_product, feed_supplier);
            return "Success";
        }catch (SQLException e){
            e.printStackTrace();
            return "SQL_Error|"+e.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            return "Internal_Error";
        }
    }
}
