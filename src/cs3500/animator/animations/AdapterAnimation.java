package cs3500.animator.animations;

import com.sun.prism.paint.Color;

import java.util.ArrayList;

import cs3500.animator.provider.model.shape.Shape;
import cs3500.animator.provider.model.transition.Transition;
import cs3500.shapes.Coordinate;

public class AdapterAnimation implements Transition<Shape, String>{

  IAnimation a;

  public AdapterAnimation (IAnimation a) {

  }

  @Override
  public void change(Shape shape) {
    a.animate(null, 3);
  }

  @Override
  public String getState() {
    switch(a.getType()) {
      case SCALE:
        ArrayList<Integer> changes = a.getChanges();
        return "(" + changes.get(0) + ", " + changes.get(1) + ")";
      case MOVE:
        ArrayList<Coordinate> coordinates = a.getChanges();
        return "(" + coordinates.get(0).getX() + ", " + coordinates.get(0).getY() + ")";
      case CHANGECOLOR:
        ArrayList<Color> colors = a.getChanges();
        Color c = colors.get(0);
        return "(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
      default:
        // Unreachable code.
        break;
    }
    // Unreachable code.
    return "error";
  }

  @Override
  public int getStart() {
    return a.getStartTime();
  }

  @Override
  public int getEnd() {
    return a.getEndTime();
  }

  @Override
  public String getSVG(String unit, int tempo, String type, boolean looping) {
    return a.description(tempo);
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  @Override
  public String toString() {
    switch(a.getType()) {
      case SCALE:
        ArrayList<Integer> changes = a.getChanges();
        return "(" + changes.get(2) + ", " + changes.get(3) + ")";
      case MOVE:
        ArrayList<Coordinate> coordinates = a.getChanges();
        return "(" + coordinates.get(1).getX() + ", " + coordinates.get(1).getY() + ")";
      case CHANGECOLOR:
        ArrayList<Color> colors = a.getChanges();
        Color c = colors.get(1);
        return "(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
      default:
        // Unreachable code.
        break;
    }
    // Unreachable code.
    return "error";
  }
}
