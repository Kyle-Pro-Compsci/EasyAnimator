package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs3500.shapes.IShape;

/**
 * The interface for a visual animator view.
 */
public interface IAnimatorHybridView extends IAnimatorView {

  /**
   * Refreshes the visual view to draw a new set of given shapes based on a given time frame.
   * @param shapes The new set of shapes to draw.
   * @param time The current time frame to draw at.
   */
  void refresh(ArrayList<IShape> shapes, int time);

  /**
   * Sets the ActionListeners of the interactive objects within this view
   * to that of the controller's which is given.
   * @param buttons The controller's ActionListener.
   */
  void setListener(ActionListener buttons);

  /**
   * Modifies the text of the play button to alternate between Play and Pause.
   */
  void playPressed();

  /**
   * Modifies the text of the loop toggle button to alternate between loop and stop looping.
   */
  void loopTogglePressed();

  /**
   * Sets the JLabel tickrate to the given int.
   * @param tickrate The int to use for the label.
   */
  void setTickrateLabel(int tickrate);

  /**
   * Returns the String contained within the JTextField.
   * @return The String within JTextField.
   */
  String getOutputField();

  /**
   * Sets the file response JLabel to the given String.
   * Used to indicate that a file has been created.
   * @param response The String to set the JLabel to.
   */
  void setOutputResponse(String response);

  /**
   * Returns copies of the shapes that are selected to appear on screen.
   * @return An arraylist of IShape that are to be shown in an animation.
   */
  ArrayList<IShape> getSelectedShapes();
}
