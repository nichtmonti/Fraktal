package Main;

import java.awt.Button;
import java.awt.TextField;

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
		for(int n=0;n<s.getContentPane().getComponentCount();n++){
			if(s.getContentPane().getComponent(n) instanceof Button||s.getContentPane().getComponent(n) instanceof TextField){
				s.getContentPane().getComponent(n).addKeyListener(key);
			}
		}
		s.setFocusTraversalKeysEnabled(true);
		s.requestFocus();
		s.run();								//Andre war hier!!!
		
		//online test1
	}
	public static SimpleDrawing getS(){
		return s;
	}
}
