package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.shapes.IShape;


/**
 * Factory class for the Animator views.
 */
public class AnimatorViewCreator {

  /**
   * Returns a new IAnimatorView determined by the given input.
   * @param viewType What type of IAnimatorView to return.
   * @return The requested IAnimatorView.
   * @throws IllegalArgumentException Thrown if an invalid String is given.
   */
  public static IAnimatorView create(String viewType) throws IllegalArgumentException {
    switch (viewType) {
      case "text":
        return new AnimatorTextView(new SimpleAnimationModel(), 24);
      case "visual": //* Altered creator to adjust to refactored view class
        return new AnimatorVisualView(new SimpleAnimationModel(), 24);
      case "svg":
        return new AnimatorSVGView(new SimpleAnimationModel(), 24, true);
      case "interactive":
        return new AnimatorHybridView(new ArrayList<IShape>(), 1);
      default:
        throw new IllegalArgumentException("Invalid view type given");
    }
  }

  /**
   * Returns a new IAnimatorView determined by the given input.
   * This version also sets the model and speed to the given inputs.
   * @param model What model to create the view with.
   * @param viewType What type of IAnimatorView to return.
   * @param speed What speed to set the view to.
   * @return The requested IAnimatorView.
   * @throws IllegalArgumentException Thrown if an invalid String is given.
   */
  public static IAnimatorView create(IAnimationModel model, String viewType, int speed)
          throws IllegalArgumentException {
    switch (viewType) {
      case "text":
        return new AnimatorTextView(model, speed);
      case "visual":
        return new AnimatorVisualView(model, speed);
      case "svg":
        return new AnimatorSVGView(model, speed, true);
      case "interactive":
        return new AnimatorHybridView(model.getShapes(), model.getTime());
      default:
        throw new IllegalArgumentException("Invalid view type given");
    }
  }
}
