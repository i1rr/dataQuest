package parser;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class DataQuestTask {

    public static void solveTaskFromBrett(String link) {
            WebDriver driver = new SafariDriver();
            driver.get(link);
            WebElement webElement =
                    driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[3]/table/tbody"));

        String innerHTML = webElement.getAttribute("innerHTML");

        int result = StringUtils.countMatches(innerHTML, "<span style=\"color: blue\">*</span>");

        WebElement name = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input"));
        name.click();
        name.sendKeys("Ivan Resemkin");

        WebElement email = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input"));
        email.click();
        email.sendKeys("rivan1986@gmail.com");

        WebElement count = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input"));
        count.click();
        count.sendKeys(Integer.toString(result));

        WebElement submit = driver.findElement(By.xpath("/html/body/form/p/input"));
        submit.click();
            driver.quit();
    }

    public static void main(String[] args) {
        DataQuestTask.solveTaskFromBrett("http://apply.dataquest.com.au");
    }
}
