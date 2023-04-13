package game_components;

import java.util.HashMap;

import visual.statik.sampled.Content;

/**
 * 
 * @author joselynetran
 *
 */
public interface Frames
{
  /**
   * Adds observer.
   * 
   */
  public void setLocation();
  
  /**
   * Notifies observe when datum is read.
   * 
   * @param key
   * @param filename
   */
  public void addFrames(final String key, final String filename);
  
  /**
   * Removes observer.
   * 
   * @return frames
   */
  public HashMap<String, Content> getFrames();
}
