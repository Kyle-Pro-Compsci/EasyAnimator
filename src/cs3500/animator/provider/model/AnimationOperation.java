package cs3500.animator.provider.model;

import java.util.List;

import cs3500.animator.provider.model.transition.Transition;

/**
 * Represents the capabilities of what an animator can do.
 */
public interface AnimationOperation<T> {

  /**
   * Creates or changes the animation.
   *
   * @param name       the name of the T to change.
   * @param transition represents how the T is going to change.
   */
  <R, U> void changeAnimation(String name, Transition<R, U> transition);

  /**
   * Adds a new t to the animation.
   *
   * @param t to be added to the animation.
   */
  void addAnimation(T t);

  /**
   * Gets the list of T. Returns a copy.
   *
   * @param time gets shapes that are present at this time.
   * @return the list of shapes that are in this animation.
   */
  List<T> getT(int time);

  /**
   * Get the T with the given name. Added 11/11 to allow for constant access for a controller.
   *
   * @param name the name of the shape to be found.
   * @return the shape with that given name.
   */
  T getT(String name);

  /**
   * Get all the animations. Added 10/28 to create a view.
   *
   * @return a copy of the list of animations.
   */
  List<T> getAllT();

  /**
   * Getter for when the animation starts.
   *
   * @return the start time of the animation.
   */
  int getStart();

  /**
   * Getter for when the animation ends.
   *
   * @return the end time of the animation.
   */
  int getEnd();

}
