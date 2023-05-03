package game_components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import io.ResourceFinder;
import utils.GameColorPallet;
import visual.statik.SimpleContent;

/**
 * Creates our GameBar mechanic.
 */
public class GameBar implements SimpleContent
{

  private RoundRectangle2D bar;          // bar that will have cursor scribe though
  private RoundRectangle2D greenZone;    // greenzone for user to hit cursor in
  private int recX, recY;                // x and y coordinate

  /**
   * Default Constructor for our GameBar.
   * 
   * @param rf
   *          resource finder
   * @param recX
   * @param recY
   * @param cursorOnBar
   * @param greenX
   * @param greenY
   * @param greenWidth
   */
  public GameBar(final ResourceFinder rf, final int recX, 
                 final int recY, final int greenX, final int greenY, final int greenWidth,
                 final CursorOnBar cursorOnBar)
  {
    this.recX = recX;
    this.recY = recY;
    this.bar = new RoundRectangle2D.Double(this.recX, this.recY, 500, 35, 15, 15);
    this.greenZone = new RoundRectangle2D.Double(greenX, greenY, greenWidth, 35, 5, 5);
  }

  @Override
  public void render(final Graphics arg0)
  {
    Graphics2D g;

    // background bar for cleaner display
    RoundRectangle2D backgroundBar = new RoundRectangle2D.Double(this.recX, this.recY, 500, 35, 15,
        15);

    // rectangle bar behind hit area
    g = (Graphics2D) arg0;
    g.setColor(new Color(255, 90, 70));
    g.fill(backgroundBar);

    // gamebar
    g.setColor(GameColorPallet.BAR_FILLER);
    g.fill(this.bar);

    // greenZone in gamebar.
    g.setColor((GameColorPallet.BRIGHT_GREEN));
    g.fill(this.greenZone);

    // Border of gamebar
    g.setStroke(new BasicStroke(4));
    g.setColor(Color.black);
    g.draw(this.bar);

  }
  
  /**
   * Getter for green zone.
   * @return the green zone.
   */
  public RoundRectangle2D getGreenZone()
  {
    return this.greenZone;
  }
}
