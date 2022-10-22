package cs3500.animator.view;

import java.io.FileNotFoundException;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.util.AnimationFileReader;

import cs3500.animator.model.SimpleAnimationModel.Builder;

public class ViewRunner {

  /**
   * Main method that runs buildings.txt in a hybrid view with a default tickrate of 24,
   * for unofficial testing.
   * @param args List of Strings, unused.
   */
  public static void main(String[] args) {
    /*
    IAnimatorView textView = new AnimatorTextView(model, 2);
    textView.view();

    IAnimatorView svgView = new AnimatorSVGView(model, 10);
    svgView.view();

    IAnimatorHybridView visualView = new AnimatorHybridView(model, 24);
    visualView.view();*/
    AnimationFileReader reader = new AnimationFileReader();
    IAnimationModel m1 = new SimpleAnimationModel();
    try {
      m1 = reader.readFile("buildings.txt", new Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    /*
    IAnimatorView svgView = new AnimatorSVGView(m1, 20, true);
    svgView.view();

    IAnimatorView textView = new AnimatorTextView(m1, 20);
    textView.view(System.out);

    IAnimatorView visualView = new AnimatorVisualView(m1, 20);
    visualView.view();*/

    IAnimatorHybridView hybridView = new AnimatorHybridView(m1.getShapes(), m1.getTime());
    IAnimatorController controller = new AnimatorController(hybridView, m1, 24);
  }

}
