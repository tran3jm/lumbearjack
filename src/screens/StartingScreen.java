package screens;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.JApplication;
import io.ResourceFinder;
import resources.Marker;

public class StartingScreen extends JPanel implements ActionListener
{
  
  private static final long serialVersionUID = 1L;
  private static String START = "Start";
  private static String LORE = "Lore";
  private static String EXIT = "Exit";

  /**
   * Attributes:
   *  -start button
   *  -resource finder
   *  -background image
   *  -danny logo
   */
  

  private ResourceFinder rf;
  

  public StartingScreen(int width, int height)
  {
    super();
    this.setSize(600, 800);
    this.rf = ResourceFinder.createInstance(new Marker());
    
  }

  public void init()
  {
    
    /**
     * Steps:
     *  -Set up buttons (Start, lore, exit)
     *  -Get background forest.jpeg (background) and Starting_Screen_Danny.png
     *  -Blur forest.jpeg
     *  -add background and then starting screen
     *  -add buttons
     */
    
   	JButton startButton = new JButton(START);
   	startButton.addActionListener(this);
   	JButton loreButton = new JButton(LORE);
   	loreButton.addActionListener(this);
   	JButton exitButton = new JButton(EXIT);
   	exitButton.addActionListener(this);
   	this.add(startButton);
   	this.add(loreButton);
   	this.add(exitButton);
   	// TODO Auto-generated method stub
    
  }

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    /**
     * Steps:
     * 
     */
    String buttonName = evt.getActionCommand();
    /**
     * Start -> Send signal to LumbearjackGame class to change screens
     * Lore -> Popup Jframe with Lore
     * Exit -> Send signal to LumbearjackGame class to exit
     */
    
    
  }

}
