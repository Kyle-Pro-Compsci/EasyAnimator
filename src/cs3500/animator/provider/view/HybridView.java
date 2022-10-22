package cs3500.animator.provider.view;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import cs3500.animator.provider.controller.ButtonType;
import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.model.shape.Shape;

/**
 * Represents a hybrid view with both SVG and visual capabilities.
 */
public class HybridView extends VisualView implements IHybridView {
  private JTextArea log;

  private JButton play;
  private JButton replay;
  private JButton selectingMode;
  private JButton loop;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private JButton export;

  private Appendable logText;

  /**
   * Creates a hybrid view.
   */
  public HybridView() {

    // Add buttons to play/exit/stop.
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.PAGE_START);

    log = new JTextArea(5, 1);
    log.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(log);
    this.add(scrollPane, BorderLayout.PAGE_END);

    // initialize the play button
    play = new JButton("Play");
    buttonPanel.add(play);

    // initialize the re-play button
    replay = new JButton("Replay");
    buttonPanel.add(replay);

    // initializes the selectingMode button
    selectingMode = new JButton("Selecting Mode");
    buttonPanel.add(selectingMode);

    // initialize the loop button
    loop = new JButton("Loop");
    buttonPanel.add(loop);

    // initialize the increaseSpeed button
    increaseSpeed = new JButton("Increase");
    buttonPanel.add(increaseSpeed);

    // initialize the decreaseSpeed button
    decreaseSpeed = new JButton("Decrease");
    buttonPanel.add(decreaseSpeed);

    // initialize the export button
    export = new JButton("Export");
    buttonPanel.add(export);

    logText = new StringBuffer();
  }

  @Override
  public void setActionListener(ActionListener a) {
    Objects.requireNonNull(a);
    play.addActionListener(a);
    replay.addActionListener(a);
    loop.addActionListener(a);
    increaseSpeed.addActionListener(a);
    decreaseSpeed.addActionListener(a);
    export.addActionListener(a);
    selectingMode.addActionListener(a);
  }

  @Override
  public void addToLog(String s) {
    Objects.requireNonNull(s, "The given message cannot be appendable.");
    try {
      logText.append(s + "\n");
      this.log.setText(logText.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid action logged.");
    }
  }

  @Override
  public void switchButtonState(ButtonType type) {
    Objects.requireNonNull(type, "The given button type cannot be null");
    switch (type) {
      case LOOP:
        this.loop.setText("Stop Looping");
        break;
      case NOTLOOP:
        this.loop.setText("Loop");
        break;
      case PLAY:
        this.play.setText("Pause");
        break;
      case PAUSE:
        this.play.setText("Play");
        break;
      default:
        throw new IllegalArgumentException("Invalid button type given.");
    }
  }

  @Override
  public void setAppendable(Appendable a) {
    Objects.requireNonNull(a, "Appendable cannot be null.");
    this.logText = a;
  }

  @Override
  public void beginExport(List<Shape> l, int tempo) {
    Objects.requireNonNull(l, "Can't export a null list of shapes.");
    JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      View temp = BuildView.create("svg");
      ITempoView svgTemp = (ITempoView) temp;
      svgTemp.setOutput(f.getAbsolutePath());
      svgTemp.setTempo(tempo);
      svgTemp.refresh(l);
      svgTemp.display();
    }
  }

  @Override
  public void select(ItemListener i, List<Shape> l, Set<Shape> shapeSet) {
    Objects.requireNonNull(i, "Given data for selection cannot be null.");
    Objects.requireNonNull(l, "Given data for selection cannot be null.");
    Objects.requireNonNull(shapeSet, "Given data for selection cannot be null.");
    if (l.contains(null) || shapeSet.contains(null)) {
      throw new NullPointerException("Illegal value.");
    }

    JPanel checkBoxPanel = new JPanel();
    checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Select the shapes to be displayed."));
    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));

    for (Shape s : l) {
      JCheckBox j = new JCheckBox(s.getName());
      j.setActionCommand(s.getName());
      j.addItemListener(i);
      if (shapeSet.contains(s)) {
        j.setSelected(true);
      } else {
        j.setSelected(false);
      }

      checkBoxPanel.add(j);
    }

    JScrollPane checkBoxPane = new JScrollPane(checkBoxPanel);
    checkBoxPane.setPreferredSize(new Dimension(200, 400));
    JOptionPane.showMessageDialog(this, checkBoxPane,
            "Select Shapes", JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void accept(Controller controller) {
    Objects.requireNonNull(controller, "Controller cannot be null.");
    controller.accept(this);
  }
}
