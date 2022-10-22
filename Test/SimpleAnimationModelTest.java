import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import java.util.ArrayList;

import cs3500.animator.animations.IAnimation;
import cs3500.animator.animations.Move;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.util.TweenModelBuilder;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;


import static org.junit.Assert.assertEquals;

/**
 * Tests the SimpleAnimationModel class.
 */
public class SimpleAnimationModelTest {

  protected Coordinate c;
  protected Coordinate c1;
  protected IShape s;
  protected IShape s1;
  protected IAnimation a;
  protected IAnimation a1;
  protected IAnimation a2;
  protected IAnimationModel m;
  protected ArrayList<IShape> shapes;
  protected ArrayList<IAnimation> anims;

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
    a2 = new Move("name", 2, 4,c, c1);

    shapes = new ArrayList<IShape>();
    anims = new ArrayList<IAnimation>();
    shapes.add(s);
    shapes.add(s1);
    anims.add(a);
    anims.add(a1);

    m = new SimpleAnimationModel();
    m.startAnimation(new ArrayList<IShape>(), new ArrayList<IAnimation>());
    m.addShape(s);
    m.addAnimation(a);
  }

  // Tests starting the animation with null.
  @Test(expected = IllegalArgumentException.class)
  public void testStart() {
    m.startAnimation(null, new ArrayList<IAnimation>());
  }

  // Tests starting the animation with null.
  @Test(expected = IllegalArgumentException.class)
  public void testStart1() {
    m.startAnimation(new ArrayList<IShape>(), null);
  }

  // Tests adding a shape.
  @Test(expected = IllegalArgumentException.class)
  public void testAdd() {
    m.startAnimation(shapes, anims);
    m.addShape(s);
  }


  // Tests adding a animation.
  @Test(expected = IllegalArgumentException.class)
  public void testAddA() {
    m.startAnimation(shapes, anims);
    m.addAnimation(a);
  }

  // Tests the isOver method.
  @Test
  public void testIsOver() {
    assertEquals(false, m.isOver());
  }

  // Tests the constructor for a SimpleAnimationModel.
  @Test
  public void testCon1() {
    SimpleAnimationModel s = new SimpleAnimationModel(shapes, anims);
    assertEquals(s.getShapes().size(), 2);
  }

  // Tests the constructor for a SimpleAnimationModel again.
  @Test
  public void testCon2() {
    SimpleAnimationModel s = new SimpleAnimationModel(shapes, anims);
    assertEquals(s.getAnimations().size(), 2);
  }

  // Tests the constructor for a SimpleAnimationModel again.
  @Test
  public void testCon3() {
    SimpleAnimationModel s = new SimpleAnimationModel(shapes, new ArrayList<>());
    assertEquals(s.getAnimations().size(), 0);
  }

  // Tests the constructor for a SimpleAnimationModel again.
  @Test
  public void testCon4() {
    SimpleAnimationModel s = new SimpleAnimationModel(new ArrayList<>(), anims);
    assertEquals(s.getShapes().size(), 0);
  }

  // Tests the getEndTime method in SimpleAnimationModel.
  @Test
  public void testGET() {
    assertEquals(m.getEndTime(), 5);
  }

  // Tests the tickTime method in SimpleAnimationModel.
  @Test
  public void testTT() {
    assertEquals(m.getTime(), 0);
    m.tickTime();
    assertEquals(m.getTime(), 1);
  }

  // Tests the setTime method in SimpleAnimationModel.
  @Test
  public void testST() {
    assertEquals(m.getTime(), 0);
    m.setTime(20);
    assertEquals(m.getTime(), 20);
  }

  // Tests the setShapes method in SimpleAnimationModel.
  @Test
  public void testSS() {
    ArrayList<IShape> temp = new ArrayList<IShape>();
    m.setShapes(temp);
    assertEquals(m.getShapes(), temp);
  }

  // Tests the performAnimations method.
  @Test
  public void testPA() {
    assertEquals(m.getShapes().get(0).getCoordinate().getX(), 1);
    m.setTime(3);
    m.performAnimations();
    assertEquals(m.getShapes().get(0).getCoordinate().getX(), 2);
  }

  // Tests the addOval method in the imbedded builder class.
  @Test
  public void testOval() {
    TweenModelBuilder<IAnimationModel> b = new SimpleAnimationModel.Builder().addOval(
            "ov", 1.0f, 2.0f, 3.0f, 4.0f, .5f, .6f, .7f, 8, 9)
            .addOval("ov1", 1.0f, 2.0f, 3.0f, 4.0f, .5f, .5f, .5f, 8, 9);
    IAnimationModel model = b.build();
    assertEquals(model.getShapes().size(), 2);
  }

  // Tests the addRectangle method in the imbedded builder class.
  @Test
  public void testRectangle() {
    TweenModelBuilder<IAnimationModel> b = new SimpleAnimationModel.Builder().addRectangle(
            "rec", 1.0f, 2.0f, 3.0f, 4.0f, .5f, .6f, .7f, 8, 9)
            .addRectangle("rect", 1.0f, 2.0f, 3.0f, 4.0f, .5f, .5f, .5f, 8, 9)
            .addRectangle("recte", 1.0f, 2.0f, 3.0f, 4.0f, .5f, .5f, .5f, 8, 9);
    IAnimationModel model = b.build();
    assertEquals(model.getShapes().size(), 3);
  }

  // Tests the addMove method in the imbedded builder class.
  @Test
  public void testAddMove() {
    TweenModelBuilder<IAnimationModel> b = new SimpleAnimationModel.Builder().addMove(
            "d", 1f, 2f, 4f, 45f, 2, 4);
    IAnimationModel model = b.build();
    assertEquals(model.getAnimations().size(), 1);
  }

  // Tests the addColorChange method in the imbedded builder class.
  @Test
  public void testAddColorChange() {
    TweenModelBuilder<IAnimationModel> b = new SimpleAnimationModel.Builder().addColorChange(
            "d", 1f, .2f, .4f, .45f, .2f, .4f, 1, 2);
    IAnimationModel model = b.build();
    assertEquals(model.getAnimations().size(), 1);
  }

  // Tests the addScaleToChange method in the imbedded builder class.
  @Test
  public void testAddScale() {
    TweenModelBuilder<IAnimationModel> b = new SimpleAnimationModel.Builder().addScaleToChange(
            "d", 1f, 2f, 4f, 45f, 2, 4);
    IAnimationModel model = b.build();
    assertEquals(model.getAnimations().size(), 1);
  }

}
