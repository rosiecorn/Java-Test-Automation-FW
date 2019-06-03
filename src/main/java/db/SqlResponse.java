package db;

import exception.CustomException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author hongle
 */
public class SqlResponse {

    /**
     * An Overload method
     * Get list of column value from a result set bases on column label
     * @param rs
     * @param colName
     * @return
     * @throws SQLException
     */
    public static List<String> getListCellValue (ResultSet rs, String colName)
    {
        try {
            List<String> list = new ArrayList<>();
            rs.beforeFirst();
            while (rs.next()) list.add(rs.getString(colName));
            return list;
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * An Overload method
     * Get list of column value from a result set bases on column index
     * @param rs
     * @param colInex
     * @return
     * @throws SQLException
     */
    public static List<String> getListCellValue (ResultSet rs, int colInex)
    {
        try {
            List<String> list = new ArrayList<>();
            rs.beforeFirst();
            while (rs.next()) list.add(rs.getString(colInex));
            return list;
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * An Overload method
     * Get list of column value from a result set bases on column  and row index
     * @param rs
     * @param rowIndex
     * @param colIndex
     * @return
     * @throws SQLException
     */

    public static String getSingleCellValue(ResultSet rs, int rowIndex, int colIndex)
    {
        try {
            rs.absolute(rowIndex);
            return rs.getString(colIndex);
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }

    }

    /**
     * An Overload method
     * Get list of column value from a result set bases on column  and row index
     * @param rs
     * @param rowIndex
     * @param colName
     * @return
     * @throws SQLException
     */

    public static String getSingleCellValue(ResultSet rs, int rowIndex, String colName)
    {
        try
        {
        rs.absolute(rowIndex);
        return rs.getString(colName);
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Return total of rows from a provided result set
     * @param rs
     * @return
     * @throws SQLException
     */
    public static int getRowCount (ResultSet rs)
    {
        try {
            rs.last();
            return rs.getRow();
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }

    }

    /**
     * Get List of cell value by row index
     * @param rs
     * @param rowIndex
     * @return
     * @throws SQLException
     */
    public List<String> getSingleRowValue (ResultSet rs, int rowIndex)
    {
        try
        {
            List<String> list = new ArrayList<>();
            rs.absolute(rowIndex);
            ResultSetMetaData md = rs.getMetaData();
            int colCnt = md.getColumnCount();

            for (int i=1; i< colCnt; i ++)
            {
                list.add(md.getColumnName(i) + ":" + rs.getString(i));
            }
            return  list;
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Get List of cell values base on range of row index
     * @param rs
     * @param fromRowIndex
     * @param toRowIndex
     * @return
     * @throws SQLException
     */
    public static List getListRowValue (ResultSet rs, int fromRowIndex, int toRowIndex)
    {
        try
        {
            ResultSetMetaData md =rs.getMetaData();
            int colCnt = md.getColumnCount();
            ArrayList list = new ArrayList();

            for (int i = fromRowIndex; i<=toRowIndex;i++)
            {
                rs.absolute(i);
                HashMap row = new HashMap(colCnt);

                for (int j=1; j<=colCnt; ++j)
                {
                    row.put(md.getColumnName(j), rs.getObject(j));
                }
                list.add(row);
            }
            return list;
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     *
     * Convert ResultSet to ArrayList
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List resultSetToArrayList (ResultSet rs)
    {
        try
        {
            ResultSetMetaData md =rs.getMetaData();
            int colCnt = md.getColumnCount();
            ArrayList list = new ArrayList();
            rs.beforeFirst();

            while (rs.next())
            {
                HashMap row = new HashMap(colCnt);
                for (int i =1; i<=colCnt; i++)
                {
                    row.put(md.getColumnName(i),rs.getObject(i));
                }
                list.add(row);
            }
            return list;
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }

    }

    /**
     * Check whether result set is empty
     * @param rs
     * @return true/ false
     * @throws SQLException
     */
    public static boolean isEmpty (ResultSet rs)
    {
        try {
            return !rs.first();
        }
        catch (SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }

    }

}

