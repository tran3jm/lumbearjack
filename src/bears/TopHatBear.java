package bears;

import io.ResourceFinder;

/**
 * TopHatBear.
 *
 */
public class TopHatBear extends DannyBear
{

  /**
   * TopHatBear.
   * @param rf
   * @param screenHeight
   */
  public TopHatBear(final ResourceFinder rf, final int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("tophatdanny.txt");
  }

}
