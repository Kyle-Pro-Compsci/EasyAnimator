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
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.IAnimatorView;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.Oval;
import cs3500.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for AnimatorSVGView, comparing the view() outputs to what is expected.
 */
public class AnimatorSVGViewTest {

  IAnimationModel emptyModel = new SimpleAnimationModel();
  IAnimationModel shapeNoAModel = new SimpleAnimationModel();
  IAnimationModel fullModel = new SimpleAnimationModel();
  IShape circle = new Oval(new Coordinate(25, 25), Color.RED,
          "circle", 0, 150, 35, 35);
  IShape square = new Rectangle(new Coordinate(250, 250), Color.BLUE,
          "square", 25, 200, 50, 50);
  IAnimation moveCircle = new Move("circle", 0, 100, new Coordinate(25, 25),
          new Coordinate(150, 50));
  IAnimation widenSquare = new Scale("square", 50, 100, 50, 50,
          200, 50);
  IAnimation colorSquare = new ChangeColor("square", 50, 125, Color.RED, Color.GREEN);

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

    fullModel.addShape(circle);
    fullModel.addShape(square);
    fullModel.addAnimation(moveCircle);
    fullModel.addAnimation(widenSquare);
    fullModel.addAnimation(colorSquare);
    shapeNoAModel.addShape(circle);
    shapeNoAModel.addShape(square);
  }

  @Test
  public void testOutputEmptyModel() {
    IAnimatorView sVGView = new AnimatorSVGView(emptyModel, 1, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "</svg>", outContent.toString().trim());
  }

  @Test
  public void testOutputShapesOnly() {
    IAnimatorView sVGView = new AnimatorSVGView(shapeNoAModel, 1, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <ellipse id=\"circle\" cx=\"25\" cy=\"25\" rx=\"35\" ry=\"35\" fill=\"" +
            "rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"0.0s\" dur=\"150.0s\" " +
            "to=\"visible\"/>\n" +
            "  </ellipse>\n" +
            "  <rect id=\"square\" x=\"250\" y=\"250\" width=\"50\" height=\"50\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"25.0s\" dur=\"175.0s\"" +
            " to=\"visible\"/>\n" +
            " </rect>\n" +
            "</svg>", outContent.toString().trim());
  }

  @Test
  public void testOutputShapesOnlyWithHighTickrate() {
    IAnimatorView sVGView = new AnimatorSVGView(shapeNoAModel, 24, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <ellipse id=\"circle\" cx=\"25\" cy=\"25\" rx=\"35\" ry=\"35\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"0.0s\" " +
            "dur=\"6.25s\" to=\"visible\"/>\n" +
            "  </ellipse>\n" +
            "  <rect id=\"square\" x=\"250\" y=\"250\" width=\"50\" " +
            "height=\"50\" fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"1.0416666666666667s\" " +
            "dur=\"7.291666666666667s\" to=\"visible\"/>\n" +
            " </rect>\n" +
            "</svg>", outContent.toString().trim());
  }

  @Test
  public void testOutputFullModelTickrate1() {
    IAnimatorView sVGView = new AnimatorSVGView(fullModel, 1, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <ellipse id=\"circle\" cx=\"25\" cy=\"25\" rx=\"35\" ry=\"35\" fill=\"" +
            "rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"0.0s\" dur=\"150.0s\" " +
            "to=\"visible\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"100.0s\" attributeName=\"cx\" from=\"25\" " +
            "to=\"150\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"100.0s\" attributeName=\"cy\" from=\"25\" " +
            "to=\"50\" fill=\"freeze\"/>\n" +
            "  </ellipse>\n" +
            "  <rect id=\"square\" x=\"250\" y=\"250\" width=\"50\" height=\"50\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"25.0s\" " +
            "dur=\"175.0s\" to=\"visible\"/>\n" +
            "    <animate begin=\"50.0s\" dur=\"50.0s\" attributeName=\"width\" from=\"50\" " +
            "to=\"200\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"50.0s\" dur=\"50.0s\" attributeName=\"height\" from=\"50\" " +
            "to=\"50\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"50.0s\" dur=\"75.0s\" attributeName=\"fill\" " +
            "from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            " </rect>\n" +
            "</svg>", outContent.toString().trim());
  }

  @Test
  public void testOutputFullModelTickrate10() {
    IAnimatorView sVGView = new AnimatorSVGView(fullModel, 10, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <ellipse id=\"circle\" cx=\"25\" cy=\"25\" rx=\"35\" ry=\"35\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"0.0s\" " +
            "dur=\"15.0s\" to=\"visible\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"10.0s\" attributeName=\"cx\" " +
            "from=\"25\" to=\"150\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"10.0s\" attributeName=\"cy\" " +
            "from=\"25\" to=\"50\" fill=\"freeze\"/>\n" +
            "  </ellipse>\n" +
            "  <rect id=\"square\" x=\"250\" y=\"250\" width=\"50\" height=\"50\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"2.5s\" dur=\"17.5s\" " +
            "to=\"visible\"/>\n" +
            "    <animate begin=\"5.0s\" dur=\"5.0s\" attributeName=\"width\" " +
            "from=\"50\" to=\"200\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"5.0s\" dur=\"5.0s\" attributeName=\"height\" " +
            "from=\"50\" to=\"50\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"5.0s\" dur=\"7.5s\" attributeName=\"fill\" " +
            "from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            " </rect>\n" +
            "</svg>", outContent.toString().trim());
  }

  @Test
  public void testOutputFullModelTickrate24() {
    IAnimatorView sVGView = new AnimatorSVGView(fullModel, 24, false);
    sVGView.view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <ellipse id=\"circle\" cx=\"25\" cy=\"25\" rx=\"35\" ry=\"35\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"0.0s\" dur=\"6.25s\" " +
            "to=\"visible\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"4.166666666666667s\" attributeName=\"cx\" " +
            "from=\"25\" to=\"150\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"0.0s\" dur=\"4.166666666666667s\" attributeName=\"cy\" " +
            "from=\"25\" to=\"50\" fill=\"freeze\"/>\n" +
            "  </ellipse>\n" +
            "  <rect id=\"square\" x=\"250\" y=\"250\" width=\"50\" height=\"50\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "    <animate attributeName=\"visibility\" begin=\"1.0416666666666667s\" " +
            "dur=\"7.291666666666667s\" to=\"visible\"/>\n" +
            "    <animate begin=\"2.0833333333333335s\" dur=\"2.0833333333333335s\" " +
            "attributeName=\"width\" from=\"50\" to=\"200\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"2.0833333333333335s\" dur=\"2.0833333333333335s\" " +
            "attributeName=\"height\" from=\"50\" to=\"50\" fill=\"freeze\"/>\n" +
            "    <animate begin=\"2.0833333333333335s\" dur=\"3.125s\" attributeName=\"fill\" " +
            "from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            " </rect>\n" +
            "</svg>", outContent.toString().trim());
  }
}
