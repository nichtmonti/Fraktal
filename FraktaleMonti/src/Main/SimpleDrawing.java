package Main;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;

import Fraktale.Blatt;
import Fraktale.Buddhabrot;
import Fraktale.Farn;
import Fraktale.Fraktal;
import Fraktale.Julia;
import Fraktale.JuliaEXP;
import Fraktale.JuliaFUN;
import Fraktale.Koch;
import Fraktale.MCFrak;
import Fraktale.Mandel;
import Fraktale.Sierpinski;
import Util.Expression;
import Util.FnParse;
import Util.Fun;
import Util.ImageFilter;
import Util.Op;
import Util.ParseState;
import Util.Variable;
import Util.complex;
import Util.vector2d;

public class SimpleDrawing extends JFrame implements ActionListener, Runnable{
	private static final long serialVersionUID = 1L;
	Thread thread1 = new Thread(this);
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	Scrollbar anSp;
	
	int sheight = 720;
	int swidth = (int)(16.0f/9.0f*sheight);
	int scaleFactor=1;
	long scale = scaleFactor*(int)5e+2;
	int iteratio=100;
	complex scroll=new complex(0,0);
	double max=5;
	TextField ScrollT,scaleT,cJul,tMax,funT, buddhaT;
	Font font = new Font("Arial",Font.BOLD,20);
	double h = (double)sheight/(double)scale/2.0;
	double w = (double)swidth/(double)scale/2.0;
	int h0,w0;
	long s0;

	enum fraktTyp {
		Mandel,Julia,JuliaEXP,JuliaFUN, MCFUN,Buddha,Sierpinski,Farn,Koch,Blatt;
	}
	int fraktAk = 0;
	fraktTyp typen[] = {fraktTyp.Mandel,fraktTyp.Julia,fraktTyp.JuliaEXP,fraktTyp.JuliaFUN,fraktTyp.Buddha, fraktTyp.Sierpinski,fraktTyp.Farn,fraktTyp.Koch, fraktTyp.Blatt};
	complex c=new complex(0,0);
	
