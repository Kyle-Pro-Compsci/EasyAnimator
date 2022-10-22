package cs3500.animator.controller;

/**
 * The interface for an AnimatorController, defining the methods it uses to control an
 * AnimatorHybridView. It also contains tertiary control over AnimatorSVGView and AnimatorTextView
 * using the exportText and exportSVG methods.
 */
public interface IAnimatorController {

  /**
   * Refreshes the animation by increasing the time of the model,
   * performing any necessary animations, and telling the hybrid view to refresh.
   */
  void refresh();

  /**
   * Starts the timer in the controller to start/resume the animation.
   */
  void start();

  /**
   * Pauses the timer in the controller to pause the animation.
   */
  void pause();

  /**
   * Resets the animation to what it would be at time 0.
   */
  void restart();

  /**
   * Toggles whether or not the animation should be looping.
   */
  void toggleLooping();

  /**
   * Increases the speed of the animation by 1 frame per second.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation by 1 frame per second until it reaches 0 frames per
   * second, at which it stops. Calling increaseSpeed() will then raise the tick rate back to 1
   * frame per second.
   */
  void decreaseSpeed();

  /**
   * Exports a new .txt file using the AnimatorTextView's view, with this controller's model.
   */
  void exportText();

  /**
   * Exports a new .svg file using the AnimatorSVGView's view, using only the selected shapes
   * within this controller's model.
   */
  void exportSVG();

}
