package minhhai2209.webdriverwrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import minhhai2209.webdriverwrapper.helper.ArrayHelper;
import minhhai2209.webdriverwrapper.helper.Function;
import minhhai2209.webdriverwrapper.helper.RetryTemplate;

public class WebDriverWrapper implements WebDriver {

  private static final int NUMBER_OF_SCREENSHOTS = 3;

  private static final long WAIT_TIME_OUT = 3600;

  protected static final String JAVASCRIPT_SNIPPET = "console.log('Waiting');";

  private WebDriver webDriver;

  public WebDriverWrapper(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void close() {
    webDriver.close();
  }

  public WebElement findElement(By by) {
    WebElement webElement = doFindElement(by);
    return wrapWebElement(webElement);
  }

  public List<WebElement> findElements(By by) {
    List<WebElement> webElements = doFindElements(by);
    List<WebElement> webElementWrappers = new ArrayList<WebElement>();
    for (WebElement webElement : webElements) {
      webElementWrappers.add(wrapWebElement(webElement));
    }
    return webElementWrappers;
  }

  public void get(String arg0) {
    webDriver.get(arg0);
  }

  public String getCurrentUrl() {
    return webDriver.getCurrentUrl();
  }

  public String getPageSource() {
    return webDriver.getPageSource();
  }

  public String getTitle() {
    return webDriver.getTitle();
  }

  public String getWindowHandle() {
    return webDriver.getWindowHandle();
  }

  public Set<String> getWindowHandles() {
    return webDriver.getWindowHandles();
  }

  public Options manage() {
    return webDriver.manage();
  }

  public Navigation navigate() {
    return webDriver.navigate();
  }

  public void quit() {
    webDriver.quit();
  }

  public TargetLocator switchTo() {
    return webDriver.switchTo();
  }

  private WebElementWrapper wrapWebElement(WebElement webElement) {
    return new WebElementWrapper(this, webElement);
  }

  private void waitUntilPageStable() {
    try {
      byte[][] screenshots = new byte[NUMBER_OF_SCREENSHOTS][];
      int i = 0;
      while (true) {
        waitUntilPageNotBlocked();
        screenshots[i] = takeScreenshot();
        if (ArrayHelper.theSame(screenshots)) {
          break;
        } else {
          i = ArrayHelper.nextIndex(i, NUMBER_OF_SCREENSHOTS);
        }
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private void waitUntilPageNotBlocked() {
    WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_OUT);
    wait.until(new Predicate<WebDriver>() {

      public boolean apply(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(JAVASCRIPT_SNIPPET);
        return true;
      }
    });
    return;
  }

  private byte[] takeScreenshot() {
    TakesScreenshot takesScreenshot = (TakesScreenshot) this;
    return takesScreenshot.getScreenshotAs(OutputType.BYTES);
  }

  private WebElement doFindElement(final By by) {
    waitUntilPageStable();
    return RetryTemplate.retry(new Function<WebElement>() {

      public WebElement apply() {
        return webDriver.findElement(by);
      }
    });
  }

  private List<WebElement> doFindElements(final By by) {
    waitUntilPageStable();
    return RetryTemplate.retry(new Function<List<WebElement>>() {

      public List<WebElement> apply() {
        return webDriver.findElements(by);
      }
    });
  }
}
