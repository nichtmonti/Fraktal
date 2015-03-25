package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import Main.SimpleDrawing;

public class inputListener implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {
	
	
	long keyTime = System.currentTimeMillis();
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	long lleft = System.currentTimeMillis();
	long mWheel=System.currentTimeMillis();

	
	
	
	
	

	public void keyPressed(KeyEvent e) {
		
		if(System.currentTimeMillis()-keyTime>100)
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
			
			
			keyTime=System.currentTimeMillis();
		}
		
		
	}


	public void keyReleased(KeyEvent e) {
		
		
	}


	public void keyTyped(KeyEvent e) {
	
		// TODO Auto-generated method stub
		
	}
	
	//MOUSE
	
	
	
	public static int getX(){
		return mouseX;
	}
	public static int getY(){
		return mouseY;
	}
	public static int getB(){

		return mouseB;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
	}
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
		
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouseB=arg0.getButton();
		
		SimpleDrawing d= (SimpleDrawing) arg0.getSource();

		if(System.currentTimeMillis()-lleft<200)d.setScale((int) (d.getScale()*1.1));
		else{
			d=centerOnMouse(d);
			d.update();
		}
		while(Mouse.getB()==-1){}
		lleft=System.currentTimeMillis();
	d=null;
	
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//mouseB=-1;
	}
	
	public SimpleDrawing centerOnMouse(SimpleDrawing sd)
	{
		double x=(double)(mouseX-sd.getSwidth()/2)/sd.getScale()+sd.getXscroll();
		double y=(double)(Mouse.getY()-sd.getSheight()/2)/sd.getScale()+sd.getYscroll();
		sd.setXscroll(x);
		sd.setYscroll(y);
		return sd;
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
		
		SimpleDrawing d= (SimpleDrawing) arg0.getSource();
		d=centerOnMouse(d);
	
		if(System.currentTimeMillis()-mWheel>100)
		{
			if(arg0.getWheelRotation()>0)
			{
				d.zoomOut(1);
			}
			else{d.zoomIn(1);}
			
		}
		
		mWheel= System.currentTimeMillis();
		d=null;
			
	}


}
