package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.SimpleDrawing;
import Main.Starter;

public class Keyboard2 implements KeyListener{

	long lleft = System.currentTimeMillis();
	private boolean[] keys = new boolean[65535];
	
	
	
	
	

	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()]=true;
		if(System.currentTimeMillis()-lleft>100)
		{
			
			SimpleDrawing d=Starter.getS();
			
			if(keys[17]==true&&keys[83]==true){
				d.genSFrakt();
				d.safe();
				d.reset();
			}
			else if(keys[17]&&keys[48]){
				d.center();
			}
			else if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				d.updateByT();
			}
			else if(e.getKeyCode()==33)
			{
				d.zoomIn(5);
			}
			else if(e.getKeyCode()==34)
			{
				d.zoomOut(5);
			}
			else if(e.getKeyCode()==122){
				if(d.rundgang.isRunning()==false)d.rundgang.start();
				else{
					d.rundgang.stop();
				}
			}
			else if(e.getKeyCode()==36)
			{
				d.riseIt();
			}
			else if(e.getKeyCode()==35)
			{
				d.lowerIt();
			}
			else System.out.println(e.getKeyCode());
			
			lleft=System.currentTimeMillis();
			d=null;
		}
		
		
	}


	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()]=false;
		
	}


	public void keyTyped(KeyEvent e) {
	
		// TODO Auto-generated method stub
		
	}
	
}
