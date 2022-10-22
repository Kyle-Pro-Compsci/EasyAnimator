package cs3500.animator.provider.model.transition;

/**
 * Represents a shift from one state to the next.
 *
 * @param <R> represents the output of the state.
 * @param <T> represents what this transition is to work with.
 */
public interface Transition<T, R> extends Comparable {

  /**
   * Modifies T in some way.
   *
   * @param t the t to be modified.
   */
  void change(T t);

  /**
   * Gets the state of this transition.
   *
   * @return this state's transition.
   */
  R getState();

  /**
   * Getter for the start of a transition.
   *
   * @return the start of the transition.
   */
  int getStart();

  /**
   * Getter for the end of a transition.
   *
   * @return the end of a transition.
   */
  int getEnd();

  /**
   * Added 10/30. Produces a formatted SVG string for this transition.
   *
   * @param unit represents the unit of time.
   * @param tempo represents the speed of the object per unit.
   * @param type represents the type of shape this is.
   * @return the appropriate SVG representation of this transition.
   */
  String getSVG(String unit, int tempo, String type, boolean looping);
}
