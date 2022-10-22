package cs3500.animator.animations;

import java.util.ArrayList;

import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;


/**
 * Represents an animation that changes the location of a shape.
 */
public class Move extends Animation {

  protected Coordinate fromCoordinate;
  protected Coordinate toCoordinate;


  /**
   * Constructs a Move object.
   * @param name The name of the shape to be moved.
   * @param startTime The starting time to move.
   * @param endTime The ending time to move.
   * @param toCoordinate The coordinate to move to.
   */
  public Move(String name, int startTime, int endTime,
              Coordinate fromCoordinate, Coordinate toCoordinate) {
    super(name, startTime, endTime);
    this.fromCoordinate = fromCoordinate;
    this.toCoordinate = toCoordinate;
  }

  //* Changed x and y from double to int. Changed the inside of the if statement to use
  //* a copy constructor instead.
  @Override
  public void animate(IShape s, int time) {

    double occurLength = this.endTime - this.startTime;
    double timeIn = time - this.startTime;

    int posX = (int)(s.getCoordinate().getX()
            + (((double)toCoordinate.getX() -
            (double)s.getCoordinate().getX()) * (timeIn / occurLength)));
    int posY = (int)(s.getCoordinate().getY()
            + (((double)toCoordinate.getY() -
            (double)s.getCoordinate().getY()) * (timeIn / occurLength)));
    s.setCoordinate(posX, posY);
  }

  @Override
  public AnimationName getType() {
    return AnimationName.MOVE;
  }

  @Override
  public IAnimation copy() {
    return new Move(this.name, startTime, endTime, fromCoordinate, toCoordinate);
  }

  //* Modifying this method to account for a possible given tickrate.
  @Override
  public String description(int tickrate) {
    String start;
    String end;
    if (tickrate == 1 || tickrate == 0) {
      start = String.valueOf(startTime) + "s";
      end = String.valueOf(endTime) + "s";
    }
    else {
      start = (double)startTime / tickrate + "s";
      end = (double)endTime / tickrate +  "s";
    }

    return "Shape " + this.name + " moves from " + this.fromCoordinate.toString()
            + " to " + this.toCoordinate.toString() + " from t=" + start + " to t="
            + end;
  }

  @Override
  public ArrayList getChanges() {

    ArrayList<Coordinate> result = new ArrayList<Coordinate>();
    result.add(new Coordinate(this.fromCoordinate));
    result.add(new Coordinate(this.toCoordinate));
    return result;
  }

}
