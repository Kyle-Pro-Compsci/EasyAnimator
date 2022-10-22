package cs3500.animator.animations;

import java.util.ArrayList;

import cs3500.shapes.IShape;

/**
 * Represents an animation that changes the width and/or height of a shape.
 */

public class Scale extends Animation {

  //* Changed from Double to int.
  protected int fromWidth;
  protected int fromHeight;

  //* Changed from double to int.
  protected int toWidth;
  protected int toHeight;

  //* Changed toWidth and toHeight from double to int.
  /**
   * Constructs the Scale object.
   * @param name The name of the shape to animate.
   * @param startTime The start time of the animation.
   * @param endTime The end time of the animation
   * @param toWidth The width to be changed to.
   * @param toHeight The height to be changed to.
   */
  public Scale(String name, int startTime, int endTime,
               int fromWidth, int fromHeight, int toWidth, int toHeight) {
    super(name, startTime, endTime);
    this.fromWidth = fromWidth;
    this.fromHeight = fromHeight;
    this.toWidth = toWidth;
    this.toHeight = toHeight;
  }

  //* Changed from doubles to ints.
  @Override
  public void animate(IShape s, int timee) {

    int width = s.getWidth();
    int height = s.getHeight();

    width = this.toWidth - width;
    height = this.toHeight - height;

    int time = this.endTime - this.startTime;
    width = width / time;
    height = height / time;

    s.addDimensions(width, height);
  }

  @Override
  public AnimationName getType() {
    return AnimationName.SCALE;
  }

  @Override
  public IAnimation copy() {
    return new Scale(this.name, startTime, endTime, fromWidth, fromHeight, toWidth, toHeight);
  }

  @Override
  public String description(int tickrate) {
    String start;
    String end;
    if (tickrate == 1 || tickrate == 0) {
      start = String.valueOf(startTime) + "s ";
      end = String.valueOf(endTime) + "s";
    }
    else {
      start = (double)startTime / tickrate + "s ";
      end = (double)endTime / tickrate +  "s";
    }

    return "Shape " + this.name + " scales from Width: " + this.fromWidth + ", Height: "
            + this.fromHeight + " to Width: " + this.toWidth + ", Height: " + this.toHeight
            + " from t=" + start + "to t=" + end;
  }

  @Override
  public ArrayList getChanges() {
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(new Integer(this.fromWidth));
    result.add(new Integer(this.fromHeight));
    result.add(new Integer(this.toWidth));
    result.add(new Integer(this.toHeight));
    return result;
  }

}
