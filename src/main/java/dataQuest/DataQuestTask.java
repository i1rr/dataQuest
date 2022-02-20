package dataQuest;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class DataQuestTask {
   private final Properties cfg = new Properties();
   private WebDriver driver;

    private void inputData(String xPathFromProps, String data) {
        WebElement webElement = driver.findElement(By.xpath(cfg.getProperty(xPathFromProps)));
        webElement.click();
        if (data != null) {
            webElement.sendKeys(cfg.getProperty(data));
        }
    }

    private void linkValidate(String link) throws NotSuitableLinkException {
        if(!link.contains("apply.dataquest.com.au")) {
            throw new NotSuitableLinkException("This link in not suitable: " + link
                    + System.lineSeparator() + "Please try again or check with Brett.");
        }
    }

    public void solveTaskFromBrett(String link) throws NotSuitableLinkException {
        linkValidate(link);

        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        DataQuestTask.class.getClassLoader()
                                .getResourceAsStream("dataQuestTask.properties"))))) {
            cfg.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver = new SafariDriver();

        driver.get(link);

        WebElement webElement = driver.findElement(By.xpath(cfg.getProperty("asterisksLocXpath")));
        String textToParse = webElement.getAttribute("innerHTML");

        String countResult = Integer.toString(
                StringUtils.countMatches(textToParse, "<span style=\"color: blue\">*</span>"));

        inputData("nameXpath", "name");
        inputData("emailXpath", "email");
        inputData("countXpath", countResult);
        inputData("submitXpath", null);

        driver.quit();
    }

    public static void main(String[] args) throws NotSuitableLinkException {
        String link = "http://apply.dataquest.com.au";
        DataQuestTask dqt = new DataQuestTask();
        dqt.solveTaskFromBrett(link);
    }
}
