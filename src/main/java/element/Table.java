package element;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author hongle
 * Reference from WebComponent example
 */

public class Table extends WebComponent {

        @FindBy(tagName = "tr")
        List<Row> rows;

        public Row getRow(int row) {
            return rows.get(row - 1);
        }

        public int getTableSize() {
            return rows.size();
        }

        public static class Row extends WebComponent {
            @FindBy(tagName = "td")
            List<WebElement> columns;

            public WebElement getCell(int column) {
                return columns.get(column - 1);
            }
        }
    }
