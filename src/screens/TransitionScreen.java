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
public class TransitionScreen extends MainScreen
{
  
  private String filename;
  private Content content;
  
  public static final String GAME_OVER = "game_over.png";
  public static final String WINNER = "winner.png";
  public static final String LEVEL_UP = "level_up.png";

  /**
   * 
   * @param width
   * @param height
   * @param rf
   */
  public TransitionScreen(final int width, final int height, final ResourceFinder rf, final String transitionImgFile)
  {
    super(width, height, rf);
    this.filename = transitionImgFile;
    this.addTransitionImage();
  }
  
  /**
   * TransitionImage.
   * 
   * @param filename
   */
  public void setTransitionImage(final String filename)
  {
    switch(filename)
    {
      case WINNER:
        this.filename = WINNER;
        break;
      case LEVEL_UP:
        this.filename = LEVEL_UP;
        break;
      case GAME_OVER: default:
        this.filename = GAME_OVER;
    }
    this.remove(this.content);
    this.addTransitionImage();
  }

  /**
   * 
   */
  private void addTransitionImage()
  {

    // initializing factories
    ImageFactory imageFactory = new ImageFactory(this.rf);
    ContentFactory contentFactory = new ContentFactory(this.rf);
    BufferedImageOpFactory bfFactory = BufferedImageOpFactory.createFactory();
    BufferedImageOp scaleOp = bfFactory.createScaleOp(0.30, 0.30);

    // initializes images
    BufferedImage winnerBubbleLetters = imageFactory.createBufferedImage(this.filename,
        BufferedImage.TYPE_INT_ARGB);
    BufferedImage after = new BufferedImage(winnerBubbleLetters.getWidth(),
        winnerBubbleLetters.getHeight(), BufferedImage.TYPE_INT_ARGB);

    // creating danny the bear as content
    scaleOp.filter(winnerBubbleLetters, after);
    this.content = contentFactory.createContent(after);
    this.content.setLocation(this.view.getWidth() / 10, this.view.getHeight() / 50);
    this.add(this.content);

  }

}
