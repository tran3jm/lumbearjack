package bears;

import io.ResourceFinder;

/**
 * Piratebear!
 *
 */
public class PirateBear extends DannyBear
{

  /**
   * Constructor for piratebear!
   * @param rf
   * @param screenHeight
   */
  public PirateBear(final ResourceFinder rf, final int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("piratedanny.txt");
  }

}
