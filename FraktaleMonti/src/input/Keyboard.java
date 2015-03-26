package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.SimpleDrawing;
import Main.Starter;

public class Keyboard implements KeyListener{

	long lleft = System.currentTimeMillis();
	private boolean[] keys = new boolean[65535];
	
	
	
	
	

	public void keyPressed(KeyEvent e) {
		
		System.out.println(e.getKeyCode());
		keys[e.getKeyCode()]=true;
		if(System.currentTimeMillis()-lleft>100)
		{
			
			SimpleDrawing d=Starter.getS();
			
			if(keys[17]==true&&keys[83]==true){
				d.genSFrakt();
				d.safe();
				d.reset();
			}
			if(keys[17]&&keys[48]){
				d.center();
			}
			else if(e.getKeyChar()=='w')
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
			else if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				d.updateByT();
			}
			else if(e.getKeyCode()==107||e.getKeyCode()==521)
			{
				d.riseIt();
			}
			else if(e.getKeyCode()==109||e.getKeyCode()==45)
			{
				d.lowerIt();
			}
			else if(e.getKeyCode()==33)
			{
				d.zoomIn(5);
			}
			else if(e.getKeyCode()==34)
			{
				d.zoomOut(5);
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
