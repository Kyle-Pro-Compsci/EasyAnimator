package cs3500.shapes;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.provider.model.shape.Shape;
import cs3500.animator.provider.model.transition.Transition;

public class AdapterShape implements Shape {
  IShape adaptee;
  List<Transition> transitions;

  public AdapterShape(IShape adaptee) {
    this.adaptee = adaptee;
  }



  @Override
  public String getName() {
    return adaptee.getName();
  }

  @Override
  public int getStart() {
    return adaptee.getStartTime();
  }

  @Override
  public int getEnd() {
    return adaptee.getEndTime();
  }

  @Override
  public List<Transition> getListTransitions() {
    return null;
  }

  @Override
  public void addTransition(Transition t) {

  }

  @Override
  public Shape getInBetween(int time) {
    return null;
  }

  @Override
  public HashMap<Integer, List<String>> getTransition() {
    return null;
  }

  @Override
  public void paintComponent(Graphics g) {

  }

  @Override
  public String getSVG(String unit, int tempo) {
    return null;
  }

  @Override
  public void setLooping(boolean b) {

  }

  @Override
  public boolean isLooping() {
    return false;
  }

  @Override
  public int compareTo(Shape o) {
    if (this.getName().equals(o.getName())) {
      return 1;
    }
    return 0;
  }
}
