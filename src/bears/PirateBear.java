package bears;

import io.ResourceFinder;

public class PirateBear extends DannyBear
{

  public PirateBear(ResourceFinder rf, int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("piratedanny.txt");
  }

}
