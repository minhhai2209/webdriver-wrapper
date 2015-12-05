package minhhai2209.webdriverwrapper.helper;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class LocationCalculator {

  public static Location getCenterOffset(WebElement element) {
    Dimension size = element.getSize();
    int xOffset = size.getWidth() / 2;
    int yOffset = size.getHeight() / 2;
    Location location = new Location(xOffset, yOffset);
    return location;
  }

  public static Location getCenterLocation(WebElement element) {
    Point topLeft = element.getLocation();
    int xTopLeft = topLeft.getX();
    int yTopLeft = topLeft.getY();
    Dimension size = element.getSize();
    int xCenterOffset = size.getWidth() / 2;
    int yCenterOffset = size.getHeight() / 2;
    int x = xTopLeft + xCenterOffset;
    int y = yTopLeft + yCenterOffset;
    Location location = new Location(x, y);
    return location;
  }
}
