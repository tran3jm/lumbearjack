package bears;

import java.util.ArrayList;

import io.ResourceFinder;

/**
 * Helper class to get selected hat for bear in custom and game screen.
 *
 * @author joselynetran and panamuhamad
 * @version 04.30.2023
 */
public class SelectedBear
{

  // text files containing image filenames to read
  private static final String[] FILENAMES = {"tophatdanny.txt", "plaindanny.txt", "strawdanny.txt",
      "piratedanny.txt", "paperhatdanny.txt", "dukeduckdanny.txt"};

  // list of different bears (different hats being different bears)
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

    // creates and puts bears in list based on each file in image filenames
    for (String file: FILENAMES) bears.add(new DannyBear(this.rf, this.height, file));
  }

  /**
   * Gets the next bear in our list.
   * @return the next bear.
   */
  public DannyBear nextBear()
  {
    this.index = (index + 1) % this.bears.size();
    return this.bears.get(index);
  }

  /**
   * Gets the previous bear in our list.
   * @return the previous bear.
   */
  public DannyBear prevBear()
  {
    // resets to end of list if index reaches below 0
    if (index - 1 < 0) this.index = this.bears.size() - 1;
    else this.index = (index - 1) % this.bears.size();
    return this.bears.get(index);
  }
  

}
