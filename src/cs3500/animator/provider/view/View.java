package cs3500.animator.provider.view;

import java.util.List;

import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents a way to animate shapes.
 */
public interface View {

  /**
   * Accepts the given controller.
   *
   * @param c the controller to be accepted.
   */
  void accept(Controller c);

  /**
   * Show the most recent updated version of the view.
   */
  void display();

  /**
   * Shows the given message.
   */
  void showMessage(String message);

  /**
   * Update the components of the view.
   *
   * @param l the list of shapes of the animation.
   */
  void refresh(List<Shape> l);
}
