package bears;

import io.ResourceFinder;

/**
 * Luffy Bear!
 *
 */
public class StrawHatBear extends DannyBear
{

  /**
   * StrawHatBear.
   * @param rf
   * @param screenHeight
   */
  public StrawHatBear(final ResourceFinder rf, final int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("strawdanny.txt");
  }

}
