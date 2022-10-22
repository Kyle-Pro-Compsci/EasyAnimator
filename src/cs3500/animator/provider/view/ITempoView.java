package cs3500.animator.provider.view;

import java.io.IOException;

/**
 * Represents a view for an animation with an output and a tempo.
 */
public interface ITempoView extends View {

  /**
   * Sets the output path to the given path.
   *
   * @param output the new output path.
   */
  void setOutput(String output);

  /**
   * Sets the output path to the given path.
   *
   * @param output the new output path.
   */
  void setOutput(Appendable output);

  /**
   * Sets the tempo of the view to the given tempo.
   *
   * @param tempo the tempo to set to.
   */
  void setTempo(int tempo);

  /**
   * Gets the output of the text at the given location.
   *
   * @return the string output.
   */
  String getOutput() throws IOException;

}
