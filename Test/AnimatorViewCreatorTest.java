import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.animator.view.AnimatorViewCreator;
import cs3500.animator.view.AnimatorHybridView;
import cs3500.animator.view.AnimatorVisualView;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests the AnimatorViewCreator class.
 */
public class AnimatorViewCreatorTest {

  AnimatorViewCreator avc;
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

  // Tests the create method for text view.
  @Test
  public void testCreate1() {
    avc.create("text").view(System.out);
    assertEquals("Shapes:", outContent.toString().trim());
  }

  // Tests the create method for a visual view.
  @Test
  public void testCreate2() {
    assertTrue(avc.create("visual") instanceof AnimatorVisualView);
  }

  // Tests the create method for an svg view.
  @Test
  public void testCreate3() {
    avc.create("svg").view(System.out);
    assertEquals("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "  <rect>\n" +
            "    <animate id=\"base\" begin=\"0;base.end\" dur=\"0.0s\" " +
            "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
            "  </rect>\n" +
            "</svg>", outContent.toString().trim());
  }



  // Tests the create method for a one off error of an illegal view type.
  @Test(expected = IllegalArgumentException.class)
  public void testCreate4() {
    avc.create("svd").view(System.out);
  }

  // Tests the create method for a hybrid view.
  @Test
  public void testCreate5() {
    assertTrue(avc.create("interactive") instanceof AnimatorHybridView);
  }

  /**
   * Cleans up the streams after a test is complete.
   */
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }
}
