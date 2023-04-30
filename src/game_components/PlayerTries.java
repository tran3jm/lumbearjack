package game_components;

import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.sampled.AggregateContent;
import visual.statik.sampled.Content;

/**
 * We should put playertries in this class once we can.
 * 
 * @author joselynetran
 *
 */
public class PlayerTries extends AggregateContent implements GameObserver
{

  private static int TOTAL_TRIES = 3;
  private static final String IMG_NAME = "tries.png";
  private int tries, minBound, maxBound;
  private ResourceFinder rf;
  private Content[] tryContent;

  /**
   * Constructor for PlayerTries.
   * @param rf resource finder.
   * @param minBound
   * @param maxBound
   */
  public PlayerTries(final ResourceFinder rf, final int minBound, final int maxBound)
  {
    this.tries = TOTAL_TRIES;
    this.rf = rf;
    this.tryContent = new Content[3];
    this.minBound = minBound;
    this.maxBound = maxBound;
   
    for (int i = 0; i < 3; i++)
      this.tryContent[i] = ScaledImage.scaledImage(IMG_NAME, rf, 0.25);
    
    init();

  }

  @Override
  public void reset()
  {
    // TODO Auto-generated method stub
    this.tries = TOTAL_TRIES;
    for (int i = 0; i < this.tries; i++)
    {
      if (this.tryContent[i] == null)
      {
        this.tryContent[i] = ScaledImage.scaledImage(IMG_NAME, rf, 0.25);
      }
    }
    init();
  }

  @Override
  public void handleHit(final double hit)
  {
    if (!(this.minBound < hit && hit < this.maxBound))
    {
      this.tries -= 1;
      // Java's garbage collector will eventually clear it to save memory.
      this.tryContent[this.tries] = null;
    }
  }

  /**
   * sets the amount of tries.
   * @param lives
   * @return
   */
  public void setLives (final int lives)
  {
    if (lives > 3)
    {
      TOTAL_TRIES = 3;
    }
    else
    {
      TOTAL_TRIES = lives;
    }
    this.tries = TOTAL_TRIES;
    for (int i = 0; i < 3; i++)
    {
      if (i >= TOTAL_TRIES)
      {
        this.tryContent[i] = null;
      }
    }
    reset();
    
  }
  
  /**
   * Getter for the amount of lives.
   * @return the lives remaining.
   */
  public int getLives()
  {
    return this.tries;
  }
  
  /**
   * Sets the new boundaries for our gamee.
   * @param minB
   * @param maxB
   */
  public void newBounds(final int minB, final int maxB)
  {
    this.minBound = minB;
    this.maxBound = maxB;
  }
  
  /**
   * Getter to grab our lives in Content.
   * @return the array of content.
   */
  public Content[] getContentArray()
  {
    return this.tryContent;
  }
  
  private void init()
  {

    for (int i = 0; i < TOTAL_TRIES; i++)
    {
      this.tryContent[i].setLocation(500 + (i * 50), 28);
    }
  }
  
  /**
   * Returns the max lives possible.
   * @return max lives.
   */
  public int getMaxLives()
  {
    return TOTAL_TRIES;
  }
 
}
