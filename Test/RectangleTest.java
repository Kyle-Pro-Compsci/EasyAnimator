import org.junit.Before;
import org.junit.Test;

import java.awt.Color;


import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;
import cs3500.shapes.ShapeType;

import static junit.framework.TestCase.assertEquals;


/**
 * Tests the Rectangle and Shape classes.
 */
public class RectangleTest {
  Coordinate c;
  Color color;
  String name;
  int startTime;
  int endTime;
  int width;
  int height;
  IShape r;

  /**
   * Resets data for testing.
   */
  @Before
  public void reset() {
    c = new Coordinate(1, 5);
    color = Color.RED;
    name = "shapee";
    startTime = 0;
    endTime = 20;
    width = 3;
    height = 6;
    r = new Rectangle(c, color, name, startTime, endTime, width, height);
  }

  // Tests the description method.
  @Test
  public void testDescription() {
    assertEquals("Name: shapee\n" +
            "Type: RECTANGLE\n" +
            "Position: (1,5), X radius: 3, Y radius: 6, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=10.0s", r.description(2));
  }

  // Tests the addDimensions method.
  @Test
  public void testAddDimensions() {
    r.addDimensions(1, -1);
    assertEquals("Name: shapee\n" +
            "Type: RECTANGLE\n" +
            "Position: (1,5), X radius: 4, Y radius: 5, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=10.0s", r.description(2));
  }

  // Tests the getCoordinate method.
  @Test
  public void testGetCoordinate() {
    assertEquals("(1,5)", r.getCoordinate().toString());
  }

  // Tests the getColor method.
  @Test
  public void testGetColor() {
    assertEquals(Color.RED, r.getColor());
  }

  // Tests the getName method.
  @Test
  public void testGetName() {
    assertEquals("shapee", r.getName());
  }

  // Tests the getStartTime method.
  @Test
  public void testGetStartTime() {
    assertEquals(0, r.getStartTime());
  }

  // Tests the getEndTime method.
  @Test
  public void testGetEndTime() {
    assertEquals(20, r.getEndTime());
  }

  // Tests the getWidth method.
  @Test
  public void testGetWidth() {
    assertEquals(3, r.getWidth());
  }

  // Tests an illegal rectangle.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal() {
    r = new Rectangle(c, color, name, 3, 1,
            width, height);
  }

  // Tests an illegal rectangle.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal1() {
    r = new Rectangle(c,
            color, name, startTime, endTime, -1, height);
  }

  // Tests an illegal rectangle.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal2() {
    r = new Rectangle(c,
            color, name, startTime, endTime, width, -1);
  }

  // Tests the setColor method.
  @Test
  public void testSetColor() {
    r.setColor(new Color(1, 3, 4));
    assertEquals(new Color(1, 3, 4), r.getColor());
  }

  //Tests the getHeight method.
  @Test
  public void testGetHeight() {
    assertEquals(6, r.getHeight());
  }

  // Tests the getType method.
  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, r.getType());
  }

  // Tests the copy method.
  @Test
  public void testCopy1() {
    assertEquals(r.copy().getCoordinate().toString(), r.getCoordinate().toString());
  }

  // Tests the copy method.
  @Test
  public void testCopy2() {
    assertEquals(r.copy().getColor().toString(), r.getColor().toString());
  }

  // Tests the copy method.
  @Test
  public void testCopy3() {
    assertEquals(r.copy().getName(), r.getName());
  }

  // Tests the copy method.
  @Test
  public void testCopy4() {
    assertEquals(r.copy().getWidth(), r.getWidth());
  }

  // Tests the copy method.
  @Test
  public void testCopy5() {
    assertEquals(r.copy().getHeight(), r.getHeight());
  }

  // Tests the copy method.
  @Test
  public void testCopy6() {
    assertEquals(r.copy().getStartTime(), r.getStartTime());
  }

  // Tests the copy method.
  @Test
  public void testCopy7() {
    assertEquals(r.copy().getEndTime(), r.getEndTime());
  }

  // Tests a tickrate of 1 for description method.
  @Test
  public void testCons() {
    assertEquals("Name: shapee\n" +
            "Type: RECTANGLE\n" +
            "Position: (1,5), X radius: 3, Y radius: 6, Color: (255,0,0)\n" +
            "Appears at: t=0s\n" +
            "Disappears at: t=20s", r.description(1));
  }
}
