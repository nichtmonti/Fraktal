package Main;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;

import Util.complex;

public class SimpleDrawing extends JFrame implements ActionListener, Runnable{
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	String msg="hallo";
	int sheight = 720;
	int swidth = (int)(16.0f/9.0f*sheight);
	int scaleFactor=1;
	int scale = scaleFactor*(int)5e+2;
	int iteratio=100;
	double xscroll=-1.402;
	double yscroll=0;
	double max=5;
	TextField xScrollT,yScrollT,scaleT,cJul,tMax;
	Font font = new Font("Arial",Font.BOLD,20);
	double h = (double)sheight/(double)scale/2.0;
	double w = (double)swidth/(double)scale/2.0;
	int h0,w0,s0;
	enum fraktTyp {
		Mandel,Julia
	}
	complex c;
	
	fraktTyp fr = fraktTyp.Mandel;
	Fraktal frakt = new Mandel(iteratio,max,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	BufferedImage img;
	Timer timer;
	

	static SimpleDrawing s;
	
	
	public SimpleDrawing() {	
		
		
		//MENU TEST
		menuBar = new JMenuBar();
	
		
		//Build the first menu.
		menu = new JMenu("A Menu");

		timer = new Timer(1000/60,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("test2");
				repaint();
			}
		});
		
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
		
		Button zoom2 =new Button("++");
		zoom2.setSize(50, 30);
		zoom2.setLocation(0, 35);
		zoom2.addActionListener(this);
		this.add(zoom2);
		
		Button zoomO2 =new Button("--");
		zoomO2.setSize(50, 30);
		zoomO2.setLocation(50, 35);
		zoomO2.addActionListener(this);
		this.add(zoomO2);
		
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
		
		scaleT=new TextField();
		scaleT.setSize(100, 20);
		scaleT.setLocation(360, 5);
		this.add(scaleT);
		
		xScrollT=new TextField();
		xScrollT.setSize(100, 20);
		xScrollT.setLocation(360, 25);
		this.add(xScrollT);
		
		yScrollT=new TextField();
		yScrollT.setSize(100, 20);
		yScrollT.setLocation(360, 45);
		this.add(yScrollT);
		
		cJul=new TextField();
		cJul.setSize(100, 20);
		cJul.setLocation(705, 5);
		this.add(cJul);
		
		tMax=new TextField();
		tMax.setSize(100,20);
		tMax.setLocation(475,5);
		this.add(tMax);
		
