package bears;

import io.ResourceFinder;

public class StrawHatBear extends DannyBear
{

  public StrawHatBear(ResourceFinder rf, int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("strawhatdanny.txt");
  }

}
