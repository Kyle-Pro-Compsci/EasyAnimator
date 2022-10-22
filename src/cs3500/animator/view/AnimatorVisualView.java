package cs3500.animator.view;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import cs3500.animator.model.IAnimationModel;

/**
 * Provides a view for a model's animation by providing a visual representation of the model's
 * shapes in each frame of the animation.
 */
public class AnimatorVisualView extends JFrame implements IAnimatorVisualView {


  private AnimatorPanel animationPanel;

  private IAnimationModel model;
  private int tickrate;

  /**
   * Constructor an AnimatorVisualView that animates the given model at the given tickrate.
   *
   * @param model    The IAnimationModel to animate, using its shapes and animations.
   * @param tickrate The speed at which to play the animation.
   */
  public AnimatorVisualView(IAnimationModel model, int tickrate) {
    super();


    this.setTitle("EasyAnimator");
    this.setSize(1200, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.model = model;
    this.tickrate = tickrate;

    this.animationPanel = new AnimatorPanel(model.getShapes(), model.getTime());
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);
  }


  @Override
  public void view(Appendable ap) {
    this.setVisible(true);
    this.startAnimation();
  }

  @Override
  public void refresh() {
    this.model.tickTime();
    this.model.performAnimations();
    this.animationPanel.setShapes(this.model.getShapes(), this.model.getTime());
    //this.revalidate();
    this.repaint();
  }

  /**
   * Runs the animation by calling refresh() on each tick of the timer, the speed of which is based
   * on the tickrate.
   */
  private void startAnimation() {

    ActionListener taskPerformer = evt -> refresh();

    Timer timer = new Timer(1000 / tickrate, taskPerformer);
    timer.start();
  }
}