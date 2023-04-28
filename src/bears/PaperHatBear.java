package bears;

import io.ResourceFinder;

public class PaperHatBear extends DannyBear
{

  public PaperHatBear(ResourceFinder rf, int screenHeight)
  {
    super(rf, screenHeight);
  }

  protected void setImageNames()
  {
    this.readImageNames("paperhatdanny.txt");
  }
}
