package WordPress;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class WordPressActuator {
    private WebDriver driver;
    private WebDriverWait wait;

    public void initSession(String webDriver, String path){
        webDriver = "webdriver.chrome.driver";
        path = "C:\\Users\\shays\\Downloads\\sqe-hw3-main\\sqe-hw3-main\\Selenium\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/wordpress/login");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }
    public void goToMainPage(){
        // Transfer to login page (no direct link)
        driver.navigate().to("http://localhost/wordpress/");
    }

    public void goToLogin(){
        // Transfer to login page (no direct link)
        driver.get("http://localhost/wordpress/wp-login.php");
    }
    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user_login']"))).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='user_pass']")).sendKeys(password);
    }

    public void submitLoginInfo()
    {
        // Admin submit his details and log in
        driver.findElement(By.id("wp-submit")).click();
    }

    public WebElement adminWelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Welcome to WordPress!')]")));
        return msg;
    }

    public void openDashboardTab(){
        // open dashboard tab for all kind of users
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*[//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpadminbar']/div[@id='wp-toolbar']/ul[@id='wp-admin-bar-root-default']/li[@id='wp-admin-bar-menu-toggle']/a[1]/span[1]]"))).click();
    }


    public void openSettingsTab(){
        // open dashboard tab for all kind of users
        driver.findElement(By.xpath("//div[contains(text(),'Settings')]")).click();
    }

    public void goToDiscussionSettings(){
        // go to discussion settings
        driver.findElement(By.xpath("//a[contains(text(),'Discussion')]")).click();
    }

    public WebElement adminIsInDiscussionSettings()
    {
        // admin in "discussion settings"
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Discussion Settings')]")));
    }

    public WebElement adminIsOnDashboard()
    {
        // admin in "discussion settings"
        return driver.findElement(By.xpath("//div[contains(text(),'Dashboard')]"));
    }

    public String isChangesSaved()
    {
        // check if the word is in disallowed keywords
        String s = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//strong[contains(text(),'Settings saved.')]")))).getText();
        System.out.println(s);
        return s;
    }

    public void adminDisallowedWord(String word)
    {
        // admin add word to disallowed words
//          wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("//textarea[@id='disallowed_keys']"))));
//        System.out.println(textbox);
//        textbox.sendKeys(word + "\n");
        driver.findElement(By.xpath("//textarea[@id='disallowed_keys']")).sendKeys(Keys.HOME + word + "\n");

    }

    public void adminSubmitChanges()
    {
        // admin save changes
        driver.findElement(By.id("submit")).click();
    }

    public void guestClickOnPost(String title)
    {
        // guest click on post
        driver.findElement(By.xpath("//*[contains(text(),'" + title + "')]")).click();
    }

    public WebElement guestIsOnPost(String title){
        return driver.findElement(By.xpath("//h1[contains(text(),'" + title + "')]"));
    }

    public void guestFillComment(String comment){
        // guest type comment
        driver.findElement(By.xpath("//*[@id='comment']")).sendKeys(comment);
    }
    public void guestPostComment(){
        // guest submit its comment
        WebElement click = driver.findElement(By.xpath("//input[@id='submit']"));
        click.click();
    }

    public void openTrash(){
        // admin open dashboard
        openDashboardTab();
        // admin click on trash
        driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[3]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/ul[2]/li[6]/a[1]\n")).click();
    }

    public WebElement searchCommentOnTrash(String comment)
    {
        // check if comment on trash
        WebElement comment_disallowed = driver.findElement(By.xpath("//*[contains(text(),'" + comment + "')]"));
        return comment_disallowed;
    }

    public WebElement searchCommentOnPost(String comment)
    {
        WebElement commentNull = null;
        try
        {
            commentNull = driver.findElement(By.xpath("//*[contains(text(),'" + comment + "')]"));
        }
        catch (Exception ignored)
        {
        }
        return commentNull;
    }


    public void createPost(){

        // open dashboard tab
        openDashboardTab();
        // open post tab in dashboard
        driver.findElement(By.xpath("//div[contains(text(),'Post')]")).click();
        // create new empty post
        driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-posts']/ul[1]/li[3]/a[1]")).click();
    }

    public void fillTitle(String title)
    {
        // fill title field
        WebElement title_field = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[aria-label='Add title']"))));
        title_field.sendKeys(title+"\n");
    }
    public void fillContent(String content){
        // fill post with content
        WebElement content_field = driver.findElement(By.cssSelector("[aria-label='Empty block; start writing or type forward slash to choose a block']"));
        content_field.sendKeys(content + "\n");
    }

    public void publishPost()
    {
        // click on "publish"
        driver.findElement(By.xpath("//button[contains(text(),'Publish')]")).click();
        // confirm "publish"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]"))).click();
    }

    public WebElement isPostCreated(String title){
        // check if post created
        return driver.findElement(By.xpath("//*[contains(text(),'" + title + "')]"));

    }

    public void watchPost()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View Post')]"))).click();
    }


    public void continueAsGuest()
    {
        // continue as guest from login page
        driver.findElement(By.xpath("//body/div[@id='login']/p[@id='backtoblog']/a[1]")).click();

    }


}