import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.animator.animations.ChangeColor;
import cs3500.animator.animations.IAnimation;
import cs3500.animator.animations.Move;
import cs3500.animator.animations.Scale;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.view.AnimatorTextView;
import cs3500.animator.view.IAnimatorView;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Oval;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for AnimatorTextView, checking the output.
 */
public class AnimatorTextViewTest {

  IAnimationModel emptyModel = new SimpleAnimationModel();
  IAnimationModel shapeNoAModel = new SimpleAnimationModel();
  IAnimationModel fullModel = new SimpleAnimationModel();
  IShape circle = new Oval(new Coordinate(25, 25), Color.RED,
          "circle", 0, 150, 35, 35);
  IShape square = new Rectangle(new Coordinate(250, 250), Color.BLUE,
          "square", 25, 100, 50, 50);
  IAnimation moveCircle = new Move("circle", 0, 100, new Coordinate(25, 25),
          new Coordinate(150, 50));
  IAnimation widenSquare = new Scale("square", 50, 100, 50, 50,
          200, 50);
  IAnimation colorCircle = new ChangeColor("circle", 50, 125, Color.RED, Color.GREEN);

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  /**
   * Sets up the stream reader before each test.
   */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  /**
   * Cleans up the streams after a test is complete.
   */
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  /**
   * Resets the testing variables before each test.
   */
  @Before
  public void reset() {
    emptyModel = new SimpleAnimationModel();
    shapeNoAModel = new SimpleAnimationModel();
    fullModel = new SimpleAnimationModel();

    shapeNoAModel.addShape(circle);
    shapeNoAModel.addShape(square);
    fullModel.addShape(circle);
    fullModel.addShape(square);
    fullModel.addAnimation(moveCircle);
    fullModel.addAnimation(widenSquare);
    fullModel.addAnimation(colorCircle);
  }

  @Test
  public void testViewEmptyModel() {
    IAnimatorView textView = new AnimatorTextView(emptyModel, 1);
    textView.view(System.out);
    assertEquals("Shapes:", outContent.toString().trim());
  }

  @Test
  public void testViewShapeOnlyModel() {
    IAnimatorView textView = new AnimatorTextView(shapeNoAModel, 1);
    textView.view(System.out);
    assertEquals("Shapes:\n" +
            "Name: circle\n" +
            "Type: OVAL\n" +
            "Position: (25,25), X radius: 35, Y radius: 35, Color: (255,0,0)\n" +
            "Appears at: t=0s\n" +
            "Disappears at: t=150s\n" +
            "\n" +
            "Name: square\n" +
            "Type: RECTANGLE\n" +
            "Position: (250,250), X radius: 50, Y radius: 50, Color: (0,0,255)\n" +
            "Appears at: t=25s\n" +
            "Disappears at: t=100s", outContent.toString().trim());
  }

  @Test
  public void testViewShapeOnlyModelWithTickrate() {
    IAnimatorView textView = new AnimatorTextView(shapeNoAModel, 20);
    textView.view(System.out);
    assertEquals("Shapes:\n" +
            "Name: circle\n" +
            "Type: OVAL\n" +
            "Position: (25,25), X radius: 35, Y radius: 35, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=7.5s\n" +
            "\n" +
            "Name: square\n" +
            "Type: RECTANGLE\n" +
            "Position: (250,250), X radius: 50, Y radius: 50, Color: (0,0,255)\n" +
            "Appears at: t=1.25s \n" +
            "Disappears at: t=5.0s", outContent.toString().trim());
  }

  @Test
  public void testViewFullModelTickrate1() {
    IAnimatorView textView = new AnimatorTextView(fullModel, 1);
    textView.view(System.out);
    assertEquals("Shapes:\n" +
            "Name: circle\n" +
            "Type: OVAL\n" +
            "Position: (25,25), X radius: 35, Y radius: 35, Color: (255,0,0)\n" +
            "Appears at: t=0s\n" +
            "Disappears at: t=150s\n" +
            "\n" +
            "Name: square\n" +
            "Type: RECTANGLE\n" +
            "Position: (250,250), X radius: 50, Y radius: 50, Color: (0,0,255)\n" +
            "Appears at: t=25s\n" +
            "Disappears at: t=100s\n" +
            "\n" +
            "Shape circle moves from (25,25) to (150,50) from t=0s to t=100s\n" +
            "Shape square scales from Width: " +
            "50, Height: 50 to Width: 200, Height: 50 from t=50s to t=100s\n" +
            "Shape circle changes color from (255,0,0) to (0,255,0) from t=50s to t=125s",
            outContent.toString().trim());
  }

  @Test
  public void testViewFullModelTickrate10() {
    IAnimatorView textView = new AnimatorTextView(fullModel, 10);
    textView.view(System.out);
    assertEquals("Shapes:\n" +
            "Name: circle\n" +
            "Type: OVAL\n" +
            "Position: (25,25), X radius: 35, Y radius: 35, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=15.0s\n" +
            "\n" +
            "Name: square\n" +
            "Type: RECTANGLE\n" +
            "Position: (250,250), X radius: 50, Y radius: 50, Color: (0,0,255)\n" +
            "Appears at: t=2.5s \n" +
            "Disappears at: t=10.0s\n" +
            "\n" +
            "Shape circle moves from (25,25) to (150,50) from t=0.0s to t=10.0s\n" +
            "Shape square scales from Width: " +
            "50, Height: 50 to Width: 200, Height: 50 from t=5.0s to t=10.0s\n" +
            "Shape circle changes color from (255,0,0) to (0,255,0) from t=5.0s to t=12.5s",
            outContent.toString().trim());
  }

  @Test
  public void testViewFulLModelTickrate25() {
    IAnimatorView textView = new AnimatorTextView(fullModel, 10);
    textView.view(System.out);
    assertEquals("Shapes:\n" +
            "Name: circle\n" +
            "Type: OVAL\n" +
            "Position: (25,25), X radius: 35, Y radius: 35, Color: (255,0,0)\n" +
            "Appears at: t=0.0s \n" +
            "Disappears at: t=15.0s\n" +
            "\n" +
            "Name: square\n" +
            "Type: RECTANGLE\n" +
            "Position: (250,250), X radius: 50, Y radius: 50, Color: (0,0,255)\n" +
            "Appears at: t=2.5s \n" +
            "Disappears at: t=10.0s\n" +
            "\n" +
            "Shape circle moves from (25,25) to (150,50) from t=0.0s to t=10.0s\n" +
            "Shape square scales from Width: " +
            "50, Height: 50 to Width: 200, Height: 50 from t=5.0s to t=10.0s\n" +
            "Shape circle changes color from (255,0,0) to (0,255,0) from t=5.0s to t=12.5s",
            outContent.toString().trim());
  }

}
