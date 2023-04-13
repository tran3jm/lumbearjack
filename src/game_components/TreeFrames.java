package game_components;

import java.util.HashMap;

import gui.ScaledImage;
import io.ResourceFinder;
import visual.statik.sampled.Content;

/**
 * 
 * @author joselynetran
 *
 */
public class TreeFrames implements Frames
{

  public static final String FH_TREE = "FULL-HEALTH TREE";
  public static final String PH_TREE = "PARTIAL-HEALTH TREE";
  public static final String CUT_TREE = "CUT TREE";
  

  private HashMap<String, Content> treeFrames = new HashMap<>();
  private ResourceFinder rf;

  /**
   * 
   * @param rf
   * @param screenHeight
   */
  public TreeFrames(final ResourceFinder rf, final int screenHeight)
  {
    this.rf = rf;
    this.addFrames(FH_TREE, "tree.png");
    this.addFrames(PH_TREE, "tree_cut.png");
    this.addFrames(CUT_TREE, "tree_chopped.png");
    setLocation();
  }

  /**
   * 
   */
  public void setLocation()
  {
    for (String key : treeFrames.keySet())
    {
      treeFrames.get(key).setLocation(250, -100);
    }
  }

  /**
   * 
   * @param key
   * @param filename
   */
  public void addFrames(final String key, final String filename)
  {
    Content content = ScaledImage.scaledImage(filename, this.rf, 0.40);
    treeFrames.put(key, content);
  }

  /**
   * 
   * @return Hashmap of the frames
   */
  public HashMap<String, Content> getFrames()
  {
    return treeFrames;
  }

}
