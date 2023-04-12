package gui;

import java.awt.*;
import visual.statik.described.Content;

/**
 * A JComponent that contains all of the font families available on the system.
 *
 * @version 1.0
 * @author Prof. David Bernstein, James Madison University
 */
public class TextGraphic extends Content
{

  private Font font;
  private String text;
  private int width;
  private int height;
  private int fSize;

  /**
   * Explicit Value Constructor.
   *
   * @param fSize
   *          The font size to use (in points)
   * @param text
   *          Text displayed
   * @param width
   *          Width of frame
   * @param height
   *          Height of frame
   * @param font
   *          Font
   */
  // [constructor
  public TextGraphic(final int fSize, final String text, final int width, final int height,
      final Font font)
  {
    this.font = font;
    this.text = text;
    this.width = width;
    this.height = height;
    this.fSize = fSize;
  }
  // ]constructor

  /**
   * Paint this component.
   *
   * @param g
   *          The Graphics context to use
   */
  public void render(final Graphics g)
  {
    float textHeight;
    float textWidth;

    Graphics2D g2;

    g2 = (Graphics2D) g;
    g2.setFont(this.font);

    textHeight = (float) height / 2.0f;
    textWidth = (float) width / 3.0f;

    g2.setColor(Color.BLACK);
    
    int additionalHeight = 0;
    for (String line: text.split("\n"))
    {
      g2.drawString(line, textWidth, textHeight + additionalHeight);
      additionalHeight += this.fSize + 20;

    }

  }

}
