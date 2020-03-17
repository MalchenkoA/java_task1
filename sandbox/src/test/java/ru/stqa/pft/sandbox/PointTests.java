package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class PointTests {

  @Test

  public void testDistance1() {
    Point p1 = new Point(3,5);
    Point p2 = new Point(6,9);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
  public void testDistance2() {
    Point p1 = new Point(2,4);
    Point p2 = new Point(5,8);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
  public void testDistance3() {
    Point p1 = new Point(3,3);
    Point p2 = new Point(3,9);
    Assert.assertEquals(p1.distance(p2), 6.0);
  }
}