		update();
	
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
	if (e.getActionCommand()==null)repaint();
	else if(e.getActionCommand().equals("+")){
		zoomIn(1);
	}
	else if(e.getActionCommand().equals("-")){
		zoomOut(1);
	}	
	else if(e.getActionCommand().equals("++")){
		zoomIn(10);
	}
	else if(e.getActionCommand().equals("--")){
		zoomOut(10);
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
		riseIt();
	}	
	else if(e.getActionCommand().equals("Iteration -")){
		iteratio*=0.9;
		update();
	}
	else if(e.getActionCommand().equals("save")){
		safe();
	}
	else if(e.getActionCommand().equals("Fraktal wechseln")){
      if(fr==fraktTyp.Mandel){
    	  fr=fraktTyp.Julia;
    	  c=new complex(xscroll, yscroll);
    	  xscroll=0;
    	  yscroll=0;
      }
      else {
    	  fr=fraktTyp.Mandel;
    	  xscroll=c.getR();
    	  yscroll=c.getI();
      }
      update();
	}
	
	
}
public void safe(){
	try {
		msg="saving...";
		System.out.println("saving");
	    String filen = "Mandelbrot_"+(float)(int)(xscroll*1000)/1000.0f+"+"+(float)(int)(yscroll*1000)/1000.0f+"i_zoom_"+scale;
	    File outputfile = new File(filen+".png");
	    ImageIO.write(img, "png", outputfile);

	} catch (Exception p) {}
}
public void reset(){
    sheight=h0;
    swidth=w0;
    scale=s0;
    update();
    msg="";
}
public void genSFrakt(){
	h0=sheight;
	w0=swidth;
	s0=scale;
	sheight = 3000;
	swidth = (int)(16.0/9.0*(float)sheight);
	scale*=swidth/w0;
	msg="generating fraktal...";
	System.out.println("generating fraktal");
	update2();
}
public void update(){
	sheight=getHeight();
	swidth=getWidth();
	update2();
}
public void update2(){
	h = (double)sheight/(double)scale/2.0;
	w = (double)swidth/(double)scale/2.0;
	if(fr==fraktTyp.Mandel){
		frakt = new Mandel(iteratio,max,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	}
	else {
		frakt = new Julia(iteratio,max,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale,c);
	}
	frakt.update();
	img = getImageFromArray(frakt.pixels,swidth,sheight);
	if(fr==fraktTyp.Julia)cJul.setVisible(true);
	else cJul.setVisible(false);
	
	if(c!=null)cJul.setText(c.toString());
	scaleT.setText(""+scale);
	xScrollT.setText(""+round(xscroll,3));
	yScrollT.setText(""+round(yscroll,3));
	
	repaint();
}
public static float round(double x, int dig) {
	return (float)(Math.round(x*Math.pow(10,dig))/Math.pow(10, dig));
}
public void riseIt(){
	if(iteratio<(int)(iteratio*1.1))iteratio*=1.1;
	else iteratio++;
	update();
}
public void lowerIt(){
	iteratio*=0.9;
	update();
}
public void paint(Graphics g) {
	timer.stop();
	g.setFont(font);
	g.setColor(Color.WHITE);
	try{g.drawImage(img, 0,0,img.getWidth(), img.getHeight(), null);}catch(java.lang.NullPointerException npe){}
	g.drawLine(swidth/2,0,swidth/2,sheight);
	g.drawLine(0,sheight/2,swidth,sheight/2);
	g.drawString(msg,830,50);
	timer.start();
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
	for(int i=0;i<height;i++){
    	boolean rot=true;
    	int c1=new Color(204,0,0).getRGB();
    	for(int j=0;j<width;j++){
    		if(pixels[j][i]!=c1){
    			rot=false;
    		}
    	}
    	if(rot&&i!=0){
    		for(int j=0;j<width;j++){
    			pixels[j][i]=pixels[j][i-1];
    		}
        }
    }
    for(int i=0;i<width;i++){
    	boolean rot=true;
    	int c1 =new Color(204,0,0).getRGB();
    	for(int j=0;j<height;j++){
    		if(pixels[i][j]!=c1){
    			rot=false;
    		}
    	}
    	if(rot&&i!=0){
    		for(int j=0;j<height;j++){
    			pixels[i][j]=pixels[i-1][j];
    		}
        }
    }
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
public boolean isInt (String s){
	try{int i=Integer.valueOf(s);}catch(NumberFormatException nfe){return false;}
	return true;
}
public boolean isNum (String s){
	try{double i=Double.parseDouble(s);}catch(NumberFormatException nfe){return false;}
	return true;
}
public complex getComp(String s){
	double r,i;
	complex c;
	String[] sp = s.replace("-", " -").replace("+"," ").trim().split(" ");
	r=Double.valueOf(sp[0]);
	i=Double.valueOf(sp[1].replace('i', ' ').trim());
	c=new complex (r,i);
	return c;
}
public boolean isComp (String s){
	try{getComp(s);}catch(java.lang.NumberFormatException e){return false;}
	return true;
}
public void updateByT() {
		
	String s=scaleT.getText().replace(',', '.'),x=xScrollT.getText().replace(',', '.'),y=yScrollT.getText().replace(',', '.'),j=cJul.getText().replace(',', '.'),m=tMax.getText().replace(',', '.');
	if(isInt(s))scale=Integer.valueOf(s);
	if(isNum(x))xscroll=Double.valueOf(x);
	if(isNum(y))yscroll=Double.valueOf(y);
	if(isComp(j))c=getComp(j);
	if(isNum(m))max=Double.valueOf(m);
	
	update();
}

}
