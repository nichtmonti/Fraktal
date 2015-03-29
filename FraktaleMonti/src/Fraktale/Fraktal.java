package Fraktale;

import Fraktale.Fraktal.dir;

public class Fraktal implements Runnable {
	
	public int[][] pixels;
	protected double xmin,xmax,ymax,ymin;
	protected long scale;
	
	public double getXmin() {
		return xmin;
	}
	public void setXmin(double xmin) {
		this.xmin = xmin;
	}
	public double getXmax() {
		return xmax;
	}
	public void setXmax(double xmax) {
		this.xmax = xmax;
	}
	public double getYmax() {
		return ymax;
	}
	public void setYmax(double ymax) {
		this.ymax = ymax;
	}
	public double getYmin() {
		return ymin;
	}
	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
	public Fraktal getCopy(){
		return new Fraktal(this);
	}
	public static enum dir{up, down, left, right};
	
	protected Fraktal (double xmin, double xmax, double ymin, double ymax,long scale){
		this.xmin=xmin;
		this.xmax=xmax;
		this.ymin=ymin;
		this.ymax=ymax;
		this.scale=scale;

		this.pixels = new int[(int)((xmax-xmin)*scale)+1][(int)((ymax-ymin)*scale)+1];
	}
	public Fraktal(Fraktal frak) {
		this.xmin=frak.xmin;
		this.xmax=frak.xmax;
		this.ymin=frak.ymin;
		this.ymax=frak.ymax;
		this.scale=frak.scale;

		this.pixels = new int[(int)((xmax-xmin)*scale)+1][(int)((ymax-ymin)*scale)+1];
	}
	public void update(){}
	public void move(dir up, int i) {
		// TODO Auto-generated method stub
		
	}
	public void run() {
		update();
	};
	
}
