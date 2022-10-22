package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.Timer;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.AnimatorTextView;
import cs3500.animator.view.IAnimatorHybridView;
import cs3500.animator.view.IAnimatorView;
import cs3500.shapes.IShape;

/**
 * The controller that interacts with an IAnimatorHybridView using data from an IAnimationModel.
 * Allows for media-player-esque control over the visual view for an animation.
 */
public class AnimatorController implements IAnimatorController, ActionListener {

  private IAnimatorHybridView view;
  private IAnimationModel model;
  private ArrayList<IShape> startingShapes;
  private int tickrate;
  private boolean running;
  private boolean looping;
  private Timer timer;

  /**
   * Constructs an Animator Controller, which should have control over a hybrid view.
   * @param view The IAnimatorHybridView to control.
   * @param model The model to be used to view.
   * @param tickrate The starting tick rate of the view.
   */
  public AnimatorController(IAnimatorHybridView view, IAnimationModel model, int tickrate) {

    this.model = model;
    this.tickrate = tickrate;
    this.view = view;
    this.running = false;
    this.startingShapes = model.getShapes();
    this.view.setListener(this);
    ActionListener taskPerformer = evt -> refresh();
    this.timer = new Timer(1000 / tickrate, taskPerformer);
    this.view.setTickrateLabel(tickrate);
    this.view.view(System.out);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Play/Pause":
        if (running) {
          this.pause();
          running = false;
        }
        else {
          this.start();
          running = true;
        }
        this.view.playPressed();
        break;
      case "Restart":
        this.restart();
        break;
      case "Increase tickrate":
        this.increaseSpeed();
        this.view.setTickrateLabel(tickrate);
        break;
      case "Decrease tickrate":
        this.decreaseSpeed();
        this.view.setTickrateLabel(tickrate);
        break;
      case "Toggle loop":
        this.toggleLooping();
        this.view.loopTogglePressed();
        break;
      case "check box":
        view.refresh(model.getShapes(), model.getTime());
        break;
      case "SVG":
        this.exportSVG();
        break;
      case "Text":
        this.exportText();
        break;
      default:
        //do nothing
        break;
    }
  }

  @Override
  public void refresh() {
    if (this.model.getTime() >= this.model.getEndTime() && looping) {
      this.restart();
      return;
    }

    this.model.tickTime();
    this.model.performAnimations();
    this.view.refresh(this.model.getShapes(), this.model.getTime());
  }

  @Override
  public void start() {
    timer.start();
  }

  @Override
  public void pause() {
    timer.stop();
  }

  @Override
  public void restart() {
    this.model.setTime(1);
    this.model.setShapes(startingShapes);
    this.view.refresh(this.model.getShapes(), this.model.getTime());
  }

  @Override
  public void toggleLooping() {
    this.looping = !looping;
  }

  @Override
  public void increaseSpeed() {
    this.tickrate += 1;
    this.timer.setDelay(1000 / tickrate);
  }

  @Override
  public void decreaseSpeed() {
    if (this.tickrate > 1) {
      this.tickrate -= 1;
      this.timer.setDelay(1000 / tickrate);
    }
  }

  @Override
  public void exportText() {
    String outputFile = this.view.getOutputField();
    if (outputFile.equals("")) {
      this.view.setOutputResponse("Please enter a file name");
      return;
    }
    else if (outputFile.length() < 4 ||
            !outputFile.substring(outputFile.length() - 4).equals(".txt")) {
      outputFile += ".txt";
    }
    IAnimatorView textView = new AnimatorTextView(this.model, this.tickrate);
    this.exportView(outputFile, textView);
  }

  @Override
  public void exportSVG() {
    String outputFile = this.view.getOutputField();
    if (outputFile.equals("")) {
      this.view.setOutputResponse("Please enter a file name");
      return;
    }
    else if (outputFile.length() < 4 ||
            !outputFile.substring(outputFile.length() - 4).equals(".svg")) {
      outputFile += ".svg";
    }

    IAnimationModel filteredModel = new SimpleAnimationModel(this.view.getSelectedShapes(),
            this.model.getAnimations());
    IAnimatorView svgView = new AnimatorSVGView(filteredModel, this.tickrate, this.looping);
    this.exportView(outputFile, svgView);
  }

  //Writes to the given outputFile based on the given view.
  private void exportView(String outputFile, IAnimatorView view) {
    Appendable ap;
    try {
      FileOutputStream output = new FileOutputStream(outputFile);
      ap = new PrintStream(output);
      view.view(ap);
      this.view.setOutputResponse(outputFile + " created.");
    } catch (FileNotFoundException e) {
      return;
    }
  }
}
