import org.junit.Before;
import org.junit.Test;

import cs3500.shapes.Coordinate;


import static org.junit.Assert.assertEquals;


/**
 * Tests the coordinate class.
 */
public class CoordinateTest {

  Coordinate c;

  /**
   * Resets data for testing.
   */
  @Before
  public void reset() {
    this.c = new Coordinate(12, 1);

  }

  // Tests making a Coordinate and the toString method.
  @Test
  public void testMake() {
    assertEquals("(12,1)", this.c.toString());
  }

  // Tests making another Coordinate.
  @Test
  public void testMake1() {
    this.c = new Coordinate(1, 2);
    assertEquals("(1,2)", this.c.toString());
  }

  // Tests the getX method.
  @Test
  public void testGetX() {
    assertEquals(12.0, this.c.getX(), .01);
  }

  // Tests the getY method.
  @Test
  public void testGetY() {
    assertEquals(1.0, this.c.getY(), .01);
  }

  // Tests the toString method.
  @Test
  public void testToString() {
    assertEquals("(12,1)", c.toString());
  }
}
