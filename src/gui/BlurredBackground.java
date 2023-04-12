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
 * 
 * @author joselynetran
 *
 */
public class BlurredBackground
{
  /**
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
    BufferedImageOp blurOp = bfFactory.createBlurOp(25);

    // initializes images
    BufferedImage backgroundPicture = imageFactory.createBufferedImage("forest.jpeg");
  
    // creating backgroundphoto as content
//    return contentFactory.createContent(blurOp.filter(backgroundPicture, null));
    return contentFactory.createContent(backgroundPicture);
  }
}
