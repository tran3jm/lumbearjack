package bears;

import io.ResourceFinder;

/**
 * NOT USED CURRENTLY.
 *
 */
public class SelectedBear
{
  
  public static final String TOPHAT = "top hat";
  public static final String STRAWHAT = "straw hat";
  public static final String PIRATEHAT = "pirate hat";
  public static final String PAPERHAT = "paper hat";
  
  private ResourceFinder rf;
  private int height;
  
  /**
   * Constructor for SelectedBear.
   * @param rf
   * @param height
   */
  public SelectedBear(final ResourceFinder rf, final int height)
  {
    this.rf = rf;
    this.height = height;
  }
  
  /**
   * DannyBear selector.
   * @param bear 
   * @return r
   */
  public DannyBear selectBear(final String bear)
  {
    DannyBear bear2;
    switch(bear)
    {
      case TOPHAT:
        bear2 =  new TopHatBear(rf, height);
        break;
      case STRAWHAT:
        bear2 =  new StrawHatBear(rf, height);
        break;
      case PIRATEHAT:
        bear2 =  new PirateBear(rf, height);
        break;
      case PAPERHAT:
        bear2 =  new PaperHatBear(rf, height);
        break;
      default:
        bear2 = new DannyBear(rf, height);
        break;
    }
    return bear2;
  }
  
}
