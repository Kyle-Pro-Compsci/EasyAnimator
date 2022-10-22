import org.junit.Before;
import org.junit.Test;


import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.animations.AnimationName;
import cs3500.animator.animations.IAnimation;
import cs3500.animator.animations.Move;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;


/**
 * Tests the Move class and the Animation Abstract class.
 */

public class MoveTest {

  protected Coordinate c;
  protected Coordinate c1;
  protected IShape s;
  protected IShape s1;
  protected IAnimation a;
  protected IAnimation a1;
  protected IAnimation a2;
  protected IAnimationModel m;


  /**
   * Resets data for testing.
   */
  @Before
  public void reset() {
    c = new Coordinate(1, 5);
    c1 = new Coordinate(3, 6);
    s = new Rectangle(c, Color.BLUE, "name", 1, 5, 2, 3);
    s1 = new Rectangle(c, Color.BLUE, "shape", 2, 7, 2, 3);
    a = new Move("name", 2, 4,c, c1);
    a1 = new Move("name", 4, 5,c, c1);
    a2 = new Move("shape", 2, 4,c, c1);

    m = new SimpleAnimationModel();
    m.startAnimation(new ArrayList<IShape>(), new ArrayList<IAnimation>());
    m.addShape(s);
    m.addAnimation(a);
  }

  // Tests the animate method.
  @Test
  public void testAnimate() {
    a.animate(s, 3);
    assertEquals("(2,5)", s.getCoordinate().toString());
  }

  // Tests the getType method.
  @Test
  public void testGetType() {
    assertEquals(AnimationName.MOVE, a.getType());
  }

  // Tests the toString method.
  @Test
  public void testDescription() {
    // Does 3 ticks of the model to get to where the animation starts.
    m.performAnimations();
    m.performAnimations();
    m.performAnimations();
    assertEquals("Shape name moves from (1,5) to (3,6) from t=1.0s to t=2.0s",
            a.description(2));
  }


  // Tests the getStartTime method.
  @Test
  public void testGetStartTime() {
    assertEquals(2, a.getStartTime());
  }

  // Tests the getEndTime method.
  @Test
  public void testGetEndTime() {
    assertEquals(4, a.getEndTime());
  }

  // Tests making an illegal Animation.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal() {
    a = new Move("name", 5, 1,c, c1);
  }

  // Tests making an illegal Animation.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal2() {
    a = new Move("name", 3, 2,c, c1);
  }

  // Tests the haveSameShape method.
  @Test
  public void testHaveSameShape() {

    assertEquals(true, a.haveSameShape(a1));
  }

  // Tests the haveSameShape method.
  @Test
  public void testHaveSameShape1() {
    assertEquals(false, a.haveSameShape(a2));
  }

  // Tests the overlapTimes method.
  @Test
  public void testOverlapTimes() {
    assertEquals(true, a.overlapTimes(a2));
  }

  // Tests the overlapTimes method.
  @Test
  public void testOverlapTimes1() {
    assertEquals(false, a.overlapTimes(a1));
  }

}
