package cs3500.animator.provider.view;

import java.util.List;
import java.util.Objects;

import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents an SVG formatted view of the animation in the given tempo.
 */
public class SVGView extends AbstractView {

  /**
   * Creates a way of formatting the animation in the SVG format.
   *
   * @param output represents where the file is to be written to.
   */
  public SVGView(Appendable output, int tempo) {
    super(output, tempo);
  }

  /**
   * Creates an SVG with no output path.
   */
  public SVGView() {
    super();
  }

  @Override
  public void display() {
    int start = 0;
    int end = 0;
    boolean looping = false;
    for (Shape s : l) {
      start = Math.min(s.getStart(), start);
      end = Math.max(s.getEnd(), end);
      looping = looping || s.isLooping();
    }

    String s = "<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">" + "\n";
    if (looping) {
      s += String.format("<rect>\n <animate id=\"base\" begin=\"0;base.end\" dur=\"%sms\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" + "</rect>\n",
              (int) (1000f / tempo * (end - start)));
    }

    for (Shape shape : l) {
      s += shape.getSVG("ms", tempo) + "\n";
    }

    this.showMessage(s + "</svg>");
  }

  @Override
  public void refresh(List<Shape> l) {
    Objects.requireNonNull(l);
    if (l.contains(null)) {
      throw new IllegalArgumentException("No shapes in the list can be null.");
    }

    this.l = l;
  }
}
