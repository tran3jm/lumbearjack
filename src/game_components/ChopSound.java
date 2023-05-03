package game_components;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gui.AudioSprite;
import io.ResourceFinder;

/**
 * Sound class for everytime danny chops.
 * 
 * @author joselynetran dan panamuhamad
 *
 */
public class ChopSound implements GameObserver
{
  private ResourceFinder rf;
  private AudioSprite chop;
  /**
   * Creates our chopSound.
   * @param rf
   */
  public ChopSound(final ResourceFinder rf)
  {
    this.rf = rf;
    InputStream is = this.rf.findInputStream("tree_chop_sound.wav");
    BufferedInputStream bf = new BufferedInputStream(is);
    try
    {
      // Creates our audio and starts music right away.
      this.chop = new AudioSprite(bf, 0);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (LineUnavailableException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedAudioFileException e)
    {
      e.printStackTrace();
    }
  }
  @Override
  public void reset()
  {
    //Does nothing
  }

  @Override
  public void handleHit(final double hit)
  {
    this.chop.handleTick(0);
  }

}
