package game_components;

import java.util.HashMap;

import visual.statik.sampled.Content;

/**
 * Frame interface for collection of images needed for GameScreen.
 * 
 * @author joselynetran
 *
 */
public interface Frames
{
  /**
   * Sets locations of frames.
   * 
   */
  public void setLocation();

  /**
   * Adds frames to collection.
   * 
   * @param key
   * @param filename
   */
  public void addFrames(final String key, final String filename);

  /**
   * Returns frames.
   * 
   * @return frames
   */
  public HashMap<String, Content> getFrames();
}
