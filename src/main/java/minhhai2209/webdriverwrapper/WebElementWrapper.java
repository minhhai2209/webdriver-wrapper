package minhhai2209.webdriverwrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import minhhai2209.webdriverwrapper.helper.Location;
import minhhai2209.webdriverwrapper.helper.LocationCalculator;

public class WebElementWrapper implements WebElement {

  private WebDriver webDriver;

  private WebElement webElement;

  public WebElementWrapper(WebDriver webDriver, WebElement webElement) {
    this.webDriver = webDriver;
    this.webElement = webElement;
  }

  public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
    return webElement.getScreenshotAs(arg0);
  }

  public void clear() {
    webElement.clear();
  }

  public void click() {
    Location location = LocationCalculator.getCenterOffset(this);
    int xOffset = location.getX();
    int yOffset = location.getY();
    new Actions(webDriver)
        .moveToElement(this, xOffset, yOffset)
        .click()
        .build()
        .perform();
  }

  public WebElement findElement(By arg0) {
    return webElement.findElement(arg0);
  }

  public List<WebElement> findElements(By arg0) {
    return webElement.findElements(arg0);
  }

  public String getAttribute(String arg0) {
    return webElement.getAttribute(arg0);
  }

  public String getCssValue(String arg0) {
    return webElement.getCssValue(arg0);
  }

  public Point getLocation() {
    return webElement.getLocation();
  }

  public Dimension getSize() {
    return webElement.getSize();
  }

  public String getTagName() {
    return webElement.getTagName();
  }

  public String getText() {
    return webElement.getText();
  }

  public boolean isDisplayed() {
    return webElement.isDisplayed();
  }

  public boolean isEnabled() {
    return webElement.isEnabled();
  }

  public boolean isSelected() {
    return webElement.isSelected();
  }

  public void sendKeys(CharSequence... arg0) {
    webElement.sendKeys(arg0);
  }

  public void submit() {
    webElement.submit();
  }

}