	fraktTyp fr = fraktTyp.Mandel;
	Fraktal frakt = new Mandel(iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
	BufferedImage img;
	Timer timer;
	
	Expression ex;
	Thread thread2;
	double[][][] matrices;
	vector2d[] vectors;
	vector2d[] transvec;
	int bc=1000000;
	
	public Timer rundgang = new Timer(5000,new ActionListener(){
		double y=-1;
		double x=-1;
		double changex=0.05, changey=0.05;
		public void actionPerformed(ActionEvent arg0) {
			changex=((double)anSp.getValue())/100.0*Math.signum(changex);
			changey=((double)anSp.getValue())/100.0*Math.signum(changey);
			x+=changex;
			if(Math.abs(x)>=(fr==fraktTyp.JuliaEXP?0.25:1)){
				y+=changey;
				changex*=-1;
				if(Math.abs(y)>=(fr==fraktTyp.JuliaEXP?0.25:1))changey*=-1;
			}
			if(fr==fraktTyp.Mandel)moveTo(new complex(x,y));
			else changeJC(x,y); 
		}
	});

	

	public SimpleDrawing() {	
		
		matrices = new double[][][]{{{0,0},{0,0}},{{0.85,0.04},{-0.04,0.85}},{{0.20,-0.26},{0.23,0.22}},{{-0.15,0.28},{0.26,0.24}}};
		transvec = new vector2d[]{new vector2d(0,0),new vector2d(0,1.6),new vector2d(0,1.6), new vector2d(0,0.44)};
		vectors=transvec;
		
		ex = new Op(new Fun("exp",new Fun("sqr",new Variable("z"))),new Variable("c"),"+");
		
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
		
		
		
	 
	
		Button center =new Button("Zentrieren");
		center.setSize(80, 30);
		center.setLocation(900, 5);
		center.addActionListener(this);
		this.add(center);
		

		
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
		
		Button plus_frak =new Button("Fraktal ->");
		plus_frak.setSize(75,30);
		plus_frak.setLocation(600,5);
		plus_frak.addActionListener(this);
		this.add(plus_frak);
		
		Button minus_frak =new Button("<- Fraktal");
		minus_frak.setSize(75,30);
		minus_frak.setLocation(525,5);
		minus_frak.addActionListener(this);
		this.add(minus_frak);
		
		scaleT=new TextField();
		scaleT.setSize(100, 20);
		scaleT.setLocation(360, 5);
		this.add(scaleT);
		
		ScrollT=new TextField();
		ScrollT.setSize(100, 20);
		ScrollT.setLocation(360, 25);
		this.add(ScrollT);
		
		cJul=new TextField();
		cJul.setSize(150, 20);
		cJul.setLocation(705, 5);
		this.add(cJul);

		anSp = new Scrollbar(Scrollbar.HORIZONTAL,5,1,1,100);
		anSp.setSize(150,20);
		anSp.setLocation(705, 25);
		this.add(anSp);
		
		tMax=new TextField();
		tMax.setSize(100,20);
		tMax.setLocation(360,45);
		this.add(tMax);
		
			
		funT=new TextField();
		funT.setSize(150,20);
		funT.setLocation(1000,5);		
		this.add(funT);
		
		buddhaT=new TextField();
		buddhaT.setSize(150,20);
		buddhaT.setLocation(1000,5);		
		this.add(buddhaT);
		
		thread2 = new Thread(frakt);
		thread1.start();
		thread2.start();
		
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
public void center() 
{
	scroll.setI(0);
	scroll.setR(0);
	if(fr == fraktTyp.Buddha)
	{
	scale=150;
	}
	else if (fr == fraktTyp.Julia)
	{
		scale= 250;
	}
	
	else if (fr == fraktTyp.Mandel)
	{
		scale= 300;
	}
	
	else if (fr == fraktTyp.JuliaFUN)
	{
		scale= 75;
	}
	
	else if(fr == fraktTyp.Sierpinski)
	{
		scroll.setI(3.09499);
		scroll.setR(0);
		scale =74;
		
	}
	
	else if (fr == fraktTyp.Farn)
	{
		scale=155;
		scroll.setI(6.51632);
		scroll.setR(0.68536);
		
	}
	
	else if( fr== fraktTyp.Koch)
	{
		scale =450;
		
	}
	
	update();
}

public void changeJC(double x, double y){
	c=new complex(x,y);
	update();
}
public void moveLeft()
{
	/*if(frakt instanceof Mandel){
		frakt.move(Fraktal.dir.left, 20);
		update3();
	}
	else*/{
		scroll.add(new complex(-w*0.1,0));
		update();
	}
}
public void moveTo(complex c){
	scroll=c;
	update();
}
public void moveRight()
{
	/*if(frakt instanceof Mandel){
		frakt.move(Fraktal.dir.right, 20);
		update3();
	}
	else*/{
		scroll.add(new complex(w*0.1,0));
		update();
	}
}

public void moveUp()
{
	/*if(frakt instanceof Mandel){
		frakt.move(Fraktal.dir.up, 20);
		update3();
	}
	else*/{
		scroll.add(new complex(0,-h*0.1));
		update();
	}
}

public void moveDown()
{	
	/*if(frakt instanceof Mandel){
		frakt.move(Fraktal.dir.down, 20);
		update3();
	}
	else*/{
		scroll.add(new complex(0,h*0.1));
		update();
	}
}

public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand()==null)repaint();
	else if(e.getActionCommand().equals("+")){
		zoomIn(1);
	}
	
	else if(e.getActionCommand().equals("Zentrieren")){
		center();
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
		
		save();
		//reset();
	}
	else if(e.getActionCommand().equals("Fraktal ->")){
		fraktAk++;
		if(fraktAk>typen.length)fraktAk-=typen.length;
		fr=typen[fraktAk%typen.length];
		if((fraktAk-1)%typen.length==0){
		  c=scroll;
		  scroll=new complex(0,0);
		}
		else if(fr==fraktTyp.Mandel){
    	  scroll=c;
		}
      this.setFocusTraversalKeysEnabled(true);
      this.requestFocus();
      
      
      update();
	}
	else if(e.getActionCommand().equals("<- Fraktal")){
		fraktAk--;
		if(fraktAk<0)fraktAk+=typen.length;
		fr=typen[fraktAk%typen.length];
		if((fraktAk-1)%typen.length==0){
		  c=scroll;
		  scroll=new complex(0,0);
		}
		else if(fr==fraktTyp.Mandel){
    	  scroll=c;
		}
      this.setFocusTraversalKeysEnabled(true);
      this.requestFocus();
     
      update();
	}
	
}
public void save(){

	final JFileChooser fc = new JFileChooser();
	fc.setFileFilter(new ImageFilter());
	fc.showSaveDialog(this);
	
	try {
		String path=fc.getSelectedFile().getPath();
		String fileName = path.substring(path.lastIndexOf('\\')+1);
		String location = path.substring(0, path.lastIndexOf('\\')+1);
		System.out.println(fileName);
		System.out.println(location);
		if(fileName==null){fileName=fr+"_"+scroll.toString()+"_"+scale;}
		if(!fileName.endsWith(".png"))fileName+=".png";
	    File outputfile = new File(location+fileName);
	    genSFrakt();
	    ImageIO.write(img, "png", outputfile);
	    System.out.println("done saving at " + outputfile.getAbsolutePath() );
	    update();
	 
	} catch (Exception p) {}
}
public void reset(){
    sheight=h0;
    swidth=w0;
    scale=s0;
    update();
  
}
public void genSFrakt(){
	h0=sheight;
	w0=swidth;
	s0=scale;
	sheight = 3000;
	swidth = (int)(16.0/9.0*(float)sheight);
	scale*=swidth/w0;
	
	System.out.println("generating fraktal");
	update2();
}
public void update(){

	
	sheight=getHeight();
	swidth=getWidth();
try{	this.setTitle((fr.name())); } catch(Exception ex){}
	update2();
}
public void update2(){
	h = (double)sheight/(double)scale/2.0;
	w = (double)swidth/(double)scale/2.0;
	if(fr==fraktTyp.Mandel){
		frakt = new Mandel(iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
		frakt=new MCFrak(frakt);
	}
	
	else if(fr==fraktTyp.JuliaEXP){
		frakt = new JuliaEXP(iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale,c);
	}
	else if(fr==fraktTyp.JuliaFUN){
		frakt = new JuliaFUN(ex,iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale,c);
	}
	else if(fr==fraktTyp.Buddha){
		frakt = new MCFrak(new Buddhabrot(bc,iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale));
	}
	else if(fr==fraktTyp.Sierpinski){
		frakt = new Sierpinski(scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
	}
	else if(fr==fraktTyp.Farn){
		frakt = new Farn(scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
	}
	else if(fr==fraktTyp.Koch){
		frakt = new Koch(scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
	}
	else if(fr==fraktTyp.Blatt)
	{frakt= new Blatt(scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale);
	}	
	else {
		frakt = new Julia(iteratio,max,scroll.getR()-w,scroll.getR()+w,scroll.getI()-h,scroll.getI()+h,scale,c);
	}
	
	
	frakt.update();
	update3();
}
public void update3(){
	img = getImageFromArray(frakt.pixels,swidth,sheight);
	if(fr!=fraktTyp.Mandel)cJul.setVisible(true);
	else cJul.setVisible(false);
	if(rundgang.isRunning())anSp.setVisible(true);
	else anSp.setVisible(false);
	if(fr!=fraktTyp.JuliaFUN)funT.setVisible(false);
	else funT.setVisible(true);
	if(fr!=fraktTyp.Buddha)buddhaT.setVisible(false);
	else buddhaT.setVisible(true);
	if(c!=null)cJul.setText(c.toString());
	buddhaT.setText(new Integer(bc).toString());
	if(ex!=null)funT.setText(ex.toString());
	scaleT.setText(""+scale);
	ScrollT.setText(scroll.toString());
	tMax.setText(""+max);
	
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
	g.setFont(font);
	g.setColor(Color.WHITE);
	try{g.drawImage(img, 0,0,img.getWidth(), img.getHeight(), null);}catch(java.lang.NullPointerException npe){}
	g.drawLine(swidth/2,0,swidth/2,sheight);
	g.drawLine(0,sheight/2,swidth,sheight/2);

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
    		//System.out.println(pixels[j][i]);
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
public long getScale() {
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
	return scroll.getR();
}
public void setXscroll(double xscroll) {
	scroll.setR(xscroll);
}
public double getYscroll() {
	return scroll.getI();
}
public void setYscroll(double yscroll) {
	this.scroll.setI(yscroll);
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
		
	String b=buddhaT.getText(),s=scaleT.getText().replace(',', '.'),x=ScrollT.getText().replace(',', '.'),j=cJul.getText().replace(',', '.'),m=tMax.getText().replace(',', '.'),f=funT.getText();
	if(isInt(s))scale=Integer.valueOf(s);
	if(isComp(x))scroll=getComp(x);
	if(isComp(j))c=getComp(j);
	if(isNum(m))max=Double.valueOf(m);
	if(isInt(b))bc=Integer.valueOf(b);
	try{
		ParseState p = FnParse.parseFn(f);
		ex=p.e;
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	
	update();
}

}
