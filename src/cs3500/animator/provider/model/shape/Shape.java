package cs3500.animator.provider.model.shape;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.provider.model.transition.Transition;

/**
 * Represents a shape to be drawn. 11/2, extended comparable to determine which shape came first.
 */
public interface Shape extends Comparable<Shape> {

  /**
   * Getter for the name.
   *
   * @return the name of the shape.
   */
  String getName();

  /**
   * Getter for start time.
   *
   * @return the start time.
   */
  int getStart();

  /**
   * Getter for end time.
   *
   * @return the end time.
   */
  int getEnd();

  /**
   * Added 10/28 for view drawing. Gets a copy of all the transition that this shape goes through.
   */
  List<Transition> getListTransitions();

  /**
   * Gives another state to transition to.
   *
   * @param t the transition to the next state.
   */
  void addTransition(Transition t);

  /**
   * Gets the shape in transition.
   *
   * @param time the current time.
   * @return a representation of a shape in transition from the last shape to this shape.
   */
  Shape getInBetween(int time);

  /**
   * Gets the transition of this shape from the last. Changed 10/27 to fix misunderstanding of
   * customer desires.
   *
   * @return a string description.
   */
  HashMap<Integer, List<String>> getTransition();

  /**
   * Added 10/30. Draws this shape on the given frame.
   *
   * @param g represents the plane to be drawn on.
   */
  void paintComponent(Graphics g);

  /**
   * Added 10/30. Formats the data into an SVG format to be drawn.
   *
   * @param unit  represents what the unit of time is to be.
   * @param tempo represents the ticks per unit.
   * @return the SVG representation of the shape.
   */
  String getSVG(String unit, int tempo);

  /**
   * Added 11/13. Sets the whether the shape is looping.
   *
   * @param b the value to set looping to.
   */
  void setLooping(boolean b);

  /**
   * Added 11/15. Determines if this shape is looping.
   *
   * @return whether or not this shape is looping.
   */
  boolean isLooping();
}
