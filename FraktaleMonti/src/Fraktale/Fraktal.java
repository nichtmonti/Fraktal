package Fraktale;

public abstract class Fraktal implements Runnable{

	public int[][] pixels;
	protected double xmin,xmax,ymax,ymin,scale;
	
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
	public abstract void update();
	public  void run()
	{
		
	}
	
}
