
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.animations.IAnimation;
import cs3500.animator.animations.Move;
import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.view.AnimatorHybridView;
import cs3500.animator.view.IAnimatorHybridView;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Class created to test the AnimatorHybridView class.
 */

public class HybridViewTest {

  private Coordinate c;
  private Coordinate c1;
  private IShape s;
  private IShape s1;
  private IAnimation a;
  private IAnimation a1;
  private IAnimationModel m;
  private ArrayList<IShape> shapes;
  private ArrayList<IAnimation> anims;
  IAnimatorHybridView v;
  AnimatorController controller;

  // Tests the getOutputField method in the hybrid view.
  @Test
  public void testGOF() {

    c = new Coordinate(1, 5);
    c1 = new Coordinate(3, 6);
    s = new Rectangle(c, Color.BLUE, "name", 1, 5, 2, 3);
    s1 = new Rectangle(c, Color.BLUE, "shape", 2, 7, 2, 3);
    a = new Move("name", 2, 4,c, c1);
    a1 = new Move("name", 4, 5,c, c1);

    shapes = new ArrayList<IShape>();
    anims = new ArrayList<IAnimation>();
    m = new SimpleAnimationModel();
    m.addShape(s);
    m.addAnimation(a);
    shapes.add(s);
    shapes.add(s1);
    anims.add(a);
    anims.add(a1);
    v = new AnimatorHybridView(m.getShapes(), 1);
    controller = new AnimatorController(v, m, 20);

    assertEquals(v.getOutputField(), "");
  }

  // Tests the getSelectedShapes method in the hybrid view.
  @Test
  public void testGSS() {

    c = new Coordinate(1, 5);
    c1 = new Coordinate(3, 6);
    s = new Rectangle(c, Color.BLUE, "name", 1, 5, 2, 3);
    s1 = new Rectangle(c, Color.BLUE, "shape", 2, 7, 2, 3);
    a = new Move("name", 2, 4,c, c1);
    a1 = new Move("name", 4, 5,c, c1);

    shapes = new ArrayList<IShape>();
    anims = new ArrayList<IAnimation>();
    m = new SimpleAnimationModel();
    m.addShape(s);
    m.addAnimation(a);
    shapes.add(s);
    shapes.add(s1);
    anims.add(a);
    anims.add(a1);
    v = new AnimatorHybridView(m.getShapes(), 1);
    controller = new AnimatorController(v, m, 20);

    assertEquals(v.getSelectedShapes(), new ArrayList<>());

  }
}
