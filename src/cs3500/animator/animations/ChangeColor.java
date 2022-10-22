package cs3500.animator.animations;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.shapes.IShape;


/**
 * Represents an animation that changes the color of a shape.
 */

public class ChangeColor extends Animation {

  protected Color fromColor;
  protected Color toColor;

  /**
   * Constructs a ChangeColor Animation that changes the color of a shape.
   *
   * @param name      The name of the animation to change.
   * @param startTime The starting time of the animation.
   * @param endTime   The ending time of the animation.
   * @param fromColor The color that the shape will start at.
   * @param toColor   The color that the shape will end at.
   */
  public ChangeColor(String name, int startTime, int endTime, Color fromColor, Color toColor) {
    super(name, startTime, endTime);
    this.fromColor = fromColor;
    this.toColor = toColor;
  }

  @Override
  public void animate(IShape s, int time) {

    double occurLength = this.endTime - this.startTime;
    double timeIn = time - this.startTime;
    double prop = timeIn / occurLength;

    int rSplit = toColor.getRed() - fromColor.getRed();
    int gSplit = toColor.getGreen() - fromColor.getGreen();
    int bSplit = toColor.getBlue() - fromColor.getBlue();

    int rTo = (int) (fromColor.getRed() + rSplit * prop);
    int gTo = (int) (fromColor.getGreen() + gSplit * prop);
    int bTo = (int) (fromColor.getBlue() + bSplit * prop);

    s.setColor(new Color(rTo, gTo, bTo));

  }

  @Override
  public AnimationName getType() {
    return AnimationName.CHANGECOLOR;
  }

  @Override
  public Animation copy() {
    return new ChangeColor(this.name, startTime, endTime, new Color(fromColor.getRGB()),
            new Color(toColor.getRGB()));
  }

  @Override
  public String description(int tickrate) {
    String start;
    String end;
    if (tickrate == 1 || tickrate == 0) {
      start = String.valueOf(startTime) + "s ";
      end = String.valueOf(endTime) + "s ";
    } else {
      start = (double) startTime / tickrate + "s ";
      end = (double) endTime / tickrate + "s ";
    }

    return "Shape " + this.name + " changes color from (" + this.fromColor.getRed() + ","
            + this.fromColor.getGreen() + ","
            + this.fromColor.getBlue() + ")"
            + " to (" + this.toColor.getRed() + ","
            + this.toColor.getGreen() + ","
            + this.toColor.getBlue() + ") from t=" + start + "to t="
            + end;
  }

  @Override
  public ArrayList getChanges() {
    ArrayList<Color> result = new ArrayList<Color>();
    result.add(new Color(this.fromColor.getRed(),
            this.fromColor.getGreen(),
            this.fromColor.getBlue()));
    result.add(new Color(this.toColor.getRed(),
            this.toColor.getGreen(),
            this.toColor.getBlue()));
    return result;
  }
}
