package bears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import game_components.Frames;
import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.sampled.Content;

/**
 * Original br eaimage frames.
 * 
 * @author joselynetran and panamuhamad
 *
 */
public class DannyBear implements Frames
{

  // Bear frames keys
  public static final String SWING = "DANNY-SWING";
  public static final String CHOP = "DANNY-CHOP";
  public static final String MISS = "DANNY-MISS";

  private int screenHeight;
  private ResourceFinder rf;

  // filename of the images
  private String[] imageNames;

  // will map bear frame to name --> <"name", bearimage>
  private HashMap<String, Content> bearFrames = new HashMap<>();

  /**
   * Constructor.
   * 
   * @param rf
   * @param screenHeight
   * @param filename
   */
  public DannyBear(final ResourceFinder rf, final int screenHeight, final String filename)
  {
    this.rf = rf;
    this.screenHeight = screenHeight;
    this.imageNames = new String[3];
    this.readImageNames(filename);

    this.addFrames(SWING, this.imageNames[0]);
    this.addFrames(CHOP, this.imageNames[1]);
    this.addFrames(MISS, this.imageNames[2]);
    setLocation();
  }

/**
 * Helper method to help with reading images from file.
 *
 * @param filename
 */
  private void readImageNames(final String filename)
  {

    InputStream is = rf.findInputStream(filename);
    BufferedReader in = new BufferedReader(new InputStreamReader(is));

    for (int i = 0; i < 3; i++)
    {
      try
      {
        this.imageNames[i] = in.readLine();
      }
      catch (IOException e)
      {
        this.imageNames[i] = "danny_prechop.png";
      }
    }
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
        bearFrames.get(key).setLocation(40, screenHeight / 9);
      else
        bearFrames.get(key).setLocation(-10, screenHeight / 9);
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
    Content content = ScaledImage.scaledImage(filename, this.rf, 0.48);
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

  /**
   * Returns Content to use one instance of content in the map.
   *
   * @param key
   * @return Hashmap of the frames
   */
  public Content getBearContent(final String key)
  {
    return bearFrames.get(key);
  }

}
