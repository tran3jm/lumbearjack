package game_components;

import java.util.HashMap;

import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.sampled.Content;

/**
 * Bear image frames.
 * 
 * @author joselynetran
 *
 */
public class BearFrames implements Frames
{

  // Bear frames keys
  public static final String SWING = "DANNY-SWING";
  public static final String CHOP = "DANNY-CHOP";
  public static final String MISS = "DANNY-MISS";

  private int screenHeight;
  private ResourceFinder rf;
  
  // will map bear frame to name --> <"name", bearimage>
  private HashMap<String, Content> bearFrames = new HashMap<>();
  

  /**
   * Constructor.
   * 
   * @param rf
   * @param screenHeight
   */
  public BearFrames(final ResourceFinder rf, final int screenHeight)
  {
    this.rf = rf;
    this.screenHeight = screenHeight;
    this.addFrames(SWING, "danny_prechop.png");
    this.addFrames(CHOP, "danny_chop.png");
    this.addFrames(MISS, "danny_miss.png");
    setLocation();
  }

  /**
   * Sets location of all frames.
   */
  public void setLocation()
  {
    for (String key : bearFrames.keySet())
    {
      // if chop bear, must move slightly to right due to width
      if (key == CHOP)
        bearFrames.get(key).setLocation(40, screenHeight / 10);
      else
        bearFrames.get(key).setLocation(-10, screenHeight / 10);
    }
  }

  /**
   * Adds frames to hashmap.
   * 
   * @param key
   * @param filename
   */
  public void addFrames(final String key, final String filename)
  {
    Content content = ScaledImage.scaledImage(filename, this.rf, 0.44);
    bearFrames.put(key, content);
  }

  /**
   * Returns tree hashmap to use for GameScreen.
   * 
   * @return Hashmap of the frames
   */
  public HashMap<String, Content> getFrames()
  {
    return bearFrames;
  }

}
