package cs3500.animator.view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.animations.IAnimation;
import cs3500.animator.model.IAnimationModel;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;
import cs3500.shapes.ShapeType;


/**
 * Represents an svg view that shows an animation in svg format.
 */
public class AnimatorSVGView extends AnimatorTextView {

  private boolean looping;

  /**
   * Constructs an AnimatorSVGView object based on the given model and tickrate.
   * @param model The model that this SVG view will model.
   * @param tickrate The tick rate that the view will use.
   * @param looping Whether or not the svg should loop.
   */
  public AnimatorSVGView(IAnimationModel model, int tickrate, boolean looping) {
    super(model, tickrate);
    this.looping = looping;
  }

  @Override
  public void view(Appendable ap) {
    String output = "";
    output += "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    double loopTimeframe = this.lastTime() / (double)this.tickrate;
    if (looping) {
      output += "  <rect>\n" +
              "    <animate id=\"base\" begin=\"0;base.end\" dur=\"" + loopTimeframe +
              "s\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
              "  </rect>\n";
    }

    for (IShape s : shapes) {
      switch (s.getType()) {
        case OVAL:
          output += "  <ellipse id=\"" + s.getName() + "\" cx=\"" + s.getCoordinate().getX()
                  + "\" cy=\"" + s.getCoordinate().getY() + "\" rx=\"" + s.getWidth()
                  + "\" ry=\"" + s.getHeight() + "\" fill=\"rgb(" + s.getColor().getRed()
                  + "," + s.getColor().getGreen() + "," + s.getColor().getBlue()
                  + ")\" visibility=\"hidden\">\n";

          output += this.visibility(s) + "\n" + this.animationSVG(s);
          if (looping) {
            output += "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"fill\" from=\"rgb(" + s.getColor().getRed() + "," +
                    s.getColor().getGreen() + "," + s.getColor().getBlue() + ")\" to=\"rgb(" +
                    s.getColor().getRed() + "," + s.getColor().getGreen() + "," +
                    s.getColor().getBlue() + ")\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"cx\" from=\"" + s.getCoordinate().getX() + "\" to=\" " +
                    s.getCoordinate().getX() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"cy\" from=\"" + s.getCoordinate().getY() + "\" to=\"" +
                    s.getCoordinate().getY() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"ry\" from=\"" + s.getHeight() + "\" to=\"" +
                    s.getHeight() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"rx\" from=\"" + s.getWidth() + "\" to=\"" +
                    s.getWidth() + "\" fill=\"freeze\"/>\n";

          }
          output += "  </ellipse>";
          break;
        case RECTANGLE:
          output += "  <rect id=\"" + s.getName() + "\" x=\"" + s.getCoordinate().getX()
                  + "\" y=\"" + s.getCoordinate().getY() + "\" width=\"" + s.getWidth()
                  + "\" height=\"" + s.getHeight() + "\" fill=\"rgb(" + s.getColor().getRed()
                  + "," + s.getColor().getGreen() + "," + s.getColor().getBlue()
                  + ")\" visibility=\"hidden\">\n";

          output += this.visibility(s) + "\n" + this.animationSVG(s);
          if (looping) {
            output += "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"fill\" from=\"rgb(" + s.getColor().getRed() + "," +
                    s.getColor().getGreen() + "," + s.getColor().getBlue() + ")\" to=\"rgb(" +
                    s.getColor().getRed() + "," + s.getColor().getGreen() + "," +
                    s.getColor().getBlue() + ")\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"x\" from=\"" + s.getCoordinate().getX() + "\" to=\" " +
                    s.getCoordinate().getX() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"y\" from=\"" + s.getCoordinate().getY() + "\" to=\"" +
                    s.getCoordinate().getY() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"height\" from=\"" + s.getHeight() + "\" to=\"" +
                    s.getHeight() + "\" fill=\"freeze\"/>\n" +

                    "    <animate begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"width\" from=\"" + s.getWidth() + "\" to=\"" +
                    s.getWidth() + "\" fill=\"freeze\"/>\n";
          }
          output += " </rect>";
          break;
        default:
        // should never reach
      }
      output += "\n";
    }

    output += "</svg>";
    try {
      ap.append(output);
    } catch (IOException e) {
      return;
    }
  }

  // Returns the tags necessary for a shape's animations in svg format.
  private String animationSVG(IShape s) {
    String output = "";

    for (IAnimation a : animations) {
      if (a.getShapeName().equals(s.getName())) {
        output += this.tagStart(a);
        String width = "width";
        String height = "height";
        String x = "x";
        String y = "y";
        boolean oval = false;
        for (IShape shape : shapes) {
          if (a.getShapeName().equals(shape.getName())) {
            oval = shape.getType() == ShapeType.OVAL;
            break;
          }
        }
        if (oval) {
          width = "rx";
          height = "ry";
          x = "cx";
          y = "cy";
        }
        switch (a.getType()) {
          case SCALE:
            ArrayList<Integer> changes = a.getChanges();
            output += "\"" + width + "\" from=\"" + changes.get(0) + "\" to=\"" + changes.get(2)
                    + "\" fill=\"freeze\"/>\n" + this.tagStart(a) + "\"" + height + "\" from=\""
                    + changes.get(1) + "\" to=\"" + changes.get(3) + "\" fill=\"freeze\"/>\n";
            break;
          case MOVE:
            ArrayList<Coordinate> coordinates = a.getChanges();
            output += "\"" + x + "\" from=\"" + coordinates.get(0).getX() + "\" to=\""
                    + coordinates.get(1).getX() + "\" fill=\"freeze\"/>\n" + this.tagStart(a)
                    + "\"" + y + "\" from=\"" + coordinates.get(0).getY() + "\" to=\""
                    + coordinates.get(1).getY() + "\" fill=\"freeze\"/>\n";
            break;
          case CHANGECOLOR:
            ArrayList<Color> colors = a.getChanges();
            output += "\"fill\" from=\"" + this.formatColor(colors.get(0)) + "\" to=\""
                    + this.formatColor(colors.get(1)) + "\" fill=\"freeze\"/>\n";
            break;
          default: // should never reach
        }
      }
    }

    return output;
  }

  // Returns what every animation tag should start with.
  private String tagStart(IAnimation a) {

    String result = "    <animate begin=\"";
    if (looping) {
      result += "base.begin+";
    }
    result += a.getStartTime() / (double)tickrate + "s\" dur=\""
            + (a.getEndTime() - a.getStartTime()) / (double)tickrate
            + "s\" attributeName=";
    return result;

  }

  // Returns the tag that animates a shape's visibility.
  private String visibility(IShape s) {

    String result =  "    <animate attributeName=\"visibility\" begin=\"";
    if (looping) {
      result += "base.begin+";
    }
    result += s.getStartTime() / (double)tickrate
            + "s\" dur=\"" + (s.getEndTime() - s.getStartTime()) / (double)tickrate
            + "s\" to=\"visible\"/>";
    return result;
  }

  // Returns the tag format for a given color.
  private String formatColor(Color c) {
    return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
  }

  // Determines the last time a shape appears.
  private int lastTime() {
    int time = 0;
    for (IShape s : this.shapes) {
      if (s.getEndTime() > time) {
        time = s.getEndTime();
      }
    }
    return time;
  }


}
