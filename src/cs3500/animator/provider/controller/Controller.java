package cs3500.animator.provider.controller;

import cs3500.animator.provider.view.ITempoView;
import cs3500.animator.provider.view.IHybridView;

/**
 * Interface to represent a controller for an Animation.
 */
public interface Controller {

  /**
   * Accepts the given view to specify the appropriate controller view relationship.
   * @param v the view to control.
   */
  void accept(ITempoView v);

  /**
   * Accepts the given view to specify the appropriate controller view relationship. Changed the
   * parameter here since the IVisualView was deleted 11/12.
   * @param v the view to control.
   */
  void accept(IHybridView v);

  /**
   * Starts the animation.
   */
  void start();

  /**
   * Display the gui and initialize the settings.
   */
  void display();

}
