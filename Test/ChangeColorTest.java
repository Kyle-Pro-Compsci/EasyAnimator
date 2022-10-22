import org.junit.Before;


import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.animations.AnimationName;
import cs3500.animator.animations.ChangeColor;
import cs3500.animator.animations.IAnimation;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ChangeColor class through the MoveTest class and some unique methods.
 */
public class ChangeColorTest extends MoveTest {

  Color color;
  Color color1;

  /**
   * Resets data for testing.
   */
  @Before
  public void reset() {
    color = new Color(1, 2, 3);
    color1 = new Color(1, 3, 5);
    c = new Coordinate(1, 5);
    c1 = new Coordinate(3, 6);
    s = new Rectangle(c, Color.BLUE, "name", 1, 5, 2, 3);
    s1 = new Rectangle(c, Color.BLUE, "shape", 2, 7, 2, 3);
    a = new ChangeColor("name", 2, 4,Color.BLUE, color);
    a1 = new ChangeColor("name", 4, 5,Color.BLUE, color1);
    a2 = new ChangeColor("shape", 2, 4,Color.BLUE, color);

    m = new SimpleAnimationModel();
    m.startAnimation(new ArrayList<IShape>(), new ArrayList<IAnimation>());
    m.addShape(s);
    m.addAnimation(a);
  }

  // Tests the animate method.
  @Override
  public void testAnimate() {
    a.animate(s, 3);
    assertEquals(new Color(0, 1, 129), s.getColor());
  }

  // Tests the description method
  @Override
  public void testDescription() {
    m.performAnimations();
    m.performAnimations();
    m.performAnimations();
    assertEquals("Shape name changes color from (0,0,255) to (1,2,3) from t=1.0s to t=2.0s ",
            a.description(2));
  }

  // Tests the getType method.
  @Override
  public void testGetType() {
    assertEquals(AnimationName.CHANGECOLOR, a.getType());
  }
}
