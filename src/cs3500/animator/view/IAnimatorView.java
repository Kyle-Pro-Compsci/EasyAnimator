package cs3500.animator.view;

/**
 * The interface for a general animator view.
 */
public interface IAnimatorView {

  //* Added appendable as an argument to enable our change of a proper file output.
  /**
   * Enscribes the text of an animation model to the console.
   */
  void view(Appendable ap);
}
