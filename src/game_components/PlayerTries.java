package game_components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.SimpleContent;
import visual.statik.sampled.AggregateContent;
import visual.statik.sampled.Content;
import visual.statik.sampled.ImageFactory;

/**
 * We should put playertries in this class once we can
 * 
 * @author joselynetran
 *
 */
public class PlayerTries extends AggregateContent implements GameObserver
{

  private static final int TOTAL_TRIES = 3;
  private int tries;
  private ResourceFinder rf;

  private Content[] tryContent;

  public PlayerTries(ResourceFinder rf)
  {
    this.tries = TOTAL_TRIES;
    this.rf = rf;
    this.tryContent = new Content[3];

    for (int i = 0; i < 3; i++)
      this.tryContent[i] = ScaledImage.scaledImage("tries.png", rf, 0.5);
    
    init();

  }

  @Override
  public void reset()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void handleHit(double hit)
  {
    // if (this.minBound < hit && hit < this.maxBound)
    // {
    //
    // }
  }

  private void init()
  {

    for (int i = 0; i < 3; i++)
    {
      this.tryContent[i].setLocation(20 + (i * 20), 20);
      this.add(this.tryContent[i]);
    }

  }

}
