package cs3500.animator.provider.view;

import java.util.Objects;

/**
 * Factory class for the different types of views.
 */
public class BuildView {

  /**
   * Creates a view given a type of string. Changed 11/12 to support new types of views.
   *
   * @param s represents which type of view.
   * @return a view based on the type of string.
   */
  public static View create(String s) {
    Objects.requireNonNull(s, "The given string cannot be null.");
    switch (s) {
      case "text":
        return new TextView();
      case "visual":
        return new VisualView();
      case "svg":
        return new SVGView();
      case "interactive":
        return new HybridView();
      default:
        throw new IllegalArgumentException("Invalid string to produce a formatted view.");
    }
  }

}
