package cs3500.shapes;

import java.awt.Color;

/**
 * Represents the basics of a shape that can be created.
 */

public abstract class Shape implements IShape {

  protected Coordinate coordinate;
  protected Color color;
  protected String name;
  protected int startTime;
  protected int endTime;
  //* Changed the following two fields to integers.
  protected int width;
  protected int height;

  //* Changed the width and height to be integers.
  /**
   * Constructs a shape object (if it weren't abstract).
   *
   * @param coordinate The coordinate of this Shape.
   * @param color      The color of this Shape.
   * @param name       The name of this Shape.
   * @param startTime  The starting time of this Shape.
   * @param endTime    The ending time of this Shape.
   * @param width      The width of this Shape.
   * @param height     The height of this Shape.
   */
  public Shape(Coordinate coordinate, Color color, String name, int startTime, int endTime,
               int width, int height) {
    this.coordinate = coordinate;
    this.color = color;
    this.name = name;
    if (endTime <= startTime) {
      throw new IllegalArgumentException("Shape ends before it begins.");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    if (width <= 0) {
      throw new IllegalArgumentException("Width too small.");
    }
    this.width = width;
    if (height <= 0) {
      throw new IllegalArgumentException("Height too small.");
    }
    this.height = height;
  }

  //* Changed to use a copy constructor.
  @Override
  public Coordinate getCoordinate() {
    return new Coordinate(this.coordinate);
  }

  //* Changed to use Color's getter methods instead of a reference.
  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String getName() {
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

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public abstract ShapeType getType();

  @Override
  public void addDimensions(int width, int height) {
    this.width += width;
    this.height += height;
  }

  @Override
  public String description(int tickrate) {
    String start;
    String end;
    if (tickrate == 1 || tickrate == 0) {
      start = startTime + "s";
      end = endTime + "s";
    }
    else {
      start = (double)startTime / tickrate + "s ";
      end = (double)endTime / tickrate +  "s";
    }

    return "Name: " + this.name + "\n"
            + "Type: " + this.getType() + "\n" + "Position: "
            + this.coordinate.toString() + ", X radius: " + this.width + ", Y radius: "
            + this.height + ", Color: " + "(" + this.color.getRed() + ","
            + this.color.getGreen() + ","
            + this.color.getBlue() + ")" + "\n"
            + "Appears at: t=" + start + "\n"
            + "Disappears at: t=" + end;
  }

  @Override
  public void setCoordinate(int x, int y) {
    this.coordinate.setCoordinate(x, y);
  }
}
