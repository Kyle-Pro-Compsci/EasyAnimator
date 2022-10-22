package cs3500.animator.provider.view;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents a visual view of the animation using swing.
 */
public class VisualView extends JFrame implements View {
  protected List<Shape> l;

  /**
   * Creates and initializes a visual animation view.
   */
  public VisualView() {
    super("Animation");
    JPanel shapePanel;
    JScrollPane scrollPane;
    this.l = new ArrayList<>();

    // set the parameters for this view
    setSize(800, 800);
    setLocation(400, 400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    shapePanel = new ShapePanel();

    scrollPane = new JScrollPane(shapePanel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Helper class to be used to display the shapes.
   */
  private class ShapePanel extends JPanel {

    /**
     * Create a shape panel.
     */
    public ShapePanel() {
      this.setPreferredSize(new Dimension(800, 900));
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (Shape s : l) {
        s.paintComponent(g);
      }
    }
  }

  @Override
  public void accept(Controller c) {
    // do nothing for this type of view.
    return ;
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void refresh(List<Shape> l) {
    Objects.requireNonNull(l);
    if (l.contains(null)) {
      throw new IllegalArgumentException("No shapes in the list can be null.");
    }

    this.l = l;
    this.repaint();
  }

  @Override
  public void showMessage(String s) {
    JOptionPane.showMessageDialog(this, s,
            "Error", JOptionPane.ERROR_MESSAGE);
    return;
  }
}
