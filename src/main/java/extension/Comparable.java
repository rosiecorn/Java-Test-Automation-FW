package extension;

import exception.CustomException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


/**
 * @author hongle
 * This class provide methods to make comparision between two Java Object Class both collection and non collection
 * Leverage Java Generics
 */
public class Comparable{

    private Comparable(){}

    public static void deepEquals(Object actual, Object expected)  {

        if (actual instanceof Collection<?>) {

            collectionComppare(actual,expected);

        } else {

            noneColectionCompare(actual,expected);
        }

    }

    private static void noneColectionCompare(Object actual, Object expected)
    {
        assertEquals("Different object name",expected.getClass().getName(), actual.getClass().getName());

        try {
            Field[] actualFields = actual.getClass().getDeclaredFields();
            Field[] expectedFields = expected.getClass().getDeclaredFields();

            if (actualFields.length == expectedFields.length) {
                for (Field field : actualFields) {
                    field.setAccessible(true);

                    assertEquals("Field " + field.getName() + " has value does not match at position ", field.get(expected), field.get(actual));
                }
            } else

                assertEquals("Number of data fields does not match", Integer.toString(expectedFields.length), Integer.toString(actualFields.length));
        }
        catch (IllegalAccessException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    private static void collectionComppare(Object actual, Object expected)
    {
        List actualList = (List) actual;
        List expectedList = (List) expected;

        assertEquals("Size of rows does not match",Integer.toString(expectedList.size()),Integer.toString(actualList.size()));

        try {
            if (actualList.size() == expectedList.size()) {

                for (int i = 0; i < actualList.size(); i++) {

                    Field[] fieldsActual = actualList.get(i).getClass().getDeclaredFields();

                    assertEquals("Different object name", (expectedList.get(i)).getClass().getName(), actualList.get(i).getClass().getName());

                    assertEquals("Number of data fields does not match", (expectedList.get(i)).getClass().getDeclaredFields().length, actualList.get(i).getClass().getDeclaredFields().length);

                    for (Field field : fieldsActual) {

                        if (Collection.class.isAssignableFrom(field.getType())) {
                            deepEquals(field, expected.getClass().getField(field.getName()));
                        }

                        field.setAccessible(true);

                        assertEquals("Field " + field.getName() + " has value does not match at position " + i, field.get(actualList.get(i)).toString(), field.get(expectedList.get(i)).toString());
                    }
                }

            }
        }
        catch (Exception ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

}



