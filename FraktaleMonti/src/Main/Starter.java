package Main;

import input.Keyboard;
import input.Mouse;

public class Starter {
	static SimpleDrawing s;
	public static void main(String[] args){
		Keyboard key = new Keyboard();
		s= new SimpleDrawing();
		s.setFocusable(true);
		s.addKeyListener(key);
		s.addMouseListener(new Mouse());
		s.addMouseMotionListener(new Mouse());
		s.addMouseWheelListener(new Mouse());
		
		s.xScrollT.addKeyListener(key);
		s.yScrollT.addKeyListener(key);
		s.scaleT.addKeyListener(key);
		
		s.setFocusTraversalKeysEnabled(true);
		s.requestFocus();
		s.run();								//Jonas war hier
		
		//online test1
	}
	public static SimpleDrawing getS(){
		return s;
	}
}
