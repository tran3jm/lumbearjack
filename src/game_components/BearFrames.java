package game_components;

import java.util.HashMap;

import gui.ScaledImage;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.statik.sampled.Content;

/**
 * 
 * @author joselynetran
 *
 */
public class BearFrames implements Frames
{

  public static final String SWING = "DANNY-SWING";
  public static final String CHOP = "DANNY-CHOP";
  public static final String MISS = "DANNY-MISS";
  
  private int screenHeight;

  private HashMap<String, Content> bearFrames = new HashMap<>();
  private ResourceFinder rf;

  /**
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
   * 
   */
  public void setLocation()
  {
    for (String key : bearFrames.keySet())
    {
      if (key == CHOP) bearFrames.get(key).setLocation(40, screenHeight / 10);
      else bearFrames.get(key).setLocation(-10, screenHeight / 10);
    }
  }

  /**
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
   * 
   * @return Hashmap of the frames
   */
  public HashMap<String, Content> getFrames()
  {
    return bearFrames;
  }

}
