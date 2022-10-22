package cs3500.animator.provider.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import cs3500.animator.provider.model.shape.Shape;
import cs3500.animator.provider.model.transition.Transition;

/**
 * Represents a textual visualization of the view in the given tempo.
 */
public class TextView extends AbstractView {

  /**
   * Creates a textual visualization of the view with the given tempo.
   *
   * @param tempo  the time unit to format with.
   * @param output represents where the data will be displayed.
   */
  public TextView(Appendable output, int tempo) {
    super(output, tempo);
  }

  /**
   * Creates a textual visualization of the view.
   */
  public TextView() {
    super();
  }

  @Override
  public void display() {
    List<String> queue = this.createStates();
    String res = "";
    int i = 0;
    for (Shape s : l) {
      res += s.toString() + "\n" +
              String.format("Appears at %s\nDisappears at %s",
                      this.format(s.getStart()), this.format(s.getEnd()));
      if (i != l.size() - 1) {
        res += "\n\n";
      }

      if (!queue.isEmpty() && i == l.size() - 1) {
        res += "\n";
      }

      i++;
    }

    for (String s : queue) {
      res += "\n";
      res += s;
    }

    this.showMessage(res);
  }

  @Override
  public void refresh(List<Shape> l) {
    Objects.requireNonNull(l);
    if (l.contains(null)) {
      throw new IllegalArgumentException("No shapes in the list can be null.");
    }

    this.l = l;
  }

  /**
   * Creates the list of states for this animation.
   *
   * @return an ordered listing of transitions and states.
   */
  private List<String> createStates() {
    List<String> res = new ArrayList<>();
    List<HashMap<Integer, List<String>>> lohm = new ArrayList<>();
    int start = 0;
    int end = 0;

    for (Shape s : l) {
      start = Math.min(start, s.getStart());
      end = Math.max(end, s.getEnd());
      HashMap<Integer, List<String>> h = this.genShapeTransition(s);
      lohm.add(h);
    }

    for (int i = start; i < end; i++) {
      for (HashMap<Integer, List<String>> h : lohm) {
        if (h.containsKey(i)) {
          res.addAll(h.get(i));
        }
      }
    }

    return res;
  }

  /**
   * Creates a mapping of times to visual representation of transitions.
   *
   * @param s represents a shape to get transitions from.
   * @return a mapping of times to shape transitions.
   */
  private HashMap<Integer, List<String>> genShapeTransition(Shape s) {
    HashMap<Integer, List<String>> lohm = new HashMap<>();
    List<Transition> l = s.getListTransitions();
    for (Transition t : l) {
      String str = String.format("Shape %s %s %s from %s to %s",
              s.getName(), t.getState(), t.toString(),
              this.format(t.getStart()), this.format(t.getEnd()));
      if (lohm.containsKey(t.getStart())) {
        lohm.get(t.getStart()).add(str);
      } else {
        List<String> los = new ArrayList<>();
        los.add(str);
        lohm.put(t.getStart(), los);
      }
    }

    return lohm;
  }

  /**
   * Formats the given time based on the tempo.
   */
  private String format(int time) {
    return String.format("t=%.1fs", time * 1f / tempo);
  }
}
