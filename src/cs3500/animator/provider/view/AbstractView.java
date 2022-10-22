package cs3500.animator.provider.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents a general view for an animation with an output and a tempo.
 */
public abstract class AbstractView implements ITempoView {
  protected Appendable output;
  protected List<Shape> l;
  protected int tempo;

  /**
   * Creates a general view with the given output and tempo.
   *
   * @param output the output for this view.
   * @param tempo  the time unit to format with.
   */
  public AbstractView(Appendable output, int tempo) {
    if (tempo < 1) {
      throw new IllegalArgumentException("Tempo cannot be less than 1");
    }

    this.output = output;
    this.l = new ArrayList<>();
    this.tempo = tempo;
  }

  /**
   * Creates a general view with default output and tempo of 1.
   */
  public AbstractView() {
    this(System.out, 1);
  }

  @Override
  public void accept(Controller c) {
    Objects.requireNonNull(c, "Given controller cannot be null.");
    c.accept(this);
  }

  @Override
  public void showMessage(String s) {
    Objects.requireNonNull(s, "The message cannot be null.");
    try {
      this.output.append(s);
    } catch (IOException i) {
      throw new IllegalArgumentException("Bad file location.");
    }
  }

  @Override
  public String getOutput() {
    return this.output.toString();
  }

  @Override
  public void setOutput(Appendable s) {
    Objects.requireNonNull(s);
    this.output = s;
  }

  @Override
  public void setOutput(String s) {
    Objects.requireNonNull(s);
    try {
      this.output = new PrintStream(s);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid location.");
    }
  }

  @Override
  public void setTempo(int tempo) {
    if (tempo < 1) {
      throw new IllegalArgumentException("Tempo cannot be less than 1");
    }

    this.tempo = tempo;
  }
}
