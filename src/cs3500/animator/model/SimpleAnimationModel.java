package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.animations.ChangeColor;
import cs3500.animator.animations.IAnimation;

import cs3500.animator.animations.Move;
import cs3500.animator.animations.Scale;
import cs3500.shapes.Coordinate;
import cs3500.shapes.IShape;

import cs3500.animator.util.TweenModelBuilder;
import cs3500.shapes.Oval;
import cs3500.shapes.Rectangle;


//* Comment changed to be more descriptive.

/**
 * This represents an animation model for simple shapes. It can start an animation, perform
 * animations, add shapes to the model, add animations to the model, determine if time is up, and
 * provide a written description for the model.
 */
public class SimpleAnimationModel implements IAnimationModel {

  protected int time;
  protected int endTime;
  protected ArrayList<IShape> shapes;
  protected ArrayList<IAnimation> animations;

  /**
   * The default constructor. It sets the endTime to fit the visibility of all the shapes.
   */
  public SimpleAnimationModel() {
    this.time = 0;

    this.shapes = new ArrayList<IShape>();
    this.animations = new ArrayList<IAnimation>();
    this.endTime = this.latestTime();

  }

  /**
   * Constructs a SimpleAnimationModel with already provided shapes and animations.
   *
   * @param shapes     The shapes for this animation. More can be added later.
   * @param animations The individual animations provided for this animation.
   */
  public SimpleAnimationModel(ArrayList<IShape> shapes, ArrayList<IAnimation> animations) {
    this.time = 1;
    this.shapes = shapes;
    this.animations = animations;

    this.checkShapes(this.shapes);
    this.checkAnimations(this.animations);
    this.endTime = this.latestTime();
  }

  @Override
  public void startAnimation(ArrayList<IShape> shapes, ArrayList<IAnimation> animations) {
    if (shapes == null) {
      throw new IllegalArgumentException("Null shapes.");
    }
    if (animations == null) {
      throw new IllegalArgumentException("Null animations.");
    }
    this.checkShapes(shapes);
    this.shapes = shapes;
    this.checkAnimations(animations);
    this.animations = animations;
  }

  //* Stopped method from incrementing time as well.
  @Override
  public void performAnimations() {
    for (IAnimation a : animations) {
      if (this.time >= a.getStartTime() && this.time < a.getEndTime()) {
        for (IShape s : shapes) {
          if (s.getName().equals(a.getShapeName())) {
            a.animate(s, this.time);
          }
        }
      }
    }
  }

  @Override
  public void addShape(IShape s) {
    for (IShape shape : shapes) {
      if (s.getName().equals(shape.getName())) {
        throw new IllegalArgumentException("Shape already added.");
      }
    }
    //* Modification added to account for change in our endtime implementation
    if (s.getEndTime() > this.endTime) {
      this.endTime = s.getEndTime();
    }
    shapes.add(s);
  }

  @Override
  public void addAnimation(IAnimation a) {
    for (int i = 0; i < this.animations.size(); i += 1) {
      if (a.getType() == animations.get(i).getType()
              && a.haveSameShape(animations.get(i))
              && a.overlapTimes(animations.get(i))) {
        throw new IllegalArgumentException("Can't add conflicting animation");
      }
    }
    animations.add(a);
  }

  @Override
  public boolean isOver() {
    return this.time >= this.endTime;
  }

  @Override
  public ArrayList<IAnimation> getAnimations() {
    ArrayList<IAnimation> clonedList = new ArrayList<IAnimation>();
    for (IAnimation a : this.animations) {
      IAnimation copy = a.copy();
      clonedList.add(copy);
    }
    return clonedList;
  }

  @Override
  public ArrayList<IShape> getShapes() {
    ArrayList<IShape> clonedList = new ArrayList<IShape>();
    for (IShape s : this.shapes) {
      IShape copy = s.copy();
      clonedList.add(copy);
    }
    return clonedList;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public void tickTime() {
    this.time += 1;
  }

  @Override
  public void setTime(int time) {
    this.time = time;
  }

  @Override
  public void setShapes(ArrayList<IShape> shapes) {
    this.shapes = new ArrayList<IShape>();
    for (IShape s : shapes) {
      this.shapes.add(s.copy());
    }
  }

  // Checks to make sure that the given shapes are valid.
  private void checkShapes(ArrayList<IShape> shapes) {
    if (shapes == null) {
      throw new IllegalArgumentException("Null shapes.");
    }
    for (int i = 0; i < shapes.size() - 1; i += 1) {
      for (int j = i + 1; j < shapes.size(); j += 1) {
        if (shapes.get(i).getName().equals(shapes.get(j).getName())) {
          throw new IllegalArgumentException("Illegal shapes.");
        }
      }
    }
  }

  // Checks to see if the animations are valid.
  private void checkAnimations(ArrayList<IAnimation> animations) {
    if (animations == null) {
      throw new IllegalArgumentException("Null animations.");
    }
    for (int i = 0; i < animations.size() - 2; i++) {
      for (int j = i + 1; j < animations.size() - 1; j++) {
        if (animations.get(i).getType() == animations.get(j).getType()
                && animations.get(i).haveSameShape(animations.get(j))
                && animations.get(i).overlapTimes(animations.get(j))) {
          throw new IllegalArgumentException("Conflicting animation types.");
        }
      }
    }
  }

  private int latestTime() {
    int time = 0;
    for (int i = 0; i < this.shapes.size(); i++) {
      if (shapes.get(i).getEndTime() > time) {
        time = shapes.get(i).getEndTime();
      }
    }
    return time;
  }

  public static final class Builder implements TweenModelBuilder<IAnimationModel> {

    protected ArrayList<IShape> shapes = new ArrayList<IShape>();
    protected ArrayList<IAnimation> animations = new ArrayList<IAnimation>();

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(
            String name, float cx, float cy, float xRadius, float yRadius, float red, float green,
             float blue, int startOfLife, int endOfLife) {
      shapes.add(new Oval(new Coordinate((int) cx, (int) cy), new Color(red, green, blue),
              name, startOfLife, endOfLife, (int) xRadius, (int) yRadius));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(
            String name, float lx, float ly, float width, float height, float red, float green,
             float blue, int startOfLife, int endOfLife) {
      shapes.add(new Rectangle(new Coordinate((int) lx, (int) ly), new Color(red, green, blue),
              name, startOfLife, endOfLife, (int) width, (int) height));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(
            String name, float moveFromX, float moveFromY, float moveToX, float moveToY,
            int startTime, int endTime) {
      animations.add(new Move(name, startTime, endTime, new Coordinate((int) moveFromX,
              (int) moveFromY), new Coordinate((int) moveToX, (int) moveToY)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(
            String name, float oldR, float oldG, float oldB, float newR, float newG, float newB,
            int startTime, int endTime) {
      animations.add(new ChangeColor(name, startTime, endTime,
              new Color(oldR, oldG, oldB), new Color(newR, newG, newB)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(
            String name, float fromSx, float fromSy, float toSx, float toSy, int startTime,
            int endTime) {
      animations.add(new Scale(name, startTime, endTime,
              (int) fromSx, (int) fromSy, (int) toSx, (int) toSy));
      return this;
    }

    @Override
    public IAnimationModel build() {
      return new SimpleAnimationModel(shapes, animations);
    }
  }

}
