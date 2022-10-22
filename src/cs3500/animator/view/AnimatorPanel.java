package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.shapes.IShape;

/**
 * Represents a panel that is to be put on a JFrame to draw shapes.
 */
public class AnimatorPanel extends JPanel {

  private ArrayList<IShape> shapes;
  private int time;

  /**
   * Constructs an AnimatorPanel object containing the given shapes at the given time.
   * @param shapes The shapes to be contained in this panel.
   * @param time The current time of the panel.
   */
  public AnimatorPanel(ArrayList<IShape> shapes, int time) {
    this.shapes = shapes;
    this.time = time;
    this.setPreferredSize(new Dimension(1000, 800));
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    for (IShape shape : shapes) {
      if (time < shape.getStartTime() || time > shape.getEndTime()) {
        continue;
      }
      g2d.setColor(shape.getColor());
      switch (shape.getType()) {
        case OVAL:
          g2d.fillOval(shape.getCoordinate().getX() - (shape.getWidth() / 2),
                  shape.getCoordinate().getY() - (shape.getHeight() / 2),
                  shape.getWidth() * 2, shape.getHeight() * 2);
          break;
        case RECTANGLE:
          g2d.fillRect(shape.getCoordinate().getX(), shape.getCoordinate().getY(),
                  shape.getWidth(), shape.getHeight());
          break;
        default:
          //should never reach
      }
    }
  }

  /**
   * Sets the shapes of this panel to the given shapes using the given time.
   * @param shapes The shapes to put into this panel, removes the old shapes.
   * @param time The time to set this panel to.
   */
  protected void setShapes(ArrayList<IShape> shapes, int time) {
    this.shapes = shapes;
    this.time = time;
  }
}
