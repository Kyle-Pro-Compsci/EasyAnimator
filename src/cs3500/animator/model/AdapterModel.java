package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.animations.IAnimation;
import cs3500.animator.provider.model.AnimationOperation;
import cs3500.animator.provider.model.shape.Shape;
import cs3500.animator.provider.model.transition.Transition;
import cs3500.shapes.AdapterShape;
import cs3500.shapes.IShape;

public class AdapterModel implements AnimationOperation<Shape> {
  IAnimationModel adaptee;

  public AdapterModel(IAnimationModel adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public <R, U> void changeAnimation(String name, Transition<R, U> transition) {

  }

  @Override
  public void addAnimation(Shape shape) {

  }

  @Override
  public List<Shape> getT(int time) {
    return null;
  }

  @Override
  public Shape getT(String name) {
    List<Shape> shapes = this.getAllT();
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    return null;
  }

  @Override
  public List<Shape> getAllT() {
    ArrayList<IShape> oldShapes = adaptee.getShapes();
    ArrayList<Shape> newShapes = new ArrayList<Shape>();
    for (IShape s : oldShapes) {
      Shape newShape = new AdapterShape(s);
      newShapes.add(newShape);
    }

    return newShapes;
  }

  @Override
  public int getStart() {
    return 1;
  }

  @Override
  public int getEnd() {
    return adaptee.getEndTime();
  }
}
