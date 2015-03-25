package Main;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import Util.complex;
//Hallo monti
public class SimpleDrawing extends JFrame implements ActionListener, Runnable{
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	
	int sheight = 720;
	int swidth = (int)(16.0f/9.0f*sheight);
	int scaleFactor=1;
	int scale = scaleFactor*(int)5e+2;
	int iteratio=100;
	double xscroll=-1.402;
	double yscroll=0;
	
	TextField tx;
	
	double h = (double)sheight/(double)scale/2.0;
	double w = (double)swidth/(double)scale/2.0;
	enum fraktTyp {
		Mandel,Julia
	}
	complex c;
	fraktTyp fr = fraktTyp.Mandel;
	Fraktal frakt = new Mandel(iteratio,5,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	BufferedImage img;
	
	

	static SimpleDrawing s;
	
	
	public SimpleDrawing() {	
		
		
		//MENU TEST
		menuBar = new JMenuBar();
	
		
		//Build the first menu.
		menu = new JMenu("A Menu");

	
		
	this.setLayout(null);
    setSize(new Dimension(swidth, sheight));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
	Button zoom =new Button("+");
	zoom.setSize(50, 30);
	zoom.setLocation(0, 5);
	zoom.addActionListener(this);
	this.add(zoom);
	
	Button zoomO =new Button("-");
	zoomO.setSize(50, 30);
	zoomO.setLocation(50, 5);
	zoomO.addActionListener(this);
	this.add(zoomO);
	
	Button left =new Button("left");
	left.setSize(30, 30);
	left.setLocation(168, 35);
	left.addActionListener(this);
	this.add(left);
	
	Button right =new Button("right");
	right.setSize(30, 30);
	right.setLocation(232, 35);
	right.addActionListener(this);
	this.add(right);
	
	Button up =new Button("up");
	up.setSize(30, 30);
	up.setLocation(200, 4);
	up.addActionListener(this);
	this.add(up);
	
	Button down =new Button("down");
	down.setSize(30, 30);
	down.setLocation(200, 35);
	down.addActionListener(this);
	this.add(down);

	Button save =new Button("save");
	save.setSize(50, 30);
	save.setLocation(100, 5);
	save.addActionListener(this);
	this.add(save);
	
	Button it_up =new Button("Iteration +");
	it_up.setSize(70, 30);
	it_up.setLocation(275, 5);
	it_up.addActionListener(this);
	this.add(it_up);
	
	Button it_down =new Button("Iteration -");
	it_down.setSize(70, 30);
	it_down.setLocation(275, 35);
	it_down.addActionListener(this);
	this.add(it_down);
	
	Button chng_frak =new Button("Fraktal wechseln");
	chng_frak.setSize(100,30);
	chng_frak.setLocation(600,5);
	chng_frak.addActionListener(this);
	this.add(chng_frak);
	
	update();
	c= new complex(-0.8,0.156);
}

public void zoomIn(int x)
{	
	for(int c=0; c<x;c++)
{
	scale*=1.1;
}
	update();
}

public void zoomOut(int x)
{	
	for(int c=0; c<x;c++)
{
	scale*=0.9;
}
	update();
}
	
public void moveLeft()
{
	xscroll-=w*0.1;
	update();
}

public void moveRight()
{
	xscroll+=w*0.1;
	update();
}

public void moveUp()
{
	yscroll-=h*0.1;
	update();
}

public void moveDown()
{
	yscroll+=h*0.1;
	update();
}

public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("+")){
		zoomIn(1);
	}
	else if(e.getActionCommand().equals("-")){
		zoomOut(1);
	}	
	
	else if(e.getActionCommand().equals("left")){
		moveLeft();
	}
	
