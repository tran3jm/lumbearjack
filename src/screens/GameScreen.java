package screens;

import game_components.*;
import io.GameComponentObserver;
import io.ResourceFinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import bears.*;
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
  private int levels = 6;
  private Content spacebarIndicator;
  private boolean levelcompleted, loser;
  private int barX, barY;
  private int greenX, greenY, greenWidth;
  private int height;
  private double cursorLocation;
  
  private enum LevelChange 
  {
     SPEED_CHANGE,
     MOVE_BAR,
     GREEN_BAR_SIZE,
     LESS_LIVES
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
    this.levelcompleted = false;
    this.loser = false;
    //this.level = 1;
    this.barX = 150;
    this.barY = height - 100;
    // Level 1 will always have the same location for the green area.
    this.greenX = this.barX + 80;
    this.greenY = this.barY + 2;
    this.greenWidth = 100;
    // setting up game components
    // We are subtracting by 12 to account for input lag.
    this.treelife = new TreeLife(this.rf, this.greenX-12, this.greenX + 110);
    this.lives = new PlayerTries(this.rf, this.greenX-12, this.greenX + 110);
    
    this.cursorOnBar = new CursorOnBar(150, height - 70, 0, 1000, 2000);
    this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
                               this.greenX, this.greenY, this.greenWidth, this.cursorOnBar);
    this.bearFrames = new DannyBear(this.rf, height).getFrames();
    this.treeFrames = new TreeFrames(this.rf, height).getFrames();
    
    this.cursorLocation = -1;
    
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
      this.remove(bearFrames.get(DannyBear.SWING));
      this.remove(bearFrames.get(DannyBear.MISS));
     
      this.add(bearFrames.get(DannyBear.CHOP));
    }
    if (this.cursorLocation == -1)
    {
      this.cursorLocation = this.cursorOnBar.getCurrentLocation().getX();      
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
      gco.notifyObservers(this.cursorLocation);
      this.cursorLocation = -1;
      this.clearFrames();
      
      // change tree depending on how much damage done to it
      if (this.treelife.halfDamage()) this.add(treeFrames.get(TreeFrames.PH_TREE));
      else if (this.treelife.hasWon()) this.add(treeFrames.get(TreeFrames.CUT_TREE));
      else this.add(treeFrames.get(TreeFrames.FH_TREE));
      
      if (!this.treelife.playerMisses()) this.add(bearFrames.get(DannyBear.SWING));
      else this.add(bearFrames.get(DannyBear.MISS));
    
    }
    else if (key == SPACEKEY && this.treelife.hasWon())
    {
      this.levelcompleted = true;
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
  public boolean levelComplete()
  {
    return this.levelcompleted;
  }

  /**
   * Returns if we have finished the last level in our game.
   * @return true or false for our winning game.
   */
  public boolean gameFinished()
  {
  	return this.levels == 0;    
  }
  /**
   * Resets gamescreeen.
   */
  public void reset()
  {
    this.treelife.reset();
    this.lives.reset();
    updateLives();
    this.levelcompleted = false;
    this.loser = false;
    this.clearFrames();
    
    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(DannyBear.SWING));
    placeGameBarFront();

  }

  /**
   * Helper method to add all game components to current screen.
   */
  private void addGameComponents()
  {
    this.add(this.treeFrames.get(TreeFrames.FH_TREE));
    this.add(this.bearFrames.get(DannyBear.SWING));
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
    this.levels -= 1;
    Random ran = new Random();
    LevelChange rng = LevelChange.values()[ran.nextInt(LevelChange.values().length)];
    // System.out.println(rng);
    switch (rng) 
    {
      case MOVE_BAR:
        int nxt = ran.nextInt(395) + 150;
        this.greenX = nxt;
        this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
            this.greenX, this.greenY, 100, this.cursorOnBar);  
        this.treelife.setNewMinBound(this.greenX-12);
        this.treelife.setNewMaxBound(this.greenX + 110);
        this.lives.newBounds(this.greenX-12, this.greenX + 110);
        break;
      case SPEED_CHANGE:
        int keyTime1, keyTime2, keyTime3;
        int change = ran.nextInt(750);
        keyTime1 = 0;
        keyTime2 = 1000 - change;
        keyTime3 = 2000 - change;
        this.cursorOnBar = new CursorOnBar(150, this.height - 70, keyTime1, keyTime2, keyTime3);
        this.setRestartTime(keyTime3);
        break;
      case GREEN_BAR_SIZE:
        int width = ran.nextInt(60);
        if (this.greenWidth < 25)
        {
          width = 25; 
        }
        this.greenWidth = 100-width;
        this.gameBar = new GameBar(this.rf, this.barX, this.barY, 
            this.greenX, this.greenY, 100-width, this.cursorOnBar); 
        this.treelife.setNewMaxBound(this.greenX + 110-width);
        this.lives.newBounds(this.greenX-12, this.greenX + 110-width);
        break;
      case LESS_LIVES:
        // If we roll Less_Lives when we already only have 1 life available, 
        // we want to call the method again to roll again.
        if (this.lives.getMaxLives() == 1)
        {
          levelUp();
        }
        else
        {
          this.lives.setLives(this.lives.getMaxLives()-1);
        }
        break;
      default:
        break;
    }
  }
  
  /**
   * Sets the bear frames.
   * @param bear the bear.
   */
  public void setBear(final DannyBear bear)
  {
    clearFrames();
    this.bearFrames = bear.getFrames();
    reset();
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
    this.remove(bearFrames.get(DannyBear.SWING));
    this.remove(bearFrames.get(DannyBear.MISS));
    this.remove(bearFrames.get(DannyBear.CHOP));
    
  }
  
}
