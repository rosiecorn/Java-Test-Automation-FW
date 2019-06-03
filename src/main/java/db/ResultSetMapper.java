package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import exception.CustomException;


/**
 * @author hongle
 * This class help user convert from resultset to Java List Object
 * Great support for api testing
 */

public class ResultSetMapper<T> {

    /**
     * Set value to field in Class object - Using Java Generics
     * @param clazz
     * @param fieldName
     * @param columnValue
     */
    private void setProperty(Object clazz, String fieldName, Object columnValue) {
        try {
            Field field = clazz.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(clazz, columnValue);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
           throw new CustomException(ex.getMessage());
        }
    }

    /**
     * This method using to convert from result set to your desired java object class
     * @param rs
     * @param clazz
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> mapRersultSetToObject(ResultSet rs, Class clazz)  {

        try {
            rs.beforeFirst();

                // check if Class clazz has the 'Entity' annotation
                if (clazz.isAnnotationPresent(Entity.class)) {

                    // get all the attributes of Class clazz
                    Field[] fields = clazz.getDeclaredFields();

                    return resultSetMapper(fields, rs, clazz);

                } else {
                    throw new CustomException(clazz.getName() + "Class  oes not have @Entity annotation");
                }

           }
        catch (SQLException ex)
        {
               throw new CustomException(ex.getMessage());
        }


    }

    /**
     * This method provide detail step when fetch cursor in resultset and map to java object class
     * @param fields
     * @param rs
     * @param clazz
     * @return
     */
    private List<T> resultSetMapper (Field[] fields, ResultSet rs, Class clazz)
    {
        List<T> outputList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                T bean = (T) clazz.newInstance();
                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {

                    String columnName = rsmd.getColumnName(_iterator + 1);

                    Object columnValue = rs.getObject(_iterator + 1);

                    entityFieldMapper(fields,columnName,columnValue,bean);
                }
                outputList.add(bean);

            }
            return outputList;
        }
        catch(InstantiationException | IllegalAccessException | SQLException ex)
            {
                throw new CustomException(ex.getMessage());
            }

        }

    /**
     * This method provide detail step from determine valid fields bases on annotation @Column and then fill in value for those field
     * @param fields
     * @param columnName
     * @param columnValue
     * @param bean
     */
    private void entityFieldMapper (Field[] fields, String columnName ,Object columnValue , T bean)
        {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column.name().equalsIgnoreCase(columnName)
                            && columnValue != null) {
                        this.setProperty(bean, field.getName(), columnValue);
                        break;
                    }
                }
            }
        }

}
