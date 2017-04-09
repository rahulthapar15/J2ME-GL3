/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gl3;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class GL3 extends MIDlet
{
  private Display  display;    // The display
  private ArcsCanvas canvas;   // Canvas 
 
  public GL3()
  {
    display = Display.getDisplay(this);
    canvas  = new ArcsCanvas(this);
  }
 
  protected void startApp()
  {
    display.setCurrent( canvas );
  }
 
  protected void pauseApp()
  { }

  protected void destroyApp( boolean unconditional )
  { }
 
  public void exitMIDlet()
  {
    destroyApp(true);
    notifyDestroyed();
  }
}

/*--------------------------------------------------
* Class ArcsCanvas
*
* Draw arcs
*-------------------------------------------------*/
class ArcsCanvas extends Canvas implements CommandListener, Runnable
{
  private Command cmExit;  // Exit midlet
  private GL3 midlet;
  
private int wedgeOne;
private int wedgeTwo;
private int wedgeThree;
private int speed;
private boolean move;
  
  int data[] ={120,120,120};
  int colors[] = {0xFF0000, 0x00FF00,0x0033FF};
 
  public ArcsCanvas(GL3 midlet)
  {
    this.midlet = midlet;
    
    wedgeOne = 0;
    wedgeTwo = 120;
    wedgeThree = 240;
    speed = 0;
    
    // Create exit command & listen for events
    cmExit = new Command("Exit", Command.EXIT, 1);
    addCommand(cmExit);
    setCommandListener(this);
  } 
  
  public void start() {
        move = true;
        Thread t = new Thread(this);
        t.start();
  
  }
  
  public void stop() {
        move = false;
  }
 

  /*--------------------------------------------------
  * Draw an arc 
  *-------------------------------------------------*/
  protected void paint(Graphics g)
  {
int width = this.getWidth();
int height = this.getHeight();
int pos1 = 67;
int pos2 = 105;

    g.setColor(255, 255, 255);
    g.fillRect(0, 0, width, height);
    g.setColor(255,0,0);
    g.fillArc(pos1, pos2, 100, 100, wedgeOne, 120);
    g.setColor(0,255,0);
    g.fillArc(pos1, pos2, 100, 100, wedgeTwo, 120);
    g.setColor(0,0,255);
    g.fillArc(pos1, pos2, 100, 100, wedgeThree, 120);

  }
  
  
  protected void keyPressed(int keyCode){
    switch (keyCode){
        //When the 6 button is pressed, the wheel spins forward 5 degrees.
        case KEY_NUM6:
            wedgeOne += 5; wedgeTwo += 5; wedgeThree += 5;
            repaint();
            break;
        //When the 4 button is pressed, the wheel spins backwards 5 degrees.
        case KEY_NUM4:
            wedgeOne -= 5; wedgeTwo -= 5; wedgeThree -= 5;
            repaint();
    }
  }

  public void commandAction(Command c, Displayable d)
  {
    if (c == cmExit)
      midlet.exitMIDlet();
  }

    public void run() {
    }
}
