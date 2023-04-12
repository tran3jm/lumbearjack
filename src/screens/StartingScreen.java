package screens;

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
public class StartingScreen extends MainScreen
{

  /**
   * 
   * @param width
   * @param height
   * @param rf
   */
  public StartingScreen(final int width, final int height, final ResourceFinder rf)
  {
    super(width, height, rf);
    this.addDanny();
  }

  /**
   * 
   */
  public void addDanny()
  {

    // initializing factories
    ImageFactory imageFactory = new ImageFactory(this.rf);
    ContentFactory contentFactory = new ContentFactory(this.rf);
    BufferedImageOpFactory bfFactory = BufferedImageOpFactory.createFactory();
    BufferedImageOp scaleOp = bfFactory.createScaleOp(0.30, 0.30);

    // initializes images
    BufferedImage danny = imageFactory.createBufferedImage("Starting_Screen_Danny.png",
        BufferedImage.TYPE_INT_ARGB);
    BufferedImage after = new BufferedImage(danny.getWidth(), danny.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    // creating danny the bear as content
    scaleOp.filter(danny, after);
    Content dannyContent = contentFactory.createContent(after);
    dannyContent.setLocation(this.view.getWidth() / 10, this.view.getHeight() / 50);
    this.add(dannyContent);

  }

}
