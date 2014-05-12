/**
 * 
 */
package cn.log.db.h2;

import java.sql.Connection;  
import java.sql.SQLException;  
import javax.naming.Context;  
import javax.naming.InitialContext;  
import javax.naming.NamingException;  
import javax.sql.DataSource;  
import org.h2.jdbcx.JdbcDataSource;

/**
 * @author zouqone
 *
 */
public class DataSourceDBConnection {
	/** 
     * 注册一个JNDI命名调用服务 
     */  
    public static void register(){  
         JdbcDataSource ds = new JdbcDataSource();  
         ds.setURL("jdbc:h2:~/test");  
         ds.setUser("sa");  
         ds.setPassword("");  
         Context ctx;  
         try {  
            ctx = new InitialContext();  
             try {  
                    ctx.bind("jdbc/dsName", ds);  
                } catch (NamingException e) {  
                    e.printStackTrace();  
                }  
        } catch (NamingException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 调用获取数据源建立JDBC连接 
     * @return 
     */  
    public static Connection getConnection(){  
        Context ctx;  
        try {  
            ctx = new InitialContext();  
             DataSource ds = (DataSource) ctx.lookup("jdbc/dsName");  
             try {  
                Connection conn = ds.getConnection();  
                return conn;  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        } catch (NamingException e) {  
            e.printStackTrace();  
        }  
        return null;  
          
    }  
    public static void main(String[] args) {  
        DataSourceDBConnection.register();  
        System.out.println(DataSourceDBConnection.getConnection());  
    }  
}
