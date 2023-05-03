package lumbearjack;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.JApplication;
import bears.DannyBear;
import gui.AudioSprite;
import gui.HelpFrame;
import io.ResourceFinder;
import resources.Marker;
import screens.*;

/**
 * 
 * 
 * @author joselynetran and panamuhamad
 *
 */
public class LumbearjackGame extends JApplication implements ActionListener, KeyListener
{

  // Button names
  private static final String CHOP = "LET'S CHOP";
  private static final String EXIT = "EXIT";
  private static final String START = "START";
  private static final String HELP = "?";
  private static final String REPLAY = "REPLAY";
  private static final String NEXT = "LEVEL UP!";
  private static final String CUSTOMIZE = "CUSTOMIZE";
  private static final String PREV_CUSTOMIZE = "<";
  private static final String NEXT_CUSTOMIZE = ">";

  // Screens/Frames to go through
  private StartingScreen startScreen;
  private LoreScreen loreScreen;
  private CustomScreen customizeScreen;
  private GameScreen gameScreen;
  private TransitionScreen transitionScreen;
  private JFrame helpPopup;

  // JButttons
  private JButton startButton;
  private JButton exitButton;
  private JButton continueButton;
  private JButton helpButton;
  private JButton replayButton;
  private JButton nextLevelButton;
  private JButton customizeButton;
  private JButton prevCustomizeButton;
  private JButton nextCustomizeButton;

  // Misc setup
  private Font font;
  private ResourceFinder rf;
  private boolean playedBefore;

  /**
   * Main Class constructor.
   * 
   * @param width
   * @param height
   * @throws IOException
   * @throws FontFormatException
   */
  public LumbearjackGame(final int width, final int height) throws FontFormatException, IOException
  {
    super(width, height);

    // resourcefinder for all screens to
    this.rf = ResourceFinder.createInstance(new Marker());

    // import font from resources
    InputStream is = this.getClass().getResourceAsStream("/resources/Rubik-VariableFont_wght.ttf");
    this.font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 19f);

