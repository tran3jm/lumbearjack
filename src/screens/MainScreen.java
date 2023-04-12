package screens;

import gui.BlurredBackground;
import io.ResourceFinder;
import visual.Visualization;
import visual.VisualizationView;
import visual.statik.sampled.Content;

/**
 * 
 * @author joselynetran
 *
 */
public class MainScreen extends Visualization
{

  protected VisualizationView view;
  protected ResourceFinder rf;

  /**
   * 
   * @param width
   * @param height
   * @param rf
   */
  public MainScreen(final int width, final int height, final ResourceFinder rf)
  {
    this.view = this.getView();
    this.view.setSize(width, height);
    this.view.setBounds(0, 0, width, height);
    this.rf = rf;
    init();
  }

  /**
   * 
   */
  public void init()
  {
    // creating backgroundphoto as content
    // find a better efficient way to do this
    Content bp = BlurredBackground.blurredBackground();
    bp.setLocation(-250, -450);
    this.add(bp);
  }

}
