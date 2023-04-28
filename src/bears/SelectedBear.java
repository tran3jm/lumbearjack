package bears;

import io.ResourceFinder;

public class SelectedBear
{
  
  public static final String TOPHAT = "top hat";
  public static final String STRAWHAT = "straw hat";
  public static final String PIRATEHAT = "pirate hat";
  public static final String PAPERHAT = "paper hat";
  
  private ResourceFinder rf;
  private int height;
  
  public SelectedBear(ResourceFinder rf, int height)
  {
    this.rf = rf;
    this.height = height;
  }
  
  public DannyBear selectBear(String bear)
  {
    switch(bear)
    {
      case TOPHAT:
        return new TopHatBear(rf, height);
      case STRAWHAT:
        return new StrawHatBear(rf, height);
      case PIRATEHAT:
        return new PirateBear(rf, height);
      case PAPERHAT:
        return new PaperHatBear(rf, height);
       default:
         return new DannyBear(rf, height);
    }
  }
  
}
