package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.animations.IAnimation;
import cs3500.shapes.IShape;

import cs3500.animator.model.IAnimationModel;

/**
 * Provides a view for a model's animation with a String description of the shapes contained
 * within the model and all of their transformations.
 */
public class AnimatorTextView implements IAnimatorView {

  protected int tickrate;
  protected ArrayList<IAnimation> animations;
  protected ArrayList<IShape> shapes;

  /**
   * Constructor for AnimatorTextView. It grabs the animations and shapes from the
   * given model, and sets the tickrate to the given tickrate.
   * @param model The IAnimationModel containing the needed animations and shapes.
   * @param tickrate The tickrate, needed to calculate when the animations occur.
   */
  public AnimatorTextView(IAnimationModel model, int tickrate) {
    animations = model.getAnimations();
    shapes = model.getShapes();
    this.tickrate = tickrate;
  }

  @Override
  public void view(Appendable ap) {
    try {
      ap.append(this.animationDescription(this.tickrate));
    } catch (IOException e) {
      return;
    }
  }


  //* Moved from model to the view.
  /**
   * Determines the description for the animation,
   * which should say, in words, what the animation is doing.
   *
   * @param tickrate The tick rate for the animation description.
   * @return The string description for the animation.
   */
  private String animationDescription(int tickrate) {
    String result = "Shapes:\n";
    for (IShape s : shapes) {
      result += s.description(tickrate);
      result += "\n\n";
    }
    for (int i = 0; i < animations.size(); i += 1) {
      result += animations.get(i).description(tickrate);
      if (i < animations.size() - 1) {
        result += "\n";
      }
    }
    return result;
  }
}
