package screens;

import gui.BlurredBackground;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.described.Stage;
import visual.statik.sampled.Content;

/**
 * 
 * @author joselynetran
 *
 */
public class MainScreenStage extends Stage
{

  protected VisualizationView view;
  protected ResourceFinder rf;

  /**
   * 
   * @param width
   * @param height
   * @param metronome
   * @param rf
   */
  public MainScreenStage(final int width, final int height, final int metronome,
      final ResourceFinder rf)
  {
    super(metronome);
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
