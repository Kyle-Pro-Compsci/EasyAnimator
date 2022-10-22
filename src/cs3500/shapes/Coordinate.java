package cs3500.shapes;

/**
 * Represents the a coordinate for a shape.
 */

public class Coordinate {

  //* Changed x and y to int from double.
  private int x;
  private int y;

  //* Changed x and y to int from double.
  /**
   * Constructs a Coordinate object.
   *
   * @param x        The x value of this Coordinate.
   * @param y        The y value of this Coordinate.
   */
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  //* Added a copy constructor.
  /**
   * Copy constructor for a Coordinate object.
   *
   * @param c The given Coordinate to copy.
   */
  public Coordinate(Coordinate c) {
    this(c.x, c.y);
  }

  //* Changed return type to int from double.
  /**
   * Determines the x value of this Coordinate.
   *
   * @return The double x of this Coordinate.
   */
  public int getX() {
    return this.x;
  }

  //* Changed return type to int from double.
  /**
   * Determines the y value of this Coordinate.
   *
   * @return The double y of this Coordinate.
   */
  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";

  }

  //* Added a new method to set the x and y of a coordinate.
  /**
   * Sets the x and y values of this coordinate to the given x and y values.
   * @param x The value to set this' x.
   * @param y The value to set this' y.
   */
  public void setCoordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

}
