package classes.dao;

import classes.model.Feed;
import org.ocpsoft.prettytime.PrettyTime;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedDAOImpl implements FeedDAO {

    private static DataSource datasource;

    public FeedDAOImpl(DataSource datasource){
        this.datasource = datasource ;
    }

    @Override
    public ArrayList<Feed> GetFeed() throws SQLException, NamingException {
        Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT CONCAT(OrderManagementDB.branch.branch_number,\".\",OrderManagementDB.purchase_order.po_number) AS po_number, expect_delivery_date FROM OrderManagementDB.purchase_order \n" +
                        "LEFT JOIN OrderManagementDB.branch ON OrderManagementDB.purchase_order.branch_id = OrderManagementDB.branch.branch_id \n" +
                        "WHERE NOW() <= expect_delivery_date AND expect_delivery_date <= date_add(NOW() , INTERVAL 20 day);"
        );
        ResultSet rs = statement.executeQuery();

        ArrayList feed_list = new ArrayList<Feed>();
        while(rs.next()){
            Feed feed = new Feed();
            feed.setDate("แจ้งเตือน");
            feed.setExtraText("ครบกำหนดวันจัดส่งในวันที่  " + rs.getDate("expect_delivery_date").toString());
            feed.setIcon("calendar alternate outline");
            String po_number = rs.getString("po_number");
            feed.setSummary("ใบสั่งซื้อเลขที่|"+po_number+"|ใกล้ครบกำหนดวันจัดส่ง !");
            feed.setAction("alert");
            feed_list.add(feed);
        }

        statement = connection.prepareStatement("SELECT * FROM OrderManagementDB.feed ORDER BY update_timestamp DESC LIMIT 10;");
        rs = statement.executeQuery();
        while(rs.next()){
            Feed feed = new Feed();
            PrettyTime prettyTime = new PrettyTime();
            feed.setDate(prettyTime.format(rs.getDate("update_timestamp")));
            feed.setIcon(getIconForAction(rs.getString("feed_action")));
            feed.setSummary(getSummaryForAction(rs.getString("feed_action"),
                    rs.getString("feed_po_number"),
                    rs.getString("feed_product"),
                    rs.getString("feed_supplier")
                    ));
            feed.setAction(rs.getString("feed_action"));
            feed_list.add(feed);
        }
        connection.close();
        return feed_list;
    }

    private String getIconForAction(String action){
        switch (action) {
            case "po_added":
                return "file alternate";
            case "product_added":
                return "shopping basket";

            case "po_delivered":
                return "truck";
            case "po_edited":
                return "edit";
            case "po_cancel":
                return "cancel";
            default:
                return "question circle outline";
        }
    }

    private String getSummaryForAction(String action, String po_number, String product, String supplier){
        switch (action) {
            case "po_added":
                return "ใบสั่งซื้อเลขที่|"+po_number+"|ถูกเพิ่มแล้ว";
            case "product_added":
                return "สินค้า|"+product+"|supplier|"+supplier+"|ถูกเพิ่มแล้ว";
            case "po_delivered":
                return "ใบสั่งซื้อเลขที่|"+po_number+"|ถูกจัดส่งแล้ว";
            case "po_edited":
                return "ใบสั่งซื้อเลขที่|"+po_number+"|ถูกแก้ไขแล้ว";
            case "po_cancel":
                return "ใบสั่งซื้อเลขที่|"+po_number+"|ถูกยกเลิกแล้ว";
            default:
                return "-";
        }
    }
}
