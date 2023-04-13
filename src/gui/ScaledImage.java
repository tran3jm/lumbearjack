package gui;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import io.ResourceFinder;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;

/**
 * 
 * @author joselynetran
 *
 */
public class ScaledImage
{

  /**
   * 
   * @param filename
   * @param rf
   * @return Scaled down version of the danny pngs
   */
  public static Content scaledImage(final String filename, final ResourceFinder rf, double scaled)
  {
    // initializing factories
    ImageFactory imageFactory = new ImageFactory(rf);
    ContentFactory contentFactory = new ContentFactory(rf);
    BufferedImageOpFactory bfFactory = BufferedImageOpFactory.createFactory();
    BufferedImageOp scaleOp = bfFactory.createScaleOp(scaled, scaled);

    // initializes images
    BufferedImage danny = imageFactory.createBufferedImage(filename, BufferedImage.TYPE_INT_ARGB);
    BufferedImage after = new BufferedImage(danny.getWidth(), danny.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    // creating danny the bear as content
    scaleOp.filter(danny, after);
    return contentFactory.createContent(after);
  }
}
