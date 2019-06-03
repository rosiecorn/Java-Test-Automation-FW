package element;

import com.github.webdriverextensions.WebComponent;
import driver.CreateDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Element extends WebComponent {

    public Element ()
    {
        super();
    }

    public void hoverAndClick()  {
        Actions action = new Actions(CreateDriver
                .getInstance().getCurrentDriver());
        action.moveToElement(this.getWrappedWebElement()).click(this.getWrappedWebElement()).build().perform();
    }

    public void dragAndDropBy(int xOffset, int yOffset)
    {
        Action dragAndDropBy = new Actions(CreateDriver.getInstance().getDriver()).dragAndDropBy(this.getWrappedWebElement(),xOffset ,yOffset).build();
        dragAndDropBy.perform();
    }

    public void dragAndDrop(WebElement target)
    {
        Action dragAndDrop = new Actions(CreateDriver.getInstance().getCurrentDriver()).dragAndDrop(this.getWrappedWebElement(),target).build();
        dragAndDrop.perform();
    }

    public  boolean isPresent(){

        return this.getWrappedWebElement() != null;

    }

    public  void moveToElement()
    {
        ((JavascriptExecutor)CreateDriver.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView();", this.getWrappedWebElement());
    }

    public  void setInnerHTML(String value)
    {
        JavascriptExecutor js = (JavascriptExecutor) CreateDriver.getInstance().getDriver();
        js.executeScript("arguments[0].innerHTML = '"+value+"'", this.isPresent());
    }

    public  void clearAndSetText (String text)
    {
        if(this.getWrappedWebElement().getAttribute("value").equals(""))
            this.getWrappedWebElement().sendKeys(text);
        else
            this.getWrappedWebElement().sendKeys(text);
            this.getWrappedWebElement().clear();

    }

    public void mouseClick () {
        Actions action = new Actions(CreateDriver.getInstance().getDriver());
        action.moveToElement(this.getWrappedWebElement()).click().build().perform();
    }

    public void javascriptClick () {
        JavascriptExecutor js = (JavascriptExecutor)CreateDriver.getInstance().getDriver();
        js.executeScript("arguments[0].click();", this.getWrappedWebElement());
    }


}
