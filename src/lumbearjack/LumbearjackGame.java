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

  // Screens/Frames to go through
  private StartingScreen startScreen;
  private LoreScreen loreScreen;
  private GameScreen gameScreen;
  private WinnerScreen winnerScreen;
  private LosingScreen losingScreen;
  private JFrame helpPopup;

  // JButttons
  private JButton startButton;
  private JButton exitButton;
  private JButton continueButton;
  private JButton helpButton;
  private JButton replayButton;
  
  // Misc setup
  private Font font;
  private boolean playedBefore;

  private ResourceFinder rf;

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
    this.rf = ResourceFinder.createInstance(new Marker());

    // import font from resources
    InputStream is = this.getClass().getResourceAsStream("/resources/Rubik-VariableFont_wght.ttf");
    this.font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 19f);

    // setting up all screens
    this.startScreen = new StartingScreen(width, height, this.rf);
    this.winnerScreen = new WinnerScreen(width, height, this.rf);
    this.loreScreen = new LoreScreen(width, height, this.rf, this.font);
    this.gameScreen = new GameScreen(width, height, this.rf);
    this.losingScreen = new LosingScreen(width, height, this.rf);
    this.helpPopup = new HelpFrame(this.rf);
    this.playedBefore = false;
  }

  /**
   * Intializes Lumbearjack Game with start screen.
   */
  public void init()
  {
    int buttonX = this.width / 4 + 70;
    int buttonY = this.height / 2 + 130;
    this.setUpButtons(buttonX, buttonY);

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
      case (START):
        wipeContentPane(cp);
        cp.add(continueButton);
        cp.add(this.loreScreen.getView());
        break;

      case (EXIT):
        this.windowClosed(null);
        break;

      // replay will send user back to GameScreen
      case (CHOP):
      case (REPLAY):
        wipeContentPane(cp);
        if (!playedBefore) 
        {
          this.helpPopup.setVisible(true);
          playedBefore = true;
        }
        cp.add(helpButton);
        cp.add(this.gameScreen.getView());
        this.gameScreen.addKeyListener(this);
        break;

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
    this.startButton = new JButton(START);
    this.replayButton = new JButton(REPLAY);
    this.exitButton = new JButton(EXIT);
    this.continueButton = new JButton(CHOP);
    this.helpButton = new JButton(HELP);

    this.buttonSetter(startButton);
    this.buttonSetter(replayButton);
    this.buttonSetter(exitButton);
    this.buttonSetter(continueButton);
    this.buttonSetter(helpButton);

    startButton.setBounds(buttonX, buttonY, 250, 55);
    replayButton.setBounds(buttonX, buttonY, 250, 55);
    exitButton.setBounds(buttonX, buttonY + 60, 250, 55);

    continueButton.setBounds(this.width / 2 + 100, this.height - 150, 250, 55);
    helpButton.setBounds(20, 20, 55, 55);
  }

  /**
   * 
   * @param button
   */
  private void buttonSetter(final JButton button)
  {
    button.setFont(font);
    button.addActionListener(this);
  }

  /**
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

    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    JPanel screen = (JPanel) this.getContentPane();

    // keycode -> keys mapped to specific numbers
    int key = e.getKeyCode();
    if (key == GameScreen.SPACEKEY && this.gameScreen.gameComplete())
    {
      wipeContentPane(screen);
      screen.add(replayButton);
      screen.add(exitButton);
      screen.add(this.winnerScreen.getView());
      this.gameScreen.reset();
    }
    else if (key == GameScreen.SPACEKEY && this.gameScreen.noLives())
    {
      wipeContentPane(screen);
      screen.add(replayButton);
      screen.add(exitButton);
      screen.add(this.losingScreen.getView());
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
