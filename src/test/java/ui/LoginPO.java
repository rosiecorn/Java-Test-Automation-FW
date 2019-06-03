package ui;

import base.BaseBrowserPO;
import element.Element;
import element.Table;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BaseBrowserPO {

    public LoginPO()
    {
        super();
    }

    //Variables
    public static final String PHPURL = "https://www.phptravels.net/login";
    public static final String NEWTOURURL = "http://newtours.demoaut.com/";


    //Web Objects

    @FindBy(name = "username")
    public Element username;

    @FindBy(name="password")
    public   Element password;

    @FindBy(name="email")
    public Element email;

    @FindBy(xpath="//*[@id='loginfrm']/button")
    public Element btnLogin;

    @FindBy(xpath="(//table)[4]")
    public Table table;



}