	else if(e.getActionCommand().equals("right")){
		moveRight();
	}	
	else if(e.getActionCommand().equals("up")){
		moveUp();
	}	
	else if(e.getActionCommand().equals("down")){
		moveDown();
	}
	else if(e.getActionCommand().equals("Iteration +")){
		iteratio*=1.1;
		update();
	}	
	else if(e.getActionCommand().equals("Iteration -")){
		iteratio*=0.9;
		update();
	}
	else if(e.getActionCommand().equals("save")){
		try {
			System.out.println("arraylen alt: "+frakt.pixels.length);
			int h0=sheight+0,w0=swidth+0,s0=scale;
			sheight = 3000;
			swidth = (int)(16.0/9.0*(float)sheight);
			scale*=swidth/w0;
			System.out.println("generating fraktal..");
			update();
			System.out.println("arraylen neu: "+frakt.pixels.length);
			System.out.println("done.");
			
			System.out.println("saving...");
		    String filen = "Mandelbrot_"+(float)(int)(xscroll*1000)/1000.0f+"+"+(float)(int)(yscroll*1000)/1000.0f+"i_zoom_"+scale;
		    File outputfile = new File(filen+".png");
		    ImageIO.write(img, "png", outputfile);
		    System.out.println("done saving "+filen);
		    
		    sheight=h0;
		    swidth=w0;
		    scale=s0;
		    update();
		} catch (Exception p) {}
	}
	else if(e.getActionCommand().equals("Fraktal wechseln")){
      if(fr==fraktTyp.Mandel){
    	  fr=fraktTyp.Julia;
      }
      else {
    	  fr=fraktTyp.Mandel;
      }
      update();
	}
	System.out.println("done button");
	
	this.setFocusTraversalKeysEnabled(true);
	
}
public void update(){
	h = (double)sheight/(double)scale/2.0;
	w = (double)swidth/(double)scale/2.0;
	if(fr==fraktTyp.Mandel){
		frakt = new Mandel(iteratio,2,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	}
	else {
		c=new complex(Double.parseDouble(tx.getText().split(" ")[0] ),Double.parseDouble(tx.getText().split(" ")[1] ));
		frakt = new Julia(iteratio,2,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale,c);
	}
	frakt.update();
	img = getImageFromArray(frakt.pixels,swidth,sheight);
	repaint();
}
public void paint(Graphics g) {
	g.setColor(Color.WHITE);
	g.drawImage(img, 0,0,img.getWidth(), img.getHeight(), null);
	g.drawLine(swidth/2,0,swidth/2,sheight);
	g.drawLine(0,sheight/2,swidth,sheight/2);
	g.drawString(xscroll+"+"+yscroll+"i",swidth/2+3,sheight/2-3);
}

/*public void loop (){
	System.out.println("loop started");
	while(true){
		long lleft = System.currentTimeMillis();
		
		if(Mouse.getB()==1){
			System.out.println("mouse");
			if(System.currentTimeMillis()-lleft<200)scale*=1.1;
			else{
				double x=(double)(Mouse.getX()-swidth/2)/scale+xscroll;
				double y=(double)(Mouse.getY()-sheight/2)/scale+yscroll;
				xscroll=x;
				yscroll=y;
				update();
			}
			while(Mouse.getB()==-1){}
			lleft=System.currentTimeMillis();
		}
		else if(Mouse.getB()!=-1)System.out.println(Mouse.getB());
	}
}*/

public static BufferedImage getImageFromArray(int[][] pixels, int width, int height) {
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    for(int i=0;i<height;i++){
    	for(int j=0;j<width;j++){
    		image.setRGB(j,i,pixels[j][i]);
    	}
    }
    return image;
}
public void run() {
	long lastup=System.currentTimeMillis();
	while(true){
		if(System.currentTimeMillis()-lastup>1000/60){
		
			lastup=System.currentTimeMillis();
		}
	}
}
public int getSheight() {
	return sheight;
}
public void setSheight(int sheight) {
	this.sheight = sheight;
}
public int getSwidth() {
	return swidth;
}
public void setSwidth(int swidth) {
	this.swidth = swidth;
}
public int getScaleFactor() {
	return scaleFactor;
}
public void setScaleFactor(int scaleFactor) {
	this.scaleFactor = scaleFactor;
}
public int getScale() {
	return scale;
}
public void setScale(int scale) {
	this.scale = scale;
}
public int getIteratio() {
	return iteratio;
}
public void setIteratio(int iteratio) {
	this.iteratio = iteratio;
}
public double getXscroll() {
	return xscroll;
}
public void setXscroll(double xscroll) {
	this.xscroll = xscroll;
}
public double getYscroll() {
	return yscroll;
}
public void setYscroll(double yscroll) {
	this.yscroll = yscroll;
}
public double getH() {
	return h;
}
public void setH(double h) {
	this.h = h;
}
public double getW() {
	return w;
}
public void setW(double w) {
	this.w = w;
}
public Fraktal getFrakt() {
	return frakt;
}
public void setFrakt(Fraktal frakt) {
	this.frakt = frakt;
}
public BufferedImage getImg() {
	return img;
}
public void setImg(BufferedImage img) {
	this.img = img;
}

}