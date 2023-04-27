package screens;

import game_components.*;
import io.GameComponentObserver;
import io.ResourceFinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import bears.PlainBearFrames;
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
  private PlayerTries lives;
  private Content spacebarIndicator;
  private boolean winner, loser;
  //private int level; 
  private int barX, barY;
  private int greenX, greenY;
  private int height;
  
  private enum LevelChange 
  {
     SPEED_CHANGE,
     MOVE_BAR
  }
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
    this.height = height;
    this.setRestartTime(2000);
    this.winner = false;
    this.loser = false;
    //this.level = 1;
    this.barX = 150;
    this.barY = height - 100;
    // Level 1 will always have the same location for the green area.
    this.greenX = this.barX + 80;
    this.greenY = this.barY + 2;
    
    // setting up game components
    this.treelife = new TreeLife(this.rf, this.greenX, this.greenX + 100);
    this.lives = new PlayerTries(this.rf, this.greenX, this.greenX + 100);
    this.cursorOnBar = new CursorOnBar(150, height - 70, 0, 1000, 2000);
    this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
                               this.greenX, this.greenY, this.cursorOnBar);
    this.bearFrames = new PlainBearFrames(this.rf, height).getFrames();
    this.treeFrames = new TreeFrames(this.rf, height).getFrames();
    
    // setting up main observer and adding observers
    gco = new GameComponentObserver();
    gco.addObserver(this.treelife);
    gco.addObserver(this.lives);
    
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
      this.remove(bearFrames.get(PlainBearFrames.SWING));
      this.remove(bearFrames.get(PlainBearFrames.MISS));
     
      this.add(bearFrames.get(PlainBearFrames.CHOP));

      
    }
    placeGameBarFront();
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();

    if (key == SPACEKEY && !this.treelife.hasWon() && this.lives.getLives() > 0)
    {
      gco.notifyObservers(this.cursorOnBar.getCurrentLocation().getX());
      this.clearFrames();
      
      // change tree depending on how much damage done to it
      if (this.treelife.halfDamage()) this.add(treeFrames.get(TreeFrames.PH_TREE));
      else if (this.treelife.hasWon()) this.add(treeFrames.get(TreeFrames.CUT_TREE));
      else this.add(treeFrames.get(TreeFrames.FH_TREE));
      
      if (!this.treelife.playerMisses()) this.add(bearFrames.get(PlainBearFrames.SWING));
      else this.add(bearFrames.get(PlainBearFrames.MISS));
    
    }
    else if (key == SPACEKEY && this.treelife.hasWon())
    {
      this.winner = true;
      this.levelUp();
    }
    else if (this.lives.getLives() <= 0)
    {
      this.loser = true;
    }
    placeGameBarFront();
  }
  
  /**
   * Returns if we lost all our lives.
   * @return boolean.
   */
  public boolean noLives() 
  {
    return this.loser;
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
    this.lives.reset();
    updateLives();
    this.winner = false;
    this.loser = false;
    this.clearFrames();
    
    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(PlainBearFrames.SWING));
    placeGameBarFront();

  }

  /**
   * Helper method to add all game components to current screen.
   */
  private void addGameComponents()
  {
    this.add(this.treeFrames.get(TreeFrames.FH_TREE));
    this.add(this.bearFrames.get(PlainBearFrames.SWING));
    this.add(this.treelife);
    this.add(this.spacebarIndicator);
    updateLives();

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

  /**
   * Updates the Screen for our Lives.
   */
  private void updateLives() 
  {
    for (int i = 0; i < this.lives.getContentArray().length; i++)
    {
      this.remove(this.lives.getContentArray()[i]);
    }
    for (int i = 0; i < this.lives.getContentArray().length; i++)
    {
      this.add(this.lives.getContentArray()[i]);
    }
  }
  
  private void placeGameBarFront()
  {
    this.remove(this.gameBar);
    this.remove(this.cursorOnBar);
    this.add(this.gameBar);
    this.add(this.cursorOnBar);
    updateLives();
  }
  
  
  /**
   * Handles our leveling features.
   */
  private void levelUp()
  {
    // increase our level (to be used/displayed)
    //this.level += 1;
    Random ran = new Random();
    LevelChange rng = LevelChange.values()[ran.nextInt(LevelChange.values().length)];
    switch (rng) 
    {
      case MOVE_BAR:
        int nxt = ran.nextInt(395) + 150;
        this.greenX = nxt;
        this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
            this.greenX, this.greenY, this.cursorOnBar);  
        this.treelife.setNewMinBound(this.greenX);
        this.treelife.setNewMaxBound(this.greenX + 100);
        this.lives.newBounds(this.greenX, this.greenX + 100);
        break;
      case SPEED_CHANGE:
        int keyTime1, keyTime2, keyTime3;
        int change = ran.nextInt(500);
        keyTime1 = 0;
        keyTime2 = 1000 - change;
        keyTime3 = 2000 - change;
        this.cursorOnBar = new CursorOnBar(150, this.height - 70, keyTime1, keyTime2, keyTime3);
        this.setRestartTime(keyTime3);
        break;
      default:
        break;
    }
  }
  
  /**
   * Helper method to remove all frames and
   * add new ones.
   */
  private void clearFrames()
  {
    this.remove(treeFrames.get(TreeFrames.CUT_TREE));
    this.remove(treeFrames.get(TreeFrames.FH_TREE));
    this.remove(treeFrames.get(TreeFrames.PH_TREE));
    this.remove(bearFrames.get(PlainBearFrames.SWING));
    this.remove(bearFrames.get(PlainBearFrames.MISS));
    this.remove(bearFrames.get(PlainBearFrames.CHOP));
    
  }
  
}
