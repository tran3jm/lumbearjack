package bears;

import io.ResourceFinder;

/**
 * PaperHatBear.
 * @author Panam
 *
 */
public class PaperHatBear extends DannyBear
{

  /**
   * PaperHat Constructor.
   * @param rf
   * @param screenHeight
   */
  public PaperHatBear(final ResourceFinder rf, final int screenHeight)
  {
    super(rf, screenHeight);
  }

  protected void setImageNames()
  {
    this.readImageNames("paperhatdanny.txt");
  }
}
