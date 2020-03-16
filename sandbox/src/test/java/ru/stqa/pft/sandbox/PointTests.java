package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class PointTests {

  @Test

  public void testDistance() {
    Point p = new Point();
    Assert.assertEquals(p.distance(2,4,5,8), 5.0);
    Assert.assertEquals(p.distance(3,5,6,9), 5.0);
    Assert.assertEquals(p.distance(3,3,3,9), 6.0);
  }
}
