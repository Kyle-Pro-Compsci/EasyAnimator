package cs3500.shapes;

import java.awt.Color;

/**
 * Represents the shape Rectangle.
 */

public class Rectangle extends Shape {

  /** Changed width and height from double to int.
  /**
   * Constructs a Rectangle object.
   *
   * @param coordinate The coordinate of this Shape.
   * @param color      The color of this Shape.
   * @param name       The name of this Shape.
   * @param startTime  The starting time of this Shape.
   * @param endTime    The ending time of this Shape.
   * @param width      The width of this Shape.
   * @param height     The height of this Shape.
   */
  public Rectangle(Coordinate coordinate, Color color, String name, int startTime, int endTime,
                   int width, int height) {
    super(coordinate, color, name, startTime, endTime, width, height);
  }

  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }

  //* Added this method.
  @Override
  public IShape copy() {
    return new Rectangle(new Coordinate(this.coordinate),
            new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()),
            this.name, this.startTime, this.endTime, this.width, this.height);
  }
}
