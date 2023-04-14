package screens;

import game_components.BearFrames;
import game_components.TreeFrames;
import game_components.TreeLife;
import io.GameComponentObserver;
import io.ResourceFinder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import visual.statik.sampled.Content;

/**
 * GameScreen that contains the actually game play of LumBearJack.

 * @author joselynetran
 *
 */
public class GameScreen extends MainScreen implements KeyListener 
{
  public static final int SPACEKEY = 32;
  private GameComponentObserver gco;
  private HashMap<String, Content> bearFrames;
  private HashMap<String, Content> treeFrames;
  private TreeLife treelife;
  private boolean winner;

  /**
   * Creates our gameplay screen for users to play.
   * @param width
   * @param height
   * @param rf
   */
  public GameScreen(final int width, final int height, final ResourceFinder rf)
  {
    super(width, height, rf);
    this.winner = false;

    // setting up game components
    this.treelife = new TreeLife(this.rf);
    bearFrames = new BearFrames(this.rf, height).getFrames();
    treeFrames = new TreeFrames(this.rf, height).getFrames();

    // setting up main observer and adding observers
    gco = new GameComponentObserver();
    gco.addObserver(treelife);

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
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();

    if (key == SPACEKEY && !this.treelife.hasWon())
    {
      gco.notifyObservers();
      
      
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
    }
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
    this.winner = false;
    
    this.remove(treeFrames.get(TreeFrames.CUT_TREE));
    this.remove(bearFrames.get(BearFrames.SWING));
    
    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(BearFrames.SWING));
    
  }

  /**
   * Helper method to add all game components to current screen.
   */
  private void addGameComponents()
  {
    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(BearFrames.SWING));
    this.add(this.treelife);
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

}
