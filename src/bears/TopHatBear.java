package bears;

import io.ResourceFinder;

public class TopHatBear extends DannyBear
{

  public TopHatBear(ResourceFinder rf, int screenHeight)
  {
    super(rf, screenHeight);
  }
  
  protected void setImageNames()
  {
    this.readImageNames("tophatdanny.txt");
  }

}
