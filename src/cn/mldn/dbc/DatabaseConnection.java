package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 本类负责数据库的连接操作
 * Created by Tony on 2017/1/2.
 */
public class DatabaseConnection {
    private static final String DBDRIVERS = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:MLDN";
    private static final String DBUSER = "scott";
    private static final String PASSWORD = "tiger";
    private Connection conn = null;
    /**
     *
     */
    public DatabaseConnection(){
        try {
            Class.forName(DBDRIVERS);
            this.conn = DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.conn;
    }

    public void close(){
        if (this.conn !=null){
            try{
                this.conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
