import org.junit.Before;


import java.awt.Color;

import cs3500.shapes.Coordinate;

import cs3500.shapes.Oval;
import cs3500.shapes.ShapeType;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the oval class through the rectangle class.
 */
public class OvalTest extends RectangleTest {

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
    r = new Oval(c, color, name, startTime, endTime, width, height);
  }

  // Tests the getCoordinate method.
  @Override
  public void testGetCoordinate() {
    assertEquals("(1,5)", r.getCoordinate().toString());
  }

  // Tests the getType method.
  @Override
  public void testGetType() {
    assertEquals(ShapeType.OVAL, r.getType());
  }

  // Tests the addDimensions method.
  @Override
  public void testAddDimensions() {
    r.addDimensions(1, -1);
    assertEquals("Name: shapee\n" +
            "Type: OVAL\n" +
            "Position: (1,5), X radius: 4, Y radius: 5, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=10.0s", r.description(2));
  }

  // Tests the description method.
  @Override
  public void testDescription() {
    assertEquals("Name: shapee\n" +
            "Type: OVAL\n" +
            "Position: (1,5), X radius: 3, Y radius: 6, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=10.0s", r.description(2));
  }

  // Tests the Constructor.
  @Override
  public void testCons() {
    assertEquals("Name: shapee\n" +
            "Type: OVAL\n" +
            "Position: (1,5), X radius: 3, Y radius: 6, Color: (255,0,0)\n" +
            "Appears at: t=0s\n" +
            "Disappears at: t=20s", r.description(1));
  }
}
