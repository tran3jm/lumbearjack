package gui;

import io.ResourceFinder;
import visual.dynamic.sampled.Screen;
import visual.statik.SimpleContent;
import visual.statik.sampled.ContentFactory;

/**
 * Sampled Dynamic Content factory create Sampled Dyanmic Content from finder, 
 * filename, and setting fps.
 * 
 * @author joselynetran
 * @version 03/20/2023
 */
public class SampledDynamicContentFactory
{

  private ResourceFinder finder;
  private String filename;
  private int fps;

  /**
   * Constructor for sampled dynamic content.
   * 
   * @param finder
   *          ResourceFinder to use
   * @param filename
   *          Filename of text of file names to convert to frames
   * @param fps
   *          Frames per second of screen
   */
  public SampledDynamicContentFactory(final ResourceFinder finder, final String filename,
      final int fps)
  {
    this.finder = finder;
    this.filename = filename;
    this.fps = fps;
  }

  /**
   * Sampled Dyanmic Content creator.
   * 
   * @return Screen with scampled dyanmic Content
   */
  public Screen createSampledDynamicContent()
  {
    ContentFactory factory;

    SimpleContent[] frames;
    String[] names = null;

    Screen screen = new Screen(fps);

    names = finder.loadResourceNames(filename);
    factory = new ContentFactory(finder);
    frames = factory.createContents(names, names.length);

    for (int i = 0; i < frames.length; i++)
    {
      screen.add(frames[i]);
    }

    return screen;
  }
}
