package Main;

import java.awt.Button;
import java.awt.TextField;

import input.*;

public class Starter {
	static SimpleDrawing s;
	public static void main(String[] args){
		Keyboard key = new Keyboard();
		Keyboard2 key2 = new Keyboard2();
		s= new SimpleDrawing();
		s.setFocusable(true);
		s.addKeyListener(key);
		s.addMouseListener(new Mouse());
		s.addMouseMotionListener(new Mouse());
		s.addMouseWheelListener(new Mouse());
		for(int n=0;n<s.getContentPane().getComponentCount();n++){
			if(s.getContentPane().getComponent(n) instanceof Button){
				s.getContentPane().getComponent(n).addKeyListener(key);
			}
			else if(s.getContentPane().getComponent(n) instanceof TextField){
				s.getContentPane().getComponent(n).addKeyListener(key2);
			}
		}
		s.setFocusTraversalKeysEnabled(true);
		s.requestFocus();
		s.run();								
		
		//online test1
	}
	public static SimpleDrawing getS(){
		return s;
	}
}
