package DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.sql.DataSource; 
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
 
public class ConnectionPool {	
	Properties prop = new Properties();
	InputStream configurations = null;
	
	public ConnectionPool() throws IOException{
		configurations = new FileInputStream("D:/Development/Eclipse/Testing/Cucumber/src/test/resources/Configurations/App.config");
		prop.load(configurations);
		System.setProperty("webdriver.chrome.driver","src/test/resources/Files/chromedriver.exe");
	}   
    private static GenericObjectPool gPool = null;
 
    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        Class.forName(prop.getProperty("JDBC_DRIVER"));
        gPool = new GenericObjectPool();
        gPool.setMaxActive(5); 
        ConnectionFactory cf = new DriverManagerConnectionFactory(prop.getProperty("JDBC_DB_URL"), prop.getProperty("JDBC_USER"), prop.getProperty("JDBC_PASS"));
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        return new PoolingDataSource(gPool);
    }
 
    public GenericObjectPool getConnectionPool() {
        return gPool;
    }
 
    private void printDbStatus() {
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }
 
    public static void main(String[] args) throws IOException {
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool jdbcObj = new ConnectionPool();
        try {   
            DataSource dataSource = jdbcObj.setUpPool();
            jdbcObj.printDbStatus();
            connObj = dataSource.getConnection();
            jdbcObj.printDbStatus(); 
 
            pstmtObj = connObj.prepareStatement("query");
            rsObj = pstmtObj.executeQuery();
            while (rsObj.next()) {
                System.out.println("results ");
            }            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if(rsObj != null) {
                    rsObj.close();
                }
                if(pstmtObj != null) {
                    pstmtObj.close();
                }
                if(connObj != null) {
                    connObj.close();
                }
            } catch(Exception sqlException) {
                sqlException.printStackTrace();
            }
        }
        jdbcObj.printDbStatus();
    }
}