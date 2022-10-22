package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Set;

import cs3500.animator.provider.controller.ButtonType;
import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents a view that visually displays an animation. 11/17 - Renamed interface to be
 * IHybridView to better represent what classes can implement it.
 */
public interface IHybridView extends View {

  /**
   * Sets the actionListener for the views various buttons.
   *
   * @param a the actionListener to set to.
   */
  void setActionListener(ActionListener a);

  /**
   * Add the given string to the log of actions that have taken place.
   * @param s the string to be added to the log.
   */
  void addToLog(String s);

  /**
   * Switch the given type of button to its counter-part (ie. loop to not loop).
   * @param type the type of button to be switched
   */
  void switchButtonState(ButtonType type);

  /**
   * Uses the given appendable to log what is going on in the animation.
   *
   * @param a the appendable to be used.
   */
  void setAppendable(Appendable a);

  /**
   * Starts the exporting process, and asks the user for where to save it to.
   */
  void beginExport(List<Shape> s, int tempo);

  /**
   * Initialize the menu that will allow for the selecting of shapes.
   *
   * @param l the list of shapes to be displayed as checkboxes.
   * @param set the shapes that have already been selected.
   * @param i represents the item listener that will know what to do when the boxes are pressed.
   */
  void select(ItemListener i, List<Shape> l, Set<Shape> set);
}
