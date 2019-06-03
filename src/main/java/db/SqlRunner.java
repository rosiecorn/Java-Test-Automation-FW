package db;

import exception.CustomException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.apache.commons.dbutils.DbUtils.close;

/**
 * Author honge
 */

public  class SqlRunner {

    private SqlRunner(){}

    /**
     * Execute a Sql query on DB server and return a result set
     * @param conn
     * @param queryString
     * @return A result set
     * @throws SQLException
     */
    public static ResultSet executeQuery (Connection conn, String queryString) throws SQLException
    {
        Statement stm=null;
        try
        {
            ResultSet rs;
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(queryString);

            return  rs;
        }
        catch (SQLException ex)
        {
            throw  new CustomException(ex.getMessage());
        }

    }

    /**
     * Execute a DDL statement
     * @param conn
     * @param queryString  An Sql data manipulation language (DML) statement, such as Insert, UPDATE or DELETE or a Sql statement that returns nothing
     * @return
     * @throws SQLException
     */
    public static boolean execute (Connection conn, String queryString) throws SQLException
    {
        boolean result;
        try (Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            result = stm.execute(queryString);
        }
        conn.commit();
        return  result;
    }
}
