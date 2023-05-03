package gui;

import javax.swing.JFrame;

import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.sampled.Screen;

/**
 * Help animation that tells user how to play game.
 *
 * @author joselynetran and panamuhamad
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
   * Intializer for animation.
   */
  public void init()
  {
    this.setSize(400, 303);
    this.setLocation(200, 100);
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
