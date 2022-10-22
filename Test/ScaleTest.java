import org.junit.Before;
import org.junit.Test;


import java.awt.Color;

import java.util.ArrayList;

import cs3500.animator.animations.AnimationName;
import cs3500.animator.animations.IAnimation;
import cs3500.animator.animations.Scale;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods in the Scale class through the TestMove class and some unique methods.
 */
public class ScaleTest extends MoveTest {

  /**
   * Resets data for testing.
   */
  @Before
  public void reset() {
    c = new Coordinate(1, 5);
    c1 = new Coordinate(3, 6);
    s = new Rectangle(c, Color.BLUE, "name", 1, 5, 2, 3);
    s1 = new Rectangle(c, Color.BLUE, "shape", 2, 7, 2, 3);
    a = new Scale("name", 2, 4, 2, 3, 4, 6);
    a1 = new Scale("name", 4, 5,2, 3,  4, 6);
    a2 = new Scale("shape", 2, 4,2, 3, 4, 6);

    m = new SimpleAnimationModel();
    m.startAnimation(new ArrayList<IShape>(), new ArrayList<IAnimation>());
    m.addShape(s);
    m.addAnimation(a);
  }

  // Tests the animate method.
  @Override
  public void testAnimate() {
    a.animate(s, 3);
    assertEquals(3.0, s.getWidth(), .01);
  }

  // Tests the animate method again.
  @Test
  public void testAnimate1() {
    a.animate(s, 3);
    assertEquals(4.0, s.getHeight(), .01);
  }

  // Tests the description method.
  @Override
  public void testDescription() {
    m.performAnimations();
    m.performAnimations();
    m.performAnimations();
    assertEquals("Shape name scales from Width: 2, Height: 3 to Width: 4, " +
            "Height: 6 from t=1.0s to t=2.0s", a.description(2));
  }

  // Tests the getType method.
  @Override
  public void testGetType() {
    assertEquals(AnimationName.SCALE, a.getType());
  }


}
