package classes.dao;

import classes.model.Feed;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedDAO {
    ArrayList<Feed> GetFeed() throws SQLException, NamingException;
}
