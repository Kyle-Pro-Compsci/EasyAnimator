package cs3500.animator.animations;

import java.util.ArrayList;

import cs3500.shapes.IShape;

//* Comment changed to be more descriptive.
/**
 * This is the interface for an Animation. It describes the things that any Animation should be able
 * to do, including but not limited to animating, getting a shape, getting the start or end type.
 */

public interface IAnimation {

  /**
   * Performs the animation on the given shape.
   * @param s The shape to animate.
   * @param time The current time to calculate a proper animation.
   */
  void animate(IShape s, int time);

  /**
   * Determines the name of shape it animates.
   *
   * @return The name of the shape this animates.
   */
  String getShapeName();

  /**
   * Determines the starting time of this IAnimation.
   *
   * @return The starting time of this IAnimation.
   */
  int getStartTime();

  /**
   * Determines the ending time of this IAnimation.
   *
   * @return The ending time of this IAnimation.
   */
  int getEndTime();

  /**
   * Determines the type of this IAnimation.
   *
   * @return The type of this IAnimation.
   */
  AnimationName getType();

  /**
   * Determines if this IAnimation has the same shape as "a"'s shape.
   *
   * @param a The IAnimation this is being compared to.
   * @return Whether this IAnimation has the same shape as "a"'s shape.
   */
  boolean haveSameShape(IAnimation a);

  /**
   * Determines if this IAnimation has overlapping time with a.
   *
   * @param a The IAnimation this is being compared to.
   * @return Whether this IAnimation has overlapping time with a.
   */
  boolean overlapTimes(IAnimation a);

  /**
   * Returns a copy, not a reference, of this IAnimation.
   * Used over a copy constructor if the exact implementing class is not known.
   * @return A new, but identical, IAnimation object.
   */
  IAnimation copy();

  /**
   * Returns a String description of how this animation plays out.
   * @param tickrate The speed at which the animation is expected to play.
   * @return The description, as a String.
   */
  String description(int tickrate);

  //* Added getChanges() to the Animations to allow the data to be extracted by the views.
  /**
   * Gets the attribute changes of this animation.
   * @param <T> An attribute to be changed.
   * @return An arraylist of the attribute changes for this animation.
   */
  <T> ArrayList<T> getChanges();
}
