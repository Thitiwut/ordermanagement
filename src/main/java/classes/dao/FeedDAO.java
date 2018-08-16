package classes.dao;

import classes.model.Feed;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedDAO {
    ArrayList<Feed> GetFeed() throws SQLException, NamingException;

    boolean insertNewFeed(String feed_action, String feed_po_number, String feed_product, String feed_supplier) throws SQLException, NamingException;
}
