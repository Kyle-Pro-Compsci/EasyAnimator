package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.AnimatorViewCreator;
import cs3500.animator.view.IAnimatorHybridView;
import cs3500.animator.view.IAnimatorView;

/**
 * Provides a command-line interaction with the Views.
 */
public final class EasyAnimator {

  /**
   * Gives a view() output and possible file output depending on the args given.
   *
   * @param args The list of arguments given by the command-line.
   */
  public static void main(String[] args) {

    StringBuilder sb = new StringBuilder();
    AnimationFileReader reader = new AnimationFileReader();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JOptionPane error = new JOptionPane();

    for (String s : args) {
      sb.append(s + " ");
    }

    Scanner sc = new Scanner(sb.toString());
    String filename = "";
    String viewType = "";
    String outputFile = "System.out";
    Appendable out = System.out;
    String speed = "1";

    while (sc.hasNext()) {
      String next = sc.next();
      switch (next) {
        case "-if":
          filename = readCommand(sc);
          break;
        case "-iv":
          viewType = readCommand(sc);
          break;
        case "-o":
          outputFile = readCommand(sc);
          break;
        case "-speed":
          speed = readCommand(sc);
          break;
        default:
          error.showMessageDialog(frame, "Invalid input");
          frame.setVisible(true);
          return;
      }
    }

    if (filename.equals("") || viewType.equals("")) {
      error.showMessageDialog(frame, "Critical argument not given.");
      frame.setVisible(true);
      return;
    }

    IAnimationModel m1;
    IAnimatorView view;

    try {
      m1 = reader.readFile(filename, new SimpleAnimationModel.Builder());
    } catch (FileNotFoundException e) {
      error.showMessageDialog(frame, "File not found.");
      frame.setVisible(true);
      return;
    }

    try {
      view = AnimatorViewCreator.create(m1, viewType, Integer.parseInt(speed));
    } catch (IllegalArgumentException e) {
      error.showMessageDialog(frame, "Illegal view type.");
      frame.setVisible(true);
      return;
    }

    if (viewType.equals("visual")) {
      view.view(out);
    }
    else if (viewType.equals("svg") || viewType.equals("text")) {
      try {
        FileOutputStream output = new FileOutputStream(outputFile);
        if (!outputFile.equals("System.out")) {
          out = new PrintStream(output);
        }
      } catch (FileNotFoundException e) {
        error.showMessageDialog(frame, "File not found.");
        frame.setVisible(true);
        return;
      }

      view.view(out);

    }
    else if (viewType.equals("interactive")) {
      IAnimatorController controller = new AnimatorController((IAnimatorHybridView)view, m1,
              Integer.parseInt(speed));
    }
    else {
      error.showMessageDialog(frame, "Illegal view type.");
      frame.setVisible(true);
      return;
    }
  }

  private static String readCommand(Scanner ap) {
    if (ap.hasNext()) {
      return ap.next();
    } else {
      throw new IllegalArgumentException("Argument not given after identifier");
    }
  }
}
