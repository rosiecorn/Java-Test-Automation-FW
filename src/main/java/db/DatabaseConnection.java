package db;

import exception.CustomException;
import global.Variables;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hongle
 * This class declare all of information to set up connection to dbms system
 * Supported dbms: oracle, postgresql, sqlserver, mysql
 */


public class DatabaseConnection {

    private DBType type;
    private Connection connection;

    public  DatabaseConnection (DBType type)
    {
        this.type = type;
    }

    /**
     * Base on each DBType, it will load suitable database provider
     * @throws ClassNotFoundException
     */

    public void  loadDatabaseProvider () throws ClassNotFoundException {
        try
        {
            if (type == DBType.MYSQL)
            {
                Class.forName("com.mysql.jdbc.Driver");
                return;
            }
            if (type == DBType.SQLSERVER)
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                return;
            }
            if(type == DBType.ORACLE)
            {
                Class.forName("oracle.jdbc.OracleDriver");
                return;
            }
            if(type == DBType.POSTGRESQL)
            {
                Class.forName("org.postgresql.Driver");
            }
        }
        catch (ClassNotFoundException ex)
        {
            throw new ClassNotFoundException(ex.getMessage());
        }
    }

    public boolean isOpening()
    {
        try
        {
            return (this.connection !=null) && (!this.connection.isClosed());
        }
        catch(SQLException e)
        {
            return false;
        }
    }

    public void close (Connection conn)
    {
        DbUtils.closeQuietly(conn);
    }

    public Connection getConnection() {

        try {

            loadDatabaseProvider();

            connection = DriverManager.getConnection(Variables.CONN_STRING, Variables.USER, Variables.PASSWORD);
            return connection;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }



}
