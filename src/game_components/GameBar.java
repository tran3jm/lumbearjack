package game_components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import io.ResourceFinder;
import visual.statik.SimpleContent;

/**
 * Creates our gamebar mechanic.
 */
public class GameBar implements SimpleContent
{
  
  private RoundRectangle2D bar;
  private RoundRectangle2D greenZone;
  private int recX, recY;
  /**
   * Default Constructor for our GameBar.
   * @param rf resource finder
   */
  public GameBar(final ResourceFinder rf)
  {
    this.recX = 465;
    this.recY = 500;
    this.bar = new RoundRectangle2D.Double(this.recX, this.recY, 200, 25, 15, 15);
    this.greenZone = new RoundRectangle2D.Double(this.recX+80, this.recY+2, 40, 21, 5, 5);

    
  }
  
  @Override
  public void render(final Graphics arg0)
  {
    Graphics2D g;

    RoundRectangle2D backgroundBar = new RoundRectangle2D.Double(this.recX, this.recY, 200, 25, 15,
        15);

    // rectangle bar behind hit area
    g = (Graphics2D) arg0;
    g.setColor(new Color(.75f, .75f, .75f, .5f));
    g.fill(backgroundBar);

    // gamebar
    g.setColor(Color.WHITE);
    g.fill(this.bar);

    // Border of gamebar
    g.setStroke(new BasicStroke(4));
    g.setColor(Color.black);
    g.draw(this.bar);
    
    // greenZone in gamebar.
    g.setColor(new Color(124,252,0));
    g.fill(this.greenZone);
  }
  
}
