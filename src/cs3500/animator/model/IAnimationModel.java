package cs3500.animator.model;

import java.util.ArrayList;

import cs3500.animator.animations.IAnimation;
import cs3500.shapes.IShape;

/**
 * This is the interface of the Animation Model.
 */

public interface IAnimationModel {

  //* Comment changed to be more descriptive.
  /**
   * Begins the animation of the model.
   *
   * @param shapes     The given shapes to animate.
   * @param animations The given animations to perform.
   */
  void startAnimation(ArrayList<IShape> shapes, ArrayList<IAnimation> animations);

  //* Comment changed to be more descriptive.
  /**
   * Performs animations at a given tick if they should have started.
   *
   */
  void performAnimations();

  //* Comment changed to be more descriptive.
  /**
   * Adds an additional IShape to the model. Checks to make sure it is unique.
   *
   * @param s A shape to be added.
   */
  void addShape(IShape s);

  //* Comment changed to be more descriptive.
  /**
   * Adds an additional IAnimation to the model. Checks to make sure it has a corresponding shape.
   *
   * @param a An animation to be added.
   */
  void addAnimation(IAnimation a);

  //* Comment changed to be more descriptive.
  /**
   * Determines if the animation is over by checking that the animation has reached its end time.
   *
   * @return Whether the animation is over or not.
   */
  boolean isOver();

  //* New method added to allow inspecting the model's animations through a structure,
  //* as requested by a TA.
  /**
   * Returns the animations stored within the model as unique copied list.
   * @return An ArrayList containing copies of the model's animations.
   */
  ArrayList<IAnimation> getAnimations();

  //* New method added to allow inspecting the model's shapes through a structure,
  //* as requested by a TA.
  /**
   * Returns the shapes stored within the model as unique copied list.
   * @return An ArrayList containing copies of the model's shapes.
   */
  ArrayList<IShape> getShapes();

  //* New method to allow inspection of time.

  /**
   * Determines the current time of the model.
   * @return An int that is the current time of the model.
   */
  int getTime();

  //* New method to allow inspection of the endTime.

  /**
   * Determines the end time of the model.
   * @return An int that is the end time of the model.
   */
  int getEndTime();

  //* New method to increase the time of the model.
  /**
   * Increases the time of the model by 1.
   */
  void tickTime();

  //* Added a method to set the time, allowing us to restart the animation from the controller

  /**
   * Sets this model's time to the given time.
   * @param time The time to set to.
   */
  void setTime(int time);

  //* Added a method to set shapes, for the reason above.

  /**
   * Sets the shapes in this model to the given shapes.
   * @param shapes The shapes to set to.
   */
  void setShapes(ArrayList<IShape> shapes);

  //* Moved getting the description to the view.
}
