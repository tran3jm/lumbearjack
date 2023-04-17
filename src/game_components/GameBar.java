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
 * Creates our gamebar mechanic.
 */
public class GameBar implements SimpleContent
{

  private RoundRectangle2D bar;
  private RoundRectangle2D greenZone;
  private int recX, recY;
  private CursorOnBar cursorOnBar;

  /**
   * Default Constructor for our GameBar.
   * 
   * @param rf
   *          resource finder
   */
  public GameBar(final ResourceFinder rf, final int recX, final int recY, CursorOnBar cursorOnBar)
  {
    this.recX = recX;
    this.recY = recY;
    this.bar = new RoundRectangle2D.Double(this.recX, this.recY, 500, 35, 15, 15);
    this.greenZone = new RoundRectangle2D.Double(this.recX + 80, this.recY + 2, 100, 35, 5, 5);
    this.cursorOnBar = cursorOnBar;

  }

  @Override
  public void render(final Graphics arg0)
  {
    Graphics2D g;

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

}
