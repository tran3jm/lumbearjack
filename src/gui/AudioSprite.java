package gui;

import java.io.*;
import javax.sound.sampled.*;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;


/**
 * A simple Sprite that can be used to present
 * sampled auditory content that is stored in a file
 * GIVEN AT URL: 
 *    https://w3.cs.jmu.edu/bernstdh/web/common/policies/multimedia-project-policies_group.php 
 * Added one method. 
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class AudioSprite extends AbstractSprite
{
  private Clip  clip;
  private int startTime;
  
  /**
   * Creates an AudioSprite which can be used to play music from a file.
   * @param is
   * @param startTime
   * @throws IOException
   * @throws LineUnavailableException
   * @throws UnsupportedAudioFileException
   */
  public AudioSprite(final InputStream is, final int startTime) 
      throws IOException, LineUnavailableException, UnsupportedAudioFileException
  {
    this.startTime = startTime;
    setVisible(false);
    
    
    // Create an AudioInputStream from the InputStream
    AudioInputStream stream = AudioSystem.getAudioInputStream(is);

    // Get the AudioFormat for the File
    AudioFormat format = stream.getFormat();

    // Create an object that contains all relevant 
    // information about a DataLine for this AudioFormat
    DataLine.Info info = new DataLine.Info(Clip.class, format);

    // Create a Clip (i.e., a pre-loaded Line)
    clip = (Clip)AudioSystem.getLine(info);

    // Tell the Clip to acquire any required system 
    // resources and become operational
    clip.open(stream);
  }

  /**
   * Handle "tick" events.
   * 
   * In this case, start the clip at the appropriate time.
   * 
   * @param time The time of the tick
   */
  @Override
  public void handleTick(final int time)
  {
    if (time == startTime) clip.start();
  }

  /**
   * Loops forever for our music.
   */
  public void loopForever()
  {
    this.clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  /**
   * Get the Content associated with this Audio Sprite.
   */
  @Override
  protected TransformableContent getContent()
  {
    // Note: The render() method in AbstractSprite does nothing if 
    // the content is null! setVisible(false) is invoked in the constructor
    // to add an extra level of safety.
    return null;
  }
}
