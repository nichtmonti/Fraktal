package Main;

import input.Keyboard;
import input.Mouse;

public class Starter {

	public static void main(String[] args){
		SimpleDrawing s= new SimpleDrawing();
		s.setFocusable(true);
		s.addKeyListener(new Keyboard());
		s.addMouseListener(new Mouse());
		s.addMouseMotionListener(new Mouse());
		s.addMouseWheelListener(new Mouse());
		s.setFocusTraversalKeysEnabled(true);
		s.requestFocus();
		s.run();								//Jonas war hier
	}
}
