package cs3500.shapes;

import java.awt.Color;

/**
 * This is the interface of the Abstract class Shape.
 */

public interface IShape {

  /**
   * Determines the coordinate of this IShape.
   *
   * @return The coordinate of this IShape.
   */
  Coordinate getCoordinate();

  /**
   * Determines the ShapeColor of this IShape.
   *
   * @return The ShapeColor of this IShape.
   */
  Color getColor();

  /**
   * Sets this shape's color to a given color.
   *
   * @param color The color to be set to.
   */
  void setColor(Color color);

  /**
   * Determines the name of this IShape.
   *
   * @return The name of this IShape.
   */
  String getName();

  /**
   * Determines the type of this IShape.
   *
   * @return The ShapeType of this IShape.
   */
  ShapeType getType();

  /**
   * Determines the starting time of this IShape.
   *
   * @return The startTime of this IShape.
   */
  int getStartTime();

  /**
   * Determines the ending time of this IShape.
   *
   * @return The endTime of this IShape.
   */
  int getEndTime();

  //* Changed to int from double.
  /**
   * Determines the width of this IShape.
   *
   * @return The width of this IShape.
   */
  int getWidth();

  //* Changed to int from double.
  /**
   * Determines the height of this IShape.
   *
   * @return The height of this IShape.
   */
  int getHeight();

  /**
   * Gets a text description of this shape.
   * @param tickrate The speed at which the animation is expected to run.
   * @return The description as a String.
   */
  String description(int tickrate);

  //* Changed from doubles to ints.
  /**
   * Adds the given values to the width and height of this IShape, respectively.
   *
   * @param width  The width to be added to this's width.
   * @param height The height to be added to this's height.
   */
  void addDimensions(int width, int height);

  //* Added a new method to return a copy instead of a reference in other areas.
  /**
   * Creates a copy of this IShape.
   * @return A copy of this IShape.
   */
  IShape copy();

  //* Adds a new method to set this shape's coordinate,

  /**
   * Sets the coordinates of the shape to the given coordinates.
   * @param x The x coordinate to set to.
   * @param y The y coordinate to set to.
   */
  void setCoordinate(int x, int y);
}
