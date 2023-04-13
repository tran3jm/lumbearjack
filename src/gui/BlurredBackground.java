package gui;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import io.ResourceFinder;
import resources.Marker;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;

/**
 * GUI class to return background blurred.
 * NOTE: Still trying to find a more efficient way to do this.
 * 
 * @author joselynetran
 *
 */
public class BlurredBackground
{
  /**
   * Returns background blurred.
   * 
   * @return blurredbackground as content
   */
  public static Content blurredBackground()
  {
    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    // initializing factories
    ImageFactory imageFactory = new ImageFactory(rf);
    ContentFactory contentFactory = new ContentFactory(rf);
    BufferedImageOpFactory bfFactory = BufferedImageOpFactory.createFactory();
    BufferedImageOp blurOp = bfFactory.createBlurOp(15);

    // initializes images
    BufferedImage backgroundPicture = imageFactory.createBufferedImage("forest.jpeg");
  
    // creating backgroundphoto as content
    return contentFactory.createContent(blurOp.filter(backgroundPicture, null));
//    return contentFactory.createContent(backgroundPicture);
  }
}
