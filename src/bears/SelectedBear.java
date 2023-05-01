package bears;

import java.util.ArrayList;

import io.ResourceFinder;

/**
 * Helper class to get selected bear for custom and game screen.
 *
 * @author joselynetran and panamuhammad
 * @version 04.30.2023
 */
public class SelectedBear
{

  private static final String[] filenames = {"tophatdanny.txt", "plaindanny.txt", "strawdanny.txt",
      "piratedanny.txt", "paperhatdanny.txt"};

  private ArrayList<DannyBear> bears;

  private ResourceFinder rf;
  private int height;
  private int index;

  /**
   * Constructor for SelectedBear.
   * 
   * @param rf
   * @param height
   */
  public SelectedBear(final ResourceFinder rf, final int height)
  {
    this.rf = rf;
    this.height = height;
    this.index = 0;
    
    this.bears = new ArrayList<>();
    for (String file: filenames) bears.add(new DannyBear(this.rf, this.height, file));
  }

  public DannyBear nextBear()
  {
    this.index = (index + 1) % this.bears.size();
    return this.bears.get(index);
  }

  public DannyBear prevBear()
  {
    if (index - 1 < 0) this.index = this.bears.size() - 1;
    else this.index = (index - 1) % this.bears.size();
    return this.bears.get(index);
  }
  

}
