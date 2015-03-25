package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.SimpleDrawing;

public class Keyboard implements KeyListener{

	long lleft = System.currentTimeMillis();
	private boolean[] keys = new boolean[65535];
	
	
	
	
	

	public void keyPressed(KeyEvent e) {
		
		System.out.println(e.getKeyChar());
		if(System.currentTimeMillis()-lleft>100)
		{
			
			SimpleDrawing d=(SimpleDrawing) e.getSource();
			if(e.getKeyChar()=='w')
			{
				d.moveUp();
			}
			
			else if(e.getKeyChar()=='s')
			{
				d.moveDown();
			}
			
			else if(e.getKeyChar()=='a')
			{
				d.moveLeft();
			}
			
			else if(e.getKeyChar()=='d')
			{
				d.moveRight();
			}
			
			
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
