package cs3500.animator.controller;

import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.view.IHybridView;
import cs3500.animator.provider.view.ITempoView;
import cs3500.animator.provider.view.View;

public class AdapterController implements Controller {

  View v;

  @Override
  public void accept(ITempoView v) {
    this.v = v;
  }

  @Override
  public void accept(IHybridView v) {
    this.v = v;
  }

  @Override
  public void start() {

  }

  @Override
  public void display() {

  }
}
