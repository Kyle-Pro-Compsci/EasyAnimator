package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs3500.shapes.IShape;
import cs3500.shapes.ShapeType;

/**
 * Provides a view for a model's animation by providing a visual representation of
 * the model's shapes in each frame of the animation, as well as the front-end visual interface
 * that allows for media-player interaction with the animation.
 */
public class AnimatorHybridView extends JFrame implements IAnimatorHybridView {


  private AnimatorPanel animationPanel;
  private ArrayList<JCheckBox> checkboxList;
  ArrayList<IShape> shapesToDraw = new ArrayList<IShape>();

  private JButton playButton;
  private JButton restartButton;
  private JButton increaseButton;
  private JButton decreaseButton;
  private JButton loopButton;
  private JTextField outputFileName;
  private JButton svgButton;
  private JButton textButton;

  private JLabel tickrate;
  private JLabel fileResponse;

  /**
   * The constructor for AnimatorHybridView. It initializes and sets the attributes of all the
   * Java Swing components that make up the interface. Sets the shapes and the time of this
   * View to the given shapes and time.
   * @param shapes The shapes to render.
   * @param time The current timeframe for the animation.
   */
  public AnimatorHybridView(ArrayList<IShape> shapes, int time) {
    super();

    JPanel containerPanel;
    JPanel buttonPanel;
    JPanel shapePanel;


    this.setTitle("EasyAnimator");
    this.setSize(1200, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    //initialize buttons
    this.playButton = new JButton("Play");
    this.restartButton = new JButton("Restart");
    this.increaseButton = new JButton("Increase tickrate");
    this.decreaseButton = new JButton("Decrease tickrate");
    this.loopButton = new JButton("Loop");
    this.svgButton = new JButton("SVG");
    this.textButton = new JButton("Text");

    //set action commands for buttons
    this.playButton.setActionCommand("Play/Pause");
    this.restartButton.setActionCommand("Restart");
    this.increaseButton.setActionCommand("Increase tickrate");
    this.decreaseButton.setActionCommand("Decrease tickrate");
    this.loopButton.setActionCommand("Toggle loop");
    this.svgButton.setActionCommand("SVG");
    this.textButton.setActionCommand("Text");

    //customize panels
    this.animationPanel = new AnimatorPanel(shapes, time);
    this.repaint();

    buttonPanel = new JPanel();
    shapePanel = new JPanel();
    containerPanel = new JPanel();
    shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.PAGE_AXIS));
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setBorder(BorderFactory.createBevelBorder(1));
    shapePanel.setBorder(BorderFactory.createBevelBorder(1));

    // Text
    this.tickrate = new JLabel("Tickrate: ");
    this.outputFileName = new JTextField(10);
    this.outputFileName.setEditable(true);
    this.fileResponse = new JLabel("");

    // add buttons to buttonPanel
    buttonPanel.add(playButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(decreaseButton);
    buttonPanel.add(tickrate);
    buttonPanel.add(increaseButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(svgButton);
    buttonPanel.add(textButton);
    buttonPanel.add(new JLabel("Output file:"));
    buttonPanel.add(outputFileName);
    buttonPanel.add(fileResponse);

    //fill out the shapePanel with checkboxes
    this.checkboxList = new ArrayList<JCheckBox>();

    for (IShape shape : shapes) {
      String shapeType;
      if (shape.getType() == ShapeType.RECTANGLE) {
        shapeType = "Rectangle";
      }
      else {
        shapeType = "Oval";
      }
      String shapeText = shape.getName() + ", " + shapeType;
      JCheckBox cb = new JCheckBox(shapeText);
      cb.setActionCommand("check box");
      shapePanel.add(cb);
      checkboxList.add(cb);
    }

    for (JCheckBox checkBox : checkboxList) {
      checkBox.setSelected(true);
    }

    // Add 2 panels to scrollpanes, and add all to the overall containerPanel
    JScrollPane animationScrollPane = new JScrollPane(animationPanel);
    JScrollPane shapeScrollPane = new JScrollPane(shapePanel);
    containerPanel.setLayout(new BorderLayout());
    containerPanel.add(animationScrollPane, BorderLayout.CENTER);
    containerPanel.add(shapeScrollPane, BorderLayout.EAST);
    containerPanel.add(buttonPanel, BorderLayout.SOUTH);

    this.add(containerPanel, BorderLayout.CENTER);

  }


  @Override
  public void view(Appendable ap) {
    this.setVisible(true);
  }

  @Override
  public void refresh(ArrayList<IShape> shapes, int time) {

    shapesToDraw = new ArrayList<IShape>();

    for (int i = 0; i < this.checkboxList.size(); i++) {
      if (checkboxList.get(i).isSelected()) {
        shapesToDraw.add(shapes.get(i));
      }
    }

    this.animationPanel.setShapes(shapesToDraw, time);
    this.repaint();
  }

  @Override
  public void setListener(ActionListener buttons) {
    this.playButton.addActionListener(buttons);
    this.restartButton.addActionListener(buttons);
    this.increaseButton.addActionListener(buttons);
    this.decreaseButton.addActionListener(buttons);
    this.loopButton.addActionListener(buttons);
    this.svgButton.addActionListener(buttons);
    this.textButton.addActionListener(buttons);
    for (JCheckBox cb : this.checkboxList) {
      cb.addActionListener(buttons);
    }
  }

  @Override
  public void playPressed() {
    if (this.playButton.getText().equals("Play")) {
      this.playButton.setText("Pause");
    }
    else {
      this.playButton.setText("Play");
    }
  }

  @Override
  public void loopTogglePressed() {
    if (this.loopButton.getText().equals("Loop")) {
      this.loopButton.setText("Stop looping");
      this.svgButton.setText("Looping SVG");
    }
    else {
      this.loopButton.setText("Loop");
      this.svgButton.setText("SVG");
    }
  }

  @Override
  public void setTickrateLabel(int tickrate) {
    this.tickrate.setText("tickrate: " + tickrate);
  }

  @Override
  public String getOutputField() {
    return this.outputFileName.getText();
  }

  @Override
  public void setOutputResponse(String response) {
    this.fileResponse.setText(response);
  }

  @Override
  public ArrayList<IShape> getSelectedShapes() {
    ArrayList<IShape> result = new ArrayList<IShape>();
    for (IShape s : this.shapesToDraw) {
      result.add(s.copy());
    }
    return result;
  }
}
