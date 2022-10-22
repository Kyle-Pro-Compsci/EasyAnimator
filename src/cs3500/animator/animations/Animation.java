package cs3500.animator.animations;

import cs3500.shapes.IShape;

/**
 * Represents the basics of an animation.
 */

public abstract class Animation implements IAnimation {

  protected String name;
  protected int startTime;
  protected int endTime;

  /**
   * Constructs an Animation (if it were not abstract).
   *
   * @param name     The name of the shape to be animated.
   * @param startTime The starting time of this animation.
   * @param endTime   The ending time of this animation.
   */
  public Animation(String name, int startTime, int endTime) {
    this.name = name;
    this.checkValues(startTime, endTime);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  // Not overwritten, just varies based on the animation.
  public abstract void animate(IShape s, int time);

  // Not overwritten, just varies based on the animation.
  public abstract AnimationName getType();

  //* Now gets the name of the shape
  @Override
  public String getShapeName() {
    return this.name;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  //* Changed to check just the start and end times.
  // Checks to see if the incoming start and end times are valid.
  private void checkValues(int startTime, int endTime) {
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time after end time.");
    }
  }

  //* Changed to check the names of the shapes.
  @Override
  public boolean haveSameShape(IAnimation a) {
    return this.name.equals(a.getShapeName());
  }

  @Override
  public boolean overlapTimes(IAnimation a) {
    return (this.startTime <= a.getStartTime() && this.endTime > a.getStartTime())
            || (this.startTime > a.getStartTime() && a.getEndTime() > this.startTime);
  }


}
