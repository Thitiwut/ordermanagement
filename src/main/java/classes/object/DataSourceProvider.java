package classes.object;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider {
    private static String EnvContextDefinition = "java:/comp/env";
    private static String DataSourceName = "OrderManagementDB";

    public DataSource GetDataSource(){
        Context initialContext = null;
        try {
            initialContext = new InitialContext();
        Context envContext  = (Context)initialContext.lookup(EnvContextDefinition);
        DataSource datasource = (DataSource)envContext.lookup(DataSourceName);
        return datasource;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