    // setting up all screens
    this.startScreen = new StartingScreen(width, height, this.rf);
    this.transitionScreen = new TransitionScreen(width, height, this.rf, TransitionScreen.LEVEL_UP);
    this.loreScreen = new LoreScreen(width, height, this.rf, this.font);
    this.customizeScreen = new CustomScreen(width, height, this.rf);
    this.gameScreen = new GameScreen(width, height, this.rf);
    this.helpPopup = new HelpFrame(this.rf);
    this.playedBefore = false;
  }

  /**
   * Intializes Lumbearjack Game with start screen.
   */
  public void init()
  {

    // setting up buttons with specific (x, y) location
    int buttonX = this.width / 4 + 70;
    int buttonY = this.height / 2 + 130;
    this.setUpButtons(buttonX, buttonY);

    // setting screen and starting buttons
    JPanel screens = (JPanel) this.getContentPane();
    screens.setLayout(null);
    screens.add(startButton);
    screens.add(exitButton);
    screens.add(this.startScreen.getView());

    backgroundMusic();

  }

  /**
   * Construct and invoke (in the event dispatch thread) an instance of this JApplication.
   * 
   * @param args
   *          The command line arguments
   * @throws IOException
   * @throws FontFormatException
   */
  public static void main(final String[] args) throws FontFormatException, IOException
  {
    JApplication app = new LumbearjackGame(800, 600);
    invokeInEventDispatchThread(app);
  }

  @Override
  public void actionPerformed(final ActionEvent evt)
  {

    String ac = evt.getActionCommand();
    JPanel cp = (JPanel) this.getContentPane();

    // must add buttons on this panels due to how the layering works
    // with the screens views/JSwing elements
    switch (ac)
    {
      // moving into the lore screen
      case (START):
        wipeContentPane(cp);
        cp.add(continueButton);
        cp.add(customizeButton);
        cp.add(this.loreScreen.getView());
        break;

      // moving into customzation
      case (CUSTOMIZE):
        wipeContentPane(cp);
        // continue button moved to the center
        continueButton.setBounds(this.width / 3, this.height - 100, 250, 55);
        cp.add(continueButton);
        cp.add(nextCustomizeButton);
        cp.add(prevCustomizeButton);
        cp.add(this.customizeScreen.getView());
        break;

      // buttons for scrubbing between hats
      case (NEXT_CUSTOMIZE):
        this.customizeScreen.nextDannyIcon();
        this.gameScreen.setBear(this.customizeScreen.getBear());
        break;
      case (PREV_CUSTOMIZE):
        this.customizeScreen.prevDannyIcon();
        this.gameScreen.setBear(this.customizeScreen.getBear());
        break;

      // exit out of everything
      case (EXIT):
        this.windowClosed(null);
        break;

      // chop/next/replay will send user back to GameScreen
      case (CHOP):
      case (NEXT):
      case (REPLAY):
        wipeContentPane(cp);
        this.customizeScreen.getBear().getBearContent(DannyBear.SWING).setLocation(40, height / 9);
        if (!playedBefore)
        {
          this.helpPopup.setVisible(true);
          playedBefore = true;
        }
        cp.add(helpButton);
        cp.add(this.gameScreen.getView());
        this.gameScreen.addKeyListener(this);
        break;

      // reopen help animation
      case (HELP):
        helpPopup.setVisible(true);
        break;

      default:
        wipeContentPane(cp);
        cp.add(this.startScreen.getView());
    }

  }

  /**
   * Helper method to create buttons.
   * 
   * @param buttonX
   * @param buttonY
   */
  private void setUpButtons(final int buttonX, final int buttonY)
  {
    // intializing all JButtons
    this.startButton = new JButton(START);
    this.replayButton = new JButton(REPLAY);
    this.exitButton = new JButton(EXIT);
    this.continueButton = new JButton(CHOP);
    this.helpButton = new JButton(HELP);
    this.nextLevelButton = new JButton(NEXT);
    this.customizeButton = new JButton(CUSTOMIZE);
    this.nextCustomizeButton = new JButton(NEXT_CUSTOMIZE);
    this.prevCustomizeButton = new JButton(PREV_CUSTOMIZE);

    // setting font and actionlisteners for all buttons
    this.buttonSetter(startButton);
    this.buttonSetter(replayButton);
    this.buttonSetter(exitButton);
    this.buttonSetter(continueButton);
    this.buttonSetter(helpButton);
    this.buttonSetter(nextLevelButton);
    this.buttonSetter(customizeButton);
    this.buttonSetter(nextCustomizeButton);
    this.buttonSetter(prevCustomizeButton);

    // setting size and location for all buttons
    startButton.setBounds(buttonX, buttonY, 250, 55);
    replayButton.setBounds(buttonX, buttonY, 250, 55);
    nextLevelButton.setBounds(buttonX, buttonY, 250, 55);
    exitButton.setBounds(buttonX, buttonY + 60, 250, 55);
    continueButton.setBounds(this.width / 2 + 100, this.height - 150, 250, 55);
    customizeButton.setBounds(this.width / 2 + 100, this.height - 90, 250, 55);
    nextCustomizeButton.setBounds(this.width - 65, this.height - 350, 55, 55);
    prevCustomizeButton.setBounds(this.width / 2 - 395, this.height - 350, 55, 55);
    helpButton.setBounds(20, 20, 55, 55);
  }

  /**
   * Helper method to set font and actionlistener for all buttons.
   * 
   * @param button
   */
  private void buttonSetter(final JButton button)
  {
    button.setFont(font);
    button.addActionListener(this);
  }

  /**
   * Helper method to wipescreen for prepping to place next screen.
   * 
   * @param cp
   */
  private void wipeContentPane(final JPanel cp)
  {
    cp.removeAll();
    cp.revalidate();
    cp.repaint();
  }

  /**
   * Will handle when user has chopped tree down and won the game to move onto winner screen.
   */
  @Override
  public void keyPressed(final KeyEvent e)
  {

  }

  @Override
  public void keyTyped(final KeyEvent e)
  {

  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    JPanel screen = (JPanel) this.getContentPane();

    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();
    // When the player presses space, and the game is over.
    if (key == GameScreen.SPACEKEY && this.gameScreen.gameFinished())
    {
      wipeContentPane(screen);
      this.transitionScreen.setTransitionImage(TransitionScreen.WINNER);
      screen.add(exitButton);
      screen.add(this.transitionScreen.getView());
      this.gameScreen.reset();
    }
    // When the player presses space, and the level is complete.
    else if (key == GameScreen.SPACEKEY && this.gameScreen.levelComplete())
    {
      wipeContentPane(screen);
      this.transitionScreen.setTransitionImage(TransitionScreen.LEVEL_UP);
      screen.add(nextLevelButton);
      screen.add(exitButton);
      screen.add(this.transitionScreen.getView());
      this.gameScreen.reset();
    }
    // When the player presses space and has no tries remaining.
    else if (key == GameScreen.SPACEKEY && this.gameScreen.noLives())
    {
      wipeContentPane(screen);
      this.transitionScreen.setTransitionImage(TransitionScreen.GAME_OVER);
      screen.add(replayButton);
      screen.add(exitButton);
      screen.add(this.transitionScreen.getView());
      this.gameScreen.reset();
    }
  }

  /**
   * Creates our background music in our title screen.
   */
  public void backgroundMusic()
  {
    // We need to only use .wav files for audio since it's the only supported.
    InputStream is = this.rf.findInputStream("danny_main_theme.wav");
    BufferedInputStream bf = new BufferedInputStream(is);
    try
    {
      // Creates our audio and starts music right away.
      AudioSprite audio = new AudioSprite(bf, 0);
      audio.handleTick(0);
      audio.loopForever();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (LineUnavailableException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedAudioFileException e)
    {
      e.printStackTrace();
    }
  }

}
