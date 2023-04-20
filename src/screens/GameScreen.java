package screens;

import game_components.*;
import io.GameComponentObserver;
import io.ResourceFinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import visual.statik.sampled.Content;

/**
 * GameScreen that contains the actually game play of LumBearJack.
 * 
 * @author joselynetran
 *
 */
public class GameScreen extends MainScreenStage implements KeyListener
{
  public static final int SPACEKEY = 32;
  private GameComponentObserver gco;

  private GameBar gameBar;
  private CursorOnBar cursorOnBar;
  private HashMap<String, Content> bearFrames;
  private HashMap<String, Content> treeFrames;
  private TreeLife treelife;
  private Content spacebarIndicator;
  private boolean winner;
  private int level; 
  private int barX, barY;
  private int greenX, greenY;
  /**
   * Creates our gameplay screen for users to play.
   * 
   * @param width
   * @param height
   * @param rf
   * @throws IOException
   */
  public GameScreen(final int width, final int height, final ResourceFinder rf) throws IOException
  {
    super(width, height, 10, rf);
    this.setRestartTime(2000);
    this.winner = false;
    this.level = 1;
    this.barX = 150;
    this.barY = height - 100;
    // Level 1 will always have the same location for the green area.
    this.greenX = this.barX + 80;
    this.greenY = this.barY + 2;
    
    // setting up game components
//    this.spacebarIndicator = ScaledImage.scaledImage("click_spacebar.png", this.rf, 0.15);
//    this.spacebarIndicator.setLocation(280, 280);
    this.treelife = new TreeLife(this.rf, this.greenX, this.greenX + 100);
    this.cursorOnBar = new CursorOnBar(150, height - 70);
    this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
                               this.greenX, this.greenY, this.cursorOnBar);
    this.bearFrames = new BearFrames(this.rf, height).getFrames();
    this.treeFrames = new TreeFrames(this.rf, height).getFrames();
    
    // setting up main observer and adding observers
    gco = new GameComponentObserver();
    gco.addObserver(this.treelife);

    this.addKeyListener(this);
    this.addGameComponents();
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();
    if (key == SPACEKEY && !this.treelife.hasWon())
    {
      // must remove current frame to replace with next frame
      this.remove(bearFrames.get(BearFrames.SWING));
      this.add(bearFrames.get(BearFrames.CHOP));
    }
    placeGameBarFront();
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();

    if (key == SPACEKEY && !this.treelife.hasWon())
    {
      gco.notifyObservers(this.cursorOnBar.getCurrentLocation().getX());

      // change tree frame if half damage
      if (this.treelife.halfDamage())
      {
        this.remove(treeFrames.get(TreeFrames.FH_TREE));
        this.add(treeFrames.get(TreeFrames.PH_TREE));

      }

      // change tree frame if full damage/user has won the game
      if (this.treelife.hasWon())
      {
        this.remove(treeFrames.get(TreeFrames.PH_TREE));
        this.add(treeFrames.get(TreeFrames.CUT_TREE));

      }

      this.remove(bearFrames.get(BearFrames.CHOP));
      this.add(bearFrames.get(BearFrames.SWING));
    }
    else if (key == SPACEKEY && this.treelife.hasWon())
    {
      this.winner = true;
      this.levelUp();
    }
    placeGameBarFront();
  }

  /**
   * Returns if game is finished.
   * 
   * @return game finished
   */
  public boolean gameComplete()
  {
    return this.winner;
  }

  /**
   * Resets gamescreeen.
   */
  public void reset()
  {
    this.treelife.reset();
    levelUp();
    this.winner = false;

    this.remove(treeFrames.get(TreeFrames.CUT_TREE));
    this.remove(bearFrames.get(BearFrames.SWING));

    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(BearFrames.SWING));
    placeGameBarFront();

  }

  /**
   * Helper method to add all game components to current screen.
   */
  private void addGameComponents()
  {
    this.add(this.treeFrames.get(TreeFrames.FH_TREE));
    this.add(this.bearFrames.get(BearFrames.SWING));
    this.add(this.treelife);
    this.add(this.spacebarIndicator);
    
    // setting up cursor bar animation
    this.add(this.gameBar);
    this.add(this.cursorOnBar);
    this.start();
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

  private void placeGameBarFront()
  {
    this.remove(this.gameBar);
    this.remove(this.cursorOnBar);
    this.add(this.gameBar);
    this.add(this.cursorOnBar);
  }
  
  /**
   * Handles our leveling features.
   */
  private void levelUp()
  {
    // increase our level (to be used/displayed)
    this.level += 1;
    Random ran = new Random();
    int nxt = ran.nextInt(150, 545);
    this.greenX = nxt;
    this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
        this.greenX, this.greenY, this.cursorOnBar);  
    this.treelife.setNewMinBound(this.greenX);
    this.treelife.setNewMaxBound(this.greenX + 100);
  }
}
