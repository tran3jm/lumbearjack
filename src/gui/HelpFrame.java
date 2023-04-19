package gui;

import javax.swing.JFrame;

import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.sampled.Screen;

/**
 * 
 * @author joselynetran
 *
 */
public class HelpFrame extends JFrame
{

  private static final long serialVersionUID = 1L;
  Screen screen;
  ResourceFinder rf;

  /**
   * 
   * @param rf
   */
  public HelpFrame(final ResourceFinder rf)
  {
    super();
    this.rf = rf;
    init();
  }

  /**
   * 
   */
  public void init()
  {
    Screen screen;
    this.setSize(400, 303);
    this.setResizable(false);
    SampledDynamicContentFactory sdcf;

    sdcf = new SampledDynamicContentFactory(this.rf, "game_instructions.txt", 6);
    screen = sdcf.createSampledDynamicContent();
    screen.setRepeating(true);
    VisualizationView view = screen.getView();
    view.setBounds(0, 0, 400, 303);
    this.add(screen.getView());
    screen.start();
  }
}
